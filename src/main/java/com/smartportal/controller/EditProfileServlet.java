package com.smartportal.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/edit-profile")
public class EditProfileServlet extends HttpServlet {

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

        // Get current user information
        String userName =
                (String) session.getAttribute("userName");

        String userEmail =
                (String) session.getAttribute("userEmail");

        // Response settings
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Edit profile page
        String html = """

                <!DOCTYPE html>

                <html lang="en">

                <head>

                    <meta charset="UTF-8">

                    <meta name="viewport"
                          content="width=device-width, initial-scale=1.0">

                    <title>Edit Profile | Smart Cookie</title>

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
                                1px solid var(--border);
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


                        .edit-card {

                            width:
                                100%;

                            max-width:
                                600px;

                            background:
                                var(--card);

                            padding:
                                40px;

                            border-radius:
                                18px;

                            border:
                                1px solid var(--border);

                            box-shadow:
                                0 15px 40px
                                rgba(0, 0, 0, 0.08);
                        }


                        .edit-card h2 {

                            font-size:
                                30px;

                            margin-bottom:
                                10px;
                        }


                        .edit-card > p {

                            color:
                                var(--muted);

                            margin-bottom:
                                35px;
                        }


                        .form-group {

                            margin-bottom:
                                22px;
                        }


                        .form-group label {

                            display:
                                block;

                            margin-bottom:
                                8px;

                            font-size:
                                14px;

                            font-weight:
                                700;
                        }


                        .form-group input {

                            width:
                                100%;

                            padding:
                                13px 15px;

                            border:
                                1px solid var(--border);

                            border-radius:
                                8px;

                            font-size:
                                14px;

                            color:
                                var(--text);

                            outline:
                                none;

                            transition:
                                border 0.2s ease;
                        }


                        .form-group input:focus {

                            border-color:
                                var(--primary);

                            box-shadow:
                                0 0 0 3px
                                rgba(
                                    108,
                                    99,
                                    255,
                                    0.10
                                );
                        }


                        .actions {

                            display:
                                flex;

                            gap:
                                15px;

                            margin-top:
                                30px;
                        }


                        .save-btn {

                            border:
                                none;

                            padding:
                                12px 22px;

                            background:
                                var(--primary);

                            color:
                                white;

                            border-radius:
                                8px;

                            font-size:
                                14px;

                            font-weight:
                                700;

                            cursor:
                                pointer;
                        }


                        .save-btn:hover {

                            background:
                                var(--primary-dark);
                        }


                        .cancel-btn {

                            display:
                                inline-block;

                            padding:
                                12px 22px;

                            background:
                                #e9e9f2;

                            color:
                                var(--text);

                            text-decoration:
                                none;

                            border-radius:
                                8px;

                            font-size:
                                14px;

                            font-weight:
                                700;
                        }


                        .cancel-btn:hover {

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


                            .edit-card {

                                padding:
                                    25px;
                            }


                            .actions {

                                flex-direction:
                                    column;
                            }


                            .save-btn,
                            .cancel-btn {

                                width:
                                    100%;

                                text-align:
                                    center;
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

                            <a href="profile">
                                Profile
                            </a>

                            <a href="logout">
                                Logout
                            </a>

                        </nav>

                    </header>


                    <main>

                        <section class="edit-card">

                            <h2>
                                Edit Profile
                            </h2>


                            <p>
                                Update your personal information.
                            </p>


                            <form
                                action="update-profile"
                                method="post">


                                <div
                                    class="form-group">

                                    <label
                                        for="name">

                                        Full Name

                                    </label>


                                    <input
                                        type="text"
                                        id="name"
                                        name="name"
                                        value="__NAME__"
                                        required>

                                </div>


                                <div
                                    class="form-group">

                                    <label
                                        for="email">

                                        Email Address

                                    </label>


                                    <input
                                        type="email"
                                        id="email"
                                        name="email"
                                        value="__EMAIL__"
                                        required>

                                </div>


                                <div class="actions">

                                    <button
                                        type="submit"
                                        class="save-btn">

                                        💾 Save Changes

                                    </button>


                                    <a
                                        href="profile"
                                        class="cancel-btn">

                                        Cancel

                                    </a>

                                </div>


                            </form>

                        </section>

                    </main>


                </body>

                </html>

                """;


        // Insert current user information
        html = html.replace(
                "__NAME__",
                userName
        );


        html = html.replace(
                "__EMAIL__",
                userEmail
        );


        // Send page
        response.getWriter().println(html);

    }

}