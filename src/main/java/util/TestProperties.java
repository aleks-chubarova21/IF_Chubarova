package util;

import java.util.Properties;


public class TestProperties {
    private static final Properties props = new Properties();

    static {
        try {
            props.load(TestProperties.class.getClassLoader()
                    .getResourceAsStream("test.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки test.properties", e);
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }
}
