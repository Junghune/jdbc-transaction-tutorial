package kr.co.mz.tutorial.jdbc.init;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import kr.co.mz.tutorial.jdbc.db.HikariPoolFactory;
import kr.co.mz.tutorial.jdbc.model.Likes;

public class CreateLikes {

    private static final String QUERY = """
        insert into board_like (board_seq,customer_seq) 
        values (?,?)""";

    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("좋아요 등록 여부 : " + createLikes(new Likes(3, 6)));
    }

    public static int createLikes(Likes likes) throws IOException, SQLException {
        var dataSource = HikariPoolFactory.createHikariDataSource();
        try (var connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setInt(1, likes.getBoardSeq());
            preparedStatement.setInt(2, likes.getCustomerSeq());
            return preparedStatement.executeUpdate();
        }
    }
}
