package com.coherent.token;

public class SingletonTokenManager {
    private static SingletonTokenManager instance;
    private static String readTokenValue;
    private static String writeTokenValue;

    private SingletonTokenManager() {
        WriteTokenReceiver writeTokenReceiver = new WriteTokenReceiver();
        ReadTokenReceiver readTokenReceiver = new ReadTokenReceiver();

        this.readTokenValue = readTokenReceiver.getReadToken();
        this.writeTokenValue = writeTokenReceiver.getWriteToken();
    }

    public static SingletonTokenManager getInstance(String tokenType) {

        if (instance == null) {
            instance = new SingletonTokenManager();
        }
        return instance;
    }


    public String getReadToken() {
        return readTokenValue;
    }

    public String getWriteTokenValue() {
        return writeTokenValue;
    }

}
