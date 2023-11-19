package com.coherent;

import helpers.PropertiesHelper;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class URIManager {
    private static PropertiesHelper propertiesHelper = new PropertiesHelper();

    public static URI buildURI(String path) {
        String urlPropFile = "url.properties";
        String scheme = propertiesHelper.propertiesReader("url.scheme", urlPropFile);
        String host = propertiesHelper.propertiesReader("url.host", urlPropFile);

        final String URL = "%s://%s%s";
        String val = String.format(URL, scheme, host, path);

        URI uri = null;
        try {
            uri = new URI(val);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return uri;
            // http://localhost:8050/zip-codes
    }
}
