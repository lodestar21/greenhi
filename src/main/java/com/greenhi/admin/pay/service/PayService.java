package com.greenhi.admin.pay.service;

import java.util.List;

import com.greenhi.admin.pay.vo.PayVO;

/**
 * 지급 관리 service
 * 
 * @author  won.lee
 * @date    2014. 8. 7. 
 * @history  
 */
public interface PayService {
  
	/**
	 * 지급  리스트 조회
	 * 
	 * @param  data 조회 조건
	 * @return data 지급 리스트 정보
	 * @throws Exception
	 * @history 
	 */
	public List<PayVO> list( PayVO data ) throws Exception;

	/**
	 * 지급  리스트 카운트 조회
	 * 
	 * @param  data 조회 조건
	 * @return int 지급 리스트 카운트
	 * @throws Exception
	 * @history 
	 */
	public int listCount( PayVO data ) throws Exception;

	/**
	 * 지급 정보 조회
	 * 
	 * @param  data 지급 정보
	 * @return PayVO 지급 정보
	 * @throws Exception
	 * @history 
	 */
	public PayVO get( PayVO data ) throws Exception;

    /**
     * 지급 등록
     *
     * @param data 지급 데이타
     * @return PayVO 처리 완료 된 지급정보
     * @throws Exception
     */
    public long insertPay( PayVO data ) throws Exception;  

	/**
	 * 지급 수정
	 * 
     * @param data 지급 데이타
     * @return PayVO 처리 완료 된 지급정보
     * @throws Exception
	 * @history 
	 */
    public int updatePay( PayVO data ) throws Exception;

    /**
     * 지급 삭제
     * 
     * @param data 지급 삭제 데이타
     * @return int 결과
     * @throws Exception
     * @history 
     */
    public int deletePay( PayVO data ) throws Exception;

}