package com.greenhi.admin.code.service;

import java.util.List;

import com.greenhi.admin.code.vo.CodeVO;

/**
 * 코드관리 service
 * 
 * @author  won.lee
 * @date    2014. 12. 28.
 * @history 
 */
public interface CodeService {

	/**
	 * 코드 리스트 조회(자식 리스트)
	 * 
	 * @param  code 조회 조건
	 * @return code 코드 리스트 정보
	 * @throws Exception
	 * @history 
	 */
	public List<CodeVO> listChildCode( CodeVO code ) throws Exception;
	
	/**
	 * 코드 리스트 조회
	 * 
	 * @param  code 조회 조건
	 * @return code 코드 리스트 정보
	 * @throws Exception
	 * @history 
	 */
	public List<CodeVO> list( CodeVO code ) throws Exception;

	/**
	 * 코드 리스트 카운트 조회
	 * 
	 * @param  code 조회 조건
	 * @return int 코드 리스트 카운트
	 * @throws Exception
	 * @history 
	 */
	public int listCount( CodeVO code ) throws Exception;

	/**
	 * 코드  정보 조회
	 * 
	 * @param  data 코드  정보
	 * @return BoardVO 코드  정보
	 * @throws Exception
	 * @history 
	 */
	public CodeVO get( CodeVO data ) throws Exception;

    /**
     * 코드 등록
     *
     * @param data 코드 데이타
     * @return UserVO 처리 완료 된 코드 정보
     * @throws Exception
     */
    public long insertCode( CodeVO data ) throws Exception;

	/**
	 * 코드 수정
	 * 
     * @param data 코드 데이타
     * @return UserVO 처리 완료 된 코드 정보
     * @throws Exception
	 * @history 
	 */
    public boolean updateCode( CodeVO data ) throws Exception;

	/**
	 * 코드 삭제
	 * 
     * @param data 코드 데이타
     * @return UserVO 처리 완료 된 코드 정보
     * @throws Exception
	 * @history 
	 */
    public boolean deleteCode( CodeVO data ) throws Exception;
}