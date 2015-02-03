package com.greenhi.admin.bank.service;

import java.util.List;

import com.greenhi.admin.bank.vo.DayBankBranchVO;

/**
 * 일자별 은행 지점 관리 service
 * 
 * @author  won.lee
 * @date    2014. 8. 7. 
 * @history  
 */
public interface DayBankBranchService {
  
	/**
	 * 일자별 은행 지점  리스트 조회
	 * 
	 * @param  user 조회 조건
	 * @return user 일자별 은행 지점 리스트 정보
	 * @throws Exception
	 * @history 
	 */
	public List<DayBankBranchVO> list( DayBankBranchVO data ) throws Exception;

    /**
     * 일자별 은행 지점 등록
     *
     * @param data 일자별 은행 지점 데이타
     * @return DayBankBranchVO 처리 완료 된 일자별 은행 지점정보
     * @throws Exception
     */
    public int saveDayBankBranch( DayBankBranchVO data, String [] dayBankBranchArray ) throws Exception;  

}