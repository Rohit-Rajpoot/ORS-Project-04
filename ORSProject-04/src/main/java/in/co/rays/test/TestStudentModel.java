package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.bean.StudentBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.StudentModel;

public class TestStudentModel {
	
	public static void main(String[] args) {
		
	    testAdd();
//		testDelete();
//		testUpdate();
	}
	
	public static void testAdd() {

		StudentBean bean = new StudentBean();
		bean.setFirstName("Aman");
		bean.setLastName("Sharma");
		bean.setDob(new Date());
		bean.setGender("Male");
		bean.setMobileNo("9876555421");
		bean.setEmail("aman@gmail.com");
		bean.setCollegeId(1);
		bean.setCreatedBy("student");
		bean.setModifiedBy("student");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		StudentModel model = new StudentModel();

		try {
			model.add(bean);
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}
	
	public static void testUpdate() {
		
		StudentModel model = new StudentModel();
		StudentBean bean = new StudentBean();
		
		try {
		
         bean = model.findByPk(2);
         
         bean.setFirstName("Varun");
         bean.setLastName("sharma");
         bean.setCollegeId(2L);
         bean.setEmail("varun@gmail.com");
         
			model.update(bean);
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
		
         
        
	}

}
