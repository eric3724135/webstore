package com.ericstudio.webstore.interceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuditingInterceptor extends HandlerInterceptorAdapter {
	Logger logger = Logger.getLogger(this.getClass());
	private String user;
	private String productId;

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		response.getStatus() == 302 表示 redirecting 會導頁回去 /products
		if (request.getRequestURI().endsWith("products/add")
				&& response.getStatus() == 302) {
			logger.info(String.format("A New product[%s] Added by %s on %s",
					productId, user, this.getCurrentTime()));
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (request.getRequestURI().endsWith("products/add")
				&& request.getMethod().equals("POST")) {
			user = request.getRemoteUser();
			productId = request.getParameterValues("productId")[0];
		}
		return true;
	}

	
	private String getCurrentTime() {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return formatter.format(calendar.getTime());
	}
}
