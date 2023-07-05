package kr.co.mz.tutorial.jdbc.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Board extends AbstractModel {

    private int seq;
    private String title;
    private String content;
    private int customerSeq;
    private final Set<BoardFile> boardFileSet = new LinkedHashSet<>();

    public Board() {
    }

    public Board(String title, String content, int customerSeq) {
        this.title = title;
        this.content = content;
        this.customerSeq = customerSeq;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { //같은참조
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {// null, 다른 클래스
            return false;
        }
        Board other = (Board) obj;
        if (seq != other.getSeq()) {
            return false;
        }
        if (title == null) {
            if (other.getTitle() != null) {
                return false;
            }
        } else if (!title.equals(other.getTitle())) {
            return false;
        }
        return true;
    }

}
