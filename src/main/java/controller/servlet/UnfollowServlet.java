package controller.servlet;

import model.User;
import services.UserManagementService;
import services.impl.UserManagementServiceImpl;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.ServletUtils.USER_LOGIN_TO_UNFOLLOW;

@WebServlet(name = "UnfollowServlet",value = "/unfollow")
public class UnfollowServlet extends HttpServlet {

    UserManagementService service;

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
        String userLoginToUnFollow = req.getParameter(USER_LOGIN_TO_UNFOLLOW);

        service.stopFollowing(currentUserLogin,userLoginToUnFollow);
        req.getRequestDispatcher("users").forward(req,resp);

    }
}
