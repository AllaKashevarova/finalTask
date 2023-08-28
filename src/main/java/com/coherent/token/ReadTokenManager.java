package com.coherent.token;

public class ReadTokenManager {
    private static ReadTokenManager instance;
    private String readToken;

    private ReadTokenManager() {
    }

    public static ReadTokenManager getInstance() {
        if (instance == null) {
            instance = new ReadTokenManager();
        }
        return instance;
    }

    public void setReadToken(String token) {
        this.readToken = token;
    }

    public String getReadToken(){
        return readToken;
    }

}
