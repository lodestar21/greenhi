package com.greenhi.admin.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import com.greenhi.common.Constants;

/**
 * 어플리케이션 세션체크
 * 
 * @author  won.lee
 * @date    2014. 8. 8. 
 * @history  
 */
@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {
	
    private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);
    
	private final static String loginUrl = "login";
    
	@Autowired
	private MessageSource messages;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("requst addr {}", request.getRemoteAddr());

		HttpSession session = request.getSession();
		
		String[] urlArray = request.getRequestURI().split( "/" );
		if ( urlArray.length < 1 ) {
			return true;
		}
		String endUrl = urlArray[urlArray.length - 1];

		if ( session.getAttribute( Constants.ADMIN_INFO_KEY ) != null ) {

			logger.debug( "session get attribuete success" );
			return true;

		} else if ( loginUrl.equals( endUrl ) ) {

			return true;
			
		} else {

			logger.debug( "session get attribuete fail" );
			session.setAttribute( Constants.RETURN_URL_KEY, request.getRequestURI() );

			response.sendRedirect( request.getContextPath() + "/User/login" );

			return false;
		}
	}
}
