package com.srk.pkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

/**
 * Servlet Filter implementation class Security_Filter
 */
@WebFilter("/Security_Filter")
public class Security_Filter implements Filter {

    /**
     * Default constructor. 
     */
    public Security_Filter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		 PrintWriter pwout= response.getWriter();
	//	HttpServletRequest req = (HttpServletRequest) request;
		 pwout.print("inside Servlet");
		 int len= request.getParameter("ccode").toString().length();
		
		if (len >4)
		 {
			 chain.doFilter(request, response);
			 }
		else
		{
			pwout.print("invalid Input");
			
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
