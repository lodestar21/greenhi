package com.greenhi.admin.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.greenhi.admin.user.vo.UserVO;

/**
 * 사용자관리 service
 * table : T_USER
 * 
 * @author  won.lee
 * @date    2014. 8. 7. 
 * @history  
 */
public interface UserService {
  
	/**
	 * 로그인 체크
	 * 
     * @param loginUser 회원 데이타, 세션
     * @return boolean 처리 완료 여부
	 * @throws Exception
	 * @history 
	 */
	public int login( UserVO loginUser, HttpSession session, HttpServletRequest req ) throws Exception;

}