package kr.co.mz.tutorial.jdbc.init;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import kr.co.mz.tutorial.jdbc.db.HikariPoolFactory;

public class CreateComment {

    private static final String QUERY = """
        INSERT INTO `webchat`.`board_comment`
        (
        `content`,
        `customer_seq`,
        `board_seq`
        )
        VALUES
        (
        ?,
        ?,
        ?
        );
        """;

    public static void main(String[] args) throws SQLException, IOException {
        var dataSource = HikariPoolFactory.createHikariDataSource();
        var connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
        preparedStatement.setString(1, "반갑네요, 용균씨!!!! 잘부탁드려요.");
        preparedStatement.setInt(2, 8);
        preparedStatement.setInt(3, 3);
        int result = preparedStatement.executeUpdate();
        System.out.println("쿼리 성공한 행수 : " + result);
    }

}
