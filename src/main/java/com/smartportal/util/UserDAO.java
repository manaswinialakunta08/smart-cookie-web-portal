package com.smartportal.dao;

import com.smartportal.model.User;
import com.smartportal.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    // Register a new user
    public boolean registerUser(User user) {

        String sql = """
                INSERT INTO users (name, email, password_hash, role)
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPasswordHash());
            statement.setString(4, user.getRole());

            int rowsInserted =
                    statement.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    // Find user using email
    public User findUserByEmail(String email) {

        String sql =
                "SELECT * FROM users WHERE email = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, email);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                User user = new User();

                user.setId(
                        resultSet.getInt("id")
                );

                user.setName(
                        resultSet.getString("name")
                );

                user.setEmail(
                        resultSet.getString("email")
                );

                user.setPasswordHash(
                        resultSet.getString("password_hash")
                );

                user.setRole(
                        resultSet.getString("role")
                );

                return user;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // Update user's name and email
    public boolean updateUserProfile(
            int userId,
            String name,
            String email) {

        String sql = """
                UPDATE users
                SET name = ?, email = ?
                WHERE id = ?
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setInt(3, userId);

            int rowsUpdated =
                    statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

}