package in.co.rays.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CourseBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModel;


public class TestCourseModel {
	
	public static void main(String[] args) throws Exception {
		
		   testAdd();
		// testDelete();
		// testFindByName();
		// testFindByPk();
		// testUpdate();
		// testSearch();
        // testList();
		
	}
	
	
	
	
	public static void testAdd() throws DuplicateRecordException {
		
		try {
			CourseModel model = new CourseModel();
			CourseBean bean= new CourseBean();
//			bean.setId(2);
			bean.setName("B.Tech");
			bean.setDescription("Bachelor Of Technology");
			bean.setDuration("4 Year");
			bean.setCreatedBy("admin");
			bean.setModifiedBy("admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			model.add(bean);
			
			System.out.println("Data Added Successfully");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void testDelete() {
		try {
			CourseModel model = new CourseModel();
			CourseBean bean = new CourseBean();
			long pk=3L;
			bean.setId(1);
			model.Delete(bean);
			System.out.println("Test Deleted");
			/*
			 * CourseBean deleteBean=model.findByPK(pk); if(deleteBean == null) {
			 * System.out.println("Test Delete fail"); }
			 */
		}catch(ApplicationException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void testFindByName() {
		try {
			CourseModel model = new CourseModel();
			CourseBean bean=new CourseBean();
			bean=model.findByName("B.com");
			if(bean==null) {
				System.out.println("test findBy Name fail");
			}
		
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getDuration());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getModifiedDatetime());
			
		}catch(ApplicationException e) {
			e.printStackTrace();
		}
	}
	
	public static void testFindByPk() {
		try {
			CourseModel model = new CourseModel();
			CourseBean bean=new CourseBean();
			long pk=1L;
			bean=model.findByPk(pk);
			if(bean==null) {
				System.out.println("test findbypk fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getDuration());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		}catch(ApplicationException e) {
			e.printStackTrace();
		}
	}
	
	public static void testUpdate() {
		try {
			CourseModel model = new CourseModel();
			CourseBean bean = model.findByPk(1L);
			bean.setName("Mca");
			//bean.setDescription("commerce");
			model.update(bean);
			System.out.println("update succ");
			/*
			 * CourseBean updateBean=model.testFindByPK(2L); if(!
			 * "ram".equals(updateBean.getName())){ System.out.println("test update fail");
			 * }
			 */
			 
		}catch(ApplicationException e) {
			e.printStackTrace();
		}catch(DuplicateRecordException e) {
			e.printStackTrace();
		}
	}
	
	public static void testSearch() throws DatabaseException {
		try {
			CourseModel model = new CourseModel();
			CourseBean bean = new CourseBean();
			List list = new ArrayList();
			list=model.search(bean);
			
			Iterator it=list.iterator();
			while(it.hasNext()) {
				bean = (CourseBean) it.next();
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
				System.out.println(bean.getDuration());
			}
		}catch(ApplicationException e) {
			e.printStackTrace();
		}
	}
	
	
	  public static void testList() throws Exception { try { 
		  
		  CourseModel model = new CourseModel();
		  CourseBean bean = new CourseBean();
		  List list = new ArrayList();
		  list =model.list(1,10);
	  if(list.size() < 0) { 
		  System.out.println("test list fail");
		  } 
	  Iterator it=list.iterator();
	  while(it.hasNext()) {
		  bean=(CourseBean) it.next();
	  System.out.println(bean.getName());
	  System.out.println(bean.getDescription());
	  System.out.println(bean.getDuration());
	  
	  }
	  
	  }catch(ApplicationException e) {
		  e.printStackTrace(); 
		  } 
	  }

}
