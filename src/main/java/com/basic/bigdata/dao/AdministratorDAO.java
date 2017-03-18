package com.basic.bigdata.dao;

import com.basic.bigdata.model.Administrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * Administrator entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 */

@Repository("tAdministratorDAO")
public class AdministratorDAO extends BaseHibernateDAOImpl<Administrator> {
	private static final Logger log = LoggerFactory.getLogger(AdministratorDAO.class);

	@SuppressWarnings("unchecked")
	public List<Administrator> queryTUserByPage(String name, int page, int size){
		return getSession().createQuery("FROM Administrator p  WHERE p.name LIKE :name ORDER BY p.id")
				.setString("name","%" +name +"%")
				.setFirstResult((page-1)*size)
				.setMaxResults(size)
				.list();
	}

	public long getCount(String name) {
		// TODO 自动生成的方法存根
		return (long) getSession().createQuery("SELECT count(c) FROM Administrator c WHERE c.name LIKE :name")
				.setString("name","%" +name +"%")
				.uniqueResult();
	}
}
