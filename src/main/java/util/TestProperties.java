package util;

import java.io.InputStream;
import java.util.Properties;

public class TestProperties {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = TestProperties.class.getClassLoader()
                .getResourceAsStream("test.properties")) {
            if (input == null) {
                throw new RuntimeException("Файл test.properties не найден в classpath");
            }
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки test.properties", e);
        }
    }

    public static String getProperty(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Ключ '" + key + "' не найден в test.properties");
        }
        return value;
    }
}
