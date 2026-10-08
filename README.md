# Smart Cookie Web Portal

A Java servlet-based web application for user authentication, profile management, role-aware dashboards, and cookie-based personalization. The project demonstrates JDBC persistence with MySQL, BCrypt password hashing, and session management.

## Project Overview

The portal lets users register, sign in, manage profile information, and save preferences. Accounts with the `ADMIN` role can access administrative pages. The application uses a layered, MVC-inspired structure with servlet controllers, data access code, and utility classes.

This project is an educational example of Java web application development, servlet request handling, database access, authentication, and session and cookie management.

## Features

- User registration and login with BCrypt password hashing
- Session-based authentication
- Role-aware dashboard access, including admin pages for `ADMIN` accounts
- Profile viewing and editing
- Remember-me and preference cookies
- MySQL persistence through JDBC
- Maven WAR packaging for Tomcat deployment

## Technology Stack

| Technology | Use |
| --- | --- |
| Java 27 | Application language |
| Jakarta Servlets 6.1 | Server-side request handling |
| Apache Tomcat 11 | Servlet container |
| Maven | Dependency management and WAR build |
| MySQL | Relational database |
| HTML, CSS, JavaScript | Web interface |
| Git and GitHub | Version control and project hosting |

The project also uses MySQL Connector/J, BCrypt (`jbcrypt`), and JUnit 5.

## Project Structure

```text
SmartWebPortal/
├── pom.xml
├── README.md
├── .gitignore
└── src/
    ├── main/
    │   ├── java/com/smartportal/
    │   │   ├── controller/
    │   │   │   ├── AdminDashboardServlet.java
    │   │   │   ├── DashboardServlet.java
    │   │   │   ├── EditProfileServlet.java
    │   │   │   ├── LoginServlet.java
    │   │   │   ├── LogoutServlet.java
    │   │   │   ├── PreferencesServlet.java
    │   │   │   ├── ProfileServlet.java
    │   │   │   ├── RegisterServlet.java
    │   │   │   ├── SavePreferencesServlet.java
    │   │   │   └── UpdateProfileServlet.java
    │   │   └── util/
    │   │       ├── DBConnection.java
    │   │       ├── PasswordUtil.java
    │   │       ├── TestConnection.java
    │   │       ├── User.java
    │   │       └── UserDAO.java
    │   └── webapp/
    │       ├── css/style.css
    │       ├── images/
    │       ├── js/register.js
    │       ├── dashboard.html
    │       ├── index.jsp
    │       ├── login.html
    │       ├── register.html
    │       └── WEB-INF/web.xml
    └── test/java/
```

## Prerequisites

- JDK 27
- Maven 3.9 or later
- MySQL 8.0 or later
- Apache Tomcat 11
- A code editor or IDE, such as IntelliJ IDEA, Eclipse, or VS Code

## Database Configuration

The application requires MySQL, normally listening on port `3306`. Create the database and users table:

```sql
CREATE DATABASE smart_web_portal;

USE smart_web_portal;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) DEFAULT 'USER'
);
```

Database settings are read from environment variables. `DB_URL` defaults to `jdbc:mysql://localhost:3306/smart_web_portal`, and `DB_USER` defaults to `root`. `DB_PASSWORD` must be set to the password for your local MySQL account. No database password is included in this repository.

In Windows PowerShell, set the variables before starting Tomcat:

```powershell
$env:DB_URL = "jdbc:mysql://localhost:3306/smart_web_portal"
$env:DB_USER = "your_mysql_user"
$env:DB_PASSWORD = "<your_mysql_password>"
```

Replace the example values with your own local settings. Keep the password private and do not commit it to version control. If Tomcat runs as a service, configure these variables in the service's environment and restart it.

## Installation and Setup

1. Install the prerequisites listed above.
2. Create the MySQL database and table using the SQL statements in [Database Configuration](#database-configuration).
3. Configure the database environment variables for the Tomcat process.
4. From the project root, build the WAR as described in [Build the Project](#build-the-project).
5. Confirm Tomcat is configured to listen on port `8081`, then deploy the WAR as described below.

## Build the Project

Run this command from the directory containing `pom.xml`:

```bash
mvn clean package
```

Maven compiles the project and generates:

```text
target/SmartWebPortal.war
```

## Deploy on Apache Tomcat

Deploy the generated WAR to the Tomcat `webapps` directory. On Windows PowerShell, for example:

```powershell
Copy-Item .\target\SmartWebPortal.war C:\path\to\tomcat\webapps\
```

Start Tomcat using its startup script (from the Tomcat `bin` directory):

```powershell
.\startup.bat
```

Tomcat is configured to use port **8081** for this application. If necessary, confirm the HTTP Connector in `conf/server.xml` uses port `8081`, and restart Tomcat after changing it. The WAR filename sets the application context path to `/SmartWebPortal`.

## Run the Application

Once Tomcat has started and deployed the WAR, open the application in a browser using the URLs below. Alternatively, import the Maven project into IntelliJ IDEA or Eclipse, configure a local Tomcat 11 server on port `8081`, and deploy the application from the IDE.

## Application URLs

| Page | URL |
| --- | --- |
| Application | `http://localhost:8081/SmartWebPortal/` |
| Login | `http://localhost:8081/SmartWebPortal/login.html` |
| Registration | `http://localhost:8081/SmartWebPortal/register.html` |

## Configuration Notes

- Port `8081` is used for Tomcat because port `8080` is occupied by Jenkins.
- The database connection uses MySQL on port `3306` by default.
- The connection URL, user, and password can be supplied through `DB_URL`, `DB_USER`, and `DB_PASSWORD`.
- The servlet API is provided by Tomcat; it is not packaged as an application dependency.
- Do not store real credentials in source control.

## Application Flow

- **Registration:** Users submit their name, email, and password. The password is hashed using BCrypt before it is stored.
- **Login:** The submitted credentials are validated against the stored password hash. A session is created after successful authentication.
- **Dashboard:** Authenticated users are directed to dashboard views appropriate to their role.
- **Profile and preferences:** Users can manage profile information and save preferences using cookies and session data.

## Security

The application hashes passwords with BCrypt, uses `HttpSession` for authenticated sessions, and applies role-aware access for administrative pages. Cookies support remember-me and saved-preference flows.

For production use, consider adding HTTPS, CSRF protection, stronger centralized input validation and authorization, encrypted secret management, database connection pooling, authentication-event logging, and login rate limiting.


## Development Notes

The project intentionally uses a classic servlet-based architecture rather than a framework such as Spring Boot. It provides examples of servlet lifecycle and request handling, JDBC-based database access, and session management.

## Contributing

Contributions are welcome. Create a feature branch, make and test your changes locally, then submit a pull request with a clear description.

