package com.basic.bigdata.service;

import com.basic.bigdata.model.THdfsbytecount;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 79875 on 2017/3/8.
 */
public interface THdfsbytecountService extends BaseService<THdfsbytecount> {
    public List<Timestamp> getTimeLineInfo();

    public List<THdfsbytecount> getTupleCountByTimeinfo(Timestamp time);

    public Long getSumTupleCountByTimeinfo(Timestamp time);

    public List<Long> getSumListTupleCountByTimeInfo();

    public void deleteAll();

    public String queryTuplecountBypage(int page, int size);

}
