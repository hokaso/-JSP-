package work;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DbUtils.HibernateUtil;
import bean.*;
import bean.Student;
public class Test {
	Session session;
	Transaction tx;
	
	//��ʼ��������������
	public void init(){
		session=HibernateUtil.getSessionObject();
		tx=session.beginTransaction();
	}
	//�ύ��ѯ
	public void destroy(){
		tx.commit();
		session.close();
	}
	@org.junit.Test
	public void aa(){
		this.init();
		//��ѯ����phone��hql���
		String hql = "from bean.Look";
		List<Look> phones = session.createQuery(hql).list();
		for(int i=0;i<phones.size();i++){
			System.out.println(phones.get(i).getLtime());
		}
			
	
		this.destroy();
	}
}
