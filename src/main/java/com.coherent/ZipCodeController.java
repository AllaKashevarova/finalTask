package com.coherent;

import helpers.PropertiesHelper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ZipCodeController {
    private HttpRequestManager httpRequestManager = new HttpRequestManager();
    private PropertiesHelper propertiesHelper = new PropertiesHelper();
    private String urlPropFile = "url.properties";
    private String zipControllerFile = "zipCodeController.properties";
    private String authCredsFile = "authCreds.properties";
    private String pathGet = propertiesHelper.propertiesReader("zip.get.path", urlPropFile);
    private String pathPost = propertiesHelper.propertiesReader("zip.post.path", urlPropFile);
    private String readScope = propertiesHelper.propertiesReader("scope.read", authCredsFile);
    private String writeScope = propertiesHelper.propertiesReader("scope.write", authCredsFile);
    private HttpHeaderManager headerManager = new HttpHeaderManager();
    private HttpClientFactory httpClientFactory = new HttpClientFactory();

    public CloseableHttpResponse getZipCodes() {
        CloseableHttpClient httpClient = httpClientFactory.createClient();
        final HttpGet httpGetZip = httpRequestManager.setHttpGet(URIManager.buildURI(pathGet));
        httpGetZip.setHeader(headerManager.getAuthToken(readScope));
        CloseableHttpResponse response = ResponseManager.getResponse(httpGetZip, httpClient);
        return response;
    }

    public CloseableHttpResponse postZipCodes(String body){
        CloseableHttpClient httpClient = httpClientFactory.createClient();
        //TODO create Client here AND think about separate method with the client
        final HttpPost httpPostZip = httpRequestManager.setHttpPostWithJson(URIManager.buildURI(pathPost), body);
        httpPostZip.setHeader(headerManager.getAuthToken(writeScope));
        CloseableHttpResponse response = ResponseManager.getResponse(httpPostZip, httpClient);

        return response;
    }






}
