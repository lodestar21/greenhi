<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:useBean id="cons" class="com.greenhi.common.Constants" scope="session"/>

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
                	<span style="font-weight: bold;">청소 데이터 등록${constants.SERVER_NAME} </span>
                    <small>청소 데이터 ${ editMode == 'update' ? '수정' : '등록' }</small>
                </h1>
            </section>

            <!-- Main content -->
            <section class="content">
				
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                        	<form name="cleanFrm" id="cleanFrm" action="${contextPath}/UserCleanInfo/${ editMode == 'update' ? 'updateProcess' : 'addProcess' }" method="post" >
							<input type="hidden" name="cleanNo" id="cleanNo" value="${clean.cleanNo}"/>
							<c:set value="<%= cons.VIEW_IMG_URL %>" var="viewImgUrl"/>
							<div class="box-body">
								<div class="form-group">
									<label style="width: 80px;">작업일</label>
									<span style="font-weight: bold;">
										<input type="hidden" name="cleanDate" id="cleanDate" value="${clean.cleanDate}"/>
										<input type="hidden" name="branchNo" id="branchNo" value="${clean.branchNo}"/>
										${clean.cleanDate}
									</span>
								</div>
								<div class="form-group">
									<label style="width: 80px;">고객사명</label>
									<span style="font-weight: bold;">${bankbranch.custCodeName}</span>
								</div>
								<div class="form-group">
									<label style="width: 80px;">은행명</label>
									<span style="font-weight: bold;">${bankbranch.bankCodeName}</span>
								</div>
								<div class="form-group">
									<label style="width: 80px;">사이트명</label>
									<span style="font-weight: bold;">${bankbranch.siteName}</span>
								</div>
								<hr>
								<div class="form-group">
									<label for="startTime" style="width: 80px;">시작시간</label>
									<input type="text" class="form-control" name="startTime" id="startTime" value="${clean.startTime}" placeholder="ex) 16:45" style="display: table-caption;;width:80px;">
								</div>
								<div class="form-group">
									<label for="endTime" style="width: 80px;">종료시간</label>
									<input type="text" class="form-control" name="endTime" id="endTime" value="${clean.endTime}" placeholder="ex) 16:45" style="display: table-caption;;width:80px;">
								</div>
								<div class="form-group">
									<label for="stateCode1" style="width: 80px;">천정 조명</label>
									<select class="form-control input-sm" id="stateCode1" name="stateCode1" style="display: table-caption;width:100px;">
										<c:forEach var="item" items="${cleanCodeList}" varStatus="status">
										<option value="${item.codeId}" <c:if test="${item.codeId == clean.stateCode1}">selected</c:if>>${item.codeName}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label for="stateCode2" style="width: 80px;">기기조명</label>
									<select class="form-control input-sm" id="stateCode2" name="stateCode2" style="display: table-caption;width:100px;">
										<c:forEach var="item" items="${cleanCodeList}" varStatus="status">
										<option value="${item.codeId}" <c:if test="${item.codeId == clean.stateCode2}">selected</c:if>>${item.codeName}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label for="stateCode3" style="width: 80px;">대기선</label>
									<select class="form-control input-sm" id="stateCode3" name="stateCode3" style="display: table-caption;width:100px;">
										<c:forEach var="item" items="${cleanCodeList}" varStatus="status">
										<option value="${item.codeId}" <c:if test="${item.codeId == clean.stateCode3}">selected</c:if>>${item.codeName}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label for="stateCode4" style="width: 80px;">인터폰</label>
									<select class="form-control input-sm" id="stateCode4" name="stateCode4" style="display: table-caption;width:100px;">
										<c:forEach var="item" items="${cleanCodeList}" varStatus="status">
										<option value="${item.codeId}" <c:if test="${item.codeId == clean.stateCode4}">selected</c:if>>${item.codeName}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label for="stateCode5" style="width: 80px;">냉난방</label>
									<select class="form-control input-sm" id="stateCode5" name="stateCode5" style="display: table-caption;width:100px;">
										<c:forEach var="item" items="${cleanCodeList}" varStatus="status">
										<option value="${item.codeId}" <c:if test="${item.codeId == clean.stateCode5}">selected</c:if>>${item.codeName}</option>
										</c:forEach>
									</select>
								</div>
								<div class="input-group margin">
                                       <div class="input-group-btn">
                                           <button type="button" class="btn btn-danger" >기타</button>
                                       </div><!-- /btn-group -->
									<input type="text" class="form-control" name="remark" id="remark" value="${clean.remark}" placeholder="특이 사항 기입...." style="display: table-caption;width:100%;">
								</div>
								<div class="form-group" style="margin-top: 20px;">
                                       <label><span style="color: red;font-weight: bold;">※ EXIF 추출이 가능한 JPG만 등록 가능합니다.</span></label>
								</div>
								<div class="form-group">
									<div>
										<label style="width: 80px;">사진 1 등록</label>
									</div>
									<div style="padding: 10px; float: left;">
										<input id="photoImg1Upload" name="photoImg1Upload" type="file" onchange="clean.imgUpload('photoImg1Upload', '${viewImgUrl}');" style="display: table-caption;">
									<input type="hidden" name="photoImg1" id="photoImg1" value="${clean.photoImg1}"/>
									<input type="hidden" name="photoImg1Info" id="photoImg1Info" value="${clean.photoImg1Info}"/>
									</div>
									<div style="float: left;">
										<button type="button" class="btn btn-success btn-sm" onclick="clean.delImg('photoImg1')" style="display:block; float:left;">삭제</button>
									</div>
									<div style="margin-top: 60px;">
										<c:choose>
											<c:when test="${clean.photoImg1 != null && clean.photoImg1 != ''}">
					                    <img src="${viewImgUrl}${clean.photoImg1}" id="photoImg1Preview" style="height:150px;width:250px;"/>
											</c:when>
											<c:otherwise>
					                    <img src="" id="photoImg1Preview" style="height:150px;width:250px;"/>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div class="form-group">
									<div>
										<label style="width: 80px;">사진 2 등록</label>
									</div>
									<div style="padding: 10px; float: left;">
										<input id="photoImg2Upload" name="photoImg2Upload" type="file" onchange="clean.imgUpload('photoImg2Upload', '${viewImgUrl}');" style="display: table-caption;">
									<input type="hidden" name="photoImg2" id="photoImg2" value="${clean.photoImg2}"/>
									<input type="hidden" name="photoImg2Info" id="photoImg2Info" value="${clean.photoImg2Info}"/>
									</div>
									<div style="float: left;">
										<button type="button" class="btn btn-success btn-sm" onclick="clean.delImg('photoImg2')" style="display:block; float:left;">삭제</button>
									</div>
									<div style="margin-top: 60px;">
										<c:choose>
											<c:when test="${clean.photoImg2 != null && clean.photoImg2 != ''}">
					                    <img src="${viewImgUrl}${clean.photoImg2}" id="photoImg2Preview" style="height:150px;width:250px;"/>
											</c:when>
											<c:otherwise>
					                    <img src="" id="photoImg2Preview" style="height:150px;width:250px;"/>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div class="form-group">
									<div>
										<label style="width: 80px;">사진 3 등록</label>
									</div>
									<div style="padding: 10px; float: left;">
										<input id="photoImg3Upload" name="photoImg3Upload" type="file" onchange="clean.imgUpload('photoImg3Upload', '${viewImgUrl}');" style="display: table-caption;">
									<input type="hidden" name="photoImg3" id="photoImg3" value="${clean.photoImg3}"/>
									<input type="hidden" name="photoImg3Info" id="photoImg3Info" value="${clean.photoImg3Info}"/>
									</div>
									<div style="float: left;">
										<button type="button" class="btn btn-success btn-sm" onclick="clean.delImg('photoImg3')" style="display:block; float:left;">삭제</button>
									</div>
									<div style="margin-top: 60px;">
										<c:choose>
											<c:when test="${clean.photoImg3 != null && clean.photoImg3 != ''}">
					                    <img src="${viewImgUrl}${clean.photoImg3}" id="photoImg3Preview" style="height:150px;width:250px;"/>
											</c:when>
											<c:otherwise>
					                    <img src="" id="photoImg3Preview" style="height:150px;width:250px;"/>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div class="form-group">
									<div>
										<label style="width: 80px;">사진 4 등록</label>
									</div>
									<div style="padding: 10px; float: left;">
										<input id="photoImg4Upload" name="photoImg4Upload" type="file" onchange="clean.imgUpload('photoImg4Upload', '${viewImgUrl}');" style="display: table-caption;">
									<input type="hidden" name="photoImg4" id="photoImg4" value="${clean.photoImg4}"/>
									<input type="hidden" name="photoImg4Info" id="photoImg4Info" value="${clean.photoImg4Info}"/>
									</div>
									<div style="float: left;">
										<button type="button" class="btn btn-success btn-sm" onclick="clean.delImg('photoImg4')" style="display:block; float:left;">삭제</button>
									</div>
									<div style="margin-top: 60px;">
										<c:choose>
											<c:when test="${clean.photoImg4 != null && clean.photoImg4 != ''}">
					                    <img src="${viewImgUrl}${clean.photoImg4}" id="photoImg4Preview" style="height:150px;width:250px;"/>
											</c:when>
											<c:otherwise>
					                    <img src="" id="photoImg4Preview" style="height:150px;width:250px;"/>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div class="form-group">
									<div>
										<label style="width: 80px;">사진 5 등록</label>
									</div>
									<div style="padding: 10px; float: left;">
										<input id="photoImg5Upload" name="photoImg5Upload" type="file" onchange="clean.imgUpload('photoImg5Upload', '${viewImgUrl}');" style="display: table-caption;">
									<input type="hidden" name="photoImg5" id="photoImg5" value="${clean.photoImg5}"/>
									<input type="hidden" name="photoImg5Info" id="photoImg5Info" value="${clean.photoImg5Info}"/>
									</div>
									<div style="float: left;">
										<button type="button" class="btn btn-success btn-sm" onclick="clean.delImg('photoImg5')" style="display:block; float:left;">삭제</button>
									</div>
									<div style="margin-top: 60px;">
										<c:choose>
											<c:when test="${clean.photoImg5 != null && clean.photoImg5 != ''}">
					                    <img src="${viewImgUrl}${clean.photoImg5}" id="photoImg5Preview" style="height:150px;width:250px;"/>
											</c:when>
											<c:otherwise>
					                    <img src="" id="photoImg5Preview" style="height:150px;width:250px;"/>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								
								<div class="box-footer" style="text-align: center;vertical-align: middle;">
					         		<c:if test="${editMode == 'update'}">
									<button type="button" class="btn btn-danger btn-sm" onclick="javascript:clean.modify('cleanFrm');">수정</button>
									<button type="button" class="btn btn-warning btn-sm" onclick="javascript:clean.isDelete(${clean.cleanNo}, 'Y');">삭제</button>
					         		</c:if>
					         		<c:if test="${editMode != 'update'}">
									<button type="button" class="btn btn-success btn-sm" onclick="javascript:clean.regist('cleanFrm');">등록</button>
					         		</c:if>
								  <button type="button" class="btn btn-default btn-sm" onclick="javascript:window.location.href='${contextPath}/UserCleanInfo/userList?cleanDate=${clean.cleanDate}'">목록</button>
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
