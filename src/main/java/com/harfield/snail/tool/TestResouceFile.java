package com.harfield.snail.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by harfield on 2017/7/26.
 */
public class TestResouceFile {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = new TestResouceFile().getClass().getResourceAsStream("/data/domain.map");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
        String line = null;
        while(null !=(line = bufferedReader.readLine())){
            System.out.println(line);
        }
        bufferedReader.close();

        System.out.println(Arrays.asList("abc c".split("\\s")));
    }
}
