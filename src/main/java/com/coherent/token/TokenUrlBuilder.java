package com.coherent.token;

import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class TokenUrlBuilder {

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
