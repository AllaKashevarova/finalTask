package com.coherent.token;

import com.coherent.HttpRequestManager;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

public class WriteTokenReceiver {
    private CloseableHttpClient httpClient;
    private String writeScope = "write";

    public WriteTokenReceiver(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String getWriteToken() {
        URI uri = TokenUrlBuilder.buildTokenUri(writeScope);

        HttpRequestManager httpRequestManager = new HttpRequestManager();
        HttpPost httpPost = httpRequestManager.setHttpPost(uri);

        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String responseBody = null;
        try {
            responseBody = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseBody;

    }

}
