package com.greenhi.admin.code.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenhi.admin.code.dao.CodeDAO;
import com.greenhi.admin.code.service.CodeService;
import com.greenhi.admin.code.vo.CodeVO;
import com.greenhi.common.exception.ApplicationException;
import com.greenhi.common.response.Status;

/**
 * 코드관리 service implement
 * 
 * @author  won.lee
 * @date    2014. 12. 28. 
 * @history  
 */
@Service
public class CodeServiceImpl implements CodeService {
	
    private static final Logger logger = LoggerFactory.getLogger(CodeServiceImpl.class);

    @Autowired
    private CodeDAO codeDao;
	
    public CodeServiceImpl() {
    }

	@Override
	public List<CodeVO> listChildCode( CodeVO code ) throws Exception {

		List<CodeVO> list = null;
		
		try {
			list = codeDao.listChildCode( code );
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "코드 리스트 얻기 실패(DB)");
		}
		return list;
	}

	@Override
	public List<CodeVO> list( CodeVO code ) throws Exception {

		List<CodeVO> list = null;
		
		try {
			list = codeDao.list( code );
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "코드 리스트 얻기 실패(DB)");
		}
		return list;
	}

	@Override
	public int listCount( CodeVO code ) throws Exception {

		int count = 0;
		
		try {
			count = codeDao.listCount( code );
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "코드 리스트 수 (DB)");
		}
		return count;
	}

	@Override
	public CodeVO get( CodeVO data ) throws Exception {

		try {
			
			return codeDao.get( data );
			
		} catch ( SQLException e ) {
    		throw new ApplicationException(Status.FAIL, "코드 조회 실패(DB)");
		}

	}

	@Override
    public long insertCode( CodeVO data ) throws Exception {
    	if(logger.isDebugEnabled())
    		logger.debug("insertCode data : {}", data.toString());

		long key = 0;

		try {

	    	//코드 중복 체크
	    	if( codeDao.selectDuplicatedCdId( data ) != null) {
				return -1;
	    	}
	    	
			int result = codeDao.insertCode( data );
	    	
	    	key = data.getCodeId();
	    	
			if ( key < 1 || result == 0 ) {
				return 0;
			}
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "코드 등록 실패(DB)");
		}
		
        return key;
    }

	@Override
	@Transactional
	public boolean updateCode( CodeVO data ) throws Exception {
    	if(logger.isDebugEnabled())
    		logger.debug("updateCode data : {}", data.toString());

		int result = 0;

		try {
			
			result = codeDao.updateCode( data );

		} catch ( SQLException se ) {
			throw new Exception( "코드 수정 실패(DB)", se );
		}
		
		return result != 0;
	}

	@Override
	@Transactional
	public boolean deleteCode( CodeVO data ) throws Exception {
    	if(logger.isDebugEnabled())
    		logger.debug("deleteCode data : {}", data.toString());

		int result = 0;

		try {
			
			result = codeDao.deleteCode( data );

		} catch ( SQLException se ) {
			throw new Exception( "코드 삭제 실패(DB)", se );
		}
		
		return result != 0;
	}
}