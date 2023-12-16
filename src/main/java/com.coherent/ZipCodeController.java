package com.coherent;

import com.coherent.token.SingletonTokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.util.EntityUtils;

import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.PropertiesHelper;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class ZipCodeController {

    public ZipCodeController() {
        SingletonTokenManager.getInstance();
    }

    private HttpRequestManager httpRequestManager = new HttpRequestManager();
    private static PropertiesHelper propertiesHelper = new PropertiesHelper();
    private static String urlPropFile = "url.properties";
    private String pathPost = propertiesHelper.propertiesReader("zip.post.path", urlPropFile);
    private static String url = propertiesHelper.propertiesReader("url", urlPropFile);
    private String pathGet = propertiesHelper.propertiesReader("zip.get.path", urlPropFile);


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
    }

    @SneakyThrows
    public List<String> sendGetZipCodes(int statusCode) {
        ObjectMapper objectMapper;
        String responseBody;
        try (CloseableHttpResponse response = httpRequestManager.sendGet(buildURI(pathGet), SingletonTokenManager.getReadToken())) {
            //getResponseLogs(response);
            //Alright, I see the solution you commented in the PR.
            // I think I'd better investigate the case to add logs on the Client level?
            // It seems wrong to move the logic of json serialization to the method that invokes logs. Or I'm wrong?

            Assertions.assertThat(response.getStatusLine().getStatusCode()).isEqualTo(201);
            objectMapper = new ObjectMapper();
            responseBody = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<String> entity = objectMapper.readValue(responseBody, List.class);
        return entity;
    }

    public CloseableHttpResponse sendPostZipCodes(List<String> body, int statusCode) {
        //getResponseLogs(response);
        return httpRequestManager.sendPost(buildURI(pathPost), SingletonTokenManager.getWriteToken(), body);
    }

    @SneakyThrows
    public void getResponseLogs(CloseableHttpResponse response) {
        Logger logger = LoggerFactory.getLogger(Object.class);
        String responseBody = EntityUtils.toString(response.getEntity());
        logger.info("Response body: {}", responseBody);
        logger.info("Status code: {}", response.getStatusLine());
    }
}
