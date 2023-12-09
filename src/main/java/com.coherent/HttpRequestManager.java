package com.coherent;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;

public class HttpRequestManager {

    public static CloseableHttpClient createClient() {
        CloseableHttpClient client = HttpClients.createDefault();
        return client;
    }

    public HttpPost setHttpPost(URI uri){
        HttpPost httpPost = new HttpPost(uri);
        return httpPost;
    }

    public HttpPost setHttpPostWithJson (URI uri, String json){
        HttpPost httpPost = new HttpPost(uri);
        final StringEntity entity;
        try {
            entity = new StringEntity(json);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        return httpPost;
    }

    public HttpGet setHttpGet(URI uri){
        HttpGet httpGet = new HttpGet(uri);
        return httpGet;
    }




}
