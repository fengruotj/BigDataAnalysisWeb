package com.basic.bigdata.dao;

import com.basic.bigdata.model.TWordcount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * TWordcount entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 */
@Repository("tWordcountDAO")
public class TWordcountDAO extends BaseHibernateDAOImpl<TWordcount> {
	private static final Logger log = LoggerFactory.getLogger(TWordcountDAO.class);

	@SuppressWarnings("unchecked")
	public List<TWordcount> queryWordByPage(String word, int page, int size){
		log.info("queryTWordByPage----"+word+" "+page+" "+size);
		return getSession().createQuery("FROM TWordcount p  WHERE p.word LIKE :word ORDER BY p.id")
				.setString("word","%" +word +"%")
				.setFirstResult((page-1)*size)
				.setMaxResults(size)
				.list();
	}

	public long getCountBypage(String word) {
		// TODO 自动生成的方法存根
		return (long) getSession().createQuery("SELECT count(c) FROM TWordcount c WHERE c.word LIKE :word")
				.setString("word","%" +word +"%")
				.uniqueResult();
	}
}
