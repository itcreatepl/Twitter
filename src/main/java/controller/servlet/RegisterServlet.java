package controller.servlet;

import controller.error.ValidationError;
import model.User;
import services.UserManagementService;
import services.impl.UserManagementServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static util.Namespace.*;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private UserManagementService service;
    private List<ValidationError> errors;

    @Override
    public void init() throws ServletException {
        service = new UserManagementServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errors = new ArrayList<>();

        String password = req.getParameter(USER_PASSWORD);
        String repeatedPassword = req.getParameter(USER_REPEATED_PASSWORD);
        if (!password.equals(repeatedPassword)) {
            ValidationError error = new ValidationError(PASSWORD_ERROR_HEADER, PASSWORD_ERROR_MESSAGE);
            errors.add(error);
        }

        String login = req.getParameter(USER_LOGIN);
        if(service.isUserExists(login)) {
            ValidationError error = new ValidationError(LOGIN_ERROR_HEADER, LOGIN_ERROR_MESSAGE);
            errors.add(error);
        }

        String email = req.getParameter(USER_EMAIL);
        if(service.isEmailExist(email)) {
            ValidationError error = new ValidationError(EMAIL_ERROR_HEADER, EMAIL_ERROR_MESSAGE);
            errors.add(error);
        }

        if(errors.size() != 0) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }


        String name = req.getParameter(USER_NAME);
        String lastName = req.getParameter(USER_SURNAME);
        User user = new User.Builder()
                .login(login)
                .email(email)
                .password(password)
                .name(name)
                .lastName(lastName)
                .build();

        service.saveUser(user);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
