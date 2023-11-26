package com.coherent.zipcode.zipCodeController;

import com.coherent.HttpClientFactory;
import com.coherent.HttpRequestManager;
import com.coherent.URIManager;
import com.coherent.ZipCodeController;
import com.coherent.token.SingletonTokenManager;
import com.coherent.zipcode.BasicTestClass;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ZipCodeGET extends BasicTestClass {
    private PropertiesHelper propertiesHelper = new PropertiesHelper();
    private ZipCodeController zipCodeController = new ZipCodeController();
    private String authCredsPropFile = "authCreds.properties";
    private String scopeRead = propertiesHelper.propertiesReader("scope.read", authCredsPropFile);
    private static final Logger logger = LoggerFactory.getLogger(ZipCodeGET.class);

    @Test
    public void shouldGetZipCodeWithGET() {
        ZipCodeController controller = new ZipCodeController();
        CloseableHttpResponse getZipCodesResponse = controller.getZipCodes();
        final int actualStatusCode = getZipCodesResponse.getStatusLine().getStatusCode();

        try {
            String responseBody = EntityUtils.toString(getZipCodesResponse.getEntity());
            logger.info("Response body: {}", responseBody);
            logger.info("Status code: {}", getZipCodesResponse.getStatusLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(HttpStatus.SC_OK, actualStatusCode);
        //BUGS:
        //Scenario 1:
        //Actual Result: 201 Status
        //Expected Result: 200 Status code
    }
}
