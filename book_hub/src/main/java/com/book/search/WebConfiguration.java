package com.book.search;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.book.search.service.LoginService;
import com.book.search.utils.CookieBox;

@Configuration
public class WebConfiguration {
	private static Logger logger = LoggerFactory.getLogger(WebConfiguration.class);

	@Autowired
	LoginService loginService;

	/***************************
	 * DB(h2) client
	 ***************************/
	@Bean
	ServletRegistrationBean h2ServletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}

	/***************************
	 * 로그인 필터
	 ***************************/
	public class LoginFilter implements Filter {

		public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
				throws IOException, ServletException {
			HttpServletRequest httpServletRequest = (HttpServletRequest) req;
			HttpServletResponse httpServletResponse = (HttpServletResponse) res;

			if (httpServletRequest.getRequestURI().startsWith("/WEB-INF/")
					|| httpServletRequest.getRequestURI().startsWith("/css/")
					|| httpServletRequest.getRequestURI().startsWith("/js/")
					|| httpServletRequest.getRequestURI().startsWith("/login")
					|| httpServletRequest.getRequestURI().startsWith("/console")) {
			} else {
				String userID = CookieBox.getUserID(httpServletRequest);

				if (!CookieBox.isLogin(httpServletRequest)) {
					httpServletResponse
							.sendRedirect("http://" + req.getServerName() + ":" + req.getServerPort() + "/login");
					return;
				}
			}

			chain.doFilter(req, res);
		}

		@Override
		public void destroy() {
		}

		@Override
		public void init(FilterConfig fc) throws ServletException {
		}
	}
	
	/***************************
	 * 로그인필터 사용
	 ***************************/
	@Bean
	FilterRegistrationBean loginServetRegistration() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new LoginFilter());
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}	

}
