package com.coherent;

import com.coherent.token.SingletonTokenManager;
import com.google.common.collect.Lists;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

import java.util.List;

public class HttpClientFactory {

    public CloseableHttpClient createClient(String tokenType) {
        final String singletonWrite = SingletonTokenManager.getWriteToken();
        final String singletonRead = SingletonTokenManager.getReadToken();

        final Header writeHeader = new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer " + singletonWrite);
        final List<Header> writeHeaders = Lists.newArrayList(writeHeader);
        final Header readHeader = new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer " + singletonRead);
        final List<Header> readHeaders = Lists.newArrayList(readHeader);

        //I added possibility to add authorization token straightaway to the HTTP Client.
        switch (tokenType) {
            case "WRITE":
                return HttpClients.custom()
                        .setDefaultHeaders(writeHeaders)
                        .build();
            case "READ":
                return HttpClients.custom()
                        .setDefaultHeaders(readHeaders)
                        .build();
            default:
                System.out.println("Provide either 'WRITE' or 'READ' token values");
                return null;

        }

    }

}
