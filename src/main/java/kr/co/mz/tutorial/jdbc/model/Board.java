package kr.co.mz.tutorial.jdbc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Board extends AbstractModel {

    private int seq;
    private String title;
    private String content;
    private int customerSeq;
    private int likes_count;
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

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Board board = (Board) o;
        return seq == board.seq;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq);
    }

    public static void fromResultSet(ResultSet resultSet, Board board) throws SQLException {
        board.setTitle(resultSet.getString(2));
        board.setContent(resultSet.getString(3));
        board.setCustomerSeq(resultSet.getInt(4));
        board.setLikes_count(resultSet.getInt(5));
        board.setCreatedTime(resultSet.getTimestamp(6));
        board.setModifiedTime(resultSet.getTimestamp(7));
    }
}
