package controller.cookie;

import javax.servlet.http.HttpServletRequest;

import static util.Namespace.USER_LOGIN;

public class Login {
    public static String getUserLoginFromSession(HttpServletRequest req) {
        return (String) req.getSession().getAttribute(USER_LOGIN);
    }
}
