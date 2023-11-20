package com.coherent.zipcode;

import com.coherent.HttpClientFactory;
import com.coherent.HttpRequestManager;
import com.coherent.URIManager;
import com.coherent.ZipCodeController;
import com.coherent.token.SingletonTokenManager;
import helpers.PropertiesHelper;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ZipCodeGET extends BasicTestClass{
    //TODO create a separate class with methods for configuration HTTP methods
    private PropertiesHelper propertiesHelper = new PropertiesHelper();
    private ZipCodeController zipCodeController = new ZipCodeController();
    private String authCredsPropFile = "authCreds.properties";
    private String scopeRead = propertiesHelper.propertiesReader("scope.read", authCredsPropFile);

    @Test
    public void shouldGetZipCodeWithGET(){

        HttpGet httpGet = zipCodeController.getZipCodes();

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final int statusCode = response.getStatusLine().getStatusCode();

        try {
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Response body: " + responseBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(HttpStatus.SC_OK, statusCode);
    }
}
