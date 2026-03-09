package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.TimeTableBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.CourseModel;
import in.co.rays.model.SubjectModel;
import in.co.rays.model.TimeTableModel;
import in.co.rays.utility.DataUtility;
import in.co.rays.utility.PropertyReader;
import in.co.rays.utility.ServletUtility;

@WebServlet(name = "TimeTableListCtl", urlPatterns = { "/ctl/TimeTableListCtl" })
public class TimeTableListCtl extends BaseCtl{
	
	@Override
	protected void preload(HttpServletRequest request) {

		SubjectModel subjectModel = new SubjectModel();
		CourseModel courseModel = new CourseModel();

			try {
				List subjectList = subjectModel.list();
				List courseList = courseModel.list();
				
				request.setAttribute("subjectList", subjectList);
				request.setAttribute("courseList", courseList);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
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

		return bean;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		TimeTableBean bean = (TimeTableBean) populateBean(request);
		TimeTableModel model = new TimeTableModel();

		try {
			List<TimeTableBean> list = model.search(bean, pageNo, pageSize);
			List<TimeTableBean> next = model.search(bean, pageNo + 1, pageSize);

			if (list == null || list.isEmpty()) {
				ServletUtility.setErrorMessage("No record found", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			request.setAttribute("nextListSize", next.size());

			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		TimeTableBean bean = (TimeTableBean) populateBean(request);
		TimeTableModel model = new TimeTableModel();

		try {
			List<TimeTableBean> list = model.search(bean, pageNo, pageSize);
			List<TimeTableBean> next = model.search(bean, pageNo + 1, pageSize);

			if (list == null || list.isEmpty()) {
				ServletUtility.setErrorMessage("No record found", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			request.setAttribute("nextListSize", next.size());

			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
		
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.TIMETABLE_LIST_VIEW;
	}

}
