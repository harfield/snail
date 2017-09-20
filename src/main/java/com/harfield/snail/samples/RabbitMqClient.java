package com.harfield.snail.samples;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by harfield on 2017/9/12.
 */
public class RabbitMqClient {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("dw");
        factory.setPassword("dw123456");
//        factory.setVirtualHost("10.215.28.69");
        factory.setHost("10.215.28.39");
        factory.setPort(5672);
        Connection conn = factory.newConnection();
    }
}
