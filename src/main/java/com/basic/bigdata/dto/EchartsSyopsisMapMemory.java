package com.basic.bigdata.dto;

import java.io.Serializable;
import java.util.List;

/**
 * locate com.basic.bigdata.dto
 * Created by 79875 on 2017/5/10.
 */
public class EchartsSyopsisMapMemory implements Serializable {
    private List<Long> timeinfo;
    private List<Integer> tupleCount;

    public EchartsSyopsisMapMemory() {
    }

    public EchartsSyopsisMapMemory(List<Long> timeinfo, List<Integer> tupleCount) {
        this.timeinfo = timeinfo;
        this.tupleCount = tupleCount;
    }

    public List<Long> getTimeinfo() {
        return timeinfo;
    }

    public void setTimeinfo(List<Long> timeinfo) {
        this.timeinfo = timeinfo;
    }

    public List<Integer> getTupleCount() {
        return tupleCount;
    }

    public void setTupleCount(List<Integer> tupleCount) {
        this.tupleCount = tupleCount;
    }
}
