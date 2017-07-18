package com.harfield.snail.tool;

import org.apache.hadoop.io.Text;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.security.MessageDigest;

import static org.apache.hadoop.io.Text.encode;

/**
 * Created by harfield on 2016/10/21.
 */
public class TestMd5 {
    public static void main(String[] args) {
        System.out.println( 1+'\001'+2);
        String s = 1+"\001"+48;

//        try {
//            ByteBuffer e = encode(s, true);
//            byte[] array = e.array();
//            int limit = e.limit();
//        } catch (CharacterCodingException var3) {
//            throw new RuntimeException("Should not have happened ", var3);
//        }

        System.out.println(MD5("ext@vaneevanee_dmp"));
        System.out.println(String.format("%.6f",3e-5));


    }


    public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
