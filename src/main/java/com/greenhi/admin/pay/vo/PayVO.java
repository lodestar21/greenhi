package com.greenhi.admin.pay.vo;

import java.util.Date;

import com.greenhi.admin.common.vo.PagingVO;

/**
 * T_PAY VO
 * table : T_PAY
 * 
 * @author  won.lee
 * @date    2014. 12. 26.
 * @history 
 */
public class PayVO extends PagingVO {

	static final long serialVersionUID = -2315031009980479818L;

	private long payNo;
    private Date createTime;
	private long createUser;
    private Date modifyTime;
	private long modifyUser;
	private String isDelete;
	private String isUse;
	private String payDate;
	private int withdrawBank;
	private String withdrawNum;
	private int depositBank;
	private String depositNum;
	private int reveiveNo;
	private int receiveMoney;
	private String remark;
	
	private String withdrawBankName;
	private String depositBankName;
	private String reveiveName;
	
	private int searchField;
	private String searchWord;
	private boolean isEqualSearch;	// 완전일치 검색
	private String orderBy;			// 오래된순 정렬 검색
	private boolean isExcel;
	private int totalCnt;

	public long getPayNo() {
		return payNo;
	}

	public void setPayNo(long payNo) {
		this.payNo = payNo;
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

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public int getWithdrawBank() {
		return withdrawBank;
	}

	public void setWithdrawBank(int withdrawBank) {
		this.withdrawBank = withdrawBank;
	}

	public String getWithdrawNum() {
		return withdrawNum;
	}

	public void setWithdrawNum(String withdrawNum) {
		this.withdrawNum = withdrawNum;
	}

	public int getDepositBank() {
		return depositBank;
	}

	public void setDepositBank(int depositBank) {
		this.depositBank = depositBank;
	}

	public String getDepositNum() {
		return depositNum;
	}

	public void setDepositNum(String depositNum) {
		this.depositNum = depositNum;
	}

	public int getReveiveNo() {
		return reveiveNo;
	}

	public void setReveiveNo(int reveiveNo) {
		this.reveiveNo = reveiveNo;
	}

	public int getReceiveMoney() {
		return receiveMoney;
	}

	public void setReceiveMoney(int receiveMoney) {
		this.receiveMoney = receiveMoney;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWithdrawBankName() {
		return withdrawBankName;
	}

	public void setWithdrawBankName(String withdrawBankName) {
		this.withdrawBankName = withdrawBankName;
	}

	public String getDepositBankName() {
		return depositBankName;
	}

	public void setDepositBankName(String depositBankName) {
		this.depositBankName = depositBankName;
	}

	public String getReveiveName() {
		return reveiveName;
	}

	public void setReveiveName(String reveiveName) {
		this.reveiveName = reveiveName;
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
        sb.append(", PAY_NO:");
        sb.append(this.payNo);
        sb.append(", PAY_DATE:");
        sb.append(this.payDate);
        sb.append(", WITHDRAW_BANK:");
        sb.append(this.withdrawBank);
        sb.append(", WITHDRAW_NUM:");
        sb.append(this.withdrawNum);
        sb.append(", DEPOSIT_BANK:");
        sb.append(this.depositBank);
        sb.append(", DEPOSIT_NUM:");
        sb.append(this.depositNum);
        sb.append(", RECEIVE_NO:");
        sb.append(this.reveiveNo);
        sb.append(", RECEIVE_MONEY:");
        sb.append(this.receiveMoney);
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