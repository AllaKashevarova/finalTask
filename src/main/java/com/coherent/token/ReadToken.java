package com.coherent.token;

import com.coherent.HttpClientFactory;
import helpers.JsonParser;
import org.apache.http.impl.client.CloseableHttpClient;

public class ReadToken {

    public String extractReadToken(){
        HttpClientFactory httpClientFactory = new HttpClientFactory();
        CloseableHttpClient client = httpClientFactory.createClient();
        ReadTokenReceiver readTokenReceiver = new ReadTokenReceiver(client);

        String unparsedReadToken = readTokenReceiver.getReadToken();
        JsonParser jsonParser = new JsonParser();
        String readToken = jsonParser.readFromJson(unparsedReadToken);

        return readToken;
    }
}
