package com.smartportal.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/preferences")
public class PreferencesServlet extends HttpServlet {

    @Override
    protected void doGet(
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

        // Default theme
        String currentTheme = "light";

        // Default language
        String currentLanguage = "en";

        // Read cookies
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                // Read theme cookie
                if ("theme".equals(cookie.getName())) {

                    currentTheme =
                            cookie.getValue();
                }

                // Read language cookie
                if ("language".equals(cookie.getName())) {

                    currentLanguage =
                            cookie.getValue();
                }
            }
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

                    <title>Preferences | Smart Cookie</title>

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


                        header h1 {

                            font-size:
                                22px;

                        }


                        nav {

                            display:
                                flex;

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

                            display:
                                flex;

                            justify-content:
                                center;

                            align-items:
                                flex-start;

                            padding:
                                60px 20px;

                        }


                        .preferences-card {

                            width:
                                100%;

                            max-width:
                                650px;

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


                        .preferences-card h2 {

                            font-size:
                                30px;

                            margin-bottom:
                                10px;

                        }


                        .description {

                            color:
                                var(--muted);

                            margin-bottom:
                                35px;

                        }


                        .preference-group {

                            padding:
                                20px 0;

                            border-bottom:
                                1px solid var(--border);

                        }


                        .preference-group:last-of-type {

                            border-bottom:
                                none;

                        }


                        .preference-group h3 {

                            margin-bottom:
                                8px;

                            font-size:
                                17px;

                        }


                        .preference-group p {

                            color:
                                var(--muted);

                            font-size:
                                13px;

                            margin-bottom:
                                15px;

                        }


                        .theme-options,
                        .language-options {

                            display:
                                flex;

                            gap:
                                15px;

                        }


                        .theme-option,
                        .language-option {

                            flex: 1;

                            padding:
                                15px;

                            border:
                                1px solid var(--border);

                            border-radius:
                                10px;

                            cursor:
                                pointer;

                            transition:
                                border-color 0.2s ease;

                        }


                        .theme-option:hover,
                        .language-option:hover {

                            border-color:
                                var(--primary);

                        }


                        .theme-option input,
                        .language-option input {

                            margin-right:
                                8px;

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


                        .back-btn {

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


                            .preferences-card {

                                padding:
                                    25px;

                            }


                            .theme-options,
                            .language-options {

                                flex-direction:
                                    column;

                            }


                            .actions {

                                flex-direction:
                                    column;

                            }

                        }

                    </style>

                </head>


                <body class="__THEME_CLASS__">


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

                        <section
                            class="preferences-card">

                            <h2>
                                Cookie Preferences
                            </h2>

                            <p class="description">
                                Customize how the Smart Cookie
                                Web Portal remembers your preferences.
                            </p>


                            <form
                                action="save-preferences"
                                method="post">


                                <!-- THEME -->

                                <div
                                    class="preference-group">

                                    <h3>
                                        🎨 Theme
                                    </h3>

                                    <p>
                                        Choose the appearance
                                        of the portal.
                                    </p>


                                    <div
                                        class="theme-options">


                                        <label
                                            class="theme-option">

                                            <input
                                                type="radio"
                                                name="theme"
                                                value="light"
                                                __LIGHT_CHECKED__>

                                            ☀️ Light

                                        </label>


                                        <label
                                            class="theme-option">

                                            <input
                                                type="radio"
                                                name="theme"
                                                value="dark"
                                                __DARK_CHECKED__>

                                            🌙 Dark

                                        </label>

                                    </div>

                                </div>


                                <!-- LANGUAGE -->

                                <div
                                    class="preference-group">

                                    <h3>
                                        🌐 Language
                                    </h3>

                                    <p>
                                        Choose your preferred
                                        portal language.
                                    </p>


                                    <div
                                        class="language-options">


                                        <label
                                            class="language-option">

                                            <input
                                                type="radio"
                                                name="language"
                                                value="en"
                                                __ENGLISH_CHECKED__>

                                            🇬🇧 English

                                        </label>


                                        <label
                                            class="language-option">

                                            <input
                                                type="radio"
                                                name="language"
                                                value="te"
                                                __TELUGU_CHECKED__>

                                            🇮🇳 తెలుగు

                                        </label>


                                        <label
                                            class="language-option">

                                            <input
                                                type="radio"
                                                name="language"
                                                value="hi"
                                                __HINDI_CHECKED__>

                                            🇮🇳 हिन्दी

                                        </label>

                                    </div>

                                </div>


                                <div class="actions">

                                    <button
                                        type="submit"
                                        class="save-btn">

                                        💾 Save Preferences

                                    </button>


                                    <a
                                        href="dashboard"
                                        class="back-btn">

                                        Cancel

                                    </a>

                                </div>


                            </form>

                        </section>

                    </main>


                </body>

                </html>

                """;


        // Apply selected theme
        if ("dark".equals(currentTheme)) {

            html = html.replace(
                    "__THEME_CLASS__",
                    "dark-theme"
            );

            html = html.replace(
                    "__DARK_CHECKED__",
                    "checked"
            );

            html = html.replace(
                    "__LIGHT_CHECKED__",
                    ""
            );

        } else {

            html = html.replace(
                    "__THEME_CLASS__",
                    ""
            );

            html = html.replace(
                    "__LIGHT_CHECKED__",
                    "checked"
            );

            html = html.replace(
                    "__DARK_CHECKED__",
                    ""
            );
        }


        // Apply selected language
        switch (currentLanguage) {

            case "te":

                html = html.replace(
                        "__TELUGU_CHECKED__",
                        "checked"
                );

                html = html.replace(
                        "__ENGLISH_CHECKED__",
                        ""
                );

                html = html.replace(
                        "__HINDI_CHECKED__",
                        ""
                );

                break;


            case "hi":

                html = html.replace(
                        "__HINDI_CHECKED__",
                        "checked"
                );

                html = html.replace(
                        "__ENGLISH_CHECKED__",
                        ""
                );

                html = html.replace(
                        "__TELUGU_CHECKED__",
                        ""
                );

                break;


            default:

                html = html.replace(
                        "__ENGLISH_CHECKED__",
                        "checked"
                );

                html = html.replace(
                        "__TELUGU_CHECKED__",
                        ""
                );

                html = html.replace(
                        "__HINDI_CHECKED__",
                        ""
                );

                break;
        }


        response.getWriter().println(html);

    }
}