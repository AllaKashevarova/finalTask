package com.coherent.token;

public class ReadTokenReceiver {
    private String readScope = "read";

    public ReadTokenReceiver() {
    }

    public String getReadToken() {
        TokenExtractor tokenExtractor = new TokenExtractor();
        String readToken = tokenExtractor.getToken(readScope);

        return readToken;
    }
}
