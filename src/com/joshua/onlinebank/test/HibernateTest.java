package com.joshua.onlinebank.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernateTest {
	@Test
	public void SessionFactoryTest(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory1");
		System.out.println(sessionFactory);
	}

}
