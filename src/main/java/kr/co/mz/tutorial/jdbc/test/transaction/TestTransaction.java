package kr.co.mz.tutorial.jdbc.test.transaction;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import kr.co.mz.tutorial.jdbc.db.HikariPoolFactory;

public class TestTransaction {

    public static void main(String[] args) throws IOException {
        var dataSource = HikariPoolFactory.createHikariDataSource();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            var board_seq = 3;
            var selectQuery = """
                select count(*) from report where board_seq=?""";
            preparedStatement = connection.prepareStatement(
                selectQuery);
            preparedStatement.setInt(1, board_seq);
            ResultSet resultSet = preparedStatement.executeQuery();
            var reportCnt = 0;
            if (resultSet.next()) {
                reportCnt = resultSet.getInt(1);
            }
            if (reportCnt > 5) {
                preparedStatement = connection.prepareStatement(
                    "select customer_seq from board where seq = ?");
                preparedStatement.setInt(1, board_seq);
                ResultSet resultSet1 = preparedStatement.executeQuery();
                if (resultSet1.next()) {
                    preparedStatement = connection.prepareStatement(
                        "update customer set blockYN=1 where seq=?");
                    preparedStatement.setInt(1, resultSet1.getInt(1));
                    int result = preparedStatement.executeUpdate();
                    System.out.println("BlackList Update Successful" + result);
                }
                preparedStatement = connection.prepareStatement("delete from board where seq = ?");
                preparedStatement.setInt(1, board_seq);
                preparedStatement.executeUpdate();
            }
            connection.commit();
            System.out.println("successful");
        } catch (SQLException sqle) {
            try {
                if (connection != null) {
                    connection.rollback();
                    System.out.println("fail");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
