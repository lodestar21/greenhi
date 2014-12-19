package com.greenhi.admin.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.greenhi.common.exception.ApplicationException;

/**
 * 어플리케이션 오류 처리
 * 
 * @author  won.lee
 * @date    2014. 8. 7. 
 * @history  
 */
public class CommonHandlerExceptionResolver implements HandlerExceptionResolver {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonHandlerExceptionResolver.class);

	@Autowired
	private MessageSource messages;
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handle, Exception exception) {
		ModelAndView modelAndView = new ModelAndView();
		//로직에 대해 발생하는 application에서의 오류 처리
		if(exception instanceof ApplicationException) {
			ApplicationException e = (ApplicationException)exception;
			if(e.getStatus() != null) {
				modelAndView.addObject("javax.servlet.error.status_code", e.getStatus().getCode());
				modelAndView.addObject("javax.servlet.error.message", messages.getMessage(e.getStatus().name(), e.getMessageArgs(), null));
			} else {
				modelAndView.addObject("javax.servlet.error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				modelAndView.addObject("javax.servlet.error.message", exception.getMessage());
			}
		} else {
			//시스템 오류
			modelAndView.addObject("javax.servlet.error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			modelAndView.addObject("javax.servlet.error.message", exception.getMessage());
		}
		if(request.getMethod().equals("GET"))
			try{
				response.sendError(1, (String)modelAndView.getModel().get("javax.servlet.error.message"));
			}catch(Exception e){e.printStackTrace();}
		else
			modelAndView.setViewName("error/error");
		logger.error(exception.getMessage(), exception);
		return modelAndView;
	}

}
