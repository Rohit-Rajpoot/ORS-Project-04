package in.co.rays.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.RoleBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.RoleModel;

public class TestRoleModel {

	public static void main(String[] args) throws Exception {
		   testAdd();
//		 testUpdate();
		// testDelete();
		// testfindByPk();
		// testFindByName();
		// testSearch();
	}

	public static void testAdd() {

		RoleModel model = new RoleModel();
		RoleBean bean = new RoleBean();
		bean.setName("Faculty");
		bean.setDescription("Faculty");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		try {
			long pk = model.add(bean);
			RoleBean addedbean = model.findByPk(pk);
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException | DuplicateRecordException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void testUpdate() {

		try {

			RoleModel model = new RoleModel();
			RoleBean bean = model.findByPk(4L);
			bean.setName("Kiosk");
			bean.setDescription("Kiosk");

			model.update(bean);
			
			System.out.println("Data Updated Successfully");

		} catch (ApplicationException | DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	public static void testDelete() {
		try {
			
			RoleModel model = new RoleModel();
			RoleBean bean = new RoleBean();
			long pk = 1L;
			bean.setId(pk);
			model.delete(bean);
			RoleBean deletedbean = model.findByPk(pk);
			if (deletedbean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testfindByPk() {
		try {
			
			RoleModel model = new RoleModel();
			RoleBean bean = model.findByPk(1L);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByName() {
		try {
			
			RoleModel model = new RoleModel();
			RoleBean bean = model.findByName("admin");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testSearch() {
		try {
			
			RoleModel model = new RoleModel();
			RoleBean bean = new RoleBean();
			List list = new ArrayList();
			bean.setName("student");
			list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (RoleBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
