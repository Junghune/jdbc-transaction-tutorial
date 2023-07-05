package kr.co.mz.tutorial.jdbc.model;

public class Likes {

    private int seq;
    private int boardSeq;
    private int customerSeq;

    public Likes(int boardSeq, int customerSeq) {
        this.boardSeq = boardSeq;
        this.customerSeq = customerSeq;
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

    public int getCustomerSeq() {
        return customerSeq;
    }

    public void setCustomerSeq(int customerSeq) {
        this.customerSeq = customerSeq;
    }
}
