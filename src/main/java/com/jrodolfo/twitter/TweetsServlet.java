package com.jrodolfo.twitter;

import com.jrodolfo.twitter.util.TwitterFeed;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created by Rod on 24-May-2016.
 */
public class TweetsServlet extends HttpServlet {

    static TwitterFeed twitterFeed;

    @Override
    public void init() {
        twitterFeed = new TwitterFeed();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("\nRunning TweetsServlet...");
        String tweetsInJsonFormat = twitterFeed.getTweetsInJsonFormat();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().println(tweetsInJsonFormat);
    }
}
