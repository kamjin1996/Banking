package com.banking.domain.user;

public class RecUser {
    private Integer id;

    private Integer recid;

    private Integer berecid;

    private String createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecid() {
        return recid;
    }

    public void setRecid(Integer recid) {
        this.recid = recid;
    }

    public Integer getBerecid() {
        return berecid;
    }

    public void setBerecid(Integer berecid) {
        this.berecid = berecid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}