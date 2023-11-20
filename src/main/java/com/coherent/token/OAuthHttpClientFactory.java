package com.coherent.token;

import com.coherent.token.SingletonTokenManager;
import com.google.common.collect.Lists;
import helpers.PropertiesHelper;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class OAuthHttpClientFactory {
    private PropertiesHelper propertiesHelper = new PropertiesHelper();
    private String authCredsPropFile = "authCreds.properties";
    private String hostName = propertiesHelper.propertiesReader("host", authCredsPropFile);
    private int portName = Integer.parseInt(propertiesHelper.propertiesReader("port", authCredsPropFile));
    private String userName = propertiesHelper.propertiesReader("user.name", authCredsPropFile);
    private String password = propertiesHelper.propertiesReader("password", authCredsPropFile);

    public CloseableHttpClient createClient() {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();

        credsProvider.setCredentials(
                new AuthScope(hostName, portName),
                new UsernamePasswordCredentials(userName, password)
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
