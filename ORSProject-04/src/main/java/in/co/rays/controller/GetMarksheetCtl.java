package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.MarksheetBean;
import in.co.rays.bean.StudentBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.MarksheetModel;
import in.co.rays.model.StudentModel;
import in.co.rays.utility.DataUtility;
import in.co.rays.utility.DataValidator;
import in.co.rays.utility.PropertyReader;
import in.co.rays.utility.ServletUtility;

@WebServlet("/GetMarksheetCtl")
public class GetMarksheetCtl extends BaseCtl{
	
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", PropertyReader.getValue("error.require", "Roll Number"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));

		return bean;
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletUtility.forward(getView(), request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String op = DataUtility.getString(request.getParameter("operation"));

		MarksheetModel model = new MarksheetModel();

     	MarksheetBean bean = (MarksheetBean) populateBean(request);
     	
     	long id = bean.getStudentId();
     	
		if (OP_GO.equalsIgnoreCase(op)) {
			try {
				bean = model.findByRollNo(bean.getRollNo());
				
				if (bean != null){
					ServletUtility.setBean(bean, request);
					
				} else {
					ServletUtility.setErrorMessage("Roll Number Does Not Exists", request);
				}
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.GET_MARKSHEET_VIEW ;
	}

}
