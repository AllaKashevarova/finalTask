package helpers;

import java.io.*;
import java.util.Properties;

public class PropertiesHelper {

    public String propertiesReader(String prop, String propFileName) {
        Properties properties = new Properties();
        InputStream inputStream;

        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

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

        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(prop);
    }
}
