package com.greenhi.admin.clean.vo;

import java.util.Date;

import com.greenhi.admin.common.vo.PagingVO;

/**
 * T_CLEAN VO
 * table : T_CLEAN
 * 
 * @author  won.lee
 * @date    2014. 12. 26.
 * @history 
 */
public class CleanInfoVO extends PagingVO {

    static final long serialVersionUID = -1781310591072370800L;

	private long branchNo;
    private long cleanUserNo;
    private String cleanDate;
    private String custCodeName;
    private String bankCodeName;
    private String localCodeName;
    private String cleanUserName;
    private String siteName;
    private long cleanNo;
    private String stateCodeName1;
    private String stateCodeName2;
    private String stateCodeName3;
    private String stateCodeName4;
    private String stateCodeName5;
    private String remark;
    private Date createTime;

	private int searchField;
	private String searchWord;
	private boolean isExcel;
	private int totalCnt;

    private String isClean;

	private int custCode;
	private int bankCode;
	private int localCode;

    private long custUserNo;

	private int stateCode1;
	private int stateCode2;
	private int stateCode3;
	private int stateCode4;
	private int stateCode5;
	
	public int getStateCode1() {
		return stateCode1;
	}
	public void setStateCode1(int stateCode1) {
		this.stateCode1 = stateCode1;
	}
	public int getStateCode2() {
		return stateCode2;
	}
	public void setStateCode2(int stateCode2) {
		this.stateCode2 = stateCode2;
	}
	public int getStateCode3() {
		return stateCode3;
	}
	public void setStateCode3(int stateCode3) {
		this.stateCode3 = stateCode3;
	}
	public int getStateCode4() {
		return stateCode4;
	}
	public void setStateCode4(int stateCode4) {
		this.stateCode4 = stateCode4;
	}
	public int getStateCode5() {
		return stateCode5;
	}
	public void setStateCode5(int stateCode5) {
		this.stateCode5 = stateCode5;
	}
	public long getCustUserNo() {
		return custUserNo;
	}
	public void setCustUserNo(long custUserNo) {
		this.custUserNo = custUserNo;
	}
	public int getCustCode() {
		return custCode;
	}
	public void setCustCode(int custCode) {
		this.custCode = custCode;
	}
	public int getBankCode() {
		return bankCode;
	}
	public void setBankCode(int bankCode) {
		this.bankCode = bankCode;
	}
	public int getLocalCode() {
		return localCode;
	}
	public void setLocalCode(int localCode) {
		this.localCode = localCode;
	}
	public String getIsClean() {
		return isClean;
	}
	public void setIsClean(String isClean) {
		this.isClean = isClean;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    public long getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(long branchNo) {
		this.branchNo = branchNo;
	}
	public long getCleanUserNo() {
		return cleanUserNo;
	}
	public void setCleanUserNo(long cleanUserNo) {
		this.cleanUserNo = cleanUserNo;
	}
	public String getCleanDate() {
		return cleanDate;
	}
	public void setCleanDate(String cleanDate) {
		this.cleanDate = cleanDate;
	}
	public String getCustCodeName() {
		return custCodeName;
	}
	public void setCustCodeName(String custCodeName) {
		this.custCodeName = custCodeName;
	}
	public String getBankCodeName() {
		return bankCodeName;
	}
	public void setBankCodeName(String bankCodeName) {
		this.bankCodeName = bankCodeName;
	}
	public String getLocalCodeName() {
		return localCodeName;
	}
	public void setLocalCodeName(String localCodeName) {
		this.localCodeName = localCodeName;
	}
	public String getCleanUserName() {
		return cleanUserName;
	}
	public void setCleanUserName(String cleanUserName) {
		this.cleanUserName = cleanUserName;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public long getCleanNo() {
		return cleanNo;
	}
	public void setCleanNo(long cleanNo) {
		this.cleanNo = cleanNo;
	}
	public String getStateCodeName1() {
		return stateCodeName1;
	}
	public void setStateCodeName1(String stateCodeName1) {
		this.stateCodeName1 = stateCodeName1;
	}
	public String getStateCodeName2() {
		return stateCodeName2;
	}
	public void setStateCodeName2(String stateCodeName2) {
		this.stateCodeName2 = stateCodeName2;
	}
	public String getStateCodeName3() {
		return stateCodeName3;
	}
	public void setStateCodeName3(String stateCodeName3) {
		this.stateCodeName3 = stateCodeName3;
	}
	public String getStateCodeName4() {
		return stateCodeName4;
	}
	public void setStateCodeName4(String stateCodeName4) {
		this.stateCodeName4 = stateCodeName4;
	}
	public String getStateCodeName5() {
		return stateCodeName5;
	}
	public void setStateCodeName5(String stateCodeName5) {
		this.stateCodeName5 = stateCodeName5;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
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
}