package com.smartportal.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Get existing session
        HttpSession session =
                request.getSession(false);

        // Check whether the user is logged in
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

        // Response settings
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Profile page
        String html = """

                <!DOCTYPE html>

                <html lang="en">

                <head>

                    <meta charset="UTF-8">

                    <meta name="viewport"
                          content="width=device-width, initial-scale=1.0">

                    <title>Profile | Smart Cookie</title>


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
                                white;

                            border-bottom:
                                1px solid
                                var(--border);

                        }


                        header h1 {

                            font-size:
                                22px;

                        }


                        nav {

                            display: flex;

                            gap:
                                20px;

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

                            min-height:
                                calc(100vh - 80px);

                            display: flex;

                            justify-content:
                                center;

                            align-items:
                                flex-start;

                            padding:
                                60px 20px;

                        }


                        .profile-card {

                            width:
                                100%;

                            max-width:
                                700px;

                            background:
                                var(--card);

                            padding:
                                40px;

                            border-radius:
                                18px;

                            border:
                                1px solid
                                var(--border);

                            box-shadow:
                                0 15px 40px
                                rgba(0, 0, 0, 0.08);

                        }


                        .profile-card h2 {

                            font-size:
                                30px;

                            margin-bottom:
                                10px;

                        }


                        .profile-card > p {

                            color:
                                var(--muted);

                            margin-bottom:
                                35px;

                        }


                        .profile-info {

                            border:
                                1px solid
                                var(--border);

                            border-radius:
                                12px;

                            padding:
                                10px 25px;

                        }


                        .profile-row {

                            display: flex;

                            justify-content:
                                space-between;

                            align-items:
                                center;

                            padding:
                                18px 0;

                            border-bottom:
                                1px solid
                                var(--border);

                        }


                        .profile-row:last-child {

                            border-bottom:
                                none;

                        }


                        .label {

                            font-weight:
                                700;

                        }


                        .value {

                            color:
                                var(--muted);

                        }


                        .role {

                            background:
                                rgba(
                                    108,
                                    99,
                                    255,
                                    0.12
                                );

                            color:
                                var(--primary);

                            padding:
                                6px 12px;

                            border-radius:
                                20px;

                            font-weight:
                                700;

                        }


                        /* Profile action buttons */

                        .profile-actions {

                            display: flex;

                            gap:
                                15px;

                            margin-top:
                                30px;

                            flex-wrap:
                                wrap;

                        }


                        .edit-btn {

                            display:
                                inline-block;

                            padding:
                                12px 20px;

                            background:
                                var(--primary);

                            color:
                                white;

                            text-decoration:
                                none;

                            border-radius:
                                8px;

                            font-weight:
                                600;

                        }


                        .edit-btn:hover {

                            background:
                                var(--primary-dark);

                        }


                        .back-btn {

                            display:
                                inline-block;

                            padding:
                                12px 20px;

                            background:
                                #e9e9f2;

                            color:
                                var(--text);

                            text-decoration:
                                none;

                            border-radius:
                                8px;

                            font-weight:
                                600;

                        }


                        .back-btn:hover {

                            background:
                                #dcdcea;

                        }


                        @media (max-width: 600px) {

                            header {

                                flex-direction:
                                    column;

                                gap:
                                    15px;

                                padding:
                                    20px;

                            }


                            main {

                                padding:
                                    30px 15px;

                            }


                            .profile-card {

                                padding:
                                    25px;

                            }


                            .profile-row {

                                flex-direction:
                                    column;

                                align-items:
                                    flex-start;

                                gap:
                                    6px;

                            }


                            .profile-actions {

                                flex-direction:
                                    column;

                            }


                            .edit-btn,
                            .back-btn {

                                text-align:
                                    center;

                                width:
                                    100%;

                            }

                        }

                    </style>

                </head>


                <body>


                    <header>

                        <h1>
                            🍪 Smart Cookie Web Portal
                        </h1>


                        <nav>

                            <a href="dashboard">
                                Dashboard
                            </a>

                            <a href="logout">
                                Logout
                            </a>

                        </nav>

                    </header>


                    <main>

                        <section
                            class="profile-card">


                            <h2>
                                My Profile
                            </h2>


                            <p>
                                View and manage your account information.
                            </p>


                            <div
                                class="profile-info">


                                <div
                                    class="profile-row">

                                    <span
                                        class="label">

                                        User ID

                                    </span>


                                    <span
                                        class="value">

                                        __ID__

                                    </span>

                                </div>


                                <div
                                    class="profile-row">

                                    <span
                                        class="label">

                                        Name

                                    </span>


                                    <span
                                        class="value">

                                        __NAME__

                                    </span>

                                </div>


                                <div
                                    class="profile-row">

                                    <span
                                        class="label">

                                        Email

                                    </span>


                                    <span
                                        class="value">

                                        __EMAIL__

                                    </span>

                                </div>


                                <div
                                    class="profile-row">

                                    <span
                                        class="label">

                                        Role

                                    </span>


                                    <span
                                        class="role">

                                        __ROLE__

                                    </span>

                                </div>


                            </div>


                            <div
                                class="profile-actions">


                                <a
                                    href="edit-profile"
                                    class="edit-btn">

                                    ✏️ Edit Profile

                                </a>


                                <a
                                    href="dashboard"
                                    class="back-btn">

                                    ← Back to Dashboard

                                </a>


                            </div>


                        </section>

                    </main>


                </body>

                </html>

                """;


        // Replace placeholders with actual user data

        html = html.replace(
                "__ID__",
                String.valueOf(userId)
        );


        html = html.replace(
                "__NAME__",
                userName
        );


        html = html.replace(
                "__EMAIL__",
                userEmail
        );


        html = html.replace(
                "__ROLE__",
                userRole
        );


        // Send HTML response
        response.getWriter().println(html);

    }

}