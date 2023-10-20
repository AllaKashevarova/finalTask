package com.coherent.token;

public class WriteTokenManager {
    private static WriteTokenManager instance;
    private static String writeTokenValue;

    private WriteTokenManager(String writeTokenValue) {
        this.writeTokenValue = writeTokenValue;
    }

    public static WriteTokenManager getInstance() {
        if (instance == null) {
            WriteTokenReceiver writeTokenReceiver = new WriteTokenReceiver();
            writeTokenValue = writeTokenReceiver.getWriteToken();

            instance = new WriteTokenManager(writeTokenValue);
        }
        return instance;
    }

    public String getWriteToken(){
        return writeTokenValue;
    }
}
