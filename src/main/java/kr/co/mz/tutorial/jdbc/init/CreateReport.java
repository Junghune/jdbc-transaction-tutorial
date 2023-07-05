package kr.co.mz.tutorial.jdbc.init;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import kr.co.mz.tutorial.jdbc.db.HikariPoolFactory;
import kr.co.mz.tutorial.jdbc.model.Report;

public class CreateReport {

    private static final String QUERY = """
        insert into report(board_seq,reporter_seq,title,content) 
        values(?,?,?,?)""";

    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("쿼리 성공한 행수 : " +
            createReport(
                new Report(3, 1, "가입인사하는 게시판이 아닙니다.", "게시글 이동을 부탁드립니다.")
            )
        );
    }

    public static int createReport(Report report) throws IOException, SQLException {
        var dataSource = HikariPoolFactory.createHikariDataSource();
        try (var connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setInt(1, report.getBoardSeq());
            preparedStatement.setInt(2, report.getReporterSeq());
            preparedStatement.setString(3, report.getTitle());
            preparedStatement.setString(4, report.getContent());
            return preparedStatement.executeUpdate();
        }
    }
}
