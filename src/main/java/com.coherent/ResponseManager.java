package com.coherent;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

public class ResponseManager {

    public static CloseableHttpResponse getResponse(HttpRequestBase httpRequestType, CloseableHttpClient client){
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpRequestType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
