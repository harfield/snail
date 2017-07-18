package com.harfield.snail.tool;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by harfield on 2016/10/14.
 */
public class Merge {
    public static void main(String[] args) throws IOException {

        BufferedReader bfr1 = new BufferedReader(new FileReader(new File("C:\\Users\\harfield\\Desktop\\letv.csv")));
        BufferedReader bfr2 = new BufferedReader(new FileReader(new File("C:\\Users\\harfield\\Desktop\\broadcast.csv")));
        String line=null;
        Map<String,String> bid =new TreeMap<String, String>();
        while((line=bfr1.readLine())!=null){
            String[] split = line.split(",", -1);
            String key = "";
            for(int i=0;i<5;i++){
                key+=","+split[i];
            }
            key=key.substring(1);
            String value= split[6]+","+split[7]+",0";
            bid.put(key,value);
        }

        while((line=bfr2.readLine())!=null){
            String[] split = line.split(",", -1);
            String key = "";
            for(int i=0;i<5;i++){
                key+=","+split[i];
            }
            key=key.substring(1);
            String value= split[5];
            if(bid.get(key)!=null){
                String value1 = bid.get(key);
                bid.put(key, value1.substring(0,value1.lastIndexOf(","))+","+value);
            }else{
                bid.put(key,"0,0,"+value);
            }
        }
       BufferedWriter bfw= new BufferedWriter(new FileWriter(new File("C:\\Users\\harfield\\Desktop\\merged.csv")));
        for(String s:bid.keySet()){
            bfw.write(s+","+bid.get(s)+"\n");
        }
        bfw.close();

    }
}
