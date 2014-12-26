package com.greenhi.admin.user.dao;

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
	
}