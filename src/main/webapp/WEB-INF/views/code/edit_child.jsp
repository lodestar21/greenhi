<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<html lang="ko">
<head>
	<c:import url="../import/mheader.jsp" />
	<script type="text/javascript">

		
	
	</script>
</head>
    <body class="skin-black">
		<c:import url="../import/cheader.jsp" />
		
	<div class="wrapper row-offcanvas row-offcanvas-left">
		<c:import url="../import/cleft.jsp" />

        <!-- Right side column. Contains the navbar and content of the page -->
        <aside class="right-side">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                	<span style="font-weight: bold;">기준 정보 관리</span>
                    <small>기준 정보 상세 코드 ${ editMode == 'update' ? '수정' : '등록' }</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="${contextPath}/"><i class="fa fa-dashboard"></i>Home</a></li>
                    <li><a href="${contextPath}/Code/list/1">기준 정보 관리</a></li>
                    <li class="active"><a href="${contextPath}/Code/list/1">기준 정보 관리 리스트</a></li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">
				
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                        	<form name="codeFrm" id="codeFrm" action="${contextPath}/Code/${ editMode == 'update' ? 'updateProcess' : 'addProcess' }" method="post" >
							<div class="box-body">
								<div class="form-group">
									<label for="cdId" style="width: 150px;">상위 코드 ID</label>
									<span style="font-weight: bold;">
										<input type="hidden" name="uprCdId" id="uprCdId" value="${codeUpr.cdId}"/>
										${codeUpr.cdNm}&nbsp;(${codeUpr.cdId})
									</span>
								</div>
								<c:if test="${editMode != 'update'}">
								<div class="form-group">
									<label for="cdId" style="width: 150px;">코드 ID</label>
									<input type="text" class="form-control input-sm" name="cdId" id="cdId" value="${code.cdId}" placeholder="코드 입력" style="display: table-caption;width:100px;">
									<span style="color: red">※ ${codeUpr.uprCdId} ~ ${codeUpr.uprCdId + 99} 사이 값으로 입력하세요.</span>
								</div>
								</c:if>
								<c:if test="${editMode == 'update'}">
								<div class="form-group">
									<label for="cdId" style="width: 150px;">코드 ID</label>
									<span style="font-weight: bold;">
										<input type="hidden" name="cdId" id="cdId" value="${code.cdId}"/>
										${code.cdId}
									</span>
								</div>
								</c:if>
								<div class="form-group">
									<label for="cdNm" style="width: 150px;">코드 이름</label>
									<input type="text" class="form-control input-sm" name="cdNm" id="cdNm" value="${code.cdNm}" placeholder="코드 이름 입력" style="display: table-caption;width:150px;">
								</div>
								<div class="form-group">
									<label for="cdEnNm" style="width: 150px;">코드 이름(영문)</label>
									<input type="text" class="form-control input-sm" name="cdEnNm" id="cdEnNm" value="${code.cdEnNm}" placeholder="코드 영문 이름 입력" style="display: table-caption;width:150px;">
								</div>
								<div class="form-group">
									<label for="sortOrdr" style="width: 150px;">정렬 순서</label>
									<input type="text" class="form-control input-sm" name="sortOrdr" id="sortOrdr" value="${code.sortOrdr}" placeholder="정렬 순서 입력" style="display: table-caption;width:50px;">
								</div>
								<div class="form-group">
									<label for="remark" style="width: 150px;">비고</label>
									<input type="text" class="form-control input-sm" name="remark" id="remark" value="${code.remark}" placeholder="비고 입력" style="display: table-caption;width:50%;">
								</div>
								<div class="form-group">
									<label style="width: 150px;">사용 여부</label>
									<select class="form-control input-sm" id="useYn" name="useYn" style="display: table-caption;width:100px;">
										<option value="0" <c:if test="${empty(code.useYn) || code.useYn == 0}">selected</c:if>>미사용</option>
										<option value="1" <c:if test="${code.useYn == 1}">selected</c:if>>사용</option>
									</select>
								</div>
								<c:if test="${editMode == 'update'}">
								<div class="form-group">
									<label style="width: 150px;">등록일자</label>
									<span>
										<fmt:formatDate value="${code.registDt}" pattern="yyyy-MM-dd HH:mm:ss" />
										<i class="fa fa-fw fa-user"></i>&nbsp;User No : ${code.registerNo}(${code.registerName})
									</span>
								</div>
								<div class="form-group">
									<label style="width: 150px;">수정일자</label>
									<span>
										<fmt:formatDate value="${code.updtDt}" pattern="yyyy-MM-dd HH:mm:ss" />
										<i class="fa fa-fw fa-user"></i>&nbsp;User No : ${code.updusrNo}(${code.updusrName})
									</span>
								</div>
								</c:if>
								
								<div class="box-footer" style="text-align: center;vertical-align: middle;">
					         		<c:if test="${editMode == 'update'}">
									<button type="button" class="btn btn-danger btn-sm" onclick="javascript:code.modifyChild('codeFrm');">상세 코드 정보 수정</button>
									<button type="button" class="btn btn-warning btn-sm" onclick="javascript:code.isDeleteChild(${code.cdId}, 1);">상세 코드 정보 삭제</button>
					         		</c:if>
					         		<c:if test="${editMode != 'update'}">
									<button type="button" class="btn btn-success btn-sm" onclick="javascript:code.registChild('codeFrm');">상세 코드 등록</button>
					         		</c:if>
								  <button type="button" class="btn btn-default btn-sm" onclick="javascript:window.location.href='${contextPath}/Code/get/${codeUpr.cdId}'">목록</button>
								</div>
								
							</div>
							</form>
						</div>
					</div>
				</div>
		<c:import url="../import/cfooter.jsp" />
				
            </section><!-- /.content -->
        </aside><!-- /.right-side -->
    </div><!-- ./wrapper -->
		
		
	</body>
</html>
