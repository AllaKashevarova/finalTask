package org.example;


import com.coherent.HttpClientFactory;
import com.coherent.token.ReadToken;
import com.coherent.token.ReadTokenManager;
import com.coherent.token.ReadTokenReceiver;
import com.coherent.token.WriteTokenReceiver;
import helpers.JsonParser;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;

public class Main {
    public static void main(String[] args) {

        HttpClientFactory httpClientFactory = new HttpClientFactory();
        CloseableHttpClient client = httpClientFactory.createClient();

        WriteTokenReceiver writeTokenReceiver = new WriteTokenReceiver(client);
        String token = writeTokenReceiver.getWriteToken();
        System.out.println(token);

        ReadTokenReceiver readTokenReceiver = new ReadTokenReceiver(client);
        String readToken = readTokenReceiver.getReadToken();
        System.out.println(readToken);

        JsonParser jsonParser = new JsonParser();
        String accessToken = jsonParser.readFromJson(readToken);
        System.out.println(accessToken);

        ReadToken readToken1 = new ReadToken();
        String parsedReadToken = readToken1.extractReadToken();
        ReadTokenManager readTokenManager = new ReadTokenManager();
        readTokenManager.setReadToken(parsedReadToken);
        String readToken2 = readTokenManager.getReadToken();


    }
}