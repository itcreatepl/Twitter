package controller.servlet;

import model.Tweet;
import services.TweetManagementService;
import services.impl.TweetManagementServiceImpl;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static util.ServletUtils.*;

@WebServlet(name = "MessagesServlet", value = "/messages")
public class MessagesServlet extends HttpServlet {

    TweetManagementService tweetManagementService;

    @Override
    public void init() throws ServletException {
        tweetManagementService = new TweetManagementServiceImpl();
    }

    /***
     * Method doGet() calls doPost() because it is easy way to handle refreshing messages.jsp after adding and removing messages.
     * Those servlets (DeleteMessageServlet and AddMessageServlet) are using POST methods to manipulate database records (Tweet.class).
     * After our logic we using dispatcher to redirect to messages servlet , after that operation first called method in servlet is doPost()
     * because in request header is information about request method. That header is used to call right method from servlet. That header is set by us when we click link (GET)
     * sending form (POST) and on server side when we call those method from other for example calling doPost() in doGet() changing this METHOD header to POST.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentUserLogin = ServletUtils.getUserLoginFromSession(req);
        Set<Tweet> followedTweets = tweetManagementService.getFollowedTweets(currentUserLogin);
        req.setAttribute(FOLLOWED_TWEETS, followedTweets);
        req.getRequestDispatcher("/tweets.jsp").forward(req, resp);
    }
}
