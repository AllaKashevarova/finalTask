package com.coherent.zipcode;

import com.coherent.user.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.RandomUitls;

public abstract class BasicTestClass {
    protected CloseableHttpClient httpClient;
    protected ZipCodeController zipCodeController = new ZipCodeController();
    protected UserController userController = new UserController();
    protected ObjectMapper objectMapper = new ObjectMapper();
    protected RandomUitls randomUitls = new RandomUitls();
    protected Faker faker = new Faker();



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
