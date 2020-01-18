package dao;

import model.User;

import java.util.Set;

public interface UserDAO {

    void saveUser(User user);

    void deleteUser(Long userId);

    User getUserByLogin(String login);

    User getUserByEmail(String email);

    User getUserById(Long userId);

    boolean isUserExists(String login);

    Set<User> getFollows(String login);

    Set<User> getFollowers(String login);

    void follow(String currentUserLogin, String userLoginToFollow);

    void stopFollowing(String currentUserLogin, String userLoginToUnfollow);

    Set<User> getNotFollowedUsers(String login);

    boolean isUserValid(String login, String password);


}
