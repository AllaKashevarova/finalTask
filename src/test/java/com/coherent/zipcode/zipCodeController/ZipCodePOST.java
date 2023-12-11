package com.coherent.zipcode.zipCodeController;

import com.coherent.zipcode.BasicTestClass;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ZipCodePOST extends BasicTestClass {

    @DisplayName("Scenario 2 - check zip codes have been created")
    @Test
    public void shouldCreateZipCodeWithPOST() throws IOException {
        List<String> requestBody = new ArrayList<>(Arrays.asList("TEST1","TEST2","TEST3"));
        CloseableHttpResponse postResponse = zipCodeController.sendPostZipCodes(requestBody, 201);
        List<String> getZipCodesList = zipCodeController.sendGetZipCodes(200);

        Assertions.assertTrue(getZipCodesList.contains(requestBody));


        //BUGS:
        //Scenario 2: No bugs
    }
//    @DisplayName("Scenario 3 - check there are no duplicates for available zipcodes")
//    @Test
//    public void shouldCreateNoDuplicatesForAvailableZipCodes() throws IOException {
//        List<String> requestBody = Arrays.asList("ABCDE","TEST4");
//        CloseableHttpResponse postZipCodesResponse = zipCodeController.sendPostZipCodes(requestBody);
//        final int statusCode = postZipCodesResponse.getStatusLine().getStatusCode();
//
//        CloseableHttpResponse getZipCodes = zipCodeController.sendGetZipCodes();
//
//        String responseBody = EntityUtils.toString(postZipCodesResponse.getEntity());
//        int occurrences = StringUtils.countMatches(responseBody, "ABCDE");
//
//        assertAll("Zip Code POST Response",
//                () -> assertEquals(HttpStatus.SC_CREATED, statusCode),
//                () -> assertEquals(1, occurrences, "Found duplicates for available zip codes: 'ABCDE'")
//        );
//    //BUGS:
//    //Scenario 3:
//    //Actual Result: Found duplicates of 'ABCDE' in the server response
//    //Expected Result: No duplicates should be on the server
//}
//
//    @DisplayName("Scenario 4 - check there are no duplicates for already used zipcodes")
//    @Test
//    public void shouldCreateNoDuplicatesForAlreadyUsedZipCodes() throws IOException {
//        List<String> requestBody = Arrays.asList("TEST1","TEST7");
//        CloseableHttpResponse postZipCodesResponse = zipCodeController.sendPostZipCodes(requestBody);
//        final int statusCode = postZipCodesResponse.getStatusLine().getStatusCode();
//
//        CloseableHttpResponse getZipCodes = zipCodeController.sendGetZipCodes();
//
//        String responseBody = EntityUtils.toString(postZipCodesResponse.getEntity());
//        int occurrences = StringUtils.countMatches(responseBody, "TEST1");
//
//        assertAll("Zip Code POST Response",
//                () -> assertEquals(HttpStatus.SC_CREATED, statusCode),
//                () -> assertEquals(1, occurrences, "Found duplicates for available zip codes: 'TEST1'")
//        );
//        //BUGS:
//        //Scenario 4:
//        //Actual Result: Found duplicates of 'TEST1' in the server response
//        //Expected Result: No duplicates should be on the server
//    }

}

