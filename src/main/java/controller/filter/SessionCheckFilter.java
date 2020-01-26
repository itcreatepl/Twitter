package controller.filter;

import hibernate.util.HibernateUtil;
import util.ServletUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "SessionCheckFilter", urlPatterns =
        {"/users", "/messages","/addMessage", "/deleteTweet","/follow","/unfollow"})
public class SessionCheckFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String userLoginFromSession = ServletUtils.getUserLoginFromSession((HttpServletRequest) servletRequest);

        if (userLoginFromSession == null) {
            servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
