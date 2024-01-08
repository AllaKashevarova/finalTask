package utils;

import com.coherent.user.HttpDeleteWithBody;
import com.coherent.user.PatchRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;

public class HttpRequestManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(HttpRequestManager.class);

    @SneakyThrows(IOException.class)
    public CloseableHttpResponse sendPost(URI uri, String bearerToken, Object postBody){
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);

        String jsonBody = objectMapper.writeValueAsString(postBody);
        StringEntity entity = new StringEntity(jsonBody);

        httpPost.setEntity(entity);
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        logger.info("Sending POST request to URI: {}", uri);
        logger.info("Request body: {}", jsonBody);
        logger.info("Authorization token: {}", bearerToken);

        return client.execute(httpPost);
    }

    @SneakyThrows(IOException.class)
    public CloseableHttpResponse sendGet(URI uri, String bearerToken) {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader(HttpHeaders.AUTHORIZATION,"Bearer " + bearerToken);
        logger.info("Sending GET request to URI: {}", uri);
        return client.execute(httpGet);
    }

    @SneakyThrows
    public CloseableHttpResponse sendPatch(URI uri, String bearerToken, Object patchBody){
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPatch httpPatch = new HttpPatch(uri);

        String jsonBody = objectMapper.writeValueAsString(patchBody);
        StringEntity entity = new StringEntity(jsonBody);

        httpPatch.setEntity(entity);
        httpPatch.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken);
        httpPatch.setHeader("Accept", "application/json");
        httpPatch.setHeader("Content-type", "application/json");

        logger.info("Sending PATCH request to URI: {}", uri);
        logger.info("Request body: {}", jsonBody);
        logger.info("Authorization token: {}", bearerToken);

        return client.execute(httpPatch);
    }

    @SneakyThrows
    public CloseableHttpResponse sendDelete(URI uri, String bearerToken, Object bodyToDelete){
        CloseableHttpClient client = HttpClients.createDefault();

        HttpDeleteWithBody httpDeleteWithBody = new HttpDeleteWithBody(uri);

        String jsonBody = objectMapper.writeValueAsString(bodyToDelete);
        StringEntity entity = new StringEntity(jsonBody);

        httpDeleteWithBody.setEntity(entity);
        httpDeleteWithBody.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken);
        httpDeleteWithBody.setHeader("Accept", "application/json");
        httpDeleteWithBody.setHeader("Content-type", "application/json");

        logger.info("Sending DELETE request to URI: {}", uri);
        logger.info("Request body: {}", jsonBody);
        logger.info("Authorization token: {}", bearerToken);

        return client.execute(httpDeleteWithBody);

    }
}
