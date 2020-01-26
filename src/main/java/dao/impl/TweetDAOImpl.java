package dao.impl;

import dao.AbstractDao;
import dao.TweetDAO;
import model.Tweet;
import model.User;

import javax.persistence.TypedQuery;
import java.util.List;

public class TweetDAOImpl extends AbstractDao implements TweetDAO {


    @Override
    public void addTweet(User author, String message) {
        Tweet tweet = new Tweet(author, message);
        hibernateUtil.save(tweet);
    }

    @Override
    public void deleteTweet(Long tweetId) {
        hibernateUtil.delete(Tweet.class, tweetId);

    }

    @Override
    public void updateTweet(Long tweetId, String message) {
        Tweet tweet = getTweet(tweetId);
        tweet.setMessage(message);
        hibernateUtil.save(tweet);
    }

    @Override
    public List<Tweet> getAllTweetByUser(String login) {

        TypedQuery<Tweet> query = entityManager
                .createQuery("select t from Tweet t where t.author.login = :login order by t.publishedAt DESC", Tweet.class);
        return query.setParameter("login", login).getResultList();

    }

    private Tweet getTweet(Long id) {
        return entityManager.find(Tweet.class, id);
    }

}
