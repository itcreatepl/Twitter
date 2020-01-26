package controller.servlet;

import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.ServletUtils.*;

@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = ServletUtils.getUserLoginFromSession(req);
        if (login == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

            for (Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals(USER_LOGIN)) {
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
                if (cookie.getName().equals(USER_PASSWORD)) {
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }

        //req.getSession().removeAttribute(LOGGED_USER);
        req.getSession().invalidate();
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

}
