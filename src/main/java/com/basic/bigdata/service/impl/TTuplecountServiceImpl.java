package com.basic.bigdata.service.impl;

import com.basic.bigdata.model.TTuplecount;
import com.basic.bigdata.service.TTuplecountService;
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
 * Created by 79875 on 2017/3/9.
 */
@Transactional(propagation= Propagation.REQUIRED)
@Service("tTuplecountService")
public class TTuplecountServiceImpl extends BaseServiceImpl implements TTuplecountService {
    @Override
    public List<Timestamp> getTimeLineInfo() {
        List<Timestamp> list = tTuplecountDAO.findList("select distinct(time) from TTuplecount order by time");
        return list;
    }

    @Override
    public List<TTuplecount> getTupleCountByTimeinfo(Timestamp time) {
        List<TTuplecount> TuleCountList = tTuplecountDAO.findByProperty("time", time);
        return TuleCountList;
    }

    @Override
    public Long getSumTupleCountByTimeinfo(Timestamp time) {
        Object o = tTuplecountDAO.get("select sum(tuplecount) from TTuplecount where time=?", time);
        return (Long)o;
    }

    @Override
    public List<Long> getSumListTupleCountByTimeInfo() {
        List<Long> list = tTuplecountDAO.findList("select sum(tuplecount) from TTuplecount group by time order by time");
        return list;
    }

    @Override
    public void deleteAll() {
        tTuplecountDAO.execute("delete from TTuplecount");
    }

    @Override
    public String queryTuplecountBypage(int page, int size) {
        List<TTuplecount> tTuplecounts=tTuplecountDAO.queryTupleByPage(page,size);
        Long total=tTuplecountDAO.getCount();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Map map=new HashMap();
        map.put("total",total);
        map.put("rows",tTuplecounts);
        return gson.toJson(map);
    }

    @Override
    public void save(TTuplecount tTuplecount) {
        tTuplecountDAO.save(tTuplecount);
    }

    @Override
    public void update(TTuplecount tTuplecount) {
        tTuplecountDAO.update(tTuplecount);
    }

    @Override
    public void merge(TTuplecount tTuplecount) {
        tTuplecountDAO.getSession().merge(tTuplecount);
    }

    @Override
    public void delete(Long id) {
        tTuplecountDAO.delete(id);
    }

    @Override
    public TTuplecount get(Long id) {
        return tTuplecountDAO.getById(id);
    }

    @Override
    public List<TTuplecount> queryALL() {
        return tTuplecountDAO.findList("from TTuplecount");
    }
}
