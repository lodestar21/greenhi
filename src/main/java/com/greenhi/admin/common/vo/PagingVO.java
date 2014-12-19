package com.greenhi.admin.common.vo;

import java.io.Serializable;

/**
 * 페이징 VO
 * 
 * @author  won.lee
 * @date    2014. 8. 8. 
 * @history  
 */
public class PagingVO implements Serializable {

	static final long serialVersionUID = 8643661324675595969L;

	protected int totalCount;
	protected int pageNum = 1;
	protected int recordPerPage = 10;
	protected int depth;
	protected long rowNum;
	protected int startIdx;

	public int getStartIdx() {

		return startIdx;
	}

	public void setStartIdx(int startIdx) {

		this.startIdx = startIdx;
	}

	public int getTotalCount() {

		return totalCount;
	}

	public void setTotalCount(int totalCount) {

		this.totalCount = totalCount;
	}

	public int getPageNum() {

		return pageNum;
	}

	public void setPageNum(int pageNum) {

		if (pageNum == 0) {
			pageNum = 1;
		}
		this.pageNum = pageNum;
		this.startIdx = (pageNum - 1) * recordPerPage;
	}

	public int getDepth() {

		return depth;
	}

	public void setDepth(int depth) {

		this.depth = depth;
	}

	public int getRecordPerPage() {

		return recordPerPage;
	}

	public void setRecordPerPage(int recordPerPage) {

		this.recordPerPage = recordPerPage;
	}

	public long getRowNum() {

		return rowNum;
	}

	public void setRowNum(long rowNum) {

		this.rowNum = rowNum;
	}
}
