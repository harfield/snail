package com.harfield.snail.tool;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by harfield on 2016/10/24.
 */
public class StringsTest {
    public static void main(String[] args) throws MalformedURLException {
        String[] split = "a-b-c".split("-");

        System.out.println(Arrays.asList(split));

        URL url = new URL("http://v.youku.com/abc/XzQpc2T");
        System.out.println(url.getHost());

        System.out.println(getHost("//v.youku.com/v.youku.com"));

//        System.out.println("a".split("b",-1));
        System.out.println( "a".split("b", -1)[0].split("c", -1)[0]);
    }


    public static String getHost(String url){
        if(url==null||url.trim().equals("")){
            return "";
        }
        String host = "";
        Pattern p =  Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
        Matcher matcher = p.matcher(url);
        if(matcher.find()){
            host = matcher.group();
        }
        return host;
    }
}
