package com.coherent;

import com.coherent.token.SingletonTokenManager;
import com.google.common.collect.Lists;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

import java.util.List;

public class HttpHeaderManager {
    public Header getAuthToken(String scope) {

        SingletonTokenManager.getInstance();
        final String singletonWrite = SingletonTokenManager.getWriteToken();
        final String singletonRead = SingletonTokenManager.getReadToken();
        final Header writeHeader = new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer " + singletonWrite);
        final List<Header> writeHeaders = Lists.newArrayList(writeHeader);
        final Header readHeader = new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer " + singletonRead);
        final List<Header> readHeaders = Lists.newArrayList(readHeader);

        switch (scope) {
            case "WRITE":
                return writeHeader;
            case "READ":
                return readHeader;
            default:
                System.out.println("Provide either 'WRITE' or 'READ' token values");
                return null;
        }
    }
}
