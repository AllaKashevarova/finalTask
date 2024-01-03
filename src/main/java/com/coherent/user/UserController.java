package com.coherent.user;

import utils.HttpRequestManager;
import com.coherent.token.SingletonTokenManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.RequestUtils;

import java.net.URI;
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
            String responseBody = EntityUtils.toString(response.getEntity());
            logger.info("Response -> " + responseBody);
            logger.info("Response StatusCode -> " + response.getStatusLine().getStatusCode());
        }
    }

    @SneakyThrows
    public int sendPatchUsers(PatchRequestBody patchRequestBody){
        try (
                CloseableHttpResponse response = httpRequestManager.sendPatch(RequestUtils.buildURI(
                                "users.path"),
                        SingletonTokenManager.getWriteToken(),
                        patchRequestBody)) {

            //Assertions.assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
            String responseBody = EntityUtils.toString(response.getEntity());
            logger.info("PATCH Response -> " + responseBody);
            logger.info("PATCH Response StatusCode -> " + response.getStatusLine().getStatusCode());
            logger.info("PATCH Response StatusLine -> " + response.getStatusLine());
            return response.getStatusLine().getStatusCode();
        }
    }
}
