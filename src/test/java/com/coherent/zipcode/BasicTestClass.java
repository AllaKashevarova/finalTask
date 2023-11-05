package com.coherent.zipcode;

import com.coherent.HttpClientFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BasicTestClass {



    @BeforeEach
    public void beforeEach(String scope) {
        //Can I use switch case in abstract class? Or I should create a super class and override methods?
        CloseableHttpClient client = null;
        switch (scope) {
            case "WRITE":
                client = new HttpClientFactory().createClient(true);
                break;
            case "READ":
                client = new HttpClientFactory().createClient(false);
        }

    }

    @AfterAll
    public void afterAll(){


    }


}
