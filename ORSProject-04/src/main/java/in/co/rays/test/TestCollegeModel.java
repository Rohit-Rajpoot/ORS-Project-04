package in.co.rays.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CollegeModel;

public class TestCollegeModel {
	

	public static void main(String[] args) throws DuplicateRecordException {

		testAdd();
		// testDelete();
		// searchFindByName();
		// searchFindByPk();
		// update();
		// search();
        // list();

	}
	
	private static void testAdd() throws DuplicateRecordException {
		try {
			CollegeBean bean = new CollegeBean();
			// bean.setId(2L);
			bean.setName("DAVV");
			bean.setAddress("Indore");
			bean.setState("MP");
			bean.setCity("Indore");
			bean.setPhoneNo("7777776666");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			
			CollegeModel model = new CollegeModel();
			long pk = model.add(bean);
			System.out.println("Test Add Successfull");
			CollegeBean addedBean = model.findByPk(pk);
			if (addedBean == null) {
				System.out.println("Test Add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void update() {
		try {
			CollegeBean bean = new CollegeBean();
			bean.setName("AU University");
			bean.setAddress("Lonavala");
			bean.setId(4L);
			CollegeModel model = new CollegeModel();
			model.update(bean);
			System.out.println("Record Updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void testDelete() {

		try {
			CollegeBean bean = new CollegeBean();
			bean.setId(3L);
			CollegeModel model = new CollegeModel();
			model.delete(bean);
			System.out.println("record delete");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	private static void list() {
		try {
			CollegeBean bean = new CollegeBean();
			List list = new ArrayList();
			CollegeModel  model = new CollegeModel();
			list = model.list(1,2);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (CollegeBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getAddress());
				System.out.println(bean.getState());
				System.out.println(bean.getCity());
				System.out.println(bean.getPhoneNo());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDatetime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void search() {
		 try {
	            CollegeBean bean = new CollegeBean();
	            List list = new ArrayList();
	            bean.setName("IIT kharagpur");
	            
	            CollegeModel model = new CollegeModel();
	            list = model.search(bean, 0, 0);
	            if (list.size() == 0) {
	                System.out.println("Test Search fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (CollegeBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getName());
	                System.out.println(bean.getAddress());
	                System.out.println(bean.getState());
	                System.out.println(bean.getCity());
	                System.out.println(bean.getPhoneNo());
	                System.out.println(bean.getCreatedBy());
	                System.out.println(bean.getCreatedDatetime());
	                System.out.println(bean.getModifiedBy());
	                System.out.println(bean.getModifiedDatetime());
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
		
	}


	private static void searchFindByPk() {

		try {

			CollegeModel model = new CollegeModel();

			CollegeBean bean = model.findByPk(5L);
			System.out.println(bean.getId());
			System.out.println(bean.getAddress());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void searchFindByName() {
		try {

			CollegeModel model = new CollegeModel();

			CollegeBean bean = model.findByName("sage");
			System.out.println(bean.getId());
			System.out.println(bean.getAddress());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
