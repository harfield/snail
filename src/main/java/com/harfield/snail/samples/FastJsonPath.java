package com.harfield.snail.samples;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://github.com/alibaba/fastjson/wiki/JSONPath
 */
public class FastJsonPath {
    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        object.put("ac","b");
        object.put("c",new HashMap<String,String>());
        System.out.println(JSONPath.eval(object,"$.*"));
        System.out.println(JSONObject.toJSONString(new JavaBean()));
        System.out.println(JSONObject.toJSONString("abc"));
        List<String> list = new ArrayList<String>();
        list.add("a");
        System.out.println(list.toString());

    }

    public static class JavaBean {
        private int id = 100;
        private String name = "abc";
        private Map content = new HashMap<String,Object>();

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map getContent() {
            return content;
        }

        public void setContent(Map content) {
            this.content = content;
        }
    }
}
