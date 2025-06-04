package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream input = new FileInputStream("src/test/resources/config.properties");
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read config file: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
