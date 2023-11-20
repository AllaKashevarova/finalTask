package com.coherent.zipcode;

import com.coherent.HttpClientFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public abstract class BasicTestClass {
    protected CloseableHttpClient httpClient;
    protected HttpClientFactory client;
    protected CloseableHttpResponse response;

    @BeforeEach
    public void beforeEach() {
        client = new HttpClientFactory();
        httpClient = client.createClient();
    }

    @AfterEach
    public void afterAll() {
        try {
            response.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
