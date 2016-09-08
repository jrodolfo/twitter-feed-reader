package com.jrodolfo.twitter.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

/**
 * This class deals with the properties file by reading it and creating an object Properties
 * Created by Rod Oliveira on 24-May-2016.
 */
public class PropertyValues {

    private static Properties properties;
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    static {
        properties = new Properties();
        InputStream inputStream = null;
        try {
            final String propFileName = "twitter4j.properties";
            inputStream = MethodHandles.lookup().lookupClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file '" + propFileName + "' was not found in the classpath.");
            }

            // get the property value and print it out
            logger.debug("oauth.consumerKey=" + properties.getProperty("oauth.consumerKey"));
            logger.debug("oauth.consumerSecret=" + properties.getProperty("oauth.consumerSecret"));
            logger.debug("oauth.accessToken=" + properties.getProperty("oauth.accessToken"));
            logger.debug("oauth.accessTokenSecret=" + properties.getProperty("oauth.accessTokenSecret"));
            logger.debug("twitter.username=" + properties.getProperty("twitter.username"));
            logger.debug("number.of.tweets=" + properties.getProperty("number.of.tweets"));

        } catch (Exception e) {
            logger.error("Exception: " + e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
