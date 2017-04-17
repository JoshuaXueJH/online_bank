package com.joshua.onlinebank.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joshua.onlinebank.domain.Account;
import com.joshua.onlinebank.domain.Transactionlog;
import com.joshua.onlinebank.domain.Transactiontype;

public class TranDaoImpl extends HibernateDaoSupport implements TranDao {

	@Override
	public Transactiontype getTranType(String name) {
		String hql = "from Transactiontype t where t.name=:name";
		Query query = super.getSession().createQuery(hql);
		query.setString("name", name);
		return (Transactiontype) query.uniqueResult();
	}

	@Override
	public boolean addLog(Transactionlog log) {
		super.getHibernateTemplate().save(log);
		return true;
	}

	@Override
	public List getLogs(Account account, int page) {
		return super.getHibernateTemplate().executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria c = session.createCriteria(Transactionlog.class);
				c.add(Restrictions.or(Restrictions.eq("accountByFromid", account),
						Restrictions.eq("toid", account.getAccountid())));
				c.addOrder(Order.desc("id"));
				c.setFirstResult(10 * (page - 1));
				c.setMaxResults(10);
				return c.list();
			}
		});
	}

	@Override
	public int getCountOfLogs(Account account) {
		String sql = "select count(*) from Transactionlog where (fromid=" + account.getAccountid() + " or toid="
				+ account.getAccountid() + ")";
		Query query = this.getSession().createSQLQuery(sql);
		Integer count = Integer.parseInt(query.uniqueResult().toString());
		return count;
	}

}
