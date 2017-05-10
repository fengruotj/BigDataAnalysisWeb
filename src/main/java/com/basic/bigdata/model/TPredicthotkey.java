package com.basic.bigdata.model;

/**
 * locate com.basic.bigdata.model
 * Created by 79875 on 2017/5/9.
 */
public class TPredicthotkey implements java.io.Serializable {

    private Long id;
    private Integer keysize;
    private Integer tablelength;

    public TPredicthotkey() {
    }

    public TPredicthotkey(Integer keysize, Integer tablelength) {
        this.keysize = keysize;
        this.tablelength = tablelength;
    }

    public TPredicthotkey(Long id, Integer keysize, Integer tablelength) {
        this.id = id;
        this.keysize = keysize;
        this.tablelength = tablelength;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getKeysize() {
        return keysize;
    }

    public void setKeysize(Integer keysize) {
        this.keysize = keysize;
    }

    public Integer getTablelength() {
        return tablelength;
    }

    public void setTablelength(Integer tablelength) {
        this.tablelength = tablelength;
    }

    @Override
    public String toString() {
        return "TPredicthotkey{" +
                "id=" + id +
                ", keysize=" + keysize +
                ", tablelength=" + tablelength +
                '}';
    }
}
