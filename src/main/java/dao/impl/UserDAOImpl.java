package dao.impl;

import dao.AbstractDao;
import dao.UserDAO;
import model.User;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDAOImpl extends AbstractDao implements UserDAO {
    @Override
    public void saveUser(User user) {
        hibernateUtil.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        hibernateUtil.delete(User.class, userId);
    }

    @Override
    public User getUserByLogin(String login) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.login = :login", User.class);
        return query.setParameter("login", login).getSingleResult();
    }

    @Override
    public User getUserByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.email = :email", User.class);
        return query.setParameter("email", email).getSingleResult();
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public boolean isUserExists(String login) {
        try {
            User userByLogin = getUserByLogin(login);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public Set<User> getFollows(String login) {
        return getUserByLogin(login).getFollows();
    }

    @Override
    public Set<User> getFollowers(String login) {
        return getUserByLogin(login).getFollowers();
    }

    @Override
    public void follow(String currentUserLogin, String userLoginToFollow) {
        User currentUser = getUserByLogin(currentUserLogin);
        User userToFollow = getUserByLogin(userLoginToFollow);
        currentUser.getFollows().add(userToFollow);
        saveUser(currentUser);

    }

    @Override
    public void stopFollowing(String currentUserLogin, String userLoginToUnfollow) {
        User currentUser = getUserByLogin(currentUserLogin);
        User userToUnFollow = getUserByLogin(userLoginToUnfollow);
        currentUser.getFollows().remove(userToUnFollow);
        saveUser(currentUser);
    }

    @Override
    public Set<User> getNotFollowedUsers(String login) {

        Query query = entityManager.createQuery("select  u from User u where u.login !=:login");
        query.setParameter("login", login);
        List<User> users = query.getResultList();
        Set<User> followedUsers = getFollowers(login);
        users.removeAll(followedUsers);
        return new HashSet<>(users);

    }

    @Override
    public boolean isUserValid(String login, String password) {
        try {
            return getUserByLogin(login).getPassword().equals(password);
        } catch (NoResultException e) {
            return false;
        }
    }
}
