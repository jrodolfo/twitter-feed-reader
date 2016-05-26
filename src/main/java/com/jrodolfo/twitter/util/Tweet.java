package com.jrodolfo.twitter.util;

/**
 * Created by Rod on 25-May-2016.
 */
public class Tweet {

    private Long createdAtLong;
    private String createdAtString;
    private String userName;
    private String userScreenName;
    private String userProfileImage;
    private String tweetContent;
    private int retweetCount;

    public Tweet(Long createdAtLong, String createdAtString, String userName, String userScreenName, String userProfileImage, String tweetContent, int retweetCount) {
        this.createdAtLong = createdAtLong;
        this.createdAtString = createdAtString;
        this.userName = userName;
        this.userScreenName = userScreenName;
        this.userProfileImage = userProfileImage;
        this.tweetContent = tweetContent;
        this.retweetCount = retweetCount;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "createdAtLong=" + createdAtLong +
                ", createdAtString='" + createdAtString + '\'' +
                ", userName='" + userName + '\'' +
                ", userScreenName='" + userScreenName + '\'' +
                ", userProfileImage='" + userProfileImage + '\'' +
                ", tweetContent='" + tweetContent + '\'' +
                ", retweetCount=" + retweetCount +
                '}';
    }

    public String toJson() {
        return "{" +
                "\"createdAtLong\": \"" + createdAtLong + "\"," +
                "\"createdAtString\": \"" + createdAtString + "\"," +
                "\"userName\": \"" + userName + "\"," +
                "\"userScreenName\": \"" + userScreenName + "\"," +
                "\"userProfileImage\": \"" + userProfileImage + "\"," +
                "\"tweetContent\": \"" + tweetContent + "\"," +
                "\"retweetCount\": \"" + retweetCount + "\"" +
                '}';
    }

}
