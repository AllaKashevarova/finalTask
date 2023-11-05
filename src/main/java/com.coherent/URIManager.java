package com.coherent;

import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class URIManager {

    public URI buildURI(String path) {
        //TODO QUESTION: Is it possible to include parameters of the URI into the method but don't use them if they're not needed?

        try {
            return new URIBuilder()
                    .setScheme("http")
                    .setHost("localhost:8050")
                    .setPath(path)
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
