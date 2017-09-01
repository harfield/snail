package com.harfield.snail.tool;


import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.net.InetAddresses;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.spanOrQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * Created by harfield on 2017/7/27.
 */
public class EsClient {
    public static void main(String[] args) throws UnknownHostException {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "es-data").build();
        TransportClient client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddresses.forString("192.168.151.15"), 9300));
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        Map<String, Object> user = new HashMap<String, Object>();
        user.put("user", "kimchy");
        user.put("postDate", new Date());
        user.put("message", "post1");
// either use client#prepare, or use Requests# to directly build index/delete requests
        bulkRequest.add(client.prepareIndex("twitter", "tweet", "1")
                .setSource(user
                )
        );
        user.put("message", "post2");
        bulkRequest.add(client.prepareIndex("twitter", "tweet", "2")
                .setSource(
                        user
                )
        );

        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        Iterator<BulkItemResponse> iterator = bulkResponse.iterator();
        System.out.println("after request : ++++++++ ");
        while (iterator.hasNext()) {
            System.out.println("-----item-------");
            BulkItemResponse next = iterator.next();
            System.out.println("itemId :" + next.getItemId());
            System.out.println("index :" + next.getIndex());
            System.out.println("-------end----------");
        }
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
        }


        client.close();
    }


}
