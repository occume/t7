package com.hy.wo.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.hy.wo.util.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LockInterceptor implements Interceptor{

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		
		ActionContext actionContext = invocation.getInvocationContext();  
		
		HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		ServletContext servletContext=(ServletContext) actionContext.get(StrutsStatics.SERVLET_CONTEXT);
		
		request.setCharacterEncoding(Constants.DEFAULT_CHARSET);
		
	
		return invocation.invoke();
	}

	private static final long serialVersionUID = 1285536698782766365L;
}
