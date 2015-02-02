package com.greenhi.admin.popup.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenhi.admin.user.service.UserService;
import com.greenhi.admin.user.vo.UserVO;
import com.greenhi.common.util.StringUtil;

/**
 * 팝업 조회 Controller
 * 
 * @author  won.lee
 * @date    2014. 10. 07.
 * @history 
 */
@Controller
@RequestMapping(value = "/popup")
public class PopupController {
	
    private static final Logger logger = LoggerFactory.getLogger(PopupController.class);

    @Autowired
    private UserService userService;
    
	/**
	 * 청소 담당자(사용자) 팝업 조회
	 * 
	 * @param   
	 * @return  
	 * @throws  
	 * @history 
	 */
	@RequestMapping( "/cleanUser" )
	public String userPopUp( HttpSession session,
			Model model,
			@ModelAttribute UserVO data) throws Exception {

		List<UserVO> list = null;
		int totalCount = 0;

		data.setEqualSearch( false );
		data.setSearchField( 1 );
		if ( StringUtil.isEmpty( data.getUserName() ) ) {
			data.setUserName( "" );
		}
		data.setSearchWord( data.getUserName());
		data.setExcel( true );
		data.setUserType( 103 );
		list = userService.list(data);
		totalCount = userService.listCount( data );

		model.addAttribute("resultCount", totalCount);
		model.addAttribute("result", list );
		model.addAttribute("search", data );
		
		return "/popup/user_popup";
	}
	
}