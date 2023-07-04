package kr.co.mz.tutorial.jdbc.model;

import java.sql.Timestamp;

public abstract class AbstractModel {

    protected Timestamp createdTime;
    protected Timestamp modifiedTime;

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
