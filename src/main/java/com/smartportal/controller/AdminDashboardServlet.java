package com.smartportal.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin")
public class AdminDashboardServlet extends HttpServlet {

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


        // Get user role
        String role =
                (String) session.getAttribute("userRole");


        // Check whether the user is an ADMIN
        if (!"ADMIN".equals(role)) {

            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Access Denied"
            );

            return;
        }


        // Get admin information
        String userName =
                (String) session.getAttribute("userName");

        String userEmail =
                (String) session.getAttribute("userEmail");


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
                        Admin Dashboard | Smart Cookie
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

                        }


                        nav a:hover {

                            color:
                                var(--primary);

                        }


                        main {

                            max-width:
                                1100px;

                            margin:
                                0 auto;

                            padding:
                                60px 25px;

                        }


                        .welcome {

                            margin-bottom:
                                35px;

                        }


                        .welcome h2 {

                            font-size:
                                34px;

                            margin-bottom:
                                10px;

                        }


                        .welcome p {

                            color:
                                var(--muted);

                            font-size:
                                16px;

                        }


                        .admin-grid {

                            display:
                                grid;

                            grid-template-columns:
                                repeat(3, 1fr);

                            gap:
                                20px;

                            margin-bottom:
                                30px;

                        }


                        .stat-card {

                            background:
                                var(--card);

                            border:
                                1px solid var(--border);

                            border-radius:
                                16px;

                            padding:
                                25px;

                            box-shadow:
                                0 10px 30px
                                rgba(0, 0, 0, 0.05);

                        }


                        .stat-card .icon {

                            font-size:
                                28px;

                            margin-bottom:
                                12px;

                        }


                        .stat-card h3 {

                            font-size:
                                16px;

                            margin-bottom:
                                8px;

                        }


                        .stat-card p {

                            color:
                                var(--muted);

                            font-size:
                                14px;

                        }


                        .admin-card {

                            background:
                                var(--card);

                            border:
                                1px solid var(--border);

                            border-radius:
                                18px;

                            padding:
                                35px;

                            box-shadow:
                                0 15px 40px
                                rgba(0, 0, 0, 0.06);

                        }


                        .admin-card h3 {

                            font-size:
                                22px;

                            margin-bottom:
                                12px;

                        }


                        .admin-card p {

                            color:
                                var(--muted);

                            margin-bottom:
                                20px;

                        }


                        .admin-badge {

                            display:
                                inline-block;

                            padding:
                                8px 16px;

                            background:
                                rgba(108, 99, 255, 0.12);

                            color:
                                var(--primary);

                            border-radius:
                                20px;

                            font-size:
                                13px;

                            font-weight:
                                800;

                        }


                        @media (max-width: 800px) {

                            header {

                                padding:
                                    20px;

                            }


                            .admin-grid {

                                grid-template-columns:
                                    1fr;

                            }

                        }


                        @media (max-width: 600px) {

                            header {

                                flex-direction:
                                    column;

                                gap:
                                    15px;

                            }


                            nav {

                                flex-wrap:
                                    wrap;

                                justify-content:
                                    center;

                            }


                            main {

                                padding:
                                    35px 15px;

                            }


                            .welcome h2 {

                                font-size:
                                    28px;

                            }

                        }

                    </style>

                </head>


                <body>


                    <header>

                        <div class="logo">

                            🍪 Smart Cookie Admin

                        </div>


                        <nav>

                            <a href="dashboard">
                                User Dashboard
                            </a>


                            <a href="profile">
                                Profile
                            </a>


                            <a href="preferences">
                                Preferences
                            </a>


                            <a href="logout">
                                Logout
                            </a>

                        </nav>

                    </header>


                    <main>


                        <section class="welcome">

                            <h2>
                                Welcome, __ADMIN_NAME__! 👑
                            </h2>

                            <p>
                                Manage and monitor the
                                Smart Cookie Web Portal.
                            </p>

                        </section>


                        <section class="admin-grid">


                            <div class="stat-card">

                                <div class="icon">
                                    👥
                                </div>

                                <h3>
                                    User Management
                                </h3>

                                <p>
                                    Manage registered users.
                                </p>

                            </div>


                            <div class="stat-card">

                                <div class="icon">
                                    🔐
                                </div>

                                <h3>
                                    Security
                                </h3>

                                <p>
                                    Role-based access control.
                                </p>

                            </div>


                            <div class="stat-card">

                                <div class="icon">
                                    🍪
                                </div>

                                <h3>
                                    Portal Settings
                                </h3>

                                <p>
                                    Manage portal preferences.
                                </p>

                            </div>


                        </section>


                        <section class="admin-card">

                            <h3>
                                🔐 Administrator Access
                            </h3>

                            <p>
                                You are authenticated as an
                                administrator.
                            </p>

                            <span class="admin-badge">
                                ADMIN
                            </span>

                        </section>


                    </main>

                </body>

                </html>

                """;


        // Replace admin information
        html = html.replace(
                "__ADMIN_NAME__",
                userName != null
                        ? userName
                        : "Administrator"
        );


        response.getWriter().println(html);
    }
}