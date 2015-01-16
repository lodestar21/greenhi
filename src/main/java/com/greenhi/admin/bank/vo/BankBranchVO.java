package com.greenhi.admin.bank.vo;

import java.util.Date;

import com.greenhi.admin.common.vo.PagingVO;

/**
 * T_BANK_BRANCH VO
 * table : T_BANK_BRANCH
 * 
 * @author  won.lee
 * @date    2014. 12. 26.
 * @history 
 */
public class BankBranchVO extends PagingVO {

    static final long serialVersionUID = -1781310591072370800L;

	private long branchNo;
    private Date createTime;
	private long createUser;
    private Date modifyTime;
	private long modifyUser;
	private String isDelete;
	private String isUse;
	private int custCode;
	private int bankCode;
	private int branchStateCode;
	private int localCode;
	private String siteName;
	private long cleanUserNo;
	private String branchAddress;
	private int sortOrdr;
	private int chargeMoney;
	private int payMoney;
	private String remark;
	
	private int searchField;
	private String searchWord;
	private boolean isEqualSearch;	// 완전일치 검색
	private String orderBy;			// 오래된순 정렬 검색
	private boolean isExcel;
	private int totalCnt;

	private String custCodeName;
	private String bankCodeName;
	private String branchStateCodeName;
	private String localCodeName;
	private String cleanUserName;
	
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

	public String getBranchStateCodeName() {
		return branchStateCodeName;
	}

	public void setBranchStateCodeName(String branchStateCodeName) {
		this.branchStateCodeName = branchStateCodeName;
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

	public long getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(long branchNo) {
		this.branchNo = branchNo;
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

	public int getBranchStateCode() {
		return branchStateCode;
	}

	public void setBranchStateCode(int branchStateCode) {
		this.branchStateCode = branchStateCode;
	}

	public int getLocalCode() {
		return localCode;
	}

	public void setLocalCode(int localCode) {
		this.localCode = localCode;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public long getCleanUserNo() {
		return cleanUserNo;
	}

	public void setCleanUserNo(long cleanUserNo) {
		this.cleanUserNo = cleanUserNo;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public int getSortOrdr() {
		return sortOrdr;
	}

	public void setSortOrdr(int sortOrdr) {
		this.sortOrdr = sortOrdr;
	}

	public int getChargeMoney() {
		return chargeMoney;
	}

	public void setChargeMoney(int chargeMoney) {
		this.chargeMoney = chargeMoney;
	}

	public int getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(int payMoney) {
		this.payMoney = payMoney;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", BRANCH_NO:");
        sb.append(this.branchNo);
        sb.append(", CUST_CODE:");
        sb.append(this.custCode);
        sb.append(", BANK_CODE:");
        sb.append(this.bankCode);
        sb.append(", BRANCH_STATE_CODE:");
        sb.append(this.branchStateCode);
        sb.append(", LOCAL_CODE:");
        sb.append(this.localCode);
        sb.append(", SITE_NAME:");
        sb.append(this.siteName);
        sb.append(", CLEAN_USER_NO:");
        sb.append(this.cleanUserNo);
        sb.append(", BRANCH_ADDRESS:");
        sb.append(this.branchAddress);
        sb.append(", SORT_ORDR:");
        sb.append(this.sortOrdr);
        sb.append(", CHARGE_MONEY:");
        sb.append(this.chargeMoney);
        sb.append(", PAY_MONEY:");
        sb.append(this.payMoney);
        sb.append(", REMARK:");
        sb.append(this.remark);
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
        return sb.toString();
    }
}