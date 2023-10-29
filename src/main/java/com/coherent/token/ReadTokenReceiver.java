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
        TokenExtractor tokenExtractor = new TokenExtractor();
        String readToken = tokenExtractor.getToken(readScope);

        return readToken;
    }
}
