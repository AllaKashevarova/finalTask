package com.coherent.token;

public class SingletonTokenManager {
    private static SingletonTokenManager instance;
    private static String readTokenValue;
    private static String writeTokenValue;

    private SingletonTokenManager() {
        WriteTokenReceiver writeTokenReceiver = new WriteTokenReceiver();
        ReadTokenReceiver readTokenReceiver = new ReadTokenReceiver();

        readTokenValue = readTokenReceiver.getReadToken();
        writeTokenValue = writeTokenReceiver.getWriteToken();
    }

    public static SingletonTokenManager getInstance() {

        if (instance == null) {
            instance = new SingletonTokenManager();
        }
        return instance;
    }

    public static String getReadToken() {
        return readTokenValue;
    }

    public static String getWriteToken() {
        return writeTokenValue;
    }

}
