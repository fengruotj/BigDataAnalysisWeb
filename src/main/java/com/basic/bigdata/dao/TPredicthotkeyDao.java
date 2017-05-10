package com.basic.bigdata.dao;

import com.basic.bigdata.model.TPredicthotkey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * locate com.basic.bigdata.dao
 * Created by 79875 on 2017/5/9.
 */
@Repository("tPredicthotkeyDao")
public class TPredicthotkeyDao extends BaseHibernateDAOImpl<TPredicthotkey> {
    private static final Logger log = LoggerFactory.getLogger(TPredicthotkeyDao.class);

    @SuppressWarnings("unchecked")
    public List<TPredicthotkey> queryTupleByPage(int page, int size) {
        return getSession().createQuery("FROM TPredicthotkey")
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .list();
    }

    public long getCount() {
        // TODO 自动生成的方法存根
        return (long) getSession().createQuery("SELECT count(c) FROM TPredicthotkey c ")
                .uniqueResult();
    }
}
