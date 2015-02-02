package com.greenhi.admin.bank.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenhi.admin.bank.dao.DayBankBranchDAO;
import com.greenhi.admin.bank.service.DayBankBranchService;
import com.greenhi.admin.bank.vo.DayBankBranchVO;
import com.greenhi.common.exception.ApplicationException;
import com.greenhi.common.response.Status;

/**
 * 일자별 은행 지점 관리 service implement
 * 
 * @author  won.lee
 * @date    2014. 8. 7. 
 * @history  
 */
@Service
public class DayBankBranchServiceImpl implements DayBankBranchService {
	
    private static final Logger logger = LoggerFactory.getLogger(BankBranchServiceImpl.class);

    @Autowired
    private DayBankBranchDAO dayBankBranchDao;

    public DayBankBranchServiceImpl() {
    }

	@Override
	public List<DayBankBranchVO> list( DayBankBranchVO data ) throws Exception {

		List<DayBankBranchVO> list = null;
		
		try {
			list = dayBankBranchDao.list( data );
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "일자별 은행 지점  리스트 얻기 실패(DB)");
		}
		return list;
	}

	@Override
    public long insertDayBankBranch( DayBankBranchVO data ) throws Exception {
    	if(logger.isDebugEnabled())
    		logger.debug("insertDayBankBranch data : {}", data.toString());

		int result = 0;

		try {

	    	//일자별 은행 지점 등록
			result = dayBankBranchDao.insertDayBankBranch( data );
	    	
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "일자별 은행 지점  등록 실패(DB)");
		}
		
        return result;
    }

	@Override
	@Transactional
	public int deleteDayBankBranch( DayBankBranchVO data ) throws Exception {
    	if(logger.isDebugEnabled())
    		logger.debug("deleteDayBankBranch data : {}", data.toString());

		int result = 0;

		try {
			
			result = dayBankBranchDao.deleteDayBankBranch( data );

		} catch ( SQLException se ) {
			throw new Exception( "일자별 은행 지점 삭제 실패(DB)", se );
		}

        return result;
	}

}