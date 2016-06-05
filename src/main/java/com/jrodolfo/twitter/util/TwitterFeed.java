package com.jrodolfo.twitter.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Rod on 24-May-2016.
 */
public class TwitterFeed {

    Properties properties;
    String userName;
    Twitter twitter;
    int numberOfTweets;
    boolean debug;
    final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public TwitterFeed() {
        try {
            properties = new PropertyValues().getProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public String getTweetsInJsonFormat() {
        logger.debug("\nGetting tweets in json format...");
        List<Tweet> listOfTweets = new ArrayList<>();
        try {
            Paging paging;
            paging = new Paging(1, numberOfTweets);
            List<Status> statuses = twitter.getUserTimeline(userName, paging);
            int numberOfTweetsRetrieved = statuses.size();
            logger.debug("Number of tweets retrieved: " + numberOfTweetsRetrieved);

            for (Status status : statuses) {
                long createdAtLong = status.getCreatedAt().getTime();
                String createdAtString = status.getCreatedAt().toString();
                String userName = status.getUser().getName();
                String userScreenName = status.getUser().getScreenName();
                String userProfileImage = status.getUser().getBiggerProfileImageURL();
                String tweetContent = status.getText();
                int retweetCount = status.getRetweetCount();
                Tweet tweet = new Tweet(createdAtLong, createdAtString, userName,
                        userScreenName, userProfileImage, tweetContent, retweetCount);
                listOfTweets.add(tweet);
                logger.debug(tweet.toString());
            }
        } catch (TwitterException twitterException) {
            twitterException.printStackTrace();
            logger.error("Failed to get timeline: " + twitterException.getMessage());
        }

         return getJsonFormatFromArray(listOfTweets);
    }

    private String getJsonFormatFromArray(List<Tweet> list) {
        StringBuilder stringBuilder = new StringBuilder();
        int numberOfElements = list.size();
        if (numberOfElements == 0) return "[]";
        stringBuilder.append("[");
        int index = 1;
        for (Tweet tweet : list) {
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
