package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.rays.bean.DeviceBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.DeviceModel;

public class TestDeviceModel {

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
		testDelete();
	}

	public static void testAdd() throws Exception {

		DeviceBean bean = new DeviceBean();
		DeviceModel model = new DeviceModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setName("xyz");
		bean.setType("SmartPhone");
		bean.setOperatingSystem("xyz");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

	public static void testUpdate() throws ApplicationException, DuplicateRecordException {
	
		DeviceModel model = new DeviceModel();
		
		DeviceBean bean = model.findByPk(15);
		
		bean.setName("Nokia N92");
		bean.setType("SmartPhone");
		
		model.update(bean);
		
	}

	public static void testDelete() throws ApplicationException {
		
		DeviceBean bean = new DeviceBean();
		DeviceModel model = new DeviceModel();
		
		bean.setId(17l);
		model.delete(bean);
	}

}
