package com.greenhi.admin.clean.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.greenhi.admin.clean.vo.CleanInfoVO;
import com.greenhi.admin.clean.vo.CleanVO;

/**
 * 청소 관리 dao
 * 
 * @author  won.lee
 * @date    2014. 8. 7. 
 * @history  
 */
@Repository
public interface CleanInfoDAO {

	/**
	 * 청소 리스트 조회
	 * 
	 * @param  CleanInfoVO 조회 조건
	 * @return CleanInfoVO 청소 리스트 정보
	 * @throws Exception
	 * @history 
	 */
	public List<CleanInfoVO> list( CleanInfoVO data ) throws Exception;

	/**
	 * 청소 리스트 카운트 조회
	 * 
	 * @param  CleanInfoVO 조회 조건
	 * @return int 청소 리스트 카운트
	 * @throws Exception
	 * @history 
	 */
	public int listCount( CleanInfoVO data ) throws Exception;

    /**
     * 청소 조회
     * 
     * @param  user 유저 정보
     * @return BankBranchVO 유저 정보
     * @throws Exception
     * @history 
     */
    public CleanVO get( CleanVO data ) throws Exception;

    /**
     * 청소 등록
     *
     * @param data 청소 등록 데이타
     * @return long USER_ID
     * @throws Exception
     */
    public void insertClean( CleanVO data ) throws Exception;

    /**
     * 청소 수정
     * 
     * @param data 청소 등록 데이타
     * @return int 결과
     * @throws Exception
     * @history 
     */
    public int updateClean( CleanVO data ) throws Exception;

    /**
     * 청소 삭제
     * 
     * @param data 청소 삭제 데이타
     * @return int 결과
     * @throws Exception
     * @history 
     */
    public int deleteClean( CleanVO data ) throws Exception;
}