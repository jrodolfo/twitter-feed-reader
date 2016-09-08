package com.jrodolfo.twitter.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class relies on twitter4j library to get the Tweets
 * Created by Rod Oliveira on 24-May-2016.
 */
public class TwitterFeed {

    Properties properties;
    String userName;
    Twitter twitter;
    int numberOfTweets;
    boolean debug;
    final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     *  TwitterFeed constructor uses values from file twitter4j.properties to create the object twitter
     */
    public TwitterFeed() {
        properties = PropertyValues.getProperties();
        debug = new Boolean(properties.getProperty("debug"));
        userName = properties.getProperty("twitter.username");
        numberOfTweets = Integer.parseInt(properties.getProperty("number.of.tweets"));
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(debug)
                .setOAuthConsumerKey(properties.getProperty("oauth.consumerKey"))
                .setOAuthConsumerSecret(properties.getProperty("oauth.consumerSecret"))
                .setOAuthAccessToken(properties.getProperty("oauth.accessToken"))
                .setOAuthAccessTokenSecret(properties.getProperty("oauth.accessTokenSecret"))
                .setJSONStoreEnabled(true);
        twitter = new TwitterFactory(cb.build()).getInstance();
    }

    /**
     * Make the request to Twitter to get the last tweets from the user
     * @return string with the json array format of the last 10 tweets
     */
    public String getTweets() {
        logger.debug("\nGetting tweets in json format...");
        List<Tweet> listOfTweets = new ArrayList<>();
        try {
            Paging paging;
            paging = new Paging(1, numberOfTweets);
            List<Status> statuses = twitter.getUserTimeline(userName, paging);
            int numberOfTweetsRetrieved = statuses.size();
            if (debug) logger.info("Number of tweets retrieved: " + numberOfTweetsRetrieved);
            for (Status status : statuses) {
                Tweet tweet = new Tweet(status.getCreatedAt().toString(),
                                        status.getUser().getName(),
                                        status.getUser().getScreenName(),
                                        status.getUser().getBiggerProfileImageURL(),
                                        status.getText(),
                                        status.getRetweetCount());
                listOfTweets.add(tweet);
                if (debug) logger.info(tweet.toString());
            }
        } catch (TwitterException twitterException) {
            twitterException.printStackTrace();
            logger.error("Failed to get timeline: " + twitterException.getMessage());
        }
         return getJsonFromList(listOfTweets);
    }

    /**
     * Read a listOfTweets and produce a json array format of it
     * @param listOfTweets
     * @return
     */
    private String getJsonFromList(List<Tweet> listOfTweets) {
        StringBuilder stringBuilder = new StringBuilder();
        int numberOfElements = listOfTweets.size();
        if (numberOfElements == 0) return "[]";
        stringBuilder.append("[");
        int index = 1;
        for (Tweet tweet : listOfTweets) {
            stringBuilder.append(tweet.toJson());
            if (index < numberOfElements) {
                stringBuilder.append(",");
            }
            index++;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
