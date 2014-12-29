<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core"				%>
<%@ taglib prefix="fmt"		uri="http://java.sun.com/jsp/jstl/fmt"				%>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions"		%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<%
	int totalRecord = 0;	// 전체 게시물 수
	int numPerPage = 10;	// 한 페이지당 게시물 수
	int pagePerBlock = 10;	// 블록당 페이지수
	int totalPage = 0;		// 전체 페이지 수
	int startPage = 1;		// 페이지 시작 번호
	int pageNum = 1;
	int numPerHidePage = 0;
	String pm = "";
	
	if(request.getParameter("pm") != null && request.getParameter("pm").trim() != "") {
		pm = request.getParameter("pm").trim();
	}
	if(request.getParameter("totalCount") != null && request.getParameter("totalCount").trim() != "") {
		totalRecord = Integer.parseInt(request.getParameter("totalCount").trim());
	}	
	if(request.getParameter("pageNum") != null && request.getParameter("pageNum").trim() != "") {
		pageNum = Integer.parseInt(request.getParameter("pageNum").trim());
	}	
	if(request.getParameter("numPerPage") != null && request.getParameter("numPerPage").trim() != "") {
		numPerPage = Integer.parseInt(request.getParameter("numPerPage").trim());
	}
	
	totalPage = (int)Math.ceil((double)totalRecord / numPerPage);
	
	numPerHidePage = ((int)((pageNum - 1) / numPerPage)) * numPerPage;
	startPage = numPerHidePage + 1 ;
%>
	<div class="box-footer clearfix">Showing ${pageNum} to ${recPerPage} of ${totalCount} entries
		<ul class="pagination pagination-sm no-margin pull-right">
<%
	if(pageNum > 1) {
%>
		<li><a href="javascript:<%=pm%>(<%= pageNum - 1 %>);">← Previous</a></li>
<%
	}
	for(int i = 0 ; i < pagePerBlock ; i++) {
		if(i == (totalPage - numPerHidePage)) {
			break;
		}
		
		if(pageNum == startPage) {
%>
		<li class="active"><a href="#"><%= startPage%></a></li>
<%
		}
		else {
%>
		<li><a href="javascript:<%=pm%>(<%= startPage %>);"><%= startPage%></a></li>
<%
		}
		
		startPage++;
	}
	
	if(pageNum < totalPage) {
%>
		<li><a href="javascript:<%=pm%>(<%= pageNum + 1 %>);">Next →</a></li>
<%
	}
%>
		  </ul>
	</div>
	
	