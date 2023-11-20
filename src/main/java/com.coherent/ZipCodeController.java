package com.coherent;

import com.coherent.token.SingletonTokenManager;
import com.google.common.collect.Lists;
import helpers.PropertiesHelper;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;

import java.util.List;

public class ZipCodeController {
    private HttpRequestManager httpRequestManager = new HttpRequestManager();
    private PropertiesHelper propertiesHelper = new PropertiesHelper();
    private String urlPropFile = "url.properties";
    private String path = propertiesHelper.propertiesReader("zip.get.path", urlPropFile);

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

    public HttpGet getZipCodes() {
        final HttpGet httpGetZip = httpRequestManager.setHttpGet(URIManager.buildURI(path));
        httpGetZip.setHeader(getAuthToken("READ"));
        return httpGetZip;
    }

}
