package com.basic.bigdata.service;

import com.basic.bigdata.model.TSpouttuplecount;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 79875 on 2017/3/8.
 */
public interface TSpouttuplecountService extends BaseService<TSpouttuplecount> {
    public List<Timestamp> getTimeLineInfo();

    public List<TSpouttuplecount> getSpoutTupleCountByTimeinfo(Timestamp time);

    public Long getSumSpoutTupleCountByTimeinfo(Timestamp time);

    public List<Long> getSumListSpoutTupleCountByTimeInfo();

    public void deleteAll();

    public String querySpoutTuplecountBypage(int page, int size);
}
