package com.greenhi.admin.bank.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.greenhi.admin.bank.vo.DayBankBranchVO;

/**
 * 일자별  관리 dao
 * 
 * @author  won.lee
 * @date    2014. 8. 7. 
 * @history  
 */
@Repository
public interface DayBankBranchDAO {

	/**
	 * 일자별  리스트 조회
	 * 
	 * @param  user 조회 조건
	 * @return user 일자별  리스트 정보
	 * @throws Exception
	 * @history 
	 */
	public List<DayBankBranchVO> list( DayBankBranchVO user ) throws Exception;

    /**
     * 일자별  수정
     * 
     * @param data 일자별  등록 데이타
     * @return int 결과
     * @throws Exception
     * @history 
     */
    public int insertDayBankBranch( DayBankBranchVO user ) throws Exception;

    /**
     * 일자별  삭제
     * 
     * @param data 일자별  삭제 데이타
     * @return int 결과
     * @throws Exception
     * @history 
     */
    public int deleteDayBankBranch( DayBankBranchVO data ) throws Exception;
}