package com.coherent;

import com.coherent.token.SingletonTokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.util.EntityUtils;

import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.http.client.methods.CloseableHttpResponse;
import utils.RequestUtils;

import java.io.IOException;
import java.util.List;

public class ZipCodeController {

    public ZipCodeController() {
        SingletonTokenManager.getInstance();

    }

    private HttpRequestManager httpRequestManager = new HttpRequestManager();
    private static final Logger logger = LoggerFactory.getLogger(ZipCodeController.class);

    @SneakyThrows
    public List<String> sendGetZipCodes(int statusCode) {
        String responseBody;
        try (CloseableHttpResponse response = httpRequestManager.sendGet(RequestUtils.buildURI(
                        "zip.get.path"),
                SingletonTokenManager.getReadToken())) {
            Assertions.assertThat(response.getStatusLine().getStatusCode()).isEqualTo(statusCode);
            responseBody = EntityUtils.toString(response.getEntity());
            logger.info("Response -> {}", responseBody);
            logger.info("Response Status Code -> {}", response.getStatusLine().getStatusCode());
        }
        return new ObjectMapper().readValue(responseBody, List.class);
    }

    public void sendPostZipCodes(List<String> body, int statusCode) {
        CloseableHttpResponse response = httpRequestManager.sendPost(RequestUtils.buildURI(
                        "zip.post.path"),
                SingletonTokenManager.getWriteToken(),
                body);
        Assertions.assertThat(response.getStatusLine().getStatusCode()).isEqualTo(statusCode);

    }
}
