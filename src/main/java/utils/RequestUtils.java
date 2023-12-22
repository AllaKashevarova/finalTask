package utils;

import java.net.URI;
import java.net.URISyntaxException;

public class RequestUtils {
    private static PropertiesHelper propertiesHelper = new PropertiesHelper();
    private static String urlPropFile = "url.properties";
    private static String url = propertiesHelper.propertiesReader("url", urlPropFile);

    public static URI buildURI(String path) {
        final String URL = "%s%s";
        String val = String.format(URL, url, propertiesHelper.propertiesReader(path, urlPropFile));

        URI uri = null;
        try {
            uri = new URI(val);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return uri;
    }
}
