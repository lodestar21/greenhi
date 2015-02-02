package com.greenhi.admin.bank.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * T_DAY_BANK_BRANCH VO
 * table : T_DAY_BANK_BRANCH
 * 
 * @author  won.lee
 * @date    2014. 12. 26.
 * @history 
 */
public class DayBankBranchVO implements Serializable {

	static final long serialVersionUID = -1781310591072370800L;

	private String cleanDate;
	private long branchNo;
    private Date createTime;
	private long createUser;
	private int custCode;
	private int bankCode;
	private long cleanUserNo;
	private String custCodeName;
	private String bankCodeName;
	private String cleanUserName;

	public String getCleanUserName() {
		return cleanUserName;
	}

	public void setCleanUserName(String cleanUserName) {
		this.cleanUserName = cleanUserName;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", CLEAN_DATE:");
        sb.append(this.cleanDate);
        sb.append(", BRANCH_NO:");
        sb.append(this.branchNo);
        sb.append(", CREATE_TIME:");
        sb.append(this.createTime);
        sb.append(", CREATE_USER:");
        sb.append(this.createUser);
        return sb.toString();
    }
}