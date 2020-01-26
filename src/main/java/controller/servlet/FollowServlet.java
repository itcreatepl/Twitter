package controller.servlet;

import services.UserManagementService;
import services.impl.UserManagementServiceImpl;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.ServletUtils.*;

@WebServlet(name = "FollowServlet", value = "/follow")
public class FollowServlet extends HttpServlet {

    UserManagementService  service;

    @Override
    public void init() throws ServletException {
       this.service = new UserManagementServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String currentUserLogin = ServletUtils.getUserLoginFromSession(req);
        String userLoginToFollow = req.getParameter(USER_LOGIN_TO_FOLLOW);

        service.follow(currentUserLogin,userLoginToFollow);
        req.getRequestDispatcher("users").forward(req,resp);

    }
}
