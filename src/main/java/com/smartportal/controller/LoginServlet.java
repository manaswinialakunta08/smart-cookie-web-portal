package com.smartportal.controller;

import com.smartportal.dao.UserDAO;
import com.smartportal.model.User;
import com.smartportal.util.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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

        // Get login details from the form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Check whether Remember Me was selected
        boolean rememberMe =
                request.getParameter("rememberMe") != null;

        // Find user in database
        User user = userDAO.findUserByEmail(email);

        // Verify user and password
        if (user != null &&
                PasswordUtil.checkPassword(
                        password,
                        user.getPasswordHash())) {

            // Create session
            HttpSession session = request.getSession();

            // Store user information in session
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getName());
            session.setAttribute("userEmail", user.getEmail());
            session.setAttribute("userRole", user.getRole());

            // Create Remember Me cookie
            if (rememberMe) {

                Cookie emailCookie =
                        new Cookie(
                                "rememberedEmail",
                                user.getEmail()
                        );

                // Cookie lasts for 30 days
                emailCookie.setMaxAge(
                        30 * 24 * 60 * 60
                );

                // Cookie available throughout the application
                emailCookie.setPath(
                        request.getContextPath()
                );

                // Send cookie to browser
                response.addCookie(emailCookie);
            }

            // Redirect to dashboard
            response.sendRedirect("dashboard");

        } else {

            // Login failed
            response.getWriter().println(
                    "Invalid email or password!"
            );
        }
    }
}