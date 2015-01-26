package com.greenhi.admin.pay.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenhi.admin.pay.dao.PayDAO;
import com.greenhi.admin.pay.service.PayService;
import com.greenhi.admin.pay.vo.PayVO;
import com.greenhi.common.exception.ApplicationException;
import com.greenhi.common.response.Status;

/**
 * 지급 관리 service implement
 * 
 * @author  won.lee
 * @date    2014. 8. 7. 
 * @history  
 */
@Service
public class PayServiceImpl implements PayService {
	
    private static final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);

    @Autowired
    private PayDAO payDao;

    public PayServiceImpl() {
    }

	@Override
	public List<PayVO> list( PayVO data ) throws Exception {

		List<PayVO> list = null;
		
		try {
			list = payDao.list( data );
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "지급  리스트 얻기 실패(DB)");
		}
		return list;
	}

	@Override
	public int listCount( PayVO data ) throws Exception {

		int count = 0;
		
		try {
			count = payDao.listCount( data );
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "지급  리스트 수 (DB)");
		}
		return count;
	}

	@Override
	public PayVO get( PayVO data ) throws Exception {

		try {
			
			return payDao.get( data );
			
		} catch ( SQLException e ) {
    		throw new ApplicationException(Status.FAIL, "지급 조회 실패(DB)");
		}

	}

	@Override
    public long insertPay( PayVO data ) throws Exception {
    	if(logger.isDebugEnabled())
    		logger.debug("insertPay data : {}", data.toString());

		long key = 0;

		try {

	    	//지급 등록
	    	payDao.insertPay( data );
	    	
	    	key = data.getPayNo();
	    	
			if ( key < 1 ) {
				return 0;
			}
		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "지급  등록 실패(DB)");
		}
		
        return key;
    }

	@Override
	@Transactional
	public int updatePay( PayVO data ) throws Exception {
    	if(logger.isDebugEnabled())
    		logger.debug("updatePay data : {}", data.toString());

		int result = 0;

		try {
			
			result = payDao.updatePay( data );

		} catch ( SQLException se ) {
    		throw new ApplicationException(Status.FAIL, "지급  수정 실패(DB)");
		}
		
        return result;
	}

	@Override
	@Transactional
	public int deletePay( PayVO data ) throws Exception {
    	if(logger.isDebugEnabled())
    		logger.debug("deletePay data : {}", data.toString());

		int result = 0;

		try {
			
			result = payDao.deletePay( data );

		} catch ( SQLException se ) {
			throw new Exception( "지급 삭제 실패(DB)", se );
		}

        return result;
	}

}