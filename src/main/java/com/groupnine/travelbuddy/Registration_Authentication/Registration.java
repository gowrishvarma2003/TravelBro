package com.groupnine.travelbuddy.Registration_Authentication;

import com.groupnine.travelbuddy.TBBase.TBBaseConnection;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.IOException;

import java.sql.*;

import java.sql.DriverManager;

@WebServlet(name = "registration", value = "/reg")
public class Registration extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // Attributes of Server-url, Host-name, and Host-pass to access the database
        String host = "jdbc:mysql://db4free.net:3306/tb_base";
        String userName = "tbadmin";
        String userPass = "admintravel123";
        DriverManager.setLoginTimeout(30);
        try {
            // Checking if JDBC driver for MySQL exist in the project
            Connection connection = new TBBaseConnection().getConnection();
            // Instantiating a new Prepared Statement (known as pre-compiled statement) to insert the acquired data
            PreparedStatement statement = connection.prepareStatement("INSERT INTO bt_base.users(fullname, email, alt_email, mobile, userpass) VALUES (?,?,?,?,?)");
            // Moving the data into the statement
            statement.setString(1, req.getParameter("fullname"));
            statement.setString(2, req.getParameter("email"));
            statement.setString(3, req.getParameter("alt_email"));
            statement.setString(4, req.getParameter("mobile"));
            statement.setString(5, req.getParameter("password"));
            // Executing the statement with all the data provided
            statement.executeUpdate();
            // Closing the statement
            statement.close();
            // Closing the connection to the database
            connection.close();
            resp.sendRedirect("login_page.jsp");
        } catch (ClassNotFoundException | SQLException | IOException | ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
