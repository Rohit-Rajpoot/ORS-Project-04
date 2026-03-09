package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.utility.ServletUtility;

@WebFilter(filterName = "FrontController", urlPatterns = {"/ctl/*", "/doc/*"})
public class FrontController implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		if (session.getAttribute("user") == null) {
			// request.setAttribute("message", " Your Session has been Expired... Please
			// Login Again"); 
			ServletUtility.setErrorMessage(" Your Session Has Been Expired... Please Login Again", req);
			
//		    Set the URI
			
//			We set the URI because if the session is expired at the time you hit the request and we are back to login page 
//			Then the request you had hit is stored in the URI and when you login again the URI will open the same page 

			String uri = req.getRequestURI();
			request.setAttribute("URI", uri);

			System.out.println("URI  get kiya frontcontroler me" + uri);

			ServletUtility.forward(ORSView.LOGIN_VIEW, req, resp);

			return;
		} else {
			chain.doFilter(req, resp);
	}

		
}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	

}
