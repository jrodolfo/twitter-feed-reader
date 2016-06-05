# Twitter Feed Reader
Simple Java web app that implements a Twitter Feed Reader

## A. How to run it?

### A.1) Clone the app:

git clone https://github.com/jrodolfo/twitter-feed-reader

### A.2) Edit oauth.consumerKey, oauth.consumerSecret, oauth.accessToken, and oauth.accessTokenSecret keys on file twitter4j.properties (Read this post to learn how to get those values: http://stackoverflow.com/questions/1808855/getting-new-twitter-api-consumer-and-secret-keys)

### A.3) Open a command prompt and type:

    mvn package
    java -jar target/dependency/jetty-runner.jar target/*.war

### A.4) Type this URL on your browser:

    http://localhost:8080

## B. What is this?

This is a simple Java web app that implements a Twitter feed reader based on the following features:

1) It shows the last 10 tweets from some Twitter user timeline (configurable on twitter4j.properties)

2) Each minute this list of tweets is automatically update to show only the 10 most recent tweets.

3) There should also be an input field where the user can type to filter the current list of tweets 
by whether the input string is present anywhere in the content of a tweet. ==>> This will be implemented soon.

4) The following details is displayed in a pleasing format for each tweet:

	­ user name
	­ user screen name (@screen_name)
	­ user profile image
	­ tweet content
	­ how many times the message was retweeted

5) The app implements a simple server to handle authentication and interactions with Twitter’s API.

6) Front end Restrictions

	The following 3rd party libraries are allowed:

		Backbone.js
		Underscore.js
		jQuery
		Jasmine (for unit testing)
		
	The rest is my supplied code

7) Back end Restrictions

	The server should be runnable as a standalone process.

	Any language can be used to act as the API to Twitter:

	- PHP    + PHP Development Server
	- Java   + embedded Jetty
	- Python + Flask
	- Ruby   + WEBrick
	- etc

Best regards,

Rod Oliveira | Software Developer | jrodolfo dot com | Halifax, Canada
