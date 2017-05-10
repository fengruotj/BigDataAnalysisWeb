package com.basic.bigdata.service.impl;

import com.basic.bigdata.model.TPredicthotkey;
import com.basic.bigdata.service.TPredicthotkeyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * locate com.basic.bigdata.service
 * Created by 79875 on 2017/5/9.
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service("tPredicthotkeyService")
public class TPredicthotkeyServiceImpl extends BaseServiceImpl implements TPredicthotkeyService {

    @Override
    public List<Long> getIdList() {
        List<Long> list = tPredicthotkeyDao.findList("select id from TPredicthotkey");
        return list;
    }

    @Override
    public List<Integer> getKeySizeList() {
        List<Integer> list = tPredicthotkeyDao.findList("select keysize from TPredicthotkey");
        return list;
    }

    @Override
    public void save(TPredicthotkey tPredicthotkey) {
        tPredicthotkeyDao.save(tPredicthotkey);
    }

    @Override
    public void update(TPredicthotkey tPredicthotkey) {
        tPredicthotkeyDao.update(tPredicthotkey);
    }

    @Override
    public void merge(TPredicthotkey tPredicthotkey) {
        tPredicthotkeyDao.getSession().merge(tPredicthotkey);
    }

    @Override
    public void delete(Long id) {
        tPredicthotkeyDao.getById(id);
    }

    @Override
    public TPredicthotkey get(Long id) {
        return tPredicthotkeyDao.getById(id);
    }

    @Override
    public List<TPredicthotkey> queryALL() {
        return tPredicthotkeyDao.findList("from TPredicthotkey");
    }
}
