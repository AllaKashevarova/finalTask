package com.coherent;

import helpers.PropertiesHelper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

public class ZipCodeController {
    private HttpRequestManager httpRequestManager = new HttpRequestManager();
    private PropertiesHelper propertiesHelper = new PropertiesHelper();
    private String urlPropFile = "url.properties";
    private String path = propertiesHelper.propertiesReader("zip.get.path", urlPropFile);

    public HttpGet getZipCodes(){
        final HttpGet httpGetZip = httpRequestManager.setHttpGet(URIManager.buildURI(path));
        return httpGetZip;
    }

}
