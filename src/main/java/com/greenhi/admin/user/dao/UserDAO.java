package com.greenhi.admin.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.greenhi.admin.user.vo.UserVO;

/**
 * 사용자관리 dao
 * 
 * @author  won.lee
 * @date    2014. 8. 7. 
 * @history  
 */
@Repository
public interface UserDAO {

    /**
     * 회원 정보 조회
     * 
     * @param  user 유저 정보
     * @return UserVO 유저 정보
     * @throws Exception
     * @history 
     */
    public UserVO get( UserVO user ) throws Exception;

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
	 * 회원 등록 시 아이디 중복 체크를 위해 기존 아이디 존재하는지 조회
	 * 
	 * @param email 신규 회원 이메일
	 * @return 기존 등록 이메일
	 * @throws Exception
	 */
	public String selectDuplicatedUserId( UserVO user ) throws Exception;
	
    /**
     * 회원 등록
     *
     * @param data 회원등록 데이타
     * @return long USER_ID
     * @throws Exception
     */
    public void insertUser( UserVO user ) throws Exception;

    /**
     * 회원 정보 수정
     * 
     * @param data 회원등록 데이타
     * @return int 결과
     * @throws Exception
     * @history 
     */
    public int updateUser( UserVO user ) throws Exception;
	
}