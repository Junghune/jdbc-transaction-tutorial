package kr.co.mz.tutorial.jdbc.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Board extends AbstractModel {

    private int seq;
    private String title;
    private String content;
    private int customerSeq;

    private final Set<BoardFile> boardFileSet = new LinkedHashSet<>();

    public Set<BoardFile> getBoardFileSet() {
        return boardFileSet;
    }

    public void addBoardFile(BoardFile boardFile) {
        boardFileSet.add(boardFile);
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
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

    public int getCustomerSeq() {
        return customerSeq;
    }

    public void setCustomerSeq(int customerSeq) {
        this.customerSeq = customerSeq;
    }

}
