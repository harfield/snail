package com.harfield.snail.tool.kafka;


import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.io.*;
import java.util.*;


/**
 * Created by harfield on 2017/3/30.
 */
public class MyConsumer {
    public static void main(String[] args) throws IOException {
        String zkConnect = "10.215.28.17:2181,10.215.28.18:2181,10.215.28.19:2181";
        String groupId = "test_snappy__";
        String topic = "fancy_req_log_new";
        Properties props = new Properties();
        props.put("auto.offset.reset", "smallest");
//        props.put("zookeeper.connect", zkConnect);
        props.put("bootstrap.servers","10.215.28.17:9092");
        props.put("group.id", groupId);
        props.put("auto.commit.interval.ms", "5000");
        ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(new ConsumerConfig(props));
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, 1);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> kafkaStreams = consumerMap.get(topic);

        FileOutputStream fo=new FileOutputStream(new File("C:\\Users\\harfield\\Desktop\\binary.txt"));
        DataOutputStream dos=new DataOutputStream(fo);
        for(KafkaStream<byte[],byte[]> stream : kafkaStreams){
            ConsumerIterator<byte[], byte[]> it = stream.iterator();
            int i=0;
            while(it.hasNext()){
                byte[] message = it.next().message();
                dos.writeInt(message.length);
                dos.write(message);
                i++;
                if(i==20000){
                    break;
                }
//                System.out.println("item : "+i++);
//                System.out.println(new java.lang.String(message));
            }
        }
        consumerConnector.shutdown();
        fo.close();



    }
}
