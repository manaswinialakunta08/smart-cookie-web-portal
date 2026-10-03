package com.smartportal.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(
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

        // Get user information from session
        int userId =
                (Integer) session.getAttribute("userId");

        String userName =
                (String) session.getAttribute("userName");

        String userEmail =
                (String) session.getAttribute("userEmail");

        String userRole =
                (String) session.getAttribute("userRole");


        // Create Admin Dashboard link
        String adminLink = "";

        if ("ADMIN".equals(userRole)) {

            adminLink = """
                    <a href="admin">
                        Admin Dashboard
                    </a>
                    """;
        }


        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");


        String html = """

                <!DOCTYPE html>

                <html lang="en">

                <head>

                    <meta charset="UTF-8">

                    <meta name="viewport"
                          content="width=device-width, initial-scale=1.0">

                    <title>
                        Dashboard | Smart Cookie
                    </title>


                    <style>

                        :root {

                            --background: #f5f7fb;
                            --card: #ffffff;
                            --text: #25263a;
                            --muted: #77798f;
                            --primary: #6c63ff;
                            --primary-dark: #5148d8;
                            --border: #e6e6ef;

                        }


                        body.dark-theme {

                            --background: #11121c;
                            --card: #1d1e2b;
                            --text: #f5f5f7;
                            --muted: #aaaabd;
                            --primary: #817aff;
                            --primary-dark: #6259e8;
                            --border: #333447;

                        }


                        * {

                            margin: 0;
                            padding: 0;
                            box-sizing: border-box;

                        }


                        body {

                            font-family:
                                "Inter",
                                "Segoe UI",
                                Arial,
                                sans-serif;

                            background:
                                var(--background);

                            color:
                                var(--text);

                            min-height:
                                100vh;

                            transition:
                                background 0.3s ease,
                                color 0.3s ease;

                        }


                        header {

                            display: flex;

                            justify-content:
                                space-between;

                            align-items:
                                center;

                            padding:
                                20px 50px;

                            background:
                                var(--card);

                            border-bottom:
                                1px solid var(--border);

                        }


                        .logo {

                            font-size:
                                22px;

                            font-weight:
                                800;

                        }


                        nav {

                            display:
                                flex;

                            align-items:
                                center;

                            gap:
                                22px;

                        }


                        nav a {

                            text-decoration:
                                none;

                            color:
                                var(--muted);

                            font-size:
                                14px;

                            font-weight:
                                600;

                            transition:
                                color 0.2s ease;

                        }


                        nav a:hover {

                            color:
                                var(--primary);

                        }


                        #themeToggle {

                            border:
                                none;

                            background:
                                var(--primary);

                            color:
                                white;

                            padding:
                                10px 16px;

                            border-radius:
                                8px;

                            cursor:
                                pointer;

                            font-size:
                                13px;

                            font-weight:
                                700;

                        }


                        #themeToggle:hover {

                            background:
                                var(--primary-dark);

                        }


                        main {

                            min-height:
                                calc(100vh - 80px);

                            display:
                                flex;

                            justify-content:
                                center;

                            align-items:
                                flex-start;

                            padding:
                                65px 20px;

                        }


                        .dashboard-card {

                            width:
                                100%;

                            max-width:
                                920px;

                            background:
                                var(--card);

                            padding:
                                45px;

                            border-radius:
                                20px;

                            border:
                                1px solid var(--border);

                            box-shadow:
                                0 18px 45px
                                rgba(0, 0, 0, 0.08);

                        }


                        .welcome-section h2 {

                            font-size:
                                34px;

                            margin-bottom:
                                12px;

                        }


                        .welcome-section p {

                            color:
                                var(--muted);

                            font-size:
                                16px;

                            margin-bottom:
                                35px;

                        }


                        .user-info {

                            background:
                                var(--background);

                            border:
                                1px solid var(--border);

                            border-radius:
                                15px;

                            padding:
                                28px;

                        }


                        .user-info h3 {

                            font-size:
                                20px;

                            margin-bottom:
                                20px;

                        }


                        .info-row {

                            display:
                                flex;

                            justify-content:
                                space-between;

                            align-items:
                                center;

                            padding:
                                16px 0;

                            border-bottom:
                                1px solid var(--border);

                        }


                        .info-row:last-child {

                            border-bottom:
                                none;

                        }


                        .info-label {

                            font-weight:
                                700;

                        }


                        .info-value {

                            color:
                                var(--muted);

                        }


                        .role-badge {

                            display:
                                inline-block;

                            padding:
                                6px 13px;

                            background:
                                rgba(108, 99, 255, 0.12);

                            color:
                                var(--primary);

                            border-radius:
                                20px;

                            font-size:
                                12px;

                            font-weight:
                                800;

                        }


                        @media (max-width: 800px) {

                            header {

                                padding:
                                    20px;

                            }


                            nav {

                                gap:
                                    12px;

                            }


                            .dashboard-card {

                                padding:
                                    30px;

                            }

                        }


                        @media (max-width: 650px) {

                            header {

                                flex-direction:
                                    column;

                                gap:
                                    18px;

                            }


                            nav {

                                flex-wrap:
                                    wrap;

                                justify-content:
                                    center;

                            }


                            main {

                                padding:
                                    30px 15px;

                            }


                            .dashboard-card {

                                padding:
                                    25px;

                            }


                            .welcome-section h2 {

                                font-size:
                                    27px;

                            }


                            .info-row {

                                flex-direction:
                                    column;

                                align-items:
                                    flex-start;

                                gap:
                                    6px;

                            }

                        }

                    </style>

                </head>


                <body>

                    <header>

                        <div class="logo">

                            🍪 Smart Cookie Web Portal

                        </div>


                        <nav>

                            <a href="dashboard">
                                Dashboard
                            </a>


                            <a href="profile">
                                Profile
                            </a>


                            <a href="preferences">
                                Preferences
                            </a>


                            __ADMIN_LINK__


                            <button
                                type="button"
                                id="themeToggle">

                                🌙 Dark Mode

                            </button>


                            <a href="logout">
                                Logout
                            </a>

                        </nav>

                    </header>


                    <main>

                        <section class="dashboard-card">


                            <div class="welcome-section">

                                <h2>
                                    Welcome, __USER_NAME__! 👋
                                </h2>

                                <p>
                                    You have successfully logged
                                    in to the Smart Cookie Web Portal.
                                </p>

                            </div>


                            <div class="user-info">

                                <h3>
                                    User Information
                                </h3>


                                <div class="info-row">

                                    <span class="info-label">
                                        User ID:
                                    </span>

                                    <span class="info-value">
                                        __USER_ID__
                                    </span>

                                </div>


                                <div class="info-row">

                                    <span class="info-label">
                                        Name:
                                    </span>

                                    <span class="info-value">
                                        __USER_NAME__
                                    </span>

                                </div>


                                <div class="info-row">

                                    <span class="info-label">
                                        Email:
                                    </span>

                                    <span class="info-value">
                                        __USER_EMAIL__
                                    </span>

                                </div>


                                <div class="info-row">

                                    <span class="info-label">
                                        Role:
                                    </span>

                                    <span class="role-badge">
                                        __USER_ROLE__
                                    </span>

                                </div>

                            </div>


                        </section>

                    </main>


                    <script>

                        // Read theme cookie
                        function getThemeCookie() {

                            const cookies =
                                document.cookie.split(";");

                            for (let cookie of cookies) {

                                cookie = cookie.trim();

                                if (
                                    cookie.startsWith("theme=")
                                ) {

                                    return cookie.substring(6);

                                }

                            }

                            return "light";
                        }


                        // Apply theme
                        function applyTheme() {

                            const theme =
                                getThemeCookie();

                            const button =
                                document.getElementById(
                                    "themeToggle"
                                );


                            if (theme === "dark") {

                                document.body.classList.add(
                                    "dark-theme"
                                );

                                button.textContent =
                                    "☀️ Light Mode";

                            } else {

                                document.body.classList.remove(
                                    "dark-theme"
                                );

                                button.textContent =
                                    "🌙 Dark Mode";

                            }

                        }


                        // Toggle theme
                        document
                            .getElementById("themeToggle")
                            .addEventListener(
                                "click",
                                function () {

                                    let currentTheme =
                                        getThemeCookie();

                                    let newTheme =
                                        currentTheme === "dark"
                                            ? "light"
                                            : "dark";


                                    document.cookie =
                                        "theme=" +
                                        newTheme +
                                        "; max-age=" +
                                        (30 * 24 * 60 * 60) +
                                        "; path=/";


                                    applyTheme();

                                }
                            );


                        // Apply theme when page loads
                        applyTheme();

                    </script>

                </body>

                </html>

                """;


        // Replace user information
        html = html.replace(
                "__USER_ID__",
                String.valueOf(userId)
        );

        html = html.replace(
                "__USER_NAME__",
                userName != null
                        ? userName
                        : ""
        );

        html = html.replace(
                "__USER_EMAIL__",
                userEmail != null
                        ? userEmail
                        : ""
        );

        html = html.replace(
                "__USER_ROLE__",
                userRole != null
                        ? userRole
                        : "USER"
        );


        // Add or remove Admin Dashboard link
        html = html.replace(
                "__ADMIN_LINK__",
                adminLink
        );


        response.getWriter().println(html);
    }
}