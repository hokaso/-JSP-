package Pet.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Pet.dao.BaseDao;
import Pet.entity.Danmu;
import Pet.entity.Manager;
import Pet.entity.Member;
import Pet.entity.News;
import Pet.entity.Vedio;
import Pet.entity.Vedio1;
import Pet.service.ManagerService;
import Pet.tool.Page;
@Service("managerService")
public class ManagerServiceImpl implements 	ManagerService{

	@Resource
	private BaseDao<Manager> baseDao;
	@Resource
	private BaseDao<News> baseDao1;
	@Resource
	private BaseDao<Danmu> baseDao2;
	@Resource
	private BaseDao<Vedio> baseDao3;
	@Resource
	private BaseDao<Vedio1> baseDao4;
	@Override
	public void ManageMember(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Manager findManagerByIdAndPassword(Manager manager) {
		// TODO Auto-generated method stub
		return baseDao.get("from Manager m where m.id=? and m.password=?", new Object[]{manager.getId(),manager.getPassword()});
	}

	@Override
	public List<News> find(String hql,int page2) {
		// TODO Auto-generated method stub
		int rows = 5;
		return baseDao1.find(hql,page2,rows);
	}
	

	@Override
	public Page queryForPage(int pageSize, int page) {
		// TODO Auto-generated method stub
		 String hql = "select count(*) from News";
		          int count = baseDao.getCount(hql); // �ܼ�¼��
		          int totalPage = Page.countTotalPage(pageSize, count); // ��ҳ��
		          int offset = Page.countOffset(pageSize, page); // ��ǰҳ��ʼ��¼
		          int length = pageSize; // ÿҳ��¼��
		         int currentPage = Page.countCurrentPage(page);
		          List<News> list = baseDao1.queryForPage("from News order by id desc", offset, length); // �÷�ҳ�ļ�¼
		          // �ѷ�ҳ��Ϣ���浽Bean��
		         Page page1 = new Page();
		         for(int i=0; i<list.size(); i++){
		        	 
		        	 String year=String.format("%tY",  list.get(i).getNewsDate());
			         String month=String .format("%tm",  list.get(i).getNewsDate());
			         String day=String .format("%td",  list.get(i).getNewsDate());
			         list.get(i).setYear(year);
			         list.get(i).setMonth(month);
			         list.get(i).setDay(day);
			         //list.get(i).setNewsId(list.get(i).getNewsId());
			         
		         }
		         page1.setPageSize(pageSize);
		         page1.setCurrentPage(currentPage);
		         page1.setAllRow(count);
		         page1.setTotalPage(totalPage);
		         page1.setList(list); 
		         //page1.setList2(list2);
		         page1.init();
		         return page1;
	}

	@Override
	public Page queryForPage1(int pageSize, int page,String T) {
		 String hql = "select count(*) from News where newsTitle like '%"+T+"%'";
         int count = baseDao.getCount(hql); // �ܼ�¼��
         int totalPage = Page.countTotalPage(pageSize, count); // ��ҳ��
         int offset = Page.countOffset(pageSize, page); // ��ǰҳ��ʼ��¼
         int length = pageSize; // ÿҳ��¼��
        int currentPage = Page.countCurrentPage(page);
         String hql1="from News where newsTitle like '%"+T+"%' order by id desc";
         List<News> list = baseDao1.queryForPage(hql1, offset, length); // �÷�ҳ�ļ�¼
        // �ѷ�ҳ��Ϣ���浽Bean��
        Page page1 = new Page();
        page1.setName(T);
        page1.setPageSize(pageSize);
        page1.setCurrentPage(currentPage);
        page1.setAllRow(count);
        page1.setTotalPage(totalPage);
        page1.setList(list);
        
        page1.init();
        return page1;
	}

	@Override
	public boolean updatePassword(Manager manager,String oldPassword) {
		 boolean isOK =baseDao.checkOldPassword("from Manager m where m.id=? and m.password=?", new Object[]{manager.getId(),oldPassword});
        if (!isOK) {
            return false;
        }
        Manager manager1=baseDao.get("from Manager m where m.id=?",new Object[]{manager.getId()});
        manager1.setPassword(manager.getPassword());
        baseDao.update(manager1);
        System.out.println("���³ɹ�");
        return true;
    }

	@Override
	public List<Danmu> findDanmus(int id) {
		
		return (List<Danmu>) baseDao2.find("from Danmu where vedio_id=?",new Object[] {id});
	}
	@Override
	public void addDanmu(Danmu danmu) {
		baseDao2.save(danmu);
		
	}

	@Override
	public List<Vedio> find1(String hql) {
		// TODO Auto-generated method stub
		return baseDao3.find(hql);
	}
	@Override
	public Page queryForPage2(int pageSize, int page) {
		// TODO Auto-generated method stub
		 String hql = "select count(*) from Vedio";
		          int count = baseDao.getCount(hql); // �ܼ�¼��
		          int totalPage = Page.countTotalPage(pageSize, count); // ��ҳ��
		          int offset = Page.countOffset(pageSize, page); // ��ǰҳ��ʼ��¼
		          int length = pageSize; // ÿҳ��¼��
		         int currentPage = Page.countCurrentPage(page);
		          List<Vedio> list = baseDao3.queryForPage("from Vedio order by id desc", offset, length); // �÷�ҳ�ļ�¼
		          // �ѷ�ҳ��Ϣ���浽Bean��
		         Page page1 = new Page();
		         for(int i=0; i<list.size(); i++){
		        	 
		        	 String year=String.format("%tY",  list.get(i).getVedioDate());
			         String month=String .format("%tm",  list.get(i).getVedioDate());
			         String day=String .format("%td",  list.get(i).getVedioDate());
			         list.get(i).setYear(year);
			         list.get(i).setMonth(month);
			         list.get(i).setDay(day);
			         //list.get(i).setNewsId(list.get(i).getNewsId());
			         
		         }
		         page1.setPageSize(pageSize);
		         page1.setCurrentPage(currentPage);
		         page1.setAllRow(count);
		         page1.setTotalPage(totalPage);
		         page1.setList(list); 
		         //page1.setList2(list2);
		         page1.init();
		         return page1;
	}
	
	@Override
	public Page queryForPage3(int pageSize, int page,String T) {
		 String hql = "select count(*) from Vedio where vedioTitle like '%"+T+"%'";
         int count = baseDao.getCount(hql); // �ܼ�¼��
         int totalPage = Page.countTotalPage(pageSize, count); // ��ҳ��
         int offset = Page.countOffset(pageSize, page); // ��ǰҳ��ʼ��¼
         int length = pageSize; // ÿҳ��¼��
        int currentPage = Page.countCurrentPage(page);
         String hql1="from Vedio where vedioTitle like '%"+T+"%' order by id desc";
         List<Vedio> list = baseDao3.queryForPage(hql1, offset, length); // �÷�ҳ�ļ�¼
        // �ѷ�ҳ��Ϣ���浽Bean��
        Page page1 = new Page();
        page1.setName(T);
        page1.setPageSize(pageSize);
        page1.setCurrentPage(currentPage);
        page1.setAllRow(count);
        page1.setTotalPage(totalPage);
        page1.setList(list);
        
        page1.init();
        return page1;
	}
	//��������Ƶ��ҳ
	@Override
	public Page queryForPage4(int pageSize, int page) {
		// TODO Auto-generated method stub
		 String hql = "select count(*) from Vedio1";
         int count = baseDao4.getCount(hql); // �ܼ�¼��
         int totalPage = Page.countTotalPage(pageSize, count); // ��ҳ��
         int offset = Page.countOffset(pageSize, page); // ��ǰҳ��ʼ��¼
         int length = pageSize; // ÿҳ��¼��
        int currentPage = Page.countCurrentPage(page);
         List<Vedio1> list = baseDao4.queryForPage("from Vedio1 order by id desc", offset, length); // �÷�ҳ�ļ�¼
         // �ѷ�ҳ��Ϣ���浽Bean��
        Page page1 = new Page();
        for(int i=0; i<list.size(); i++){
       	 
       	 String year=String.format("%tY",  list.get(i).getVedioDate());
	         String month=String .format("%tm",  list.get(i).getVedioDate());
	         String day=String .format("%td",  list.get(i).getVedioDate());
	         list.get(i).setYear(year);
	         list.get(i).setMonth(month);
	         list.get(i).setDay(day);
	         //list.get(i).setNewsId(list.get(i).getNewsId());
	         
        }
        page1.setPageSize(pageSize);
        page1.setCurrentPage(currentPage);
        page1.setAllRow(count);
        page1.setTotalPage(totalPage);
        page1.setList(list); 
        //page1.setList2(list2);
        page1.init();
        return page1;
	}
//��������Ƶ��ѯ
	@Override
	public Page queryForPage5(int pageSize, int page,String T) {
		 String hql = "select count(*) from Vedio1 where vedioTitle like '%"+T+"%'";
         int count = baseDao4.getCount(hql); // �ܼ�¼��
         int totalPage = Page.countTotalPage(pageSize, count); // ��ҳ��
         int offset = Page.countOffset(pageSize, page); // ��ǰҳ��ʼ��¼
         int length = pageSize; // ÿҳ��¼��
        int currentPage = Page.countCurrentPage(page);
         String hql1="from Vedio1 where vedioTitle like '%"+T+"%' order by id desc";
         List<Vedio1> list = baseDao4.queryForPage(hql1, offset, length); // �÷�ҳ�ļ�¼
        // �ѷ�ҳ��Ϣ���浽Bean��
        Page page1 = new Page();
        page1.setName(T);
        page1.setPageSize(pageSize);
        page1.setCurrentPage(currentPage);
        page1.setAllRow(count);
        page1.setTotalPage(totalPage);
        page1.setList(list);
        
        page1.init();
        return page1;
	}

	@Override
	public List<Vedio> find1(String hql, int page2) {
		int rows = 5;
		return baseDao3.find(hql,page2,rows);
	}
}
