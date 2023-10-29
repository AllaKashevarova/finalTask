package org.example;


import com.coherent.HttpClientFactory;
import com.coherent.token.ReadTokenReceiver;
import com.coherent.token.WriteTokenReceiver;
import org.apache.http.impl.client.CloseableHttpClient;

public class Main {
    public static void main(String[] args) {
        CloseableHttpClient client = new HttpClientFactory().createClient();

        ReadTokenReceiver readTokenReceiver = new ReadTokenReceiver();
        String readToken = readTokenReceiver.getReadToken();
        System.out.println("Read token: " + readToken);


        WriteTokenReceiver writeTokenReceiver = new WriteTokenReceiver();
        String token = writeTokenReceiver.getWriteToken();
        System.out.println("Write token: " + token);

    }
}