package com.coherent.token;

public class WriteTokenManager {
    private static WriteTokenManager instance;
    private String writeToken;

    private WriteTokenManager() {
    }

    public static WriteTokenManager getInstance() {
        if (instance == null) {
            instance = new WriteTokenManager();
        }
        return instance;
    }

    public void setWriteToken(String token) {
        this.writeToken = token;

    }

    public String getWriteToken(){
        return writeToken;
    }
}
