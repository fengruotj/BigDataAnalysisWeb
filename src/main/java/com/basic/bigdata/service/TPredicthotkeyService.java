package com.basic.bigdata.service;

import com.basic.bigdata.model.TPredicthotkey;

import java.util.List;

/**
 * locate com.basic.bigdata.service
 * Created by 79875 on 2017/5/9.
 */
public interface TPredicthotkeyService extends BaseService<TPredicthotkey> {
    public List<Long> getIdList();

    public List<Integer> getKeySizeList();
}
