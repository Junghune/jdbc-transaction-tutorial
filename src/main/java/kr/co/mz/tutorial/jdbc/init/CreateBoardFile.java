package kr.co.mz.tutorial.jdbc.init;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;
import kr.co.mz.tutorial.jdbc.db.HikariPoolFactory;
import kr.co.mz.tutorial.jdbc.file.FileService;
import kr.co.mz.tutorial.jdbc.model.BoardFile;

public class CreateBoardFile {

    private static final String QUERY = """
        insert into board_file(board_seq,file_uuid,file_name,file_path,file_size,file_type) 
        values(?,?,?,?,?,?)""";

    public static void main(String[] args) throws SQLException, IOException {
        var dataSource = HikariPoolFactory.createHikariDataSource();
        System.out.println("쿼리 성공한 행수 : " + createBoardFile(
                dataSource.getConnection(),
                new BoardFile(UUID.randomUUID().toString(), "직박구리부리박기.txt",
                    FileService.BASIC_DIRECTORY + FileService.generateFileDirectoryName() + "/직박구리부리박기.txt",
                    999, "txt"
                )
            )
        );
    }

    public static int createBoardFile(Connection connection, BoardFile boardFile)
        throws SQLException {
        String fileDirectoryName = FileService.generateFileDirectoryName() + File.separator + boardFile.getFileName();
        try (
            var preparedStatement = connection.prepareStatement(QUERY);
        ) {
            preparedStatement.setInt(1, boardFile.getBoardSeq());
            preparedStatement.setString(2, UUID.randomUUID().toString());
            preparedStatement.setString(4, boardFile.getFileName());
            preparedStatement.setString(3, fileDirectoryName);
            preparedStatement.setLong(5, boardFile.getFileSize());
            preparedStatement.setString(6, boardFile.getFileExtension());
            return preparedStatement.executeUpdate();
        }
    }
}
