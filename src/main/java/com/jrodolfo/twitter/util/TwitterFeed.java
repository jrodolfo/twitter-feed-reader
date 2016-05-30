package com.jrodolfo.twitter.util;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    TweetCollection tweetCollection = new TweetCollection();
    Map<Long, Tweet> map = tweetCollection.getMap();

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
        try {
            List<Status> statuses = twitter.getUserTimeline(userName);
            if (debug) System.out.println("\nGetting tweets in json format...");
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
                this.map.put(createdAtLong, tweet);
                if (debug) System.out.println(tweet);
            }
        } catch (TwitterException twitterException) {
            twitterException.printStackTrace();
            System.out.println("Failed to get timeline: " + twitterException.getMessage());
        }
        Map<Long, Tweet> subMap = tweetCollection.getLastElementsFromMap(numberOfTweets);
        List<Tweet> list = tweetCollection.getListFromMap(subMap);
        List<Tweet> listReverse = tweetCollection.reverseList((ArrayList<Tweet>) list);
        return tweetCollection.toJson((ArrayList<Tweet>) listReverse);
    }
}
