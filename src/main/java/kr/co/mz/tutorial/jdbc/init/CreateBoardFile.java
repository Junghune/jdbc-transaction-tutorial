package kr.co.mz.tutorial.jdbc.init;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;
import kr.co.mz.tutorial.jdbc.db.HikariPoolFactory;
import kr.co.mz.tutorial.jdbc.model.BoardFile;

public class CreateBoardFile {

    public static final String BASIC_DIRECTORY = "/Users/mz01-junghunee/Documents/tutorial_directory/";
    private static final String QUERY = """
        insert into board_file(board_seq,file_uuid,file_name,file_path,file_size,file_type) 
        values(?,?,?,?,?,?)""";

    public static void main(String[] args) throws SQLException, IOException {
        var dataSource = HikariPoolFactory.createHikariDataSource();

        // TODO filePath에 대해 수정이 필요함

        System.out.println("쿼리 성공한 행수 : " + createBoardFile(
                dataSource.getConnection(),
                new BoardFile(UUID.randomUUID().toString(), "직박구리부리박기.txt", System.getProperty("user.dir"),
                    999, "txt"
                )
            )
        );
    }

    public static int createBoardFile(Connection connection, BoardFile boardFile)
        throws SQLException {
        String fileDirectoryName = BASIC_DIRECTORY + generateFileDirectoryName();
        try (
            var preparedStatement = connection.prepareStatement(QUERY);
        ) {
            preparedStatement.setInt(1, boardFile.getBoardSeq());
            preparedStatement.setString(2, UUID.randomUUID().toString());
            preparedStatement.setString(4, boardFile.getFileName());
            preparedStatement.setString(3,
                fileDirectoryName + File.separator + boardFile.getFileName());
            preparedStatement.setLong(5, boardFile.getFileSize());
            preparedStatement.setString(6, boardFile.getFileExtension());
            return preparedStatement.executeUpdate();

        }
    }

    public static String generateFileDirectoryName() {
        return LocalDateTime.now().toLocalDate().toString().substring(0, 10);
    }
}
