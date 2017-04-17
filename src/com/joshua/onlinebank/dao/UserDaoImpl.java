package com.joshua.onlinebank.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joshua.onlinebank.domain.Account;
import com.joshua.onlinebank.domain.Personinfo;
import com.joshua.onlinebank.domain.Status;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public Account getAccount(String username) {
		String hql = "from Account as a where a.username=:username";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString("username", username);
		return (Account) query.uniqueResult();
	}

	@Override
	public boolean modifyAccount(Account account) {
		super.getHibernateTemplate().merge(account);
		//super.getHibernateTemplate().update(account);
		return true;
	}

	@Override
	public boolean modifyPersoninfo(Personinfo personinfo) {
		super.getHibernateTemplate().update(personinfo);
		return true;
	}

	@Override
	public void reflush(Account account) {
		super.getHibernateTemplate().refresh(account);
	}

	@Override
	public Account getAccount(Integer accountid) {
		return super.getHibernateTemplate().get(Account.class, accountid);
	}

	@Override
	public Status getStatus(Integer id) {
		return super.getHibernateTemplate().get(Status.class, id);
	}

	@Override
	public List searchPersoninfo(Status status) {
		String hql = "from Personinfo p where p.account.status.id=:id";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger("id", status.getId());
		return query.list();
	}

	@Override
	public List getAllPersonInfos() {
		String hql = "from Personinfo";
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}

	@Override
	public List searchPersoninfo(Personinfo personinfo) {
		return super.getHibernateTemplate().executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria c = session.createCriteria(Personinfo.class);
				if (personinfo.getRealname() != null && !personinfo.getRealname().equals("")) {
					if (personinfo.getCardid() != null) {
						c.add(Restrictions.or(Restrictions.eq("realname", personinfo.getRealname()),
								Restrictions.eq("cardid", personinfo.getCardid())));
					} else {
						c.add(Restrictions.like("realname", personinfo.getRealname(), MatchMode.ANYWHERE));
					}
				}
				c.addOrder(Order.asc("id"));
				return c.list();
			}
		});
	}

	@Override
	public void addAccount(Account account) {
		super.getHibernateTemplate().save(account);
	}

	@Override
	public void addPersoninfo(Personinfo personinfo) {
		super.getHibernateTemplate().save(personinfo);
	}

	@Override
	public void del(Account account) {
		super.getHibernateTemplate().delete(account);
	}

}
