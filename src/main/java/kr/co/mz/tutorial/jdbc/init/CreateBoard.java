package kr.co.mz.tutorial.jdbc.init;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import kr.co.mz.tutorial.jdbc.db.HikariPoolFactory;
import kr.co.mz.tutorial.jdbc.file.FileService;
import kr.co.mz.tutorial.jdbc.model.Board;
import kr.co.mz.tutorial.jdbc.model.BoardFile;

public class CreateBoard {

    private static final String QUERY = """
        insert into board(title,content,customer_seq) 
        values(?,?,?)""";

    public static void main(String[] args) throws SQLException, IOException {
        var board = new Board("안녕하십니까!", "반가워요~", 8);
        String fileDirectoryName = FileService.BASIC_DIRECTORY + FileService.generateFileDirectoryName();
        board.addBoardFile(
            new BoardFile(UUID.randomUUID().toString(), "직박구리부리박기.txt",
                fileDirectoryName + File.separator + "직박구리부리박기.txt",
                999, "txt"
            )
        );
        System.out.println("등록된 게시물 수 : " + createBoard(board));
    }

    private static int createBoard(final Board board) throws SQLException, IOException {
        var dataSource = HikariPoolFactory.createHikariDataSource();
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        try (
            var preparedStatement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, board.getTitle());
            preparedStatement.setString(2, board.getContent());
            preparedStatement.setInt(3, board.getCustomerSeq());
            int insertedCount = preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int boardSeq = 0;
            if (generatedKeys.next()) {
                boardSeq = generatedKeys.getInt(1);
            }
            for (var boardFile : board.getBoardFileSet()) {
                boardFile.setBoardSeq(boardSeq);
                CreateBoardFile.createBoardFile(connection, boardFile);
            }

            connection.commit();
            return insertedCount;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

}
