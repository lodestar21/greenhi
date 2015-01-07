package com.greenhi.admin.user.vo;

import java.util.Date;

import com.greenhi.admin.common.vo.PagingVO;

/**
 * T_USER VO
 * table : T_USER
 * 
 * @author  won.lee
 * @date    2014. 12. 26.
 * @history 
 */
public class UserVO extends PagingVO {

    static final long serialVersionUID = -1781310591072370800L;
    
	private long userNo;
	private String userId;
	private String userName;
	private int userType;
	private int custCode;
	private int localCode;
	private String email;
	private String phoneNumber;
	private int userStat;
	private String passWord;
	private int depositBank;
	private String accountNum;
	private String remark;
    private Date createTime;
	private long createUser;
    private Date modifyTime;
	private long modifyUser;
	private String isDelete;
	
	private int searchField;
	private String searchWord;
	private boolean isEqualSearch;	// 완전일치 검색
	private String orderBy;			// 오래된순 정렬 검색
	private boolean isExcel;
	private int totalCnt;

	private String userTypeName;
	private String localCodeName;
	private String userStatName;
	private String depositBankName;
	private String custCodeName;

	private String saveId;
	
	public String getCustCodeName() {
		return custCodeName;
	}

	public void setCustCodeName(String custCodeName) {
		this.custCodeName = custCodeName;
	}

	public String getDepositBankName() {
		return depositBankName;
	}

	public void setDepositBankName(String depositBankName) {
		this.depositBankName = depositBankName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isSaveIdFlag() {

		return "Y".equals( saveId );
	}
	
	public String getSaveId() {
		return saveId;
	}

	public void setSaveId(String saveId) {
		this.saveId = saveId;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getLocalCodeName() {
		return localCodeName;
	}

	public void setLocalCodeName(String localCodeName) {
		this.localCodeName = localCodeName;
	}

	public String getUserStatName() {
		return userStatName;
	}

	public void setUserStatName(String userStatName) {
		this.userStatName = userStatName;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getCustCode() {
		return custCode;
	}

	public void setCustCode(int custCode) {
		this.custCode = custCode;
	}

	public int getLocalCode() {
		return localCode;
	}

	public void setLocalCode(int localCode) {
		this.localCode = localCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserStat() {
		return userStat;
	}

	public void setUserStat(int userStat) {
		this.userStat = userStat;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getDepositBank() {
		return depositBank;
	}

	public void setDepositBank(int depositBank) {
		this.depositBank = depositBank;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
        sb.append(", USER_NO:");
        sb.append(this.userNo);
        sb.append(", USER_ID:");
        sb.append(this.userId);
        sb.append(", USER_NAME:");
        sb.append(this.userName);
        sb.append(", USER_TYPE:");
        sb.append(this.userType);
        sb.append(", CUST_CODE:");
        sb.append(this.custCode);
        sb.append(", LOCAL_CODE:");
        sb.append(this.localCode);
        sb.append(", EMAIL:");
        sb.append(this.email);
        sb.append(", USER_STAT:");
        sb.append(this.userStat);
        sb.append(", PASS_WORD:");
        sb.append(this.passWord);
        sb.append(", DEPOSIT_BANK:");
        sb.append(this.depositBank);
        sb.append(", ACCOUNT_NUM:");
        sb.append(this.accountNum);
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
        return sb.toString();
    }
}