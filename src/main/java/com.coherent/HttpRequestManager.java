package com.coherent;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.net.URI;

public class HttpRequestManager {

    public HttpPost setHttpPost(URI uri){
        HttpPost httpPost = new HttpPost(uri);
        return httpPost;
    }

    public HttpGet setHttpGet(URI uri){
        HttpGet httpGet = new HttpGet(uri);
        return httpGet;
    }




}
