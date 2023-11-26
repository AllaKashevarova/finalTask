package com.coherent.zipcode;

import com.coherent.HttpClientFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public abstract class BasicTestClass {
    protected CloseableHttpClient httpClient;

    @AfterEach
    public void afterAll() {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error closing HTTP client", e);
        }
    }
}
