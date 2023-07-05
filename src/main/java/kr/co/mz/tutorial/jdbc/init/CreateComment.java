package kr.co.mz.tutorial.jdbc.init;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import kr.co.mz.tutorial.jdbc.db.HikariPoolFactory;
import kr.co.mz.tutorial.jdbc.model.Comment;

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
        System.out.println("쿼리 성공한 행수 : " +
            createComment(
                new Comment(8, 3, "반갑네요, 용균씨!!!! 잘부탁드려요.")
            )
        );
    }

    public static int createComment(Comment comment) throws SQLException, IOException {
        var dataSource = HikariPoolFactory.createHikariDataSource();
        try (var connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setString(1, comment.getContent());
            preparedStatement.setInt(2, comment.getCustomerSeq());
            preparedStatement.setInt(3, comment.getBoardSeq());
            return preparedStatement.executeUpdate();
        }
    }

}
