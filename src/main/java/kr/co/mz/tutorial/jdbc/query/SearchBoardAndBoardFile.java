package kr.co.mz.tutorial.jdbc.query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import kr.co.mz.tutorial.jdbc.db.HikariPoolFactory;
import kr.co.mz.tutorial.jdbc.model.Board;
import kr.co.mz.tutorial.jdbc.model.BoardFile;

public class SearchBoardAndBoardFile {

    private static final String QUERY = """
            SELECT
                *
            FROM
                board b
                    LEFT JOIN
                board_file bf ON b.seq = bf.board_seq
            order by
            b.seq desc
        """;

    public static void main(String[] args) throws SQLException, IOException {
        var dataSource = HikariPoolFactory.createHikariDataSource();

        try (var connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(QUERY)) {
            var resultSet = preparedStatement.executeQuery();
            var boardList = new LinkedHashSet<Board>();
            Board board = new Board();
            while (resultSet.next()) {
                if (resultSet.getInt(1) != board.getSeq()) {
                    board = new Board();
                }
                board.setSeq(resultSet.getInt(1));
                board.setTitle(resultSet.getString(2));
                board.setContent(resultSet.getString(3));
                board.setCustomerSeq(resultSet.getInt(4));
                board.setCreatedTime(resultSet.getTimestamp(5));
                board.setModifiedTime(resultSet.getTimestamp(6));
                if (resultSet.getInt(7) != 0) {
                    var boardFile = new BoardFile();
                    boardFile.setSeq(resultSet.getInt(7));
                    boardFile.setBoardSeq(resultSet.getInt(8));
                    boardFile.setFileUuid(resultSet.getString(9));
                    boardFile.setFileName(resultSet.getString(10));
                    board.addBoardFile(boardFile);
                }
                boardList.add(board);
            }
            for (Board board3 : boardList) {
                System.out.println("board" + board3.getSeq());
                for (BoardFile boardFile : board3.getBoardFileSet()) {
                    System.out.println("file" + boardFile.getSeq());
                }
            }
            connection.commit();
        }
    }

}
