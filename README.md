# Smart Cookie Web Portal

A Java-based web portal for secure user authentication, role-based access control, profile management, and cookie-driven personalization. This project demonstrates how to build a servlet-based web application using Java, MySQL, and modern security practices such as BCrypt password hashing and session management.

## Project Overview

Smart Cookie Web Portal is a lightweight web application that allows users to:

- register for a new account
- sign in with secure credentials
- manage their profile information
- persist personalization preferences using cookies
- access role-specific dashboard views
- interact with an admin dashboard when assigned administrative privileges

The application is built using Jakarta Servlet technology and follows a classic MVC-inspired structure with controllers, data access objects, and utility classes.

## Why This Project

This project is useful for learning and demonstrating:

- Java web application development
- servlet-based request handling
- database connectivity with JDBC
- secure password storage with BCrypt
- session and cookie management
- role-based access control in a web app
- effective Maven-based project organization

## Key Features

- User registration with password hashing
- Secure login flow with credential validation
- Session-based authentication
- “Remember Me” cookie functionality
- Profile viewing and updating
- Preference/theme storage using cookies
- Admin-only dashboard access
- MySQL persistence layer
- Maven project structure for easy build and deployment

## Technology Stack

- Java 27
- Maven
- Jakarta Servlet API 6.1.0
- MySQL Connector/J 9.4.0
- BCrypt via jbcrypt
- JUnit 5
- Apache Tomcat (or any servlet container compatible with Jakarta EE)

## System Architecture

The application follows a layered structure:

1. Presentation Layer
   - HTML pages and frontend assets in `src/main/webapp`
   - Servlet controllers handling requests and responses

2. Application Layer
   - logic for login, registration, preferences, and profile operations

3. Data Access Layer
   - `UserDAO` manages database interactions

4. Persistence Layer
   - MySQL database stores user data and credentials

5. Security Layer
   - password hashes are stored and checked using BCrypt
   - sessions restrict unauthorized access to private pages

## Directory Structure

```text
SmartWebPortal/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/smartportal/
│   │   │       ├── controller/
│   │   │       │   ├── LoginServlet.java
│   │   │       │   ├── RegisterServlet.java
│   │   │       │   ├── DashboardServlet.java
│   │   │       │   ├── AdminDashboardServlet.java
│   │   │       │   ├── ProfileServlet.java
│   │   │       │   ├── EditProfileServlet.java
│   │   │       │   ├── SavePreferencesServlet.java
│   │   │       │   ├── UpdateProfileServlet.java
│   │   │       │   └── LogoutServlet.java
│   │   │       ├── dao/
│   │   │       │   └── UserDAO.java
│   │   │       ├── model/
│   │   │       │   └── User.java
│   │   │       └── util/
│   │   │           ├── DBConnection.java
│   │   │           ├── PasswordUtil.java
│   │   │           └── TestConnection.java
│   │   └── webapp/
│   │       ├── css/
│   │       │   └── style.css
│   │       ├── js/
│   │       │   └── register.js
│   │       ├── login.html
│   │       ├── register.html
│   │       ├── dashboard.html
│   │       ├── index.jsp
│   │       └── WEB-INF/
│   └── test/
│       └── java/
├── target/
└── .gitignore
```

## Prerequisites

Before running this application, ensure that you have installed:

- JDK 27 or later
- Maven 3.9+
- MySQL 8.0 or newer
- Apache Tomcat 10.1+ or another Jakarta-compatible servlet container
- A code editor or IDE such as IntelliJ IDEA, Eclipse, or VS Code

## Database Setup

Create a MySQL database for the application:

```sql
CREATE DATABASE smart_web_portal;
```

Create the users table:

```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) DEFAULT 'USER'
);
```

This table stores the user’s basic information, password hash, and role.

## Environment Configuration

This project reads database credentials from environment variables. Do not hardcode actual secrets in the repository.

### Linux/macOS

```bash
export DB_URL="jdbc:mysql://localhost:3306/smart_web_portal"
export DB_USER="your_db_user"
export DB_PASSWORD="your_db_password"
```

### Windows PowerShell

```powershell
$env:DB_URL = "jdbc:mysql://localhost:3306/smart_web_portal"
$env:DB_USER = "your_db_user"
$env:DB_PASSWORD = "your_db_password"
```

### Windows Command Prompt

```cmd
set DB_URL=jdbc:mysql://localhost:3306/smart_web_portal
set DB_USER=your_db_user
set DB_PASSWORD=your_db_password
```

> Important: Replace the sample values above with your own local credentials. Never commit real secrets to version control.

## Building the Project

From the root directory of the project, run:

```bash
mvn clean package
```

This will compile the Java sources and produce a WAR package in the `target` directory.

## Running the Application

### Option 1: Deploy to Tomcat

1. Build the project:

```bash
mvn clean package
```

2. Copy the generated WAR file to Tomcat's `webapps` directory:

```bash
cp target/SmartWebPortal.war /path/to/tomcat/webapps/
```

3. Start Tomcat:

```bash
/path/to/tomcat/bin/startup.sh
```

4. Open the application in a browser:

```text
http://localhost:8080/SmartWebPortal/
```

### Option 2: Run using your IDE

- Import the Maven project into IntelliJ IDEA or Eclipse.
- Configure a Tomcat local server.
- Deploy the app and run it from the IDE.

## Application Flow

### Registration

A new user submits their name, email, and password. The password is hashed using BCrypt before storage in the database.

### Login

The user enters their email and password. The system retrieves the user record, verifies the hash, and creates a session if successful.

### Dashboard Access

After a valid login, the user is redirected to a dashboard. Role-specific content is displayed based on the account type.

### Admin Features

Users with the role `ADMIN` can access administrative pages and privileged functionality.

### Profile and Preferences

Users can view and edit their profile details and save preference information using cookies and session data.

## Security Features

This project includes a few important security practices:

- Passwords are never stored in plain text
- BCrypt is used for hashing and verification
- User sessions are tracked with `HttpSession`
- Authorization is role-aware
- Cookies are used responsibly for saved preferences and remember-me flows

## Recommended Production Enhancements

For real-world deployment, consider the following improvements:

- enable HTTPS everywhere
- add CSRF protection
- validate and sanitize all user inputs
- implement centralized role validation
- encrypt sensitive configuration values
- use connection pooling for production databases
- add logging and auditing for authentication events
- implement rate limiting on login attempts

## Development Notes

The project is intentionally simple and educational. It demonstrates a classic servlet-based architecture without the complexity of modern Java frameworks such as Spring Boot.

This makes it an excellent reference for understanding:

- servlet lifecycle
- request dispatching
- database access patterns
- session management
- servlet authentication basics

## License

This project is intended for educational and demonstration purposes.

## Contributing

Contributions are welcome. If you would like to improve the project, please:

1. create a feature branch
2. make your changes
3. test the application locally
4. submit a pull request with a clear description of the enhancement

## Contact

For support or collaboration, please contact the project maintainer via the repository owner or your organization’s preferred communication channel.

## Summary

Smart Cookie Web Portal is a practical Java web application that brings together authentication, database persistence, cookies, role-based access, and web UI logic in a single project. It is suitable for learning web application security, Java EE basics, and building servlet-based portals from scratch.
