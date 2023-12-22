package com.coherent.zipcode.zipCodeController;

import com.coherent.zipcode.BasicTestClass;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GetZipCodes extends BasicTestClass {

    @Test
    public void shouldGetZipCodeWithGET() {
        List<String> expectedZipCodes = new ArrayList<>(Arrays.asList("12345" ,"23456", "ABCDE"));
        List<String> zipCodesResponse = zipCodeController.sendGetZipCodes(201);

        org.assertj.core.api.Assertions.assertThat(zipCodesResponse).containsAll(expectedZipCodes);
        //BUGS:
        //Scenario 1:
        //Actual Result: 201 Status
        //Expected Result: 200 Status code
    }
}
