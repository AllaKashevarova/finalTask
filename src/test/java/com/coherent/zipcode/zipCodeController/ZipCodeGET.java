package com.coherent.zipcode.zipCodeController;

import com.coherent.ZipCodeController;
import com.coherent.zipcode.BasicTestClass;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ZipCodeGET extends BasicTestClass {

    @Test
    public void shouldGetZipCodeWithGET() {
        ZipCodeController controller = new ZipCodeController();
        CloseableHttpResponse getZipCodesResponse = controller.sendGetZipCodes();
        final int actualStatusCode = getZipCodesResponse.getStatusLine().getStatusCode();
        
        Assertions.assertEquals(HttpStatus.SC_OK, actualStatusCode);
        //BUGS:
        //Scenario 1:
        //Actual Result: 201 Status
        //Expected Result: 200 Status code
    }
}
