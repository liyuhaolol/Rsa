package com.tangyin.cmp.token;

import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("setting.properties"));
        } catch (IOException var1) {
            var1.printStackTrace();
        }

    }

    public Config() {
    }

    public static String getValue(String key) {
        return properties.getProperty(key);
    }

    protected static void updateProperties(String key, String value) {
        properties.setProperty(key, value);
    }
}
