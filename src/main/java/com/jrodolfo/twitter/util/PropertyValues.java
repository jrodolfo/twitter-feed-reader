package com.jrodolfo.twitter.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

/**
 * Created by Rod on 21-May-2016.
 */
public class PropertyValues {

    final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

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
            logger.debug("oauth.consumerKey=" + properties.getProperty("oauth.consumerKey"));
            logger.debug("oauth.consumerSecret=" + properties.getProperty("oauth.consumerSecret"));
            logger.debug("oauth.accessToken=" + properties.getProperty("oauth.accessToken"));
            logger.debug("oauth.accessTokenSecret=" + properties.getProperty("oauth.accessTokenSecret"));
            logger.debug("twitter.username=" + properties.getProperty("twitter.username"));
            logger.debug("number.of.tweets=" + properties.getProperty("number.of.tweets"));

        } catch (Exception e) {
            logger.debug("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return properties;
    }
}
