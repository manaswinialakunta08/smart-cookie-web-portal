package com.smartportal.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/save-preferences")
public class SavePreferencesServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Check whether the user is logged in
        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("userId") == null) {

            response.sendRedirect("login.html");
            return;
        }

        // Get selected theme
        String theme =
                request.getParameter("theme");

        // Get selected language
        String language =
                request.getParameter("language");


        // Validate theme
        if (!"light".equals(theme) &&
                !"dark".equals(theme)) {

            theme = "light";
        }


        // Validate language
        if (!"en".equals(language) &&
                !"te".equals(language) &&
                !"hi".equals(language)) {

            language = "en";
        }


        // Create theme cookie
        Cookie themeCookie =
                new Cookie(
                        "theme",
                        theme
                );

        // Theme cookie lasts for 30 days
        themeCookie.setMaxAge(
                30 * 24 * 60 * 60
        );

        // Make cookie available throughout application
        themeCookie.setPath(
                request.getContextPath()
        );


        // Create language cookie
        Cookie languageCookie =
                new Cookie(
                        "language",
                        language
                );

        // Language cookie lasts for 30 days
        languageCookie.setMaxAge(
                30 * 24 * 60 * 60
        );

        // Make cookie available throughout application
        languageCookie.setPath(
                request.getContextPath()
        );


        // Send cookies to browser
        response.addCookie(themeCookie);

        response.addCookie(languageCookie);


        // Return to preferences page
        response.sendRedirect("preferences");
    }
}