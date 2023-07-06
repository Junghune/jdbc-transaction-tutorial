package kr.co.mz.tutorial.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import kr.co.mz.tutorial.jdbc.db.HikariPoolFactory;

public class MyServletContextListener implements ServletContextListener {

    private Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            var dataSource = HikariPoolFactory.createHikariDataSource();
            connection = dataSource.getConnection();
            sce.getServletContext().setAttribute("dbConnection", connection);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
