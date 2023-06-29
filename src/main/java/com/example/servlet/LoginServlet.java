package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {
            resp.sendRedirect("/login.jsp");
        } else {
            resp.sendRedirect("/user/hello.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        boolean isCorrectLogin = Users.getInstance().getUsers().contains(login);
        boolean isCorrectPassword = password != null && !password.trim().isEmpty();

        if (isCorrectLogin && isCorrectPassword) {
            req.getSession().setAttribute("user", login);
            resp.sendRedirect("/user/hello.jsp");
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
