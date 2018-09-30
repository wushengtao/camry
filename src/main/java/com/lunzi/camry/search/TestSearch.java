package com.lunzi.camry.search;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Collections;

/**
 * Created by lunzi on 2018/9/20 下午3:18
 */
@Slf4j
public class TestSearch {
    private static String hostName="172.21.10.61";
    private static Client getClient() throws UnknownHostException {
        Settings settings=Settings.builder().put("cluster.name","bigtoy-infiniti").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(hostName), 8989));
//                .addTransportAddress(new TransportAddress(InetAddress.getByName(hostName), 9301));
        return client;
    }
    public static void query() throws UnknownHostException {
        Client client=getClient();
        MatchAllQueryBuilder qb=new MatchAllQueryBuilder();
        SearchResponse searchResponse=client.prepareSearch("allotedorder").setTypes("allotedorder").
                setQuery(qb).setFrom(0).setSize(100).get();
        SearchHits searchHits=searchResponse.getHits();
        for(SearchHit hit:searchHits){
            System.out.println(hit.getSourceAsString());
        }
    }
    public static void queryBy() throws UnknownHostException {
//        Client client=getClient();
//        TermQueryBuilder queryBuilder= QueryBuilders.termQuery("value","1");
//        SearchResponse searchResponse=client.prepareSearch("dic")
//                .setTypes("logs").setQuery(queryBuilder).setFrom(0).setSize(100).setExplain(true).execute().actionGet();
//        SearchHits searchHits=searchResponse.getHits();
//        for(SearchHit searchHit:searchHits){
//            System.out.println(searchHit.getSourceAsString());
//        }
        Client client=getClient();
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
        TermQueryBuilder term1=QueryBuilders.termQuery("id","1");
        TermQueryBuilder term2=QueryBuilders.termQuery("key_code","test");
        boolQueryBuilder.must(term1).must(term2);
        SearchResponse searchResponse=client.prepareSearch("dic")
                .setQuery(boolQueryBuilder)
                .setTypes("logs").setExplain(true).get();
        System.out.println(searchResponse.toString());

    }
    public static void main(String args[]) throws UnknownHostException {
        query();
    }
}
