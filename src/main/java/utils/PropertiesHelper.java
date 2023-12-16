package utils;

import java.io.*;
import java.util.Properties;

public class PropertiesHelper {

    public String propertiesReader(String prop, String propFileName) {
        Properties properties = new Properties();


        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {

            if (inputStream != null) {
                try {
                    properties.load(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties.getProperty(prop);
    }
}
