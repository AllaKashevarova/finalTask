package com.coherent.zipcode.zipCodeController;

import com.coherent.zipcode.BasicTestClass;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

public class PostZipCodes extends BasicTestClass {

    @DisplayName("Scenario 2 - check zip codes have been created")
    @Test
    public void shouldCreateZipCodeWithPOST() throws IOException {
        List<String> requestBody = new ArrayList<>(Arrays.asList("TEST1", "TEST2", "TEST3"));
        zipCodeController.sendPostZipCodes(requestBody, 201);
        List<String> getZipCodesList = zipCodeController.sendGetZipCodes(201);

        org.assertj.core.api.Assertions.assertThat(getZipCodesList).containsAll(requestBody);
        //BUGS:
        //Scenario 2: No bugs
    }

    @DisplayName("Scenario 3 - check there are no duplicates for available zipcodes")
    @Test
    public void shouldCreateNoDuplicatesForAvailableZipCodes() throws IOException {
        List<String> requestBody = new ArrayList<>(Arrays.asList("ABCDE", "12345"));
        zipCodeController.sendPostZipCodes(requestBody, 201);
        List<String> getZipCodesList = zipCodeController.sendGetZipCodes(201);
        Set<String> zipCodesWithoutDuplicates = Sets.newHashSet(getZipCodesList);

        org.assertj.core.api.Assertions.assertThat(getZipCodesList.size()).isEqualTo(zipCodesWithoutDuplicates.size());
    }

    //    //BUGS:
//    //Scenario 3:
//    //Actual Result: Found duplicates
//    //Expected Result: No duplicates should be on the server
    @DisplayName("Scenario 4 - check there are no duplicates for already used zipcodes")
    @Test
    public void shouldCreateNoDuplicatesForAlreadyUsedZipCodes() throws IOException {
        List<String> requestBody = new ArrayList<>(Arrays.asList("TEST15", "TEST16", "TEST17", "TEST18"));
        zipCodeController.sendPostZipCodes(requestBody, 201);
        List<String> getZipCodesList = zipCodeController.sendGetZipCodes(201);
        long count = getZipCodesList.stream().filter(element -> element.equals("TEST1")).count();

        org.assertj.core.api.Assertions.assertThat(1).isEqualTo(count);
    }
    //BUGS:
    //Scenario 4:
    //Actual Result: Found duplicates of 'TEST1' in the server response
    //Expected Result: No duplicates should be on the server
}



