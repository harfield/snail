package com.harfield.snail.samples;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Created by harfield on 2017/8/22.
 */
public class JMX {


    public interface HelloMBean {
        public String getName();

        public void setName(String name);

        public void printHello();

        public void printHello(String whoName);
    }

    public static class Hello implements HelloMBean {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void printHello() {
            System.out.println("Hello World, " + name);
        }

        public void printHello(String whoName) {
            System.out.println("Hello , " + whoName);
        }


        public static void main(String[] args) throws Exception {
//      MBeanServer server = MBeanServerFactory.createMBeanServer();
                MBeanServer server = ManagementFactory.getPlatformMBeanServer();
                ObjectName helloName = new ObjectName("chengang:name=HelloWorld");
                server.registerMBean(new Hello(), helloName);
                ObjectName adapterName = new ObjectName(
                        "HelloAgent:name=htmladapter,port=8082");
//                HtmlAdaptorServer adapter = new HtmlAdaptorServer();
//                server.registerMBean(adapter, adapterName);
//                adapter.start();
//                System.out.println("start.....");
            }

    }

}