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
            // 같은 주소의 board 를 추가하지 않도록 set 으로 생성
            var boardSet = new LinkedHashSet<Board>();
            // 최초 board 생성
            Board board = new Board();
            while (resultSet.next()) {
                // boardSeq 값이 다른 데이터가 들어오면 board 를 새로 생성
                if (resultSet.getInt(1) != board.getSeq()) {
                    board = new Board();
                }
                board.setSeq(resultSet.getInt(1));
                board.setTitle(resultSet.getString(2));
                board.setContent(resultSet.getString(3));
                board.setCustomerSeq(resultSet.getInt(4));
                board.setCreatedTime(resultSet.getTimestamp(5));
                board.setModifiedTime(resultSet.getTimestamp(6));
                // 파일의 데이터가 있다면 board 에 추가
                if (resultSet.getInt(7) != 0) {
                    var boardFile = new BoardFile();
                    boardFile.setSeq(resultSet.getInt(7));
                    boardFile.setBoardSeq(resultSet.getInt(8));
                    boardFile.setFileUuid(resultSet.getString(9));
                    boardFile.setFileName(resultSet.getString(10));
                    board.addBoardFile(boardFile);
                }
                // 같은 주소라면 보드를 다시 추가하지 않고 다른 주소라면 추가
                boardSet.add(board);
            }
            for (Board selectedBoard : boardSet) {
                System.out.println("board" + selectedBoard.getSeq());
                for (BoardFile boardFile : selectedBoard.getBoardFileSet()) {
                    System.out.println("file" + boardFile.getSeq());
                }
            }
        }
    }
}
