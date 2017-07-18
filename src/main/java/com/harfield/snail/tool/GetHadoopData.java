package com.harfield.snail.tool;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * Created by harfield on 2016/12/13.
 */
public class GetHadoopData extends Configured implements Tool {
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new Configuration(), new GetHadoopData(), args);
    }

    @Override
    public int run(String[] args) throws Exception {
        try {
            Path path = new Path("/warehouse/dwd.db/d_ad_rtb_all_data/thisdate="+args[0]);
            Configuration conf = getConf();

            FileSystem fs = FileSystem.get(path.toUri(), conf);
            Set<FileStatus> dirSet = new HashSet<FileStatus>();
            Set<Path> fileSet=new HashSet<Path>();
            if (fs.isDirectory(path)) {
                FileStatus[] fileStatuses = fs.listStatus(path);
                for(FileStatus st:fileStatuses){
                    if(st.isDirectory()){
                        dirSet.add(st);
                    }else {
                        fileSet.add(st.getPath());
                    }
                }
            }
            while(dirSet.size() > 0){
                Set<FileStatus> innerSet = new HashSet<FileStatus>();
                Iterator<FileStatus> iterator = dirSet.iterator();
                while (iterator.hasNext()){
                    FileStatus next = iterator.next();
                    if(next.isDirectory()){
                        for(FileStatus newFs : fs.listStatus(next.getPath())){
                           innerSet.add(newFs);
                        }
                    }else{
                        fileSet.add(next.getPath());
                    }
                }
                dirSet = innerSet;
            }
            for(Path lfs: fileSet ) {
                if(fs.isDirectory(lfs)){
                    continue;
                }
                FSDataInputStream hdfsInStream = fs.open(lfs);

                byte[] ioBuffer = new byte[1024*4];
                int readLen = hdfsInStream.read(ioBuffer);
                while (readLen != -1) {
                    String s = new String(ioBuffer, 0, readLen);
                    String[] split = s.split("\001", -1);
                    try {
                    if("0".equals(split[3])){
                           sb.setLength(0);
                           String dotIp = divedByDot(Long.parseLong(split[19]));
                           sb.append(dotIp).append(",");
                           String cookieId = split[0].replace("\n","").replace("\r","");
                           sb.append(cookieId).append(",");
                           String url = split[13].replace("\n","").replace("\r","");
                           sb.append(url).append(",");
                           String refer = split[20].replace("\n","").replace("\r","");
                           sb.append(refer).append(",");
                           long time = df.parse(split[17]).getTime() / 1000L;
                           sb.append(time).append(",");
                           String ua = split[21].replace("\n","").replace("\r","");
                           sb.append(ua).append(",");
                           String screenSize = "";
                           sb.append(screenSize).append(",");
                           String dpi = "";
                           sb.append(dpi).append(",");
                           String screenDensity = "";
                           sb.append(screenDensity).append(",");
                           String lang = "";
                           sb.append(lang);
                           System.out.println(sb.toString());
                       }
                    }catch (Exception e){

                    }
                    readLen = hdfsInStream.read(ioBuffer);
                }
                hdfsInStream.close();
            }
            fs.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
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
