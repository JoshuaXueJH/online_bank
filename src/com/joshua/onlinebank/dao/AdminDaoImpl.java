package com.joshua.onlinebank.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joshua.onlinebank.domain.Admin;

public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {

	@Override
	public Admin getAdmin(String username) {
		String hql = "from Admin as a where a.username=:username";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString("username", username);
		return (Admin) query.uniqueResult();
	}

	@Override
	public boolean modifyAdminAccount(Admin admin) {
		super.getHibernateTemplate().merge(admin);
		return true;
	}

}
