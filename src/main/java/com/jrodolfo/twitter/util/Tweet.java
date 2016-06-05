package com.jrodolfo.twitter.util;

/**
 * Just a wrapper class to hold some data from each Tweet
 * Created by Rod Oliveira on 24-May-2016.
 */
public class Tweet {

    private String createdAt;
    private String userName;
    private String userScreenName;
    private String userProfileImageURL;
    private String tweetContent;
    private int retweetCount;

    /**
     *
     * @param createdAt
     * @param userName
     * @param userScreenName
     * @param userProfileImageURL
     * @param tweetContent
     * @param retweetCount
     */
    public Tweet(String createdAt, String userName, String userScreenName, String userProfileImageURL, String tweetContent, int retweetCount) {
        this.createdAt = createdAt;
        this.userName = userName;
        this.userScreenName = userScreenName;
        this.userProfileImageURL = userProfileImageURL;
        this.tweetContent = tweetContent;
        this.retweetCount = retweetCount;
    }

    /**
     *  String used for debug
     * @return
     */
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

    /**
     *   Used to build the json format of the object
     * @return
     */
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
