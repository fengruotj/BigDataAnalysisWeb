package com.basic.bigdata.dto;

import java.util.List;

/**
 * Created by 79875 on 2017/3/9.
 */
public class EChartsWordCount {
    private List<String> timewordinfo;
    private List<Long> count;

    public EChartsWordCount(List<String> timewordinfo, List<Long> count) {
        this.timewordinfo = timewordinfo;
        this.count = count;
    }

    public EChartsWordCount() {
    }

    public List<String> getTimewordinfo() {
        return timewordinfo;
    }

    public void setTimewordinfo(List<String> timewordinfo) {
        this.timewordinfo = timewordinfo;
    }

    public List<Long> getCount() {
        return count;
    }

    public void setCount(List<Long> count) {
        this.count = count;
    }
}
