package com.coherent;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.HttpHeaders;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import utils.PropertiesHelper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;

public class HttpRequestManager {
    private static final PropertiesHelper propertiesHelper = new PropertiesHelper();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public static CloseableHttpClient createClient() {
        return HttpClients.createDefault();
    }

    @SneakyThrows(IOException.class)
    public CloseableHttpResponse sendPost(URI uri, String bearerToken, List<String> postBody){
        CloseableHttpClient client = createClient();
        HttpPost httpPost = new HttpPost(uri);

        String jsonBody = objectMapper.writeValueAsString(postBody);
        StringEntity entity = new StringEntity(jsonBody);

        httpPost.setEntity(entity);
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        return client.execute(httpPost);
    }

    @SneakyThrows(IOException.class)
    public CloseableHttpResponse sendGet(URI uri, String bearerToken) {
        CloseableHttpClient client = createClient();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader(HttpHeaders.AUTHORIZATION,"Bearer " + bearerToken);
        return client.execute(httpGet);
    }
}
