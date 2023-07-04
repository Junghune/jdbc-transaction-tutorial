package kr.co.mz.tutorial.jdbc.init;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import kr.co.mz.tutorial.jdbc.db.HikariPoolFactory;

public class CreateReport {

    private static final String QUERY = """
        insert into report(board_seq,reporter_seq,title,content) 
        values(?,?,?,?)""";

    public static void main(String[] args) throws SQLException, IOException {
        var dataSource = HikariPoolFactory.createHikariDataSource();
        var connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
        preparedStatement.setInt(1, 3);
        preparedStatement.setInt(2, 1);
        preparedStatement.setString(3, "가입인사하는 게시판이 아닙니다.");
        preparedStatement.setString(4, "게시글 이동을 부탁드립니다.");
        int result = preparedStatement.executeUpdate();
        System.out.println("쿼리 성공한 행수 : " + result);
    }
}
