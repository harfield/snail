import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapreduce.MRJobConfig;
import org.apache.hadoop.mapreduce.security.TokenCache;
import org.apache.hadoop.util.Progressable;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by harfield on 2017/7/17.
 */
public class MultipleTextOutputFormat<K, V> extends FileOutputFormat<K, V> {

    public RecordWriter<K, V> getRecordWriter(FileSystem ignored, JobConf job, String name, Progressable progress) throws IOException {
        return new LazyRecordWriter<K, V>(name, job, progress);

    }

    @Override
    public void checkOutputSpecs(FileSystem ignored, JobConf job)
            throws FileAlreadyExistsException,
            InvalidJobConfException, IOException {
        // Ensure that the output directory is set and not already there
        Path outDir = getOutputPath(job);
        if (outDir == null && job.getNumReduceTasks() != 0) {
            throw new InvalidJobConfException("Output directory not set in JobConf.");
        }
        if (outDir != null) {
            FileSystem fs = outDir.getFileSystem(job);
            // normalize the output directory
            outDir = fs.makeQualified(outDir);
            setOutputPath(job, outDir);

            // get delegation token for the outDir's file system
            TokenCache.obtainTokensForNamenodes(job.getCredentials(),
                    new Path[]{outDir}, job);

            // check its existence
//            if (fs.exists(outDir)) {
//                throw new FileAlreadyExistsException("Output directory " + outDir +
//                        " already exists");
//            }
        }
    }

    private static class LazyRecordWriter<K, V> implements RecordWriter<K, V> {

        protected String name;
        protected JobConf job;
        protected Progressable progress;
        protected Map<String, LineRecordWriter> writers = new HashMap<String, LineRecordWriter>();

        public LazyRecordWriter(String name, JobConf job, Progressable progress) {
            this.name = job.get(MRJobConfig.ID) + "-" + name;
            this.job = job;
            this.progress = progress;
        }

        public void write(K key, V value) throws IOException {
            boolean nullKey = key == null || key instanceof NullWritable;
            boolean nullValue = value == null || value instanceof NullWritable;
            if (nullKey) {
                return;
            }
            int pos = key.toString().indexOf("|");
            String valueKey = key.toString();
            String pathKey = null;
            if (pos > 0) {
                pathKey = key.toString().substring(0, pos);
                pathKey = pathKey.replaceAll("#", "/") + "/" + name;
                valueKey = valueKey.substring(pos + 1);
            } else {
                pathKey = name;
            }
            if (!writers.containsKey(pathKey)) {
                boolean isCompressed = getCompressOutput(job);
                String keyValueSeparator = job.get("mapreduce.output.textoutputformat.separator",
                        "\t");
                Class<? extends CompressionCodec> codecClass = null;
                Path file = null;
//                FileOutputCommitter committer = (FileOutputCommitter) job.getOutputCommitter();
                if (!isCompressed) {
                    file = FileOutputFormat.getTaskOutputPath(job, pathKey);
                    FileSystem fs = file.getFileSystem(job);
                    FSDataOutputStream fileOut = fs.create(file, progress);
                    writers.put(pathKey, new LineRecordWriter<K, V>(fileOut, keyValueSeparator));
                } else {
                    codecClass = getOutputCompressorClass(job, GzipCodec.class);
                    // create the named codec
                    CompressionCodec codec = ReflectionUtils.newInstance(codecClass, job);
                    // build the filename including the extension

                    file = FileOutputFormat.getTaskOutputPath(job,
                            pathKey + codec.getDefaultExtension());
                    FileSystem fs = file.getFileSystem(job);
                    FSDataOutputStream fileOut = fs.create(file, progress);
                    writers.put(pathKey, new LineRecordWriter<K, V>(new DataOutputStream
                            (codec.createOutputStream(fileOut)),
                            keyValueSeparator));
                }
            }
            writers.get(pathKey).write(valueKey, value);
        }

        public void close(Reporter reporter) throws IOException {
            for (RecordWriter w : writers.values()) {
                w.close(reporter);
            }
        }


    }

    @InterfaceAudience.Public
    @InterfaceStability.Stable
    protected static class LineRecordWriter<K, V> implements RecordWriter<K, V> {
        private static final String utf8 = "UTF-8";
        private static final byte[] newline;

        static {
            try {
                newline = "\n".getBytes(utf8);
            } catch (UnsupportedEncodingException uee) {
                throw new IllegalArgumentException("can't find " + utf8 + " encoding");
            }
        }

        protected DataOutputStream out;
        private final byte[] keyValueSeparator;

        public LineRecordWriter(DataOutputStream out, String keyValueSeparator) {
            this.out = out;
            try {
                this.keyValueSeparator = keyValueSeparator.getBytes(utf8);
            } catch (UnsupportedEncodingException uee) {
                throw new IllegalArgumentException("can't find " + utf8 + " encoding");
            }
        }

        public LineRecordWriter(DataOutputStream out) {
            this(out, "\t");
        }

        /**
         * Write the object to the byte stream, handling Text as a special
         * case.
         *
         * @param o the object to print
         * @throws IOException if the write throws, we pass it on
         */
        private void writeObject(Object o) throws IOException {
            if (o instanceof Text) {
                Text to = (Text) o;
                out.write(to.getBytes(), 0, to.getLength());
            } else {
                out.write(o.toString().getBytes(utf8));
            }
        }

        public synchronized void write(K key, V value)
                throws IOException {

            boolean nullKey = key == null || key instanceof NullWritable;
            boolean nullValue = value == null || value instanceof NullWritable;
            if (nullKey && nullValue) {
                return;
            }
            if (!nullKey) {
                writeObject(key);
            }
            if (!(nullKey || nullValue)) {
                out.write(keyValueSeparator);
            }
            if (!nullValue) {
                writeObject(value);
            }
            out.write(newline);
        }

        public synchronized void close(Reporter reporter) throws IOException {
            out.close();
        }
    }
}
