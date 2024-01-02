package com.coherent;

import com.coherent.token.SingletonTokenManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.RequestUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class UserController {
    private HttpRequestManager httpRequestManager = new HttpRequestManager();
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController() {
        SingletonTokenManager.getInstance();
    }

    @SneakyThrows
    public List<User> sendGetUsers() {
        ObjectMapper objectMapper;
        String responseBody;
        try (CloseableHttpResponse response = httpRequestManager
                .sendGet(RequestUtils.buildURI("users.path"), SingletonTokenManager.getReadToken())) {

            Assertions.assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
            objectMapper = new ObjectMapper();
            responseBody = EntityUtils.toString(response.getEntity());

            logger.debug("Response -> {}", responseBody);
        }

        List<User> usersList = objectMapper.readValue(responseBody, new TypeReference<List<User>>() {
        });
        return usersList;
    }

    @SneakyThrows
    public List<User> sendGetUsersWithParams(List<NameValuePair> queryParams) {
        ObjectMapper objectMapper;
        String responseBody;

        URI uri = new URIBuilder(RequestUtils.buildURI("users.path"))
                .addParameters(queryParams)
                .build();


        try (CloseableHttpResponse response = httpRequestManager
                .sendGet(uri, SingletonTokenManager.getReadToken())) {

            Assertions.assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
            objectMapper = new ObjectMapper();
            responseBody = EntityUtils.toString(response.getEntity());

            logger.debug("Response -> {}", responseBody);
        }

        List<User> usersList = objectMapper.readValue(responseBody, new TypeReference<List<User>>() {
        });
        return usersList;

    }

    @SneakyThrows
    public void sendPostUsers(User body) {
        try (
                CloseableHttpResponse response = httpRequestManager.sendPost(RequestUtils.buildURI(
                                "users.path"),
                        SingletonTokenManager.getWriteToken(),
                        body)) {

            Assertions.assertThat(response.getStatusLine().getStatusCode()).isEqualTo(201);
            ObjectMapper objectMapper = new ObjectMapper();
            String responseBody = EntityUtils.toString(response.getEntity());
            logger.info("Response -> " + responseBody);
            logger.info("Response StatusCode -> " + response.getStatusLine().getStatusCode());
        }
    }

}
