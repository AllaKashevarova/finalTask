package org.example;


import com.coherent.HttpClientFactory;
import com.coherent.token.OAuthHttpClientFactory;
import com.coherent.token.ReadTokenReceiver;
import com.coherent.token.SingletonTokenManager;
import com.coherent.token.WriteTokenReceiver;
import org.apache.http.impl.client.CloseableHttpClient;

public class Main {
    public static void main(String[] args) {
//        CloseableHttpClient client = new OAuthHttpClientFactory().createClient();
//        SingletonTokenManager.getInstance();
//
//        ReadTokenReceiver readTokenReceiver = new ReadTokenReceiver();
//        String readToken = readTokenReceiver.getReadToken();
//        System.out.println("Read token: " + readToken);
//
//
//        String singletonRead = SingletonTokenManager.getReadToken();
//        System.out.println("SingletonReadValue: " + singletonRead);
//        System.out.println("SingletonReadValue: " + SingletonTokenManager.getReadToken());
//
//        String singletonWrite = SingletonTokenManager.getWriteToken();
//        System.out.println("SingletonWriteValue: " + singletonWrite);
//
//
//        WriteTokenReceiver writeTokenReceiver = new WriteTokenReceiver();
//        String token = writeTokenReceiver.getWriteToken();
//        System.out.println("Write token: " + token);

        HttpClientFactory httpClientFactory = new HttpClientFactory();
        String testClass = httpClientFactory.createClient("READ").toString();
        System.out.println("Test HttpClientFactory: " + testClass);

    }
}