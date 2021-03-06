# Twitter Feed Reader
Simple Java web app that implements a Twitter Feed Reader

## What is this?

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

6) Front end restrictions

	The following 3rd party libraries are allowed:

		Backbone.js
		Underscore.js
		jQuery
		Jasmine (for unit testing)
		
	The rest is my supplied code

7) Back end restrictions

	The server should be runnable as a standalone process.

	Any language can be used to act as the API to Twitter:

	- PHP    + PHP Development Server
	- Java   + embedded Jetty
	- Python + Flask
	- Ruby   + WEBrick
	- etc

## How to run it?

1) Clone the app:

git clone https://github.com/jrodolfo/twitter-feed-reader

2) Edit oauth.consumerKey, oauth.consumerSecret, oauth.accessToken, and oauth.accessTokenSecret keys on file twitter4j.properties. Read this post to learn how to get those values: http://stackoverflow.com/questions/1808855/getting-new-twitter-api-consumer-and-secret-keys

3) Open a command prompt and type:

    mvn package
    java -jar target/dependency/jetty-runner.jar --port 9090 target/*.war

4) Type this URL on your browser:

    http://localhost:9090

Change the port number in case 9090 is already in use on your machine.

## To do list

1) Implement the search functionality

2) Create unit test for backend

3) Create test for frontend using Jarmine

4) Investigate why log level and format is not working properly

5) Investigate why images/site/favicon.ico is not being loaded

Best regards,

Rod Oliveira | Software Developer | jrodolfo.com | Halifax, Canada
