package com.coherent;

import com.coherent.token.SingletonTokenManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.PropertiesHelper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class ZipCodeController {
    private HttpRequestManager httpRequestManager = new HttpRequestManager();
    private static PropertiesHelper propertiesHelper = new PropertiesHelper();
    private static String urlPropFile = "url.properties";
    private  String zipControllerFile = "zipCodeController.properties";
    private String authCredsFile = "authCreds.properties";
    private String pathGet = propertiesHelper.propertiesReader("zip.get.path", urlPropFile);
    private String pathPost = propertiesHelper.propertiesReader("zip.post.path", urlPropFile);
    private String readScope = propertiesHelper.propertiesReader("scope.read", authCredsFile);
    private String writeScope = propertiesHelper.propertiesReader("scope.write", authCredsFile);
    private static String url = propertiesHelper.propertiesReader("url", urlPropFile);
    private static CloseableHttpClient httpClient = HttpRequestManager.createClient();

    private static URI buildURI(String path) {
        final String URL = "%s%s";
        String val = String.format(URL, url, path);

        URI uri = null;
        try {
            uri = new URI(val);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return uri;
        // http://localhost:8050/zip-codes
    }

    @SneakyThrows
    public CloseableHttpResponse sendGetZipCodes() throws IOException {
        final HttpGet httpGetZip = httpRequestManager.setHttpGet(buildURI(pathGet));
        SingletonTokenManager.getInstance();
        httpGetZip.setHeader(new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer " + SingletonTokenManager.getReadToken()));
        return httpClient.execute(httpGetZip);
    }


    public static CloseableHttpResponse getResponse(HttpRequestBase httpRequestType, CloseableHttpClient client, int status){
        Logger logger = LoggerFactory.getLogger(ResponseManager.class);
        CloseableHttpResponse response = httpClient.execute(httpRequestType);


        String responseBody = EntityUtils.toString(response.getEntity());
        logger.info("Response body: {}", responseBody);
        logger.info("Status code: {}", response.getStatusLine());

        //Assertions.assertEquals(status, response.getStatusLine().getStatusCode());
        return response;
    }

    public CloseableHttpResponse postZipCodes(List<String> body){
        String requestString = listToString(body);
        final HttpPost httpPostZip = httpRequestManager.setHttpPostWithJson(buildURI(pathPost), requestString);
        SingletonTokenManager.getInstance();
        httpPostZip.setHeader(new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer " + SingletonTokenManager.getWriteToken()));
        CloseableHttpResponse response = getResponse(httpPostZip, httpClient, HttpStatus.SC_CREATED);

        return response;
    }

    private static String listToString(List<String> requestBody){
        ObjectMapper objectMapper = new ObjectMapper();
        String requestString = null;
        try {
            requestString = objectMapper.writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return requestString;
    }
}
