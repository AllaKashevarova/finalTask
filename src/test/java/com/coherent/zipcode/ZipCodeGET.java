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

    //final String singletonRead = SingletonTokenManager.getReadToken();

    @Override
    protected CloseableHttpClient createHttpClient() {
        HttpClientFactory httpClientFactory = new HttpClientFactory();
        CloseableHttpClient client = httpClientFactory.createClient(scopeRead);
        return client;
    }
    @Test
    public void shouldGetZipCodeWithGET(){
        CloseableHttpClient client = createHttpClient();
        HttpGet httpGet = zipCodeController.getZipCodes();
        //httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + singletonRead);

        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
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
