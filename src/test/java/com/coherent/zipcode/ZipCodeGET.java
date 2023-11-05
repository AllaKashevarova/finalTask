package com.coherent.zipcode;

import com.coherent.HttpRequestManager;
import com.coherent.URIManager;
import helpers.PropertiesHelper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ZipCodeGET extends BasicTestClass{
    private URIManager getZipCodeURI = new URIManager();
    private PropertiesHelper propertiesHelper = new PropertiesHelper();
    private String zipCodePropFile = "zipCodeController.properties";
    private String getPath = propertiesHelper.propertiesReader("get.path", zipCodePropFile);
    private HttpRequestManager request = new HttpRequestManager();



    @Test
    public void shouldGetZipCodeWithGET(){
        HttpGet httpGet = request.setHttpGet(getZipCodeURI.buildURI(getPath));





    }
}
