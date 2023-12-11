package com.coherent;

import com.coherent.token.SingletonTokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.PropertiesHelper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ZipCodeController {

    public ZipCodeController() {
        SingletonTokenManager.getInstance();
    }

    private HttpRequestManager httpRequestManager = new HttpRequestManager();
    private static PropertiesHelper propertiesHelper = new PropertiesHelper();
    private static String urlPropFile = "url.properties";
    private String authCredsFile = "authCreds.properties";
    private String pathPost = propertiesHelper.propertiesReader("zip.post.path", urlPropFile);
    private String readScope = propertiesHelper.propertiesReader("scope.read", authCredsFile);
    private String writeScope = propertiesHelper.propertiesReader("scope.write", authCredsFile);
    private static String url = propertiesHelper.propertiesReader("url", urlPropFile);
    private String pathGet = propertiesHelper.propertiesReader("zip.get.path", urlPropFile);

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
    public List<String> sendGetZipCodes(int statusCode) {
        CloseableHttpResponse response = httpRequestManager.sendGet(buildURI(pathGet), SingletonTokenManager.getReadToken());
        //QUESTION: when I use this log method then I get "Can't read from closed stream" message.
        // How can I re-write logger method? :
        //getResponseLogs(response);

        InputStream inputStream = response.getEntity().getContent();
        String responseBody = readInputStream(inputStream);
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> entity = objectMapper.readValue(responseBody, List.class);

        return entity;
    }
    @SneakyThrows
    private String readInputStream(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()))) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }
    }

    public CloseableHttpResponse sendPostZipCodes(List<String> body, int statusCode) {
        CloseableHttpResponse response = httpRequestManager.sendPost(buildURI(pathPost), SingletonTokenManager.getWriteToken(), body);
        //getResponseLogs(response);
        return response;
    }

    @SneakyThrows
    public void getResponseLogs(CloseableHttpResponse response) {
        Logger logger = LoggerFactory.getLogger(Object.class);
        String responseBody = EntityUtils.toString(response.getEntity());
        logger.info("Response body: {}", responseBody);
        logger.info("Status code: {}", response.getStatusLine());
    }
}
