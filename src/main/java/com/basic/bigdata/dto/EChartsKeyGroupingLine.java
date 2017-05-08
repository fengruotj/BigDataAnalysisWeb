package com.basic.bigdata.dto;

import java.util.List;

/**
 * locate com.basic.bigdata.dto
 * Created by 79875 on 2017/5/8.
 */
public class EChartsKeyGroupingLine {
    private List timewordinfo;
    private List<Long> count;

    public EChartsKeyGroupingLine() {
    }

    public EChartsKeyGroupingLine(List<String> timewordinfo, List<Long> count) {
        this.timewordinfo = timewordinfo;
        this.count = count;
    }

    public List getTimewordinfo() {
        return timewordinfo;
    }

    public void setTimewordinfo(List timewordinfo) {
        this.timewordinfo = timewordinfo;
    }

    public List<Long> getCount() {
        return count;
    }

    public void setCount(List<Long> count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "EChartsKeyGroupingLine{" +
                "boltinfo=" + timewordinfo +
                ", count=" + count +
                '}';
    }
}
