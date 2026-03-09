package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.RoleBean;
import in.co.rays.bean.TimeTableBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModel;
import in.co.rays.model.RoleModel;
import in.co.rays.model.SubjectModel;
import in.co.rays.model.TimeTableModel;
import in.co.rays.utility.DataUtility;
import in.co.rays.utility.DataValidator;
import in.co.rays.utility.PropertyReader;
import in.co.rays.utility.ServletUtility;

@WebServlet(name = "TimeTableCtl", urlPatterns = { "/ctl/TimeTableCtl" })
public class TimeTableCtl extends BaseCtl{
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("semester"))) {
			request.setAttribute("semester", PropertyReader.getValue("error.require", "Semester"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("examDate"))) {
			request.setAttribute("examDate", PropertyReader.getValue("error.require", "Date Of Exam"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("examDate"))) {
			request.setAttribute("examDate", PropertyReader.getValue("error.date", "Date Of Exam"));
			pass = false;
		} else if (DataValidator.isSunday(request.getParameter("examDate"))) {
			request.setAttribute("examDate", "Exam Should Not Be On Sunday");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("examTime"))) {
			request.setAttribute("examTime", PropertyReader.getValue("error.require", "Exam Time"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("courseId"))) {
			request.setAttribute("courseId", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("subjectId"))) {
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		}

		return pass;
		
	}
	
	@Override
	protected void preload(HttpServletRequest request) {

		SubjectModel subjectModel = new SubjectModel();
		CourseModel courseModel = new CourseModel();

			
			try {
				List subjectList = subjectModel.list();
				List courseList = courseModel.list();
				request.setAttribute("courseList", courseList);
				request.setAttribute("subjectList", subjectList);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
		RoleModel roleModel = new RoleModel();
		try {
			List<RoleBean> roleList = roleModel.list();
			request.setAttribute("roleList", roleList);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		
		TimeTableBean bean = new TimeTableBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
		bean.setSemester(DataUtility.getString(request.getParameter("semester")));
		bean.setExamTime(DataUtility.getString(request.getParameter("examTime")));
		bean.setExamDate(DataUtility.getDate(request.getParameter("examDate")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		
		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));

		TimeTableModel model = new TimeTableModel();

		if (id > 0) {
			try {
				TimeTableBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String op = DataUtility.getString(request.getParameter("operation"));

		TimeTableModel model = new TimeTableModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			TimeTableBean bean = (TimeTableBean) populateBean(request);

			TimeTableBean bean1;
			TimeTableBean bean2;
			TimeTableBean bean3;

			try {
				bean1 = model.checkByCourseName(bean.getCourseId(), bean.getExamDate());

				bean2 = model.checkBySubjectName(bean.getCourseId(), bean.getSubjectId(), bean.getExamDate());

				bean3 = model.checkBySemester(bean.getCourseId(), bean.getSubjectId(), bean.getSemester(),
						bean.getExamDate());

				if (bean1 == null && bean2 == null && bean3 == null) {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Timetable Added Successfully", request);
				} else {
					bean = (TimeTableBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Timetable Already Exist!", request);
				}
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Timetable Already Exist!", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {

			TimeTableBean bean = (TimeTableBean) populateBean(request);

//			TimeTableBean bean4;

			try {

//				bean4 = model.checkByExamTime(bean.getCourseId(), bean.getSubjectId(), bean.getSemester(),
//						bean.getExamDate(), bean.getExamTime(), bean.getDescription());

     			if (id > 0) {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Timetable Updated Successfully", request);
				} else {
					bean = (TimeTableBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Timetable Already Exist!", request);
				}
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Timetable Already Exist!", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.TIMETABLE_VIEW;
	}

}
