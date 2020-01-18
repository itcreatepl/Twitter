package services.impl;

import dao.TweetDAO;
import dao.UserDAO;
import dao.impl.TweetDAOImpl;
import dao.impl.UserDAOImpl;
import model.Tweet;
import model.User;
import services.TweetManagementService;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TweetManagementServiceImpl implements TweetManagementService {

    private TweetDAO tweetDAO;
    private UserDAO userDAO;

    public TweetManagementServiceImpl() {
        this.tweetDAO = new TweetDAOImpl();
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public void addTweet(String userLogin, String message) {
        User userByLogin = userDAO.getUserByLogin(userLogin);
        tweetDAO.addTweet(userByLogin, message);
    }

    @Override
    public void updateTweet(Long tweetId, String message) {

    }

    @Override
    public void deleteTweet(Long tweetId) {
        tweetDAO.deleteTweet(tweetId);
    }

    @Override
    public Set<Tweet> getFollowedTweets(String userLogin) {
        User user = userDAO.getUserByLogin(userLogin);
        Set<User> follows = user.getFollows();
        Set<Tweet> result = new LinkedHashSet<>();
        for (User u : follows) {
            List<Tweet> allTweetByUser = tweetDAO.getAllTweetByUser(u.getLogin());
            for (Tweet t : allTweetByUser) {
                result.add(t);
            }
        }
        result.addAll(tweetDAO.getAllTweetByUser(userLogin));
        return result;
    }
}
