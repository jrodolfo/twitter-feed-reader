package com.jrodolfo.twitter;

import com.jrodolfo.twitter.util.TwitterFeed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * This servlet produces a json array with the last tweets
 * Created by Rod Oliveira on 24-May-2016.
 */
public class TweetsServlet extends HttpServlet {

    TwitterFeed twitterFeed;
    final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     *  The init method is designed to be called only once. It is called when the servlet is first created,
     *  and not called again for each user request. So, it is used for one-time initializations, just as
     *  with the init method of applets. From: http://www.tutorialspoint.com/servlets/servlets-life-cycle.htm
     */
    @Override
    public void init() {
        twitterFeed = new TwitterFeed();
    }

    /**
     * This method handles the requests and return the last tweets in json array format
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        LocalTime now = ZonedDateTime.now().toLocalTime().truncatedTo(ChronoUnit.SECONDS);
        logger.info("TweetsServlet started at " + now);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().println(twitterFeed.getTweets());
    }
}
