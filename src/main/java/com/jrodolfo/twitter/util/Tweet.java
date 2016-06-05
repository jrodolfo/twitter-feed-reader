package com.jrodolfo.twitter.util;

/**
 * Created by Rod on 25-May-2016.
 */
public class Tweet {

    private String createdAt;
    private String userName;
    private String userScreenName;
    private String userProfileImageURL;
    private String tweetContent;
    private int retweetCount;

    public Tweet(String createdAt, String userName, String userScreenName, String userProfileImageURL, String tweetContent, int retweetCount) {
        this.createdAt = createdAt;
        this.userName = userName;
        this.userScreenName = userScreenName;
        this.userProfileImageURL = userProfileImageURL;
        this.tweetContent = tweetContent;
        this.retweetCount = retweetCount;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "createdAt='" + createdAt + '\'' +
                ", userName='" + userName + '\'' +
                ", userScreenName='" + userScreenName + '\'' +
                ", userProfileImageURL='" + userProfileImageURL + '\'' +
                ", tweetContent='" + tweetContent + '\'' +
                ", retweetCount=" + retweetCount +
                '}';
    }

    public String toJson() {
        return "{" +
                "\"createdAt\": \"" + createdAt + "\"," +
                "\"userName\": \"" + userName + "\"," +
                "\"userScreenName\": \"" + userScreenName + "\"," +
                "\"userProfileImageURL\": \"" + userProfileImageURL + "\"," +
                "\"tweetContent\": \"" + tweetContent + "\"," +
                "\"retweetCount\": \"" + retweetCount + "\"" +
                '}';
    }

}
