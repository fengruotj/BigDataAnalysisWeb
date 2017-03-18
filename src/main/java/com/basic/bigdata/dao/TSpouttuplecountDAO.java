package com.basic.bigdata.dao;

import com.basic.bigdata.model.TSpouttuplecount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * TSpouttuplecount entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 */
@Repository("tSpouttuplecountDAO")
public class TSpouttuplecountDAO extends BaseHibernateDAOImpl<TSpouttuplecount> {
	private static final Logger log = LoggerFactory.getLogger(TSpouttuplecountDAO.class);

	@SuppressWarnings("unchecked")
	public List<TSpouttuplecount> queryTupleByPage(int page, int size){
		return getSession().createQuery("FROM TSpouttuplecount order by time")
				.setFirstResult((page-1)*size)
				.setMaxResults(size)
				.list();
	}

	public long getCount() {
		// TODO 自动生成的方法存根
		return (long) getSession().createQuery("SELECT count(c) FROM TSpouttuplecount c ")
				.uniqueResult();
	}

}
