package com.smartportal.controller;

import com.smartportal.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/update-profile")
public class UpdateProfileServlet extends HttpServlet {

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

        // Get existing session
        HttpSession session =
                request.getSession(false);

        // Check whether user is logged in
        if (session == null ||
                session.getAttribute("userId") == null) {

            response.sendRedirect("login.html");
            return;
        }


        // Get user ID from session
        int userId =
                (Integer) session.getAttribute("userId");


        // Get updated information from form
        String name =
                request.getParameter("name");

        String email =
                request.getParameter("email");


        // Remove unnecessary spaces
        if (name != null) {
            name = name.trim();
        }

        if (email != null) {
            email = email.trim();
        }


        // Basic validation
        if (name == null ||
                name.isEmpty() ||
                email == null ||
                email.isEmpty()) {

            response.getWriter().println(
                    "Name and email are required."
            );

            return;
        }


        // Update database
        boolean updated =
                userDAO.updateUserProfile(
                        userId,
                        name,
                        email
                );


        if (updated) {

            // Update session information
            session.setAttribute(
                    "userName",
                    name
            );

            session.setAttribute(
                    "userEmail",
                    email
            );


            // Go back to profile
            response.sendRedirect("profile");

        } else {

            response.getWriter().println(
                    "Profile update failed. " +
                    "The email may already be registered."
            );
        }

    }

}