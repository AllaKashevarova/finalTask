package com.coherent.token;

public class ReadTokenManager {
    private static ReadTokenManager instance;
    private static String readTokenValue;

    private ReadTokenManager(String readTokenValue) {
        this.readTokenValue = readTokenValue;
    }


    public static ReadTokenManager getInstance() {
        if (instance == null) {
            ReadTokenReceiver readTokenReceiver = new ReadTokenReceiver();
            readTokenValue = readTokenReceiver.getReadToken();

            instance = new ReadTokenManager(readTokenValue);
        }
        return instance;
    }

    public String getReadToken(){
        return readTokenValue;
    }

}
