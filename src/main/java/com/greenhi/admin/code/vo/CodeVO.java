package com.greenhi.admin.code.vo;

import java.util.Date;

import com.greenhi.admin.common.vo.PagingVO;

/**
 * TC_CODE VO
 * table : TC_CODE
 * 
 * @author  won.lee
 * @date    2014. 8. 11. 
 * @history  
 */
public class CodeVO extends PagingVO {

	static final long serialVersionUID = 3671849762146430480L;
	
    public CodeVO() {}

	private long codeId;
    private Date createTime;
	private long createUser;
    private Date modifyTime;
	private long modifyUser;
	private String isDelete;
    private String isUse;
    private long uperCode;
    private String codeName;
    private int sortOrdr;
    private String remark;

	private int searchField;
	private String searchWord;
	private boolean isEqualSearch;	// 완전일치 검색
	private String orderBy;			// 오래된순 정렬 검색
	private boolean isExcel;
	private int totalCnt;

	public int getSearchField() {
		return searchField;
	}

	public void setSearchField(int searchField) {
		this.searchField = searchField;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public boolean isEqualSearch() {
		return isEqualSearch;
	}

	public void setEqualSearch(boolean isEqualSearch) {
		this.isEqualSearch = isEqualSearch;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isExcel() {
		return isExcel;
	}

	public void setExcel(boolean isExcel) {
		this.isExcel = isExcel;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

    public long getCodeId() {
		return codeId;
	}

	public void setCodeId(long codeId) {
		this.codeId = codeId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(long createUser) {
		this.createUser = createUser;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public long getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(long modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public long getUperCode() {
		return uperCode;
	}

	public void setUperCode(long uperCode) {
		this.uperCode = uperCode;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public int getSortOrdr() {
		return sortOrdr;
	}

	public void setSortOrdr(int sortOrdr) {
		this.sortOrdr = sortOrdr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", CODE_ID:");
        sb.append(this.codeId);
        sb.append(", CREATE_TIME:");
        sb.append(this.createTime);
        sb.append(", CREATE_USER:");
        sb.append(this.createUser);
        sb.append(", MODIFY_TIME:");
        sb.append(this.modifyTime);
        sb.append(", MODIFY_USER:");
        sb.append(this.modifyUser);
        sb.append(", IS_DELETE:");
        sb.append(this.isDelete);
        sb.append(", IS_USE:");
        sb.append(this.isUse);
        sb.append(", UPER_CODE:");
        sb.append(this.uperCode);
        sb.append(", CODE_NAME:");
        sb.append(this.codeName);
        sb.append(", SORT_ORDR:");
        sb.append(this.sortOrdr);
        sb.append(", REMARK:");
        sb.append(this.remark);
        return sb.toString();
    }
}