package com.greenhi.admin.user.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenhi.admin.user.dao.UserDAO;
import com.greenhi.admin.user.service.UserService;
import com.greenhi.admin.user.vo.UserVO;
import com.greenhi.common.Constants;

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

}