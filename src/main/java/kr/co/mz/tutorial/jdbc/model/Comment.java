package kr.co.mz.tutorial.jdbc.model;

public class Comment {

    private int seq;
    private int customerSeq;
    private int boardSeq;
    private String content;

    public Comment(int customerSeq, int boardSeq, String content) {
        this.customerSeq = customerSeq;
        this.boardSeq = boardSeq;
        this.content = content;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getCustomerSeq() {
        return customerSeq;
    }

    public void setCustomerSeq(int customerSeq) {
        this.customerSeq = customerSeq;
    }

    public int getBoardSeq() {
        return boardSeq;
    }

    public void setBoardSeq(int boardSeq) {
        this.boardSeq = boardSeq;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
