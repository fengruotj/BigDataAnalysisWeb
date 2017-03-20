package com.basic.bigdata.dto;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 79875 on 2017/3/9.
 */
public class EChartsHdfsByteCount {
    private List<Timestamp> timeinfo;
    private List<Long> tupleCount;

    public EChartsHdfsByteCount() {
    }

    public EChartsHdfsByteCount(List<Timestamp> timeinfo, List<Long> tupleCount) {
        this.timeinfo = timeinfo;
        this.tupleCount = tupleCount;
    }

    public List<Timestamp> getTimeinfo() {
        return timeinfo;
    }

    public void setTimeinfo(List<Timestamp> timeinfo) {
        this.timeinfo = timeinfo;
    }

    public List<Long> getTupleCount() {
        return tupleCount;
    }

    public void setTupleCount(List<Long> tupleCount) {
        this.tupleCount = tupleCount;
    }
}
