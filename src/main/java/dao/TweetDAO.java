package dao;

import model.Tweet;
import model.User;

import java.util.List;

public interface TweetDAO {

    void addTweet(User author, String message);

    void updateTweet(Long tweetId, String message);

    void deleteTweet(Long tweetId);

    List<Tweet> getAllTweetByUser(String login);
}
