package com.jrodolfo.twitter;

import com.jrodolfo.twitter.util.TwitterFeed;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by Rod on 24-May-2016.
 */
public class TweetsServlet extends HttpServlet {

    TwitterFeed twitterFeed;

    @Override
    public void init() {
        twitterFeed = new TwitterFeed();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        LocalTime now = ZonedDateTime.now().toLocalTime().truncatedTo(ChronoUnit.SECONDS);
        System.out.println("TweetsServlet started at " + now);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().println(twitterFeed.getTweetsInJsonFormat());
    }
}
