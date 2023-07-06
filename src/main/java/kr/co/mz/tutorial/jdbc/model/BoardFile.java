package kr.co.mz.tutorial.jdbc.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardFile extends AbstractModel {

    private int seq;
    private int boardSeq;
    private String fileUuid;
    private String fileName;
    private String filePath;
    private long fileSize;
    private String fileExtension;

    public BoardFile() {
    }

    public BoardFile(int seq, int boardSeq, String fileUuid, String fileName, String filePath, long fileSize,
        String fileExtension) {
        this.seq = seq;
        this.boardSeq = boardSeq;
        this.fileUuid = fileUuid;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.fileExtension = fileExtension;
    }

    public BoardFile(String fileUuid, String fileName, String filePath, long fileSize, String fileExtension) {
        this.fileUuid = fileUuid;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.fileExtension = fileExtension;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getBoardSeq() {
        return boardSeq;
    }

    public void setBoardSeq(int boardSeq) {
        this.boardSeq = boardSeq;
    }

    public String getFileUuid() {
        return fileUuid;
    }

    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public static BoardFile formResultSet(ResultSet resultSet) throws SQLException {
        return new BoardFile(
            resultSet.getInt(1), resultSet.getInt(8), resultSet.getString(9),
            resultSet.getString(10), resultSet.getString(11), resultSet.getLong(12),
            resultSet.getString(13)
        );
    }
}
