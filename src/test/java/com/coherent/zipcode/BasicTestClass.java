package com.coherent.zipcode;

import com.coherent.UserController;
import com.coherent.ZipCodeController;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.AfterEach;

public abstract class BasicTestClass {
    protected CloseableHttpClient httpClient;
    protected ZipCodeController zipCodeController = new ZipCodeController();
    protected UserController userController = new UserController();

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
