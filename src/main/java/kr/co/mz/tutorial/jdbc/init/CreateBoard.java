package kr.co.mz.tutorial.jdbc.init;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import kr.co.mz.tutorial.jdbc.db.HikariPoolFactory;
import kr.co.mz.tutorial.jdbc.model.Board;
import kr.co.mz.tutorial.jdbc.model.BoardFile;

public class CreateBoard {

    private static final String QUERY = """
        insert into board(title,content,customer_seq) 
        values(?,?,?)""";

    public static void main(String[] args) throws SQLException, IOException {
        var board = new Board();
        board.setTitle("");
        board.setContent("");
        board.setCustomerSeq(8);

        var boardFile = new BoardFile();
        boardFile.setFileName("");
        boardFile.setFilePath(System.getProperty("user.dir"));
        boardFile.setFileSize(999);
        boardFile.setFileType("txt");

        board.addBoardFile(boardFile);

        var insertedCount = createBoard(board);
        System.out.println("등록된 게시물 수 : " + insertedCount);
    }

    private static int createBoard(final Board board) throws SQLException, IOException {
        var dataSource = HikariPoolFactory.createHikariDataSource();
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        try (
            var preparedStatement = connection.prepareStatement(QUERY);
        ) {
            preparedStatement.setString(1, board.getTitle());
            preparedStatement.setString(2, board.getContent());
            preparedStatement.setInt(3, board.getCustomerSeq());
            int insertedCount = preparedStatement.executeUpdate();

//            for (var boardFile : board.getBoardFileSet()) {
//                // TODO 지금 insert한 데이터의 PK를 얻고 싶은데 어떻게 하지? 검색어: mysql, auto_increment, get inserted primary key
//                boardFile.setBoardSeq(0);
//                CreateBoardFile.createBoardFile(connection, boardFile);
//            }

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
