package com.basic.bigdata.service.impl;

import com.basic.bigdata.model.THdfsbytecount;
import com.basic.bigdata.service.THdfsbytecountService;
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
 * Created by 79875 on 2017/3/20.
 */
@Transactional(propagation= Propagation.REQUIRED)
@Service("tHdfsbytecountService")
public class THdfsbytecountServiceImpl extends BaseServiceImpl implements THdfsbytecountService {
    @Override
    public List<Timestamp> getTimeLineInfo() {
        List<Timestamp> list = tHdfsbytecountDAO.findList("select distinct(time) from THdfsbytecount order by time");
        return list;
    }

    @Override
    public List<THdfsbytecount> getTupleCountByTimeinfo(Timestamp time) {
        List<THdfsbytecount> TuleCountList = tHdfsbytecountDAO.findByProperty("time", time);
        return TuleCountList;
    }

    @Override
    public Long getSumTupleCountByTimeinfo(Timestamp time) {
        Object o = tHdfsbytecountDAO.get("select sum(bytecount) from THdfsbytecount where time=?", time);
        return (Long)o;
    }

    @Override
    public List<Long> getSumListTupleCountByTimeInfo() {
        List<Long> list = tHdfsbytecountDAO.findList("select sum(bytecount) from THdfsbytecount group by time order by time");
        return list;
    }

    @Override
    public void deleteAll() {
        tHdfsbytecountDAO.execute("delete from THdfsbytecount");
    }

    @Override
    public String queryTuplecountBypage(int page, int size) {
        List<THdfsbytecount> tHdfsbytecountList=tHdfsbytecountDAO.queryTupleByPage(page,size);
        Long total=tHdfsbytecountDAO.getCount();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Map map=new HashMap();
        map.put("total",total);
        map.put("rows",tHdfsbytecountList);
        return gson.toJson(map);
    }

    @Override
    public void save(THdfsbytecount tHdfsbytecount) {
        tHdfsbytecountDAO.save(tHdfsbytecount);
    }

    @Override
    public void update(THdfsbytecount tHdfsbytecount) {
        tHdfsbytecountDAO.update(tHdfsbytecount);
    }

    @Override
    public void merge(THdfsbytecount tHdfsbytecount) {
        tHdfsbytecountDAO.getSession().merge(tHdfsbytecount);
    }

    @Override
    public void delete(Long id) {
        tHdfsbytecountDAO.delete(id);
    }

    @Override
    public THdfsbytecount get(Long id) {
        return tHdfsbytecountDAO.getById(id);
    }

    @Override
    public List<THdfsbytecount> queryALL() {
        return tHdfsbytecountDAO.findList("from THdfsbytecount");
    }
}
