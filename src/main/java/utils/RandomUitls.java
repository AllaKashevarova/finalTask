package utils;

import com.coherent.zipcode.ZipCodeController;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomUitls {
    ZipCodeController zipCodeController = new ZipCodeController();

    public String randomZip(){
        String zip = RandomStringUtils.randomAlphanumeric(6);
        return zip;
    }

    public void sendGetWithRandomZipCodes(String oldZip, String newZip){

        List<String> requestBody = new ArrayList<>(Arrays.asList(oldZip, newZip));
        zipCodeController.sendPostZipCodes(requestBody, 201);
    }


}
