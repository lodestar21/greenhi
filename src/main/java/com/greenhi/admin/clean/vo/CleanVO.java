package com.greenhi.admin.clean.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * T_CLEAN VO
 * table : T_CLEAN
 * 
 * @author  won.lee
 * @date    2014. 12. 26.
 * @history 
 */
public class CleanVO implements Serializable {

    static final long serialVersionUID = -1781310591072370800L;

	private long cleanNo;
    private Date createTime;
	private long createUser;
    private Date modifyTime;
	private long modifyUser;
	private String isDelete;
	private String isUse;
	private long branchNo;
    private String cleanDate;
    private String startTime;
    private String endTime;
	private int stateCode1;
	private int stateCode2;
	private int stateCode3;
	private int stateCode4;
	private int stateCode5;
    private String remark;
    private String photoImg1;
    private String photoImg1Info;
    private String photoImg2;
    private String photoImg2Info;
    private String photoImg3;
    private String photoImg3Info;
    private String photoImg4;
    private String photoImg4Info;
    private String photoImg5;
    private String photoImg5Info;
    private String custCodeName;
    private String bankCodeName;
    private String siteName;

	private long cleanNoParam;
	private long branchNoParam;
    private String cleanDateParam;
	
	public long getCleanNoParam() {
		return cleanNoParam;
	}
	public void setCleanNoParam(long cleanNoParam) {
		this.cleanNoParam = cleanNoParam;
	}
	public long getBranchNoParam() {
		return branchNoParam;
	}
	public void setBranchNoParam(long branchNoParam) {
		this.branchNoParam = branchNoParam;
	}
	public String getCleanDateParam() {
		return cleanDateParam;
	}
	public void setCleanDateParam(String cleanDateParam) {
		this.cleanDateParam = cleanDateParam;
	}
	public long getCleanNo() {
		return cleanNo;
	}
	public void setCleanNo(long cleanNo) {
		this.cleanNo = cleanNo;
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
	public long getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(long branchNo) {
		this.branchNo = branchNo;
	}
	public String getCleanDate() {
		return cleanDate;
	}
	public void setCleanDate(String cleanDate) {
		this.cleanDate = cleanDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPhotoImg1() {
		return photoImg1;
	}
	public void setPhotoImg1(String photoImg1) {
		this.photoImg1 = photoImg1;
	}
	public String getPhotoImg1Info() {
		return photoImg1Info;
	}
	public void setPhotoImg1Info(String photoImg1Info) {
		this.photoImg1Info = photoImg1Info;
	}
	public String getPhotoImg2() {
		return photoImg2;
	}
	public void setPhotoImg2(String photoImg2) {
		this.photoImg2 = photoImg2;
	}
	public String getPhotoImg2Info() {
		return photoImg2Info;
	}
	public void setPhotoImg2Info(String photoImg2Info) {
		this.photoImg2Info = photoImg2Info;
	}
	public String getPhotoImg3() {
		return photoImg3;
	}
	public void setPhotoImg3(String photoImg3) {
		this.photoImg3 = photoImg3;
	}
	public String getPhotoImg3Info() {
		return photoImg3Info;
	}
	public void setPhotoImg3Info(String photoImg3Info) {
		this.photoImg3Info = photoImg3Info;
	}
	public String getPhotoImg4() {
		return photoImg4;
	}
	public void setPhotoImg4(String photoImg4) {
		this.photoImg4 = photoImg4;
	}
	public String getPhotoImg4Info() {
		return photoImg4Info;
	}
	public void setPhotoImg4Info(String photoImg4Info) {
		this.photoImg4Info = photoImg4Info;
	}
	public String getPhotoImg5() {
		return photoImg5;
	}
	public void setPhotoImg5(String photoImg5) {
		this.photoImg5 = photoImg5;
	}
	public String getPhotoImg5Info() {
		return photoImg5Info;
	}
	public void setPhotoImg5Info(String photoImg5Info) {
		this.photoImg5Info = photoImg5Info;
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
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
}