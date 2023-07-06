package kr.co.mz.tutorial.jdbc.query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import kr.co.mz.tutorial.jdbc.db.HikariPoolFactory;
import kr.co.mz.tutorial.jdbc.model.Board;
import kr.co.mz.tutorial.jdbc.model.BoardFile;

public class SearchBoardAndBoardFile2 {

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
            var boardMap = new HashMap<Integer, Board>(); // 동등 객체를 빠르게 찾는데 효율적인 hashSet을 사용.      순서 필요없어서 일반 Hash 사용. linked 는 순서 기준.
            Board board;
            while (resultSet.next()) {
                BoardFile boardFile = null;
                if (resultSet.getInt(7) != 0) {
                    boardFile = new BoardFile();
                    boardFile.setSeq(resultSet.getInt(7));
                    //파일 만듦
                }
                //보드 만듦.
                board = new Board();
                board.setSeq(resultSet.getInt(1));
                board.setTitle(resultSet.getString(2));
                if (!boardMap.containsKey(board.getSeq())) {
                    board.addBoardFile(boardFile); // 없다면 방금 만든 보드에 넣어줌.
                    boardMap.put(resultSet.getInt(1), board);
                } else {
                    var foundBoard = boardMap.get(board.getSeq());
                    //todo boardFile 이름이 같을때 처리
                    foundBoard.addBoardFile(boardFile); // 보드 찾아서 file 넣어줌.
                }
            }
            for (Map.Entry<Integer, Board> b : boardMap.entrySet()) {//출력
                System.out.println("board" + b.toString());
                for (BoardFile bf : b.getValue().getBoardFileSet()) {
                    System.out.println("file" + bf.toString());
                }
            }
        }
    }


}
