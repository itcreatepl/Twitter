package util;

import javax.servlet.http.HttpServletRequest;

public class ServletUtils {
    public static final String USER_LOGIN = "login";
    public static final String USER_NAME = "name";
    public static final String USER_SURNAME = "lastName";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_REPEATED_PASSWORD = "repeatedPassword";

    public static final String PASSWORD_ERROR_HEADER = "INVALID PASSWORD";
    public static final String PASSWORD_ERROR_MESSAGE = "Passwords not be equals";
    public static final String LOGIN_ERROR_HEADER = "INVALID LOGIN";
    public static final String LOGIN_ERROR_MESSAGE = "Typed login is already in use";
    public static final String EMAIL_ERROR_HEADER = "INVALID EMAIL";
    public static final String EMAIL_ERROR_MESSAGE = "Typed mail is already in use";


    public static final String LOGIN_OR_PASSWORD_ERROR_HEADER = "Error";
    public static final String LOGIN_OR_PASSWORD_ERROR_MESSAGE = "Invalid login or password";

    public static final String LOGGED_USER = "loggedUser";

    public static final String REMEMBER = "remember";
    public static final String CHECKBOX_CHECKED = "on";

    public static final String FOLLOWED_USERS = "followedUsers";
    public static final String NOT_FOLLOWED_USERS = "notFollowedUsers";
    public static final String USER_LOGIN_TO_FOLLOW = "userLoginToFollow";
    public static final String USER_LOGIN_TO_UNFOLLOW = "userLoginToUnfollow";


    public static String getUserLoginFromSession(HttpServletRequest req) {
        return (String) req.getSession().getAttribute(LOGGED_USER);
    }


}