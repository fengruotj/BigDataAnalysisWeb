package com.basic.bigdata.service;

import com.basic.bigdata.model.TWordcount;

import java.util.List;

/**
 * Created by 79875 on 2017/3/8.
 */
public interface TWordcountService extends BaseService<TWordcount> {

    public List<String> getTimeLineWordInfoList();

    public List<Long> getTimeLineCountList();

    public List<Long> getFinalCountList();

    public List<String> getFinalWordList();

    public void deleteAll();

    public String queryTuplecountBypage(String word,int page, int size);
}
