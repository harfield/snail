package com.harfield.snail.tool;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Random;

public class MrTempl extends Configured implements Tool {

    public static void main(String[] args) throws Exception {
        int run = ToolRunner.run(new Configuration(), new MrTempl(), args);
        System.exit(run);
    }
    @Override
    public int run(String[] args) throws Exception {
        if(args.length < 4) {
            System.out.println("Usage:<reduceNum> <inputs> <date> <h>..");
            return 1;
        }
        int reduceNum = Integer.parseInt(args[0]);

        Configuration conf=getConf();

        conf.setInt(MRJobConfig.JVM_NUMTASKS_TORUN,10);
        conf.setFloat(MRJobConfig.COMPLETED_MAPS_FOR_REDUCE_SLOWSTART, 0.9f);

        Job job = Job.getInstance(conf, this.getClass().getName()+ args[2] + args[3]);
        job.setJarByClass(MrTempl.class);

        job.setMapSpeculativeExecution(true);
//        job.setReduceSpeculativeExecution(false);
//        job.setMaxReduceAttempts(1);

        job.setInputFormatClass(TextInputFormat.class);
        FileInputFormat.setInputDirRecursive(job,true);

//        for(int i=1;i < args.length;i++){
            FileInputFormat.addInputPath(job, new Path(args[1]));
//        }

        job.setOutputFormatClass(TextOutputFormat.class);

        FileOutputFormat.setOutputPath(job,new Path("/tmp/rtb_data/" + args[2] + "/" + "/"+ args[3]));

        job.setMapperClass(RtbMap.class);
        job.setReducerClass(RtbReducer.class);
        job.setNumReduceTasks(reduceNum);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);


//        job.setPartitionerClass(SecondaryPartitioner.class);
//        job.setGroupingComparatorClass(SecondaryGroupingComparator.class);

        return  job.waitForCompletion(true) ? 0:1;
    }


    public static class RtbMap extends Mapper<Writable, Text, Text, Text> {
        Text outputKey ;
        Text outValue;
        StringBuilder sb;
        Random random = new Random(100);
        static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            super.setup(context);
            outputKey = new Text();
            outValue = new Text();
            sb = new StringBuilder();
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            super.cleanup(context);
        }

        @Override
        protected void map(Writable key, Text value, Context context) throws IOException, InterruptedException {
            String[] split = value.toString().split("\001",-1);
            try {
                if("0".equals(split[3])){
                   if( random.nextInt(50) != 1)return;
                    sb.setLength(0);
                    String dotIp = divedByDot(Long.parseLong(split[19]));
                    sb.append(dotIp).append(",");
                    String cookieId = split[0].replace("\n"," ").replace("\r"," ");
                    sb.append(cookieId).append(",");
                    String url = split[13].replace("\n"," ").replace("\r"," ");
                    sb.append(url);
//                    sb.append(url).append(",");
//                    String refer = split[20].replace("\n","").replace("\r","");
//                    sb.append(refer).append(",");
//                    long time = dateFormat.parse(split[17]).getTime() / 1000L;
//                    sb.append(time).append(",");
//                    String ua = split[21].replace("\n","").replace("\r","");
//                    sb.append(ua).append(",");
//                    String screenSize = "";
//                    sb.append(screenSize).append(",");
//                    String dpi = "";
//                    sb.append(dpi).append(",");
//                    String screenDensity = "";
//                    sb.append(screenDensity).append(",");
//                    String lang = "";
//                    sb.append(lang);
                    outValue.set(sb.toString());
                    outputKey.set(sb.toString().hashCode()+"");
                    context.write(outputKey,outValue);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
        public static String divedByDot(long ip) {
            long positiveLong = ip & 0x00000000FFFFFFFFL;
            if (positiveLong == 0) return "0.0.0.0";

            //return String.format("%l.%l.%l.%l",positiveLong>>24,positiveLong>>16,positiveLong>>8 );
            String s = Long.toHexString(positiveLong);
            return String.format("%d.%d.%d.%d",(positiveLong&0x00000000ff000000L)>>24,
                    (positiveLong&0x0000000000ff0000L)>>16,
                    (positiveLong&0x000000000000ff00L)>>8,
                    (positiveLong&0x00000000000000ffL)
            );
        }

    }
    public static class RtbReducer extends Reducer<Text, Text, Text, NullWritable> {

        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            Iterator<Text> iterator = values.iterator();
            while (iterator.hasNext()){
                Text val = iterator.next();
                context.write(val,NullWritable.get());
            }
        }
    }


}
