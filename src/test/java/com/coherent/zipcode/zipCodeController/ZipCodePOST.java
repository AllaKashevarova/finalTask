package com.coherent.zipcode.zipCodeController;

import com.coherent.URIManager;
import com.coherent.ZipCodeController;
import com.coherent.zipcode.BasicTestClass;
import helpers.PropertiesHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ZipCodePOST extends BasicTestClass {
    //TODO QUESTION: Can I use this propertiesHelper in BaseClass?
    private PropertiesHelper propertiesHelper = new PropertiesHelper();
    private String zipControllerFile = "zipCodeController.properties";
    private String zipPostBody = propertiesHelper.propertiesReader("zip.list", zipControllerFile);
    private URIManager zipPostUri = new URIManager();
    private ZipCodeController zipCodeController = new ZipCodeController();
    private static final Logger logger = LoggerFactory.getLogger(ZipCodePOST.class);

//    @ParameterizedTest
//    @CsvSource({
//            "TEST1,TEST2,TEST3",
//            "TEST1,TEST4,TEST5",
//    })

    @DisplayName("Scenario 2 - check zip codes have been created")
    @Test
    public void shouldCreateZipCodeWithPOST() {
        String body = "[\"TEST1\",\"TEST2\",\"TEST3\"]";
        CloseableHttpResponse postZipCodesResponse = zipCodeController.postZipCodes(body);
        final int statusCode = postZipCodesResponse.getStatusLine().getStatusCode();

        CloseableHttpResponse getZipCodes = zipCodeController.getZipCodes();

        String responseBody = null;
        try {
            responseBody = EntityUtils.toString(getZipCodes.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.info("Get zip codes response body: {}", responseBody);
        logger.info("Status code: {}", postZipCodesResponse.getStatusLine());

        String finalResponseBody = responseBody;
        assertAll("Zip Code POST Response",
                () -> assertEquals(HttpStatus.SC_CREATED, statusCode),
                () -> assertTrue(finalResponseBody.contains("TEST1"), "Expected value 'TEST1' not found on the server"),
                () -> assertTrue(finalResponseBody.contains("TEST2"), "Expected value 'TEST2' not found on the server"),
                () -> assertTrue(finalResponseBody.contains("TEST3"), "Expected value 'TEST3' not found on the server")

        );
        //BUGS:
        //Scenario 2: No bugs
    }

    @DisplayName("Scenario 3 - check there are no duplicates for available zipcodes")
    @Test
    public void shouldCreateNoDuplicatesForAvailableZipCodes() {
        String body = "[\"ABCDE\",\"TEST4\"]";
        CloseableHttpResponse postZipCodesResponse = zipCodeController.postZipCodes(body);
        final int statusCode = postZipCodesResponse.getStatusLine().getStatusCode();

        CloseableHttpResponse getZipCodes = zipCodeController.getZipCodes();

        String responseBody = null;
        try {
            responseBody = EntityUtils.toString(getZipCodes.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("Get zip codes response body: {}", responseBody);
        logger.info("Status code: {}", postZipCodesResponse.getStatusLine());
        String finalResponseBody = responseBody;
        int occurences = StringUtils.countMatches(responseBody, "ABCDE");

        assertAll("Zip Code POST Response",
                () -> assertEquals(HttpStatus.SC_CREATED, statusCode),
                () -> assertEquals(1, occurences, "Found duplicates for available zip codes: 'ABCDE'")
        );
    //BUGS:
    //Scenario 3:
    //Actual Result: Found duplicates of 'ABCDE' in the server response
    //Expected Result: No duplicates should be on the server
}

    @DisplayName("Scenario 4 - check there are no duplicates for already used zipcodes")
    @Test
    public void shouldCreateNoDuplicatesForAlreadyUsedZipCodes() {
        String body = "[\"TEST1\",\"TEST7\"]";
        CloseableHttpResponse postZipCodesResponse = zipCodeController.postZipCodes(body);
        final int statusCode = postZipCodesResponse.getStatusLine().getStatusCode();

        CloseableHttpResponse getZipCodes = zipCodeController.getZipCodes();

        String responseBody = null;
        try {
            responseBody = EntityUtils.toString(getZipCodes.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("Get zip codes response body: {}", responseBody);
        logger.info("Status code: {}", postZipCodesResponse.getStatusLine());
        String finalResponseBody = responseBody;
        int occurences = StringUtils.countMatches(responseBody, "TEST1");

        assertAll("Zip Code POST Response",
                () -> assertEquals(HttpStatus.SC_CREATED, statusCode),
                () -> assertEquals(1, occurences, "Found duplicates for available zip codes: 'TEST1'")
        );
        //BUGS:
        //Scenario 4:
        //Actual Result: Found duplicates of 'TEST1' in the server response
        //Expected Result: No duplicates should be on the server
    }

}

