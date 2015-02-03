package com.greenhi.admin.clean.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenhi.admin.clean.dao.CleanInfoDAO;
import com.greenhi.admin.clean.service.CleanInfoService;
import com.greenhi.admin.clean.vo.CleanInfoVO;
import com.greenhi.admin.clean.vo.CleanVO;
import com.greenhi.common.exception.ApplicationException;
import com.greenhi.common.response.Status;

/**
 * 청소 관리 service implement
 * 
 * @author  won.lee
 * @date    2014. 8. 7. 
 * @history  
 */
@Service
public class CleanInfoServiceImpl implements CleanInfoService {
	
    private static final Logger logger = LoggerFactory.getLogger(CleanInfoServiceImpl.class);

    @Autowired
    private CleanInfoDAO cleanInfoDAO;

    public CleanInfoServiceImpl() {
    }

	@Override
	public List<CleanInfoVO> list( CleanInfoVO data ) throws Exception {

		List<CleanInfoVO> list = null;
		
		try {
			list = cleanInfoDAO.list( data );
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "청소  리스트 얻기 실패(DB)");
		}
		return list;
	}

	@Override
	public int listCount( CleanInfoVO data ) throws Exception {

		int count = 0;
		
		try {
			count = cleanInfoDAO.listCount( data );
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "청소  리스트 수 (DB)");
		}
		return count;
	}

	@Override
	public CleanVO get( CleanVO data ) throws Exception {

		try {
			
			return cleanInfoDAO.get( data );
			
		} catch ( SQLException e ) {
    		throw new ApplicationException(Status.FAIL, "청소 조회 실패(DB)");
		}

	}

	@Override
    public long insertClean( CleanVO data ) throws Exception {
    	if(logger.isDebugEnabled())
    		logger.debug("insertClean data : {}", data.toString());

		long key = 0;

		try {

	    	//청소 등록
	    	cleanInfoDAO.insertClean( data );
	    	
	    	key = data.getBranchNo();
	    	
			if ( key < 1 ) {
				return 0;
			}
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "청소  등록 실패(DB)");
		}
		
        return key;
    }

	@Override
	@Transactional
	public int updateClean( CleanVO data ) throws Exception {
    	if(logger.isDebugEnabled())
    		logger.debug("updateClean data : {}", data.toString());

		int result = 0;

		try {
			
			result = cleanInfoDAO.updateClean( data );

		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "청소  수정 실패(DB)");
		}
		
        return result;
	}

	@Override
	@Transactional
	public int deleteClean( CleanVO data ) throws Exception {
    	if(logger.isDebugEnabled())
    		logger.debug("deleteClean data : {}", data.toString());

		int result = 0;

		try {
			
			result = cleanInfoDAO.deleteClean( data );

		} catch ( SQLException se ) {
			throw new Exception( "청소 삭제 실패(DB)", se );
		}

        return result;
	}

}