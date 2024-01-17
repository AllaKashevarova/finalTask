package com.coherent.zipcode.zipCodeController;

import com.coherent.zipcode.BasicTestClass;

import io.qameta.allure.Allure;
import io.qameta.allure.Issue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.qameta.allure.Step;


public class GetZipCodes extends BasicTestClass {


    @Test
    @Issue("BUG-1")
    public void shouldGetZipCodeWithGET() {
        Allure.step("Send the list of Zip Codes");
        List<String> expectedZipCodes = new ArrayList<>(Arrays.asList("12345", "23456", "ABCDE"));
        Allure.addAttachment("Expected list of Zip Codes: ", expectedZipCodes.toString());
        List<String> zipCodesResponse = zipCodeController.sendGetZipCodes(200);

        org.assertj.core.api.Assertions.assertThat(zipCodesResponse).containsAll(expectedZipCodes);
        //BUGS:
        //Scenario 1:
        //Actual Result: 201 Status
        //Expected Result: 200 Status code
    }
}
