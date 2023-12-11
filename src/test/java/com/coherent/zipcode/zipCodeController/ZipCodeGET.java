package com.coherent.zipcode.zipCodeController;

import com.coherent.HttpRequestManager;
import com.coherent.ZipCodeController;
import com.coherent.zipcode.BasicTestClass;
import com.google.common.collect.Sets;
import lombok.SneakyThrows;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class ZipCodeGET extends BasicTestClass {

    @Test
    public void shouldGetZipCodeWithGET() {
        List<String> expectedZipCodes = new ArrayList<>(Arrays.asList("12345" ,"23456", "ABCDE"));
        List<String> zipCodesResponse = zipCodeController.sendGetZipCodes(201);
        Assertions.assertTrue(zipCodesResponse.containsAll(expectedZipCodes));
        //QUESTION: why checking size is a valid assertion here?
//        Set<String> set = Sets.newHashSet(zipCodesResponse);
//        Assertions.assertEquals(zipCodesResponse.size(), set.size());

        //QUESTION: we don't verify status code here. If we verify the status on the getListZipFromBody()
        //method level then it doesn't excecute the test

        //BUGS:
        //Scenario 1:
        //Actual Result: 201 Status
        //Expected Result: 200 Status code
    }
}
