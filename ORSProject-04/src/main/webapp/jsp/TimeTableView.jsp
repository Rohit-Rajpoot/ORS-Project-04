<%@page import="in.co.rays.bean.TimeTableBean"%>
<%@page import="in.co.rays.controller.TimeTableCtl"%>
<%@page import="in.co.rays.utility.ServletUtility"%>

<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.rays.utility.HTMLUtility"%>
<%@page import="in.co.rays.utility.DataUtility"%>
<%@page import="in.co.rays.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>

	<form action="<%=ORSView.TIMETABLE_CTL%>" method="POST">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.bean.TimeTableBean"
			scope="request"></jsp:useBean>

		<%
			List<TimeTableBean> courseList = (List<TimeTableBean>) request.getAttribute("courseList");
			List<TimeTableBean> subjectList = (List<TimeTableBean>) request.getAttribute("subjectList");
		%>

		<div align="center">
			<h1 align="center" style="margin-bottom: -15; color: navy">
		    	<%
					if (bean != null && bean.getId() > 0) {
				%>
				Update
				<%
					} else {
				%>
				Add
				<%
					}
				%>
				TimeTable
			</h1>
			
			<div style="height: 15px; margin-bottom: 12px">
                <h3 align="center">
                    <font color="green">
                        <%=ServletUtility.getSuccessMessage(request)%>
                    </font>
                </h3>
                <h3 align="center">
                    <font color="red">
                        <%=ServletUtility.getErrorMessage(request)%>
                    </font>
                </h3>
            </div>

            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
			

			<table>

				<tr>
					<th align="left">Course<span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), courseList)%></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("courseId", request)%></font></td>
				</tr>
				
				<tr>
					<th align="left">Subject<span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("subjectId", String.valueOf(bean.getSubjectId()), subjectList)%></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("subjectId", request)%></font></td>
				</tr>
				
				<tr>
					<th align="left">Semester<span style="color: red">*</span></th>
					<td>
						<%
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("1", "1");
							map.put("2", "2");
							map.put("3", "3");
							map.put("4", "4");
							map.put("5", "5");
							map.put("6", "6");
							map.put("7", "7");
							map.put("8", "8");

							String htmlList = HTMLUtility.getList("semester", bean.getSemester(), map);
						%> <%=htmlList%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("semester", request)%></font></td>
				</tr>
				
				<tr>
					<th align="left">Exam Date<span style="color: red">*</span></th>
					<td><input type="text" id="udatee" name="examDate"
						placeholder="Select Exam Date"
						value="<%=DataUtility.getDateString(bean.getExamDate())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("examDate", request)%></font></td>
				</tr>
				
				<tr>
					<th align="left">Exam Time<span style="color: red">*</span></th>
					<td>
						<%
							LinkedHashMap<String, String> map1 = new LinkedHashMap<String, String>();
							map1.put("08:00 AM to 11:00 AM", "08:00 AM to 11:00 AM");
							map1.put("12:00 PM to 03:00 PM", "12:00 PM to 03:00 PM");
							map1.put("04:00 PM to 07:00 PM", "04:00 PM to 07:00 PM");

							String htmlList1 = HTMLUtility.getList("examTime", bean.getExamTime(), map1);
						%> <%=htmlList1%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("examTime", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Description<span style="color: red">*</span></th>
					<td align="center"><textarea
							style="width: 170px; resize: none;" name="description" rows="3"
							placeholder="Enter Short description"><%=DataUtility.getStringData(bean.getDescription()).trim()%></textarea>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
				</tr>

				<tr>
					<th></th>
					<td></td>
				</tr>

				<tr>
					<th></th>
					
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2">
				    	<input type="submit" name="operation" value="<%=TimeTableCtl.OP_UPDATE%>"> 
						<input type="submit" name="operation" value="<%=TimeTableCtl.OP_CANCEL%>">
					</td>
					<%
						} else {
					%>
					<td align="left" colspan="2">
			    		<input type="submit" name="operation" value="<%=TimeTableCtl.OP_SAVE%>"> 
			    		<input type="submit" name="operation" value="<%=TimeTableCtl.OP_RESET%>">
					</td>
					<%
						}
					%>
					
			</table>
		</div>
	</form>

</body>
</html>