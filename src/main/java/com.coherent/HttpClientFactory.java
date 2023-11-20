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

    public CloseableHttpClient createClient() {
        CloseableHttpClient client = HttpClients.createDefault();
        return client;
        }
    }

