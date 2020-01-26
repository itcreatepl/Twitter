package services;

import model.Tweet;
import model.User;

import java.util.Set;

public interface TweetManagementService {

    void addTweet(String userLogin, String message);

    void updateTweet(Long tweetId, String message);

    void deleteTweet(Long tweetId);

    Set<Tweet> getFollowedTweets(String userLogin);

    Set<Tweet> getFollowedTweetsStream(String userLogin);
}
