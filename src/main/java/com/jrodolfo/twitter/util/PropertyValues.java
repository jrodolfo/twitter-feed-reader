package com.jrodolfo.twitter.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Rod on 21-May-2016.
 */
public class PropertyValues {

    public Properties getProperties() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            final String propFileName = "twitter4j.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file '" + propFileName + "' was not found in the classpath.");
            }

            // get the property value and print it out
            Boolean debug = new Boolean(properties.getProperty("debug"));
            System.out.println("debug=" + debug);
            if (debug) {
                System.out.println("oauth.consumerKey=" +       properties.getProperty("oauth.consumerKey"));
                System.out.println("oauth.consumerSecret=" +    properties.getProperty("oauth.consumerSecret"));
                System.out.println("oauth.accessToken=" +       properties.getProperty("oauth.accessToken"));
                System.out.println("oauth.accessTokenSecret=" + properties.getProperty("oauth.accessTokenSecret"));
                System.out.println("twitter.username=" +        properties.getProperty("twitter.username"));
                System.out.println("number.of.tweets=" +        properties.getProperty("number.of.tweets"));
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return properties;
    }
}
