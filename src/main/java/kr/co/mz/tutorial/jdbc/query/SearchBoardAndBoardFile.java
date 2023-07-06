package kr.co.mz.tutorial.jdbc.query;

import static kr.co.mz.tutorial.jdbc.model.Board.fromResultSet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import kr.co.mz.tutorial.jdbc.db.HikariPoolFactory;
import kr.co.mz.tutorial.jdbc.model.Board;
import kr.co.mz.tutorial.jdbc.model.BoardFile;

public class SearchBoardAndBoardFile {

    //TODO
    private static final String QUERY = """
            SELECT
                *
            FROM
                board b
                    LEFT JOIN
                board_file bf ON b.seq = bf.board_seq
        """;

    public static void main(String[] args) throws SQLException, IOException {
        var dataSource = HikariPoolFactory.createHikariDataSource();

        try (var connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(QUERY)) {
            var resultSet = preparedStatement.executeQuery();
            // Map 생성
            var boardMap = new LinkedHashMap<Integer, Board>();
            // 최초 board 생성
            while (resultSet.next()) {
                Board board = new Board();
                var board_seq = resultSet.getInt(1);
                var optional = Optional.ofNullable(boardMap.get(board_seq));
                board = optional.orElseGet(() -> {
                    Board board2 = new Board();
                    try {
                        Board.fromResultSet(resultSet, board2);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return board2;
                });
                // boardSeq 값이 다른 데이터가 들어오면 board 를 새로 생성
                fromResultSet(resultSet, board);
                board.addBoardFile(BoardFile.formResultSet(resultSet));
                // 같은 주소라면 보드를 다시 추가하지 않고 다른 주소라면 추가
                boardMap.put(resultSet.getInt(1), board);
            }
            for (Map.Entry<Integer, Board> entry : boardMap.entrySet()) {
                System.out.println("board" + entry.getValue().getSeq());
                for (BoardFile boardFile : entry.getValue().getBoardFileSet()) {
                    System.out.println("file" + boardFile.getSeq());
                }
            }
        }
    }
}
