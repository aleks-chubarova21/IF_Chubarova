package util;

import java.io.InputStream;
import java.util.Properties;

public class TestProperties {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = TestProperties.class
                .getClassLoader()
                .getResourceAsStream("test.properties")) {

            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось загрузить test.properties", e);
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }
}
