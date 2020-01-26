package controller.servlet;

import services.TweetManagementService;
import services.impl.TweetManagementServiceImpl;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.ServletUtils.*;

@WebServlet(name = "AddMessagesServlet",value = "/addMessage")
public class AddMessagesServlet extends HttpServlet {

    TweetManagementService tweetManagementService;


    @Override
    public void init() throws ServletException {
        tweetManagementService = new TweetManagementServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentUserLogin = ServletUtils.getUserLoginFromSession(req);
        String tweetMessage = req.getParameter(TWEET_MESSAGE);
        if(tweetMessage == null || tweetMessage.isEmpty()){
            req.getRequestDispatcher("/tweets.jsp").forward(req, resp);
            return;
        }

        tweetManagementService.addTweet(currentUserLogin,tweetMessage);
        req.getRequestDispatcher("messages").forward(req,resp);
    }

}
