package com.basic.bigdata.service;

import com.basic.bigdata.model.TTuplecount;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 79875 on 2017/3/8.
 */
public interface TTuplecountService extends BaseService<TTuplecount> {
    public List<Timestamp> getTimeLineInfo();

    public List<TTuplecount> getTupleCountByTimeinfo(Timestamp time);

    public Long getSumTupleCountByTimeinfo(Timestamp time);

    public List<Long> getSumListTupleCountByTimeInfo();

    public void deleteAll();

    public String queryTuplecountBypage(int page, int size);

}
