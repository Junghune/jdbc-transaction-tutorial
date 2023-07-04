package kr.co.mz.tutorial.jdbc.model;

public class BoardFile extends AbstractModel {

    private int seq;
    private int boardSeq;
    private String fileUuid;
    private String fileName;
    private String filePath;
    private long fileSize;
    private String fileType;

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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
