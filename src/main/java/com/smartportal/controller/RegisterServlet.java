package com.smartportal.controller;

import com.smartportal.dao.UserDAO;
import com.smartportal.model.User;
import com.smartportal.util.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Get form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Create User object
        User user = new User();

        user.setName(name);
        user.setEmail(email);
        String hashedPassword = PasswordUtil.hashPassword(password);
        user.setPasswordHash(hashedPassword);
        user.setRole("USER");

        // Save user
        boolean registered = userDAO.registerUser(user);

        if (registered) {

            response.getWriter().println(
                    "Registration successful!"
            );

        } else {

            response.getWriter().println(
                    "Registration failed!"
            );
        }
    }
}