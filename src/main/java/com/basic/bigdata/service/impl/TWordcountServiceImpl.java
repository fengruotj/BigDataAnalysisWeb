package com.basic.bigdata.service.impl;

import com.basic.bigdata.model.TWordcount;
import com.basic.bigdata.service.TWordcountService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 79875 on 2017/3/9.
 */
@Transactional(propagation= Propagation.REQUIRED)
@Service("tWordcountServiceImpl")
public class TWordcountServiceImpl extends BaseServiceImpl implements TWordcountService {
    @Override
    public void save(TWordcount tWordcount) {
        twordcountDAO.save(tWordcount);
    }

    @Override
    public void update(TWordcount tWordcount) {
        twordcountDAO.update(tWordcount);
    }

    @Override
    public void merge(TWordcount tWordcount) {
        twordcountDAO.getSession().merge(tWordcount);
    }

    @Override
    public void delete(Long id) {
        twordcountDAO.delete(id);
    }

    @Override
    public TWordcount get(Long id) {
        return twordcountDAO.getById(id);
    }

    @Override
    public List<TWordcount> queryALL() {
        return twordcountDAO.findList("from TWordcount");
    }

    @Override
    public List<String> getTimeLineWordInfoList() {
        List<String> list = twordcountDAO.findList("select concat(time,': ',word) from TWordcount");
        return list;
    }

    @Override
    public List<Long> getTimeLineCountList() {
        return twordcountDAO.findList("select a.count from TWordcount a");
    }

    @Override
    public List<Long> getFinalCountList() {
        List<Long> list = twordcountDAO.findList("select max(a.count) from TWordcount a group by word order by word");
        return list;
    }

    @Override
    public List<String> getFinalWordList() {
        return twordcountDAO.findList("select distinct(word) from TWordcount order by word");
    }

    @Override
    public void deleteAll() {
        twordcountDAO.execute("delete from TWordcount");
    }

    @Override
    public String queryTuplecountBypage(String word, int page, int size) {
        List<TWordcount> wordcountList=twordcountDAO.queryWordByPage(word,page, size);
        Long total=twordcountDAO.getCountBypage(word);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Map map=new HashMap();
        map.put("total",total);
        map.put("rows",wordcountList);
        return gson.toJson(map);
    }
}
