package com.greenhi.admin.user.service;

import java.util.List;

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

	/**
	 * 사용자 리스트 조회
	 * 
	 * @param  user 조회 조건
	 * @return user 회원 리스트 정보
	 * @throws Exception
	 * @history 
	 */
	public List<UserVO> list( UserVO user ) throws Exception;

	/**
	 * 사용자 리스트 카운트 조회
	 * 
	 * @param  user 조회 조건
	 * @return int 회원 리스트 카운트
	 * @throws Exception
	 * @history 
	 */
	public int listCount( UserVO user ) throws Exception;

	/**
	 * 회원 정보 조회
	 * 
	 * @param  user 회원 정보
	 * @return UserVO 회원 정보
	 * @throws Exception
	 * @history 
	 */
	public UserVO get( UserVO user ) throws Exception;

    /**
     * 회원 등록
     *
     * @param data 회원 데이타
     * @return UserVO 처리 완료 된 회원정보
     * @throws Exception
     */
    public long insertUser( UserVO data ) throws Exception;  

	/**
	 * 회원 수정
	 * 
     * @param data 회원 데이타
     * @return UserVO 처리 완료 된 회원정보
     * @throws Exception
	 * @history 
	 */
    public int updateUser( UserVO user ) throws Exception;

}