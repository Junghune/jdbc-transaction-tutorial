package kr.co.mz.tutorial.jdbc.init;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import kr.co.mz.tutorial.jdbc.db.HikariPoolFactory;

public class CreateLikes {

    private static final String QUERY = """
        insert into board_like (board_seq,customer_seq) 
        values (?,?)""";

    public static void main(String[] args) throws SQLException, IOException {
        var dataSource = HikariPoolFactory.createHikariDataSource();
        var connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
        preparedStatement.setInt(1, 3);
        preparedStatement.setInt(2, 6);
        int result = preparedStatement.executeUpdate();
        System.out.println("쿼리 성공한 행수 : " + result);
    }
}
