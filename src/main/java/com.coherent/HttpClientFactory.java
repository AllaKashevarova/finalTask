package com.coherent;

import com.coherent.token.SingletonTokenManager;
import com.google.common.collect.Lists;
import helpers.PropertiesHelper;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class HttpClientFactory {
    private PropertiesHelper propertiesHelper = new PropertiesHelper();
    private String authCredsPropFile = "authCreds.properties";
    private String hostName = propertiesHelper.propertiesReader("host", authCredsPropFile);
    private int portName = Integer.parseInt(propertiesHelper.propertiesReader("port", authCredsPropFile));
    private String userName = propertiesHelper.propertiesReader("user.name", authCredsPropFile);
    private String password = propertiesHelper.propertiesReader("password", authCredsPropFile);

    public CloseableHttpClient createClient(boolean useWriteToken) {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        final String singletonWrite = SingletonTokenManager.getWriteToken();
        final String singletonRead = SingletonTokenManager.getReadToken();
        final Header writeHeader = new BasicHeader(HttpHeaders.AUTHORIZATION, singletonWrite);
        final List<Header> writeHeaders = Lists.newArrayList(writeHeader);
        final Header readHeader = new BasicHeader(HttpHeaders.AUTHORIZATION, singletonWrite);
        final List<Header> readHeaders = Lists.newArrayList(readHeader);


        credsProvider.setCredentials(
                new AuthScope(hostName, portName),
                new UsernamePasswordCredentials(userName, password)
        );

        //I added possibility to add authorization token straightaway to the HTTP Client. Is it a good approach?
        if (useWriteToken) {
            return HttpClients.custom()
                    .setDefaultCredentialsProvider(credsProvider)
                    .setDefaultHeaders(writeHeaders)
                    .build();
        } else {
            return HttpClients.custom()
                    .setDefaultCredentialsProvider(credsProvider)
                    .setDefaultHeaders(readHeaders)
                    .build();

        }
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
