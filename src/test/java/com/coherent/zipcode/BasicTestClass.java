package com.coherent.zipcode;

import com.coherent.ResponseManager;
import com.coherent.ZipCodeController;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.AfterEach;
import utils.LoggerHelper;

public abstract class BasicTestClass {
    protected CloseableHttpClient httpClient;
    protected ResponseManager responseManager = new ResponseManager();
    protected ZipCodeController zipCodeController = new ZipCodeController();

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
