package controller.servlet;

import services.TweetManagementService;
import services.impl.TweetManagementServiceImpl;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteMessagesServlet", value = "/deleteTweet")
public class DeleteMessagesServlet extends HttpServlet {

    TweetManagementService tweetManagementService;

    @Override
    public void init() throws ServletException {
        this.tweetManagementService = new TweetManagementServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentUserLogin = ServletUtils.getUserLoginFromSession(req);
        String tweetIdString = req.getParameter("tweetId");

        if(tweetIdString == null || tweetIdString.isEmpty()){
            req.getRequestDispatcher("/tweets.jsp").forward(req, resp);
            return;
        }

        Long tweetId = Long.parseLong(tweetIdString);
        tweetManagementService.deleteTweet(tweetId);
        req.getRequestDispatcher("messages").forward(req,resp);

    }


}
