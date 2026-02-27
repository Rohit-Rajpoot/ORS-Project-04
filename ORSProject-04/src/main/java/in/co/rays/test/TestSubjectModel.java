package in.co.rays.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.SubjectBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.SubjectModel;


public class TestSubjectModel {
	
	public static void main(String[] args) throws Exception {
		
		 testAdd();
		// testDelete();
		// testFindByName();
		// testUpdate();
		// testFindByPk();
        // testSearch();
		// testList();

	}
  
	public static void testAdd() throws DuplicateRecordException {

		try {
			SubjectModel model = new SubjectModel();

			SubjectBean bean = new SubjectBean();
			// bean.setId(1);
			bean.setName("Data Science");
			bean.setDescription("Program Analysis");
			bean.setCourseId(4);
//			bean.setCourseName("B.C.A");
			bean.setCreatedBy("admin");
			bean.setModifiedBy("admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			long pk = model.add(bean);
			
			System.out.println("Data Added");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testDelete() {
		try {
			SubjectBean bean = new SubjectBean();
			SubjectModel model = new SubjectModel();

			long pk = 1L;
			bean.setId(1);
			model.Delete(bean);
			System.out.println("Test Deleted");
			/*
			 * SubjectBean deleteBean=model.findByPK(pk); if(deleteBean == null) {
			 * System.out.println("Test Delete fail"); }
			 */

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByName() {
		try {
			
			SubjectModel model = new SubjectModel();

			SubjectBean bean = new SubjectBean();
			bean = model.findByName("css");
			if (bean == null) {
				System.out.println("test findBy Name fail");
			}

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getModifiedDatetime());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() {
		try {
			SubjectModel model = new SubjectModel();

			SubjectBean bean = model.findByPk(2L);
			bean.setName("java");
			// bean.setDescription("commerce");
			model.update(bean);
			System.out.println("update succ");

			/*
			 * SubjectBean updateBean=model.testFindByPK(2L); if(!
			 * "ram".equals(updateBean.getSubjectName())){
			 * System.out.println("test update fail"); }
			 */

		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByPk() {
		try {
			SubjectModel model = new SubjectModel();

			SubjectBean bean = new SubjectBean();
			long pk = 2L;
			bean = model.findByPk(pk);
			if (bean == null) {
				System.out.println("test findbypk fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testSearch() throws DatabaseException {
		try {
			SubjectBean bean = new SubjectBean();
			SubjectModel model = new SubjectModel();

			//bean.setSubjectName("Java");
		    // bean.setId(2L);
			//bean.setCourseId(2L);
			bean.setCourseName("Computers Commarce");
			List list = new ArrayList();
			list = model.search(bean,1,10);

			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (SubjectBean) it.next();
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testList() throws Exception {
		try {
			SubjectModel model = new SubjectModel();

			SubjectBean bean = new SubjectBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (SubjectBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
				System.out.println(bean.getCourseId());
				System.out.println(bean.getCourseName());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedDatetime());

			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
