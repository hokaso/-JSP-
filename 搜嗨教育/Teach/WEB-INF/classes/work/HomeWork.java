package work;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DbUtils.HibernateUtil;

public class HomeWork {
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
	public List<Object[]> ComputerOffice(){
		this.init();
		String sql = "select c.cno,c.cname from bean.Course c where ctype='���԰칫'";
		List<Object[]> result=session.createQuery(sql).list();
		this.destroy();
		return result;
	}
	public List<Object[]> Design(){
		this.init();
		String sql = "select c.cno,c.cname from bean.Course c where ctype='ƽ�����'";
		List<Object[]> result=session.createQuery(sql).list();
		this.destroy();
		return result;
	}
	public List<Object[]> Programa(){
		this.init();
		String sql = "select c.cno,c.cname from bean.Course c where ctype='���򿪷�'";
		List<Object[]> result=session.createQuery(sql).list();
		this.destroy();
		return result;
	}
	public List<Object[]> WebDesign(){
		this.init();
		String sql = "select c.cno,c.cname from bean.Course c where ctype='��ҳ���'";
		List<Object[]> result=session.createQuery(sql).list();
		this.destroy();
		return result;
	}
	public List<Object[]> Video(){
		this.init();
		String sql = "select c.cno,c.cname from bean.Course c where ctype='Ӱ�Ӷ���'";
		List<Object[]> result=session.createQuery(sql).list();
		this.destroy();
		return result;
	}
	public List<Object[]> InnerDesign(){
		this.init();
		String sql = "select c.cno,c.cname from bean.Course c where ctype='�������'";
		List<Object[]> result=session.createQuery(sql).list();
		this.destroy();
		return result;
	}
	public List<Object[]> OutDesign(){
		this.init();
		String sql = "select c.cno,c.cname from bean.Course c where ctype='�������'";
		List<Object[]> result=session.createQuery(sql).list();
		this.destroy();
		return result;
	}
	@org.junit.Test
	public List<List<Object[]>> DoHomeWork() {
		List<Object[]> result=ComputerOffice();
		List<Object[]> result2=Design();
		List<Object[]> result3=Programa();
		List<Object[]> result4=WebDesign();
		List<Object[]> result5=Video();
		List<Object[]> result6=InnerDesign();
		List<Object[]> result7=OutDesign();
		List<List<Object[]>> all=new ArrayList<List<Object[]>>();
		all.add(result);
		all.add(result2);
		all.add(result3);
		all.add(result4);
		all.add(result5);
		all.add(result6);
		all.add(result7);
		
//			for(int j=0;j<all.get(0).size();j++){
//				System.out.println(all.get(0).get(j)[1]);
//			}
			
		

		return all;
	}
	
}
