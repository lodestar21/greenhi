package com.greenhi.admin.user.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenhi.admin.user.dao.UserDAO;
import com.greenhi.admin.user.service.UserService;
import com.greenhi.admin.user.vo.UserVO;
import com.greenhi.common.Constants;
import com.greenhi.common.exception.ApplicationException;
import com.greenhi.common.response.Status;
import com.greenhi.common.util.StringUtil;

/**
 * 사용자관리 service implement
 * table : TN_ADMIN_USER
 * 
 * @author  won.lee
 * @date    2014. 8. 7. 
 * @history  
 */
@Service
public class UserServiceImpl implements UserService {
	
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDao;

    public UserServiceImpl() {
    }

	@Override
	public int login( UserVO loginUser, HttpSession session, HttpServletRequest request ) throws Exception {

		boolean loginResult = false;
		
		UserVO user = new UserVO();
		user.setUserId( loginUser.getUserId() );
		user.setUserStat( Constants.USER_STAT_201 );
		user.setIsDelete( "N" );
		
		user = userDao.get( user );
		
		if ( user == null ) {
			return -1;
		} else {
			
			loginResult = BCrypt.checkpw( loginUser.getPassWord(), user.getPassWord() );

			if ( loginResult ) {
				session.setAttribute( Constants.ADMIN_INFO_KEY, user );
			} else {
				return -2;
			}
			
		}
		
		return 1;
	}

	@Override
	public List<UserVO> list( UserVO user ) throws Exception {

		List<UserVO> list = null;
		
		try {
			list = userDao.list( user );
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "회원 리스트 얻기 실패(DB)");
		}
		return list;
	}

	@Override
	public int listCount( UserVO user ) throws Exception {

		int count = 0;
		
		try {
			count = userDao.listCount( user );
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "회원 리스트 수 (DB)");
		}
		return count;
	}

	@Override
	public UserVO get( UserVO user ) throws Exception {

		try {
			
			return userDao.get( user );
			
		} catch ( SQLException e ) {
    		throw new ApplicationException(Status.FAIL, "가입자 조회 실패(DB)");
		}

	}

	@Override
    public long insertUser( UserVO user ) throws Exception {
    	if(logger.isDebugEnabled())
    		logger.debug("insertUser data : {}", user.toString());

		long key = 0;

		try {

			UserVO userVo = new UserVO();
			userVo.setUserId( user.getUserId().trim() );
			
	    	// 아이디 중복 체크
	    	if( userDao.selectDuplicatedUserId( userVo ) != null) {
				return -1;
	    	}
	    	
	    	user.setUserId( user.getUserId().trim() );
	    	user.setPassWord( BCrypt.hashpw( user.getPassWord().trim(), BCrypt.gensalt( Constants.SALT_VALUE ) ) );
	    	
	    	//회원등록
	    	userDao.insertUser( user );
	    	
	    	key = user.getUserNo();
	    	
			if ( key < 1 ) {
				return 0;
			}
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "회원 등록 실패(DB)");
		}
		
        return key;
    }

	@Override
	@Transactional
	public int updateUser( UserVO user ) throws Exception {
    	if(logger.isDebugEnabled())
    		logger.debug("updateUser data : {}", user.toString());

		int result = 0;

		try {
			
	    	// 비밀번호 값이 존재하는 경우에 암호화 처리
	    	if ( !StringUtil.isEmpty( user.getPassWord() ) ) {
		    	user.setPassWord( BCrypt.hashpw( user.getPassWord().trim(), BCrypt.gensalt( Constants.SALT_VALUE ) ) );
	    	}
	    	
	    	if ( user.getUserType() == 101 ) {
	    		user.setCustName( "" );
	    		user.setLocalCode( 0 );
	    	} else if ( user.getUserType() == 102 ) {
	    		user.setLocalCode( 0 );
	    	} else if ( user.getUserType() == 103 ) {
	    		user.setCustName( "" );
	    	}
			
			result = userDao.updateUser( user );

		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "회원 수정 실패(DB)");
		}
		
        return result;
	}

}