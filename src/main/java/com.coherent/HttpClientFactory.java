package com.coherent;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.net.URISyntaxException;

public class HttpClientFactory {

    public CloseableHttpClient createClient() {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();

        credsProvider.setCredentials(
                new AuthScope("localhost", 8050),
                //TODO get creds from authCreds.properties
                new UsernamePasswordCredentials("0oa157tvtugfFXEhU4x7", "X7eBCXqlFC7x-mjxG5H91IRv_Bqe1oq7ZwXNA8aq")
        );

        return HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();
    }

    public static URI buildTokenUri(String scope) {
        try {
            return new URIBuilder()
                    .setScheme("http")
                    .setHost("localhost:8050")
                    .setPath("/oauth/token")
                    .setParameter("grant_type", "client_credentials")
                    .setParameter("scope", scope)
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
