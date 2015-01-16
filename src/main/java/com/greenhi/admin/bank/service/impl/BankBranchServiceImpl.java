package com.greenhi.admin.bank.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenhi.admin.bank.dao.BankBranchDAO;
import com.greenhi.admin.bank.service.BankBranchService;
import com.greenhi.admin.bank.vo.BankBranchVO;
import com.greenhi.common.exception.ApplicationException;
import com.greenhi.common.response.Status;

/**
 * 은행 지점 관리 service implement
 * 
 * @author  won.lee
 * @date    2014. 8. 7. 
 * @history  
 */
@Service
public class BankBranchServiceImpl implements BankBranchService {
	
    private static final Logger logger = LoggerFactory.getLogger(BankBranchServiceImpl.class);

    @Autowired
    private BankBranchDAO bankBranchDao;

    public BankBranchServiceImpl() {
    }

	@Override
	public List<BankBranchVO> list( BankBranchVO data ) throws Exception {

		List<BankBranchVO> list = null;
		
		try {
			list = bankBranchDao.list( data );
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "은행 지점  리스트 얻기 실패(DB)");
		}
		return list;
	}

	@Override
	public int listCount( BankBranchVO data ) throws Exception {

		int count = 0;
		
		try {
			count = bankBranchDao.listCount( data );
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "은행 지점  리스트 수 (DB)");
		}
		return count;
	}

	@Override
	public BankBranchVO get( BankBranchVO data ) throws Exception {

		try {
			
			return bankBranchDao.get( data );
			
		} catch ( SQLException e ) {
    		throw new ApplicationException(Status.FAIL, "은행 지점 조회 실패(DB)");
		}

	}

	@Override
    public long insertBankBranch( BankBranchVO data ) throws Exception {
    	if(logger.isDebugEnabled())
    		logger.debug("insertBankBranch data : {}", data.toString());

		long key = 0;

		try {

	    	//은행 지점 등록
	    	bankBranchDao.insertBankBranch( data );
	    	
	    	key = data.getBranchNo();
	    	
			if ( key < 1 ) {
				return 0;
			}
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "은행 지점  등록 실패(DB)");
		}
		
        return key;
    }

	@Override
	@Transactional
	public int updateBankBranch( BankBranchVO data ) throws Exception {
    	if(logger.isDebugEnabled())
    		logger.debug("updateBankBranch data : {}", data.toString());

		int result = 0;

		try {
			
			result = bankBranchDao.updateBankBranch( data );

		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "은행 지점  수정 실패(DB)");
		}
		
        return result;
	}

	@Override
	@Transactional
	public int deleteBankBranch( BankBranchVO data ) throws Exception {
    	if(logger.isDebugEnabled())
    		logger.debug("deleteBankBranch data : {}", data.toString());

		int result = 0;

		try {
			
			result = bankBranchDao.deleteBankBranch( data );

		} catch ( SQLException se ) {
			throw new Exception( "은행 지점 삭제 실패(DB)", se );
		}

        return result;
	}

}