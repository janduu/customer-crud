package com.example.util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


@WebServlet("/connection-test")
public class TestConnectionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String url = "jdbc:postgresql://localhost/springmvc";
        Properties props = new Properties();
        props.setProperty("user","springmvc");
        props.setProperty("password","kali");
//        props.setProperty("ssl", "false");
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, props);
            resp.getWriter().printf("Connected to %s successfully!", url);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            resp.getWriter().printf("Connection to springmvc failed!\n%s", e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.getWriter().close();
        }
    }
}
