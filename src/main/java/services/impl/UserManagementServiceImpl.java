package services.impl;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import model.User;
import services.UserManagementService;

import javax.persistence.NoResultException;
import java.util.Set;

public class UserManagementServiceImpl implements UserManagementService {

    private UserDAO userDAO;

    public UserManagementServiceImpl() {
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userDAO.deleteUser(userId);

    }

    @Override
    public boolean isUserExists(String login) {
        try {
            User userByLogin = userDAO.getUserByLogin(login);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public boolean isEmailExist(String email) {
        try {
            User user = userDAO.getUserByEmail(email);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }


    @Override
    public Set<User> getNotFollowedUsers(String login) {
            return userDAO.getNotFollowedUsers(login);
    }

    @Override
    public void follow(String currentUserLogin, String userLoginToFollow) {
        User currentUser = userDAO.getUserByLogin(currentUserLogin);
        User userToFollow = userDAO.getUserByLogin(userLoginToFollow);
        currentUser.getFollows().add(userToFollow);
        saveUser(currentUser);

    }

    @Override
    public void stopFollowing(String currentUserLogin, String userLoginToUnfollow) {
        User currentUser = userDAO.getUserByLogin(currentUserLogin);
        User userToUnFollow = userDAO.getUserByLogin(userLoginToUnfollow);
        currentUser.getFollows().remove(userToUnFollow);
        saveUser(currentUser);
    }

    @Override
    public boolean isUserValid(String login, String password) {
        try {
            return userDAO.getUserByLogin(login).getPassword().equals(password);
        } catch (NoResultException e) {
            return false;
        }
    }
    @Override
    public boolean validationUserLoginAndPassword(String login, String password) {
        User user = userDAO.getUserByLogin(login);
        return user.getPassword().equals(password);
    }
}
