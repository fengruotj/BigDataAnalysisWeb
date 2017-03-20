package com.basic.bigdata.dao;

import com.basic.bigdata.model.THdfsbytecount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 79875 on 2017/3/20.
 */
@Repository("tHdfsbytecountDAO")
public class THdfsbytecountDAO extends BaseHibernateDAOImpl<THdfsbytecount> {
    private static final Logger log = LoggerFactory.getLogger(THdfsbytecountDAO.class);

    @SuppressWarnings("unchecked")
    public List<THdfsbytecount> queryTupleByPage(int page, int size){
        return getSession().createQuery("FROM THdfsbytecount order by time")
                .setFirstResult((page-1)*size)
                .setMaxResults(size)
                .list();
    }

    public long getCount() {
        // TODO 自动生成的方法存根
        return (long) getSession().createQuery("SELECT count(c) FROM THdfsbytecount c ")
                .uniqueResult();
    }
}
