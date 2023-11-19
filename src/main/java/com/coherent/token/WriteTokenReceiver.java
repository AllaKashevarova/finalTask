package com.coherent.token;

public class WriteTokenReceiver {
    private String writeScope = "write";

    public WriteTokenReceiver() {
    }

    public String getWriteToken() {
        TokenExtractor tokenExtractor = new TokenExtractor();
        String writeToken = tokenExtractor.getToken(writeScope);

        return writeToken;
    }

}
