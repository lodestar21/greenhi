package com.greenhi.admin.code.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.greenhi.admin.code.vo.CodeVO;

/**
 * 코드관리 dao
 * 
 * @author  won.lee
 * @date    2014. 12. 28. 
 * @history  
 */
@Repository
public interface CodeDAO {

	/**
	 * 코드 리스트 조회(자식 리스트)
	 * 
	 * @param  user 조회 조건
	 * @return user 코드 리스트 정보
	 * @throws Exception
	 * @history 
	 */
	public List<CodeVO> listChildCode( CodeVO user ) throws Exception;
	
	/**
	 * 코드 리스트 조회
	 * 
	 * @param  user 조회 조건
	 * @return user 코드 리스트 정보
	 * @throws Exception
	 * @history 
	 */
	public List<CodeVO> list( CodeVO user ) throws Exception;

	/**
	 * 코드 리스트 카운트 조회
	 * 
	 * @param  user 조회 조건
	 * @return int 코드 리스트 카운트
	 * @throws Exception
	 * @history 
	 */
	public int listCount( CodeVO user ) throws Exception;

    /**
     * 코드 정보 조회
     * 
     * @param  data 코드 정보
     * @return BoardVO 코드 정보
     * @throws Exception
     * @history 
     */
    public CodeVO get( CodeVO data ) throws Exception;

    /**
     * 코드등록
     *
     * @param data 코드글 등록 데이타
     * @return 
     * @throws Exception
     */
    public int insertCode( CodeVO data ) throws Exception;

    /**
     * 코드 수정
     * 
     * @param data 코드 등록 데이타
     * @return int 결과
     * @throws Exception
     * @history 
     */
    public int updateCode( CodeVO data ) throws Exception;

	/**
	 * 코드 등록 시 코드 중복 체크
	 * 
	 * @param data 코드 데이타
	 * @return 기존 등록 이메일
	 * @throws Exception
	 */
	public String selectDuplicatedCdId( CodeVO data ) throws Exception;

    /**
     * 코드 삭제
     * 
     * @param data 코드 삭제 데이타
     * @return int 결과
     * @throws Exception
     * @history 
     */
    public int deleteCode( CodeVO data ) throws Exception;
}