package com.greenhi.admin.bank.service;

import java.util.List;

import com.greenhi.admin.bank.vo.BankBranchVO;

/**
 * 은행 지점 관리 service
 * 
 * @author  won.lee
 * @date    2014. 8. 7. 
 * @history  
 */
public interface BankBranchService {
  
	/**
	 * 은행 지점  리스트 조회
	 * 
	 * @param  user 조회 조건
	 * @return user 은행 지점 리스트 정보
	 * @throws Exception
	 * @history 
	 */
	public List<BankBranchVO> list( BankBranchVO user ) throws Exception;

	/**
	 * 은행 지점  리스트 카운트 조회
	 * 
	 * @param  user 조회 조건
	 * @return int 은행 지점 리스트 카운트
	 * @throws Exception
	 * @history 
	 */
	public int listCount( BankBranchVO user ) throws Exception;

	/**
	 * 은행 지점 정보 조회
	 * 
	 * @param  user 은행 지점 정보
	 * @return BankBranchVO 은행 지점 정보
	 * @throws Exception
	 * @history 
	 */
	public BankBranchVO get( BankBranchVO user ) throws Exception;

    /**
     * 은행 지점 등록
     *
     * @param data 은행 지점 데이타
     * @return BankBranchVO 처리 완료 된 은행 지점정보
     * @throws Exception
     */
    public long insertBankBranch( BankBranchVO data ) throws Exception;  

	/**
	 * 은행 지점 수정
	 * 
     * @param data 은행 지점 데이타
     * @return BankBranchVO 처리 완료 된 은행 지점정보
     * @throws Exception
	 * @history 
	 */
    public int updateBankBranch( BankBranchVO user ) throws Exception;

    /**
     * 은행 지점 삭제
     * 
     * @param data 은행 지점 삭제 데이타
     * @return int 결과
     * @throws Exception
     * @history 
     */
    public int deleteBankBranch( BankBranchVO data ) throws Exception;

}