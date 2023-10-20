package com.coherent.token;

import com.coherent.HttpClientFactory;
import com.coherent.HttpRequestManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

public class ReadTokenReceiver {
    private String readScope = "read";

    public ReadTokenReceiver() {
    }

    public String getReadToken() {
        CloseableHttpClient client = new HttpClientFactory().createClient();

        URI uri = HttpClientFactory.buildTokenUri(readScope);

        HttpRequestManager httpRequestManager = new HttpRequestManager();
        HttpPost httpPost = httpRequestManager.setHttpPost(uri);
        CloseableHttpResponse response = null;

        try {
            response = client.execute(httpPost);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String parsedReadToken = null;
        try {
            String responseBody = EntityUtils.toString(response.getEntity());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            parsedReadToken = jsonNode.get("access_token").asText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return parsedReadToken;
    }
}
