package com.harfield.snail.tool.kafka;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
public class MyProducer {

        private static final String TOPIC = "test"; //kafka创建的topic
        private static long cnt = 0;
        private static final String CONTENT = "This is a single message " + (cnt ++) ; //要发送的内容
        private static final String BROKER_LIST = "10.215.28.17:9092,10.215.28.18:9092,10.215.28.19:9092,10.215.28.41:9092"; //broker的地址和端口
        private static final String SERIALIZER_CLASS = "kafka.serializer.DefaultEncoder"; // 序列化类

        public static void main(String[] args) throws IOException {
            Properties props = new Properties();
            props.put("serializer.class", SERIALIZER_CLASS);
            props.put("metadata.broker.list", BROKER_LIST);
//            props.put("compression.codec", "GZIP");
//            props.put("compression.topics",TOPIC );
            props.put("batch.size",20*1024*1024);
            ProducerConfig config = new ProducerConfig(props);
            Producer<String, byte[]> producer = new Producer<String, byte[]>(config);

            //Send one message.
//            KeyedMessage<String,  Byte[]> message =
//                    new KeyedMessage<String,  Byte[]>(TOPIC, CONTENT);
//            producer.send(message);

            //Send multiple messages.
            List<KeyedMessage<String,byte[]>> messages =
                    new ArrayList<KeyedMessage<String,byte[]>>();
            FileInputStream fi=new FileInputStream(new File("C:\\Users\\harfield\\Desktop\\binary.txt"));
            DataInputStream dis = new DataInputStream(fi);

            long total=0;
            long cnt=0;
            int rows=0;
            int bufSize = dis.readInt();
            byte[] buf=new byte[bufSize];
            while ((cnt=dis.read(buf))!=-1){
                total += cnt;
                messages.add(new KeyedMessage<String,byte[]>
                        (TOPIC,buf));
                if(rows%1000==0){
                producer.send(messages);
                    messages =  new ArrayList<KeyedMessage<String,byte[]>>();
                }
//                producer.send(new KeyedMessage<String,byte[]>
//                        (TOPIC,buf));
                try{
                bufSize = dis.readInt();
                buf=new byte[bufSize];
                }catch (Exception e){
                    break;
                }
            }
            producer.send(messages);
            producer.close();
            dis.close();
            System.out.println("total bytes : " + total);

    }
}
