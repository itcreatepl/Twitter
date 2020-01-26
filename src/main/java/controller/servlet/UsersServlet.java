package controller.servlet;

import model.User;
import services.impl.UserManagementServiceImpl;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static util.ServletUtils.*;

@WebServlet(name = "UsersServlet", value = "/users")
public class UsersServlet extends HttpServlet {

    private UserManagementServiceImpl service;

    @Override
    public void init() throws ServletException {
        service = new UserManagementServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = ServletUtils.getUserLoginFromSession(req);
        Set<User> notFollowedUsers = service.getNotFollowedUsers(login);
        Set<User> followedUsers = service.getFollowedUsers(login);

        req.setAttribute(NOT_FOLLOWED_USERS, notFollowedUsers);
        req.setAttribute(FOLLOWED_USERS, followedUsers);
        req.getRequestDispatcher("/users.jsp").forward(req, resp);

    }


}
