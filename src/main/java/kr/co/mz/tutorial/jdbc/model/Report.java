package kr.co.mz.tutorial.jdbc.model;

public class Report {

    private int seq;
    private int boardSeq;
    private int reporterSeq;
    private String title;
    private String content;

    public Report(int boardSeq, int reporterSeq, String title, String content) {
        this.boardSeq = boardSeq;
        this.reporterSeq = reporterSeq;
        this.title = title;
        this.content = content;
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

    public int getReporterSeq() {
        return reporterSeq;
    }

    public void setReporterSeq(int reporterSeq) {
        this.reporterSeq = reporterSeq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
