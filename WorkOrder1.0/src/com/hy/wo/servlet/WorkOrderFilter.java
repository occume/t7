package com.hy.wo.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.hy.wo.util.Constants;

public class WorkOrderFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(Constants.DEFAULT_CHARSET);
		chain.doFilter(request, response);
	}
	public void init(FilterConfig filterConfig) throws ServletException {}
	public void destroy() {}
}
