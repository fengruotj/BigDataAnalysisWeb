package com.basic.bigdata.service.impl;

import com.basic.bigdata.model.TSpouttuplecount;
import com.basic.bigdata.service.TSpouttuplecountService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 79875 on 2017/3/8.
 */
@Transactional(propagation= Propagation.REQUIRED)
@Service("tSpouttuplecountService")
public class TSpouttuplecountServiceImpl extends BaseServiceImpl implements TSpouttuplecountService {

    @Override
    public void save(TSpouttuplecount tSpouttuplecount) {
        tSpouttuplecountDAO.save(tSpouttuplecount);
    }

    @Override
    public void update(TSpouttuplecount tSpouttuplecount) {
        tSpouttuplecountDAO.update(tSpouttuplecount);
    }

    @Override
    public void merge(TSpouttuplecount tSpouttuplecount) {
        tSpouttuplecountDAO.getSession().merge(tSpouttuplecount);
    }

    @Override
    public void delete(Long id) {
        tSpouttuplecountDAO.delete(id);
    }

    @Override
    public TSpouttuplecount get(Long id) {
        return tSpouttuplecountDAO.getById(id);
    }

    @Override
    public List<TSpouttuplecount> queryALL() {
        return tSpouttuplecountDAO.findList("from TSpouttuplecount");
    }

    @Override
    public List<Timestamp> getTimeLineInfo() {
        List<Timestamp> list = tSpouttuplecountDAO.findList("select distinct(time) from TSpouttuplecount order by time");
        return list;
    }

    @Override
    public List<TSpouttuplecount> getSpoutTupleCountByTimeinfo(Timestamp time) {
        List<TSpouttuplecount> spoutTuleCountList = tSpouttuplecountDAO.findByProperty("time", time);
        return spoutTuleCountList;
    }

    @Override
    public Long getSumSpoutTupleCountByTimeinfo(Timestamp time) {
        Object o = tSpouttuplecountDAO.get("select sum(tuplecount) from TSpouttuplecount where time=?", time);
        return (Long)o;
    }

    @Override
    public List<Long> getSumListSpoutTupleCountByTimeInfo(){
        List<Long> list = tSpouttuplecountDAO.findList("select sum(tuplecount) from TSpouttuplecount group by time order by time");
        return list;
    }

    @Override
    public void deleteAll() {
        tSpouttuplecountDAO.execute("delete from TSpouttuplecount");
    }

    @Override
    public String querySpoutTuplecountBypage(int page, int size) {
        List<TSpouttuplecount> tTuplecounts=tSpouttuplecountDAO.queryTupleByPage(page,size);
        Long total=tSpouttuplecountDAO.getCount();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Map map=new HashMap();
        map.put("total",total);
        map.put("rows",tTuplecounts);
        return gson.toJson(map);
    }
}
