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
                	<span style="font-weight: bold;">청소 진행 현황 </span>
                    <small>청소 데이터 ${ editMode == 'update' ? '수정' : '등록' }</small>
                </h1>
            </section>

            <!-- Main content -->
            <section class="content">
				
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                        	<form name="cleanFrm" id="cleanFrm" action="${contextPath}/AdminCleanInfo/${ editMode == 'update' ? 'updateProcess' : 'addProcess' }" method="post" >
							<input type="hidden" name="cleanNo" id="cleanNo" value="${clean.cleanNo}"/>
							<c:set value="<%= cons.VIEW_IMG_URL %>" var="viewImgUrl"/>
							<div class="box-body">
								<div class="form-group">
									<label style="width: 150px;">작업일</label>
									<span style="font-weight: bold;">
										<input type="hidden" name="cleanDate" id="cleanDate" value="${clean.cleanDate}"/>
										<input type="hidden" name="branchNo" id="branchNo" value="${clean.branchNo}"/>
										<input type="hidden" name="createUser" id="createUser" value="${bankbranch.cleanUserNo}"/>
										${clean.cleanDate}
									</span>
								</div>								
								<div class="form-group">
									<label style="width: 150px;">고객사명</label>
									<input type="text" class="form-control" value="${bankbranch.custCodeName}" style="display: table-caption;;width:150px;" readonly="readonly">
									<label style="width: 150px;margin-left: 50px;">은행명</label>
									<input type="text" class="form-control" value="${bankbranch.bankCodeName}" style="display: table-caption;;width:150px;" readonly="readonly">
								</div>						
								<div class="form-group">
									<label style="width: 150px;">사이트명</label>
									<input type="text" class="form-control" value="${bankbranch.siteName}" style="display: table-caption;;width:150px;" readonly="readonly">
									<label style="width: 150px;margin-left: 50px;">작업자</label>
									<input type="text" class="form-control" value="${bankbranch.cleanUserName}" style="display: table-caption;;width:150px;" readonly="readonly">
								</div>
                                <div class="box-body table-responsive no-padding">
                                    <table class="table table-hover">
                                        <tr>
                                            <th style="text-align:center;">시작시간</th>
                                            <th style="text-align:center;">종료시간</th>
                                            <th style="text-align:center;">천정 조명</th>
                                            <th style="text-align:center;">기기조명</th>
                                            <th style="text-align:center;">대기선</th>
                                            <th style="text-align:center;">인터폰</th>
                                            <th style="text-align:center;">냉난방</th>
                                        </tr>
                                        <tr>
                                            <td style="text-align:center;vertical-align: middle;">
                                            	<input type="text" class="form-control" name="startTime" id="startTime" value="${clean.startTime}" placeholder="ex) 16:45" style="display: table-caption;;width:80px;">
                                            </td>
                                            <td style="text-align:center;vertical-align: middle;">
                                            	<input type="text" class="form-control" name="endTime" id="endTime" value="${clean.endTime}" placeholder="ex) 16:45" style="display: table-caption;;width:80px;">
                                            </td>
                                            <td style="text-align:center;vertical-align: middle;">
												<select class="form-control input-sm" id="stateCode1" name="stateCode1" style="display: table-caption;width:100px;">
													<c:forEach var="item" items="${cleanCodeList}" varStatus="status">
													<option value="${item.codeId}" <c:if test="${item.codeId == clean.stateCode1}">selected</c:if>>${item.codeName}</option>
													</c:forEach>
												</select>
                                            </td>
                                            <td style="text-align:center;vertical-align: middle;">
												<select class="form-control input-sm" id="stateCode2" name="stateCode2" style="display: table-caption;width:100px;">
													<c:forEach var="item" items="${cleanCodeList}" varStatus="status">
													<option value="${item.codeId}" <c:if test="${item.codeId == clean.stateCode2}">selected</c:if>>${item.codeName}</option>
													</c:forEach>
												</select>
                                            </td>
                                            <td style="text-align:center;vertical-align: middle;">
												<select class="form-control input-sm" id="stateCode3" name="stateCode3" style="display: table-caption;width:100px;">
													<c:forEach var="item" items="${cleanCodeList}" varStatus="status">
													<option value="${item.codeId}" <c:if test="${item.codeId == clean.stateCode3}">selected</c:if>>${item.codeName}</option>
													</c:forEach>
												</select>
                                            </td>
                                            <td style="text-align:center;vertical-align: middle;">
												<select class="form-control input-sm" id="stateCode4" name="stateCode4" style="display: table-caption;width:100px;">
													<c:forEach var="item" items="${cleanCodeList}" varStatus="status">
													<option value="${item.codeId}" <c:if test="${item.codeId == clean.stateCode4}">selected</c:if>>${item.codeName}</option>
													</c:forEach>
												</select>
                                            </td>
                                            <td style="text-align:center;vertical-align: middle;">
												<select class="form-control input-sm" id="stateCode5" name="stateCode5" style="display: table-caption;width:100px;">
													<c:forEach var="item" items="${cleanCodeList}" varStatus="status">
													<option value="${item.codeId}" <c:if test="${item.codeId == clean.stateCode5}">selected</c:if>>${item.codeName}</option>
													</c:forEach>
												</select>
                                            </td>
                                        </tr>
                                    </table>
                                </div><!-- /.box-body -->
								
								<div class="input-group margin">
                                       <div class="input-group-btn">
                                           <button type="button" class="btn btn-danger" >기타</button>
                                       </div><!-- /btn-group -->
									<input type="text" class="form-control" name="remark" id="remark" value="${clean.remark}" placeholder="특이 사항 기입...." style="display: table-caption;width:100%;">
								</div>
								<div class="form-group" style="margin-top: 20px;">
                                       <label><span style="color: red;font-weight: bold;">※ EXIF 추출이 가능한 JPG만 등록 가능합니다.</span></label>
								</div>
								
                                <div class="box-body">
                                 <h3 class="timeline-header">Photos</h3>
                                    <table class="table table-bordered">
                                        <tr>
                                            <th>사진 1</th>
                                            <th>사진 2</th>
                                            <th>사진 3</th>
                                            <th>사진 4</th>
                                            <th>사진 5</th>
                                        </tr>
                                        <tr>
                                            <td>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg1) && clean.photoImg1 != ''}">
	                                            		<a href="${viewImgUrl}${clean.photoImg1 }" target="_blank"><img src="${viewImgUrl}${clean.photoImg1 }" alt="..." class='margin' style="height:100px;width:100px;"/></a>
	                                            	</c:when>
	                                            	<c:otherwise><img src="http://placehold.it/100x100" alt="..." class='margin' style="height:100px;width:100px;" /></c:otherwise>
                                            	</c:choose>
                                            	<div style="margin-left: 15px;">
	                                            	<label for="photoImg1Upload" class="btn btn-default btn-sm">이미지 업로드</label>
													<input type="file" id="photoImg1Upload" name="photoImg1Upload" onchange="clean.imgUpload2('photoImg1Upload', '${viewImgUrl}');" style="display:none;">
													<input type="hidden" name="photoImg1" id="photoImg1" value="${clean.photoImg1}"/>
													<input type="hidden" name="photoImg1Info" id="photoImg1Info" value="${clean.photoImg1Info}"/>
												</div>
                                            </td>
                                            <td>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg2) && clean.photoImg2 != ''}">
	                                            		<a href="${viewImgUrl}${clean.photoImg2 }" target="_blank"><img src="${viewImgUrl}${clean.photoImg2 }" alt="..." class='margin' style="height:100px;width:100px;" /></a>
	                                            	</c:when>
	                                            	<c:otherwise><img src="http://placehold.it/100x100" alt="..." class='margin' style="height:100px;width:100px;" /></c:otherwise>
                                            	</c:choose>
                                            	<div style="margin-left: 15px;">
	                                            	<label for="photoImg2Upload" class="btn btn-default btn-sm">이미지 업로드</label>
													<input type="file" id="photoImg2Upload" name="photoImg2Upload" onchange="clean.imgUpload2('photoImg2Upload', '${viewImgUrl}');" style="display:none;">
													<input type="hidden" name="photoImg2" id="photoImg2" value="${clean.photoImg2}"/>
													<input type="hidden" name="photoImg2Info" id="photoImg2Info" value="${clean.photoImg2Info}"/>
												</div>
                                            </td>
                                            <td>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg3) && clean.photoImg3 != ''}">
	                                            		<a href="${viewImgUrl}${clean.photoImg3 }" target="_blank"><img src="${viewImgUrl}${clean.photoImg3 }" alt="..." class='margin' style="height:100px;width:100px;" /></a>
	                                            	</c:when>
	                                            	<c:otherwise><img src="http://placehold.it/100x100" alt="..." class='margin' style="height:100px;width:100px;" /></c:otherwise>
                                            	</c:choose>
                                            	<div style="margin-left: 15px;">
	                                            	<label for="photoImg3Upload" class="btn btn-default btn-sm">이미지 업로드</label>
													<input type="file" id="photoImg3Upload" name="photoImg3Upload" onchange="clean.imgUpload2('photoImg3Upload', '${viewImgUrl}');" style="display:none;">
													<input type="hidden" name="photoImg3" id="photoImg3" value="${clean.photoImg3}"/>
													<input type="hidden" name="photoImg3Info" id="photoImg3Info" value="${clean.photoImg3Info}"/>
												</div>
                                            </td>
                                            <td>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg4) && clean.photoImg4 != ''}">
	                                            		<a href="${viewImgUrl}${clean.photoImg4 }" target="_blank"><img src="${viewImgUrl}${clean.photoImg4 }" alt="..." class='margin' style="height:100px;width:100px;" /></a>
	                                            	</c:when>
	                                            	<c:otherwise><img src="http://placehold.it/100x100" alt="..." class='margin' style="height:100px;width:100px;" /></c:otherwise>
                                            	</c:choose>
                                            	<div style="margin-left: 15px;">
	                                            	<label for="photoImg4Upload" class="btn btn-default btn-sm">이미지 업로드</label>
													<input type="file" id="photoImg4Upload" name="photoImg4Upload" onchange="clean.imgUpload2('photoImg4Upload', '${viewImgUrl}');" style="display:none;">
													<input type="hidden" name="photoImg4" id="photoImg4" value="${clean.photoImg4}"/>
													<input type="hidden" name="photoImg4Info" id="photoImg4Info" value="${clean.photoImg4Info}"/>
												</div>
                                            </td>
                                            <td>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg5) && clean.photoImg5 != ''}">
	                                            		<a href="${viewImgUrl}${clean.photoImg5 }" target="_blank"><img src="${viewImgUrl}${clean.photoImg5 }" alt="..." class='margin' style="height:100px;width:100px;" /></a>
	                                            	</c:when>
	                                            	<c:otherwise><img src="http://placehold.it/100x100" alt="..." class='margin' style="height:100px;width:100px;" /></c:otherwise>
                                            	</c:choose>
                                            	<div>
	                                            	<label for="photoImg5Upload" class="btn btn-default btn-sm">이미지 업로드</label>
													<input type="file" id="photoImg5Upload" name="photoImg5Upload" onchange="clean.imgUpload2('photoImg5Upload', '${viewImgUrl}');" style="display:none;">
													<input type="hidden" name="photoImg5" id="photoImg5" value="${clean.photoImg5}"/>
													<input type="hidden" name="photoImg5Info" id="photoImg5Info" value="${clean.photoImg5Info}"/>
												</div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="text-align:center;">
                                                     <dl>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg1) && clean.photoImg1 != ''}">
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            	</c:when>
	                                            	<c:otherwise>
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            	</c:otherwise>
                                            	</c:choose>
	                                            		<dd><span id="photoImg1InfoDesc">${clean.photoImg1Info }</span></dd>
	                                            		<dt><button type="button" class="btn btn-success btn-sm" onclick="clean.delImg2('photoImg1')" style="display:block; float:left;margin-left: 45px;">삭제</button></dt>
	                                            	</dl>
                                            </td>
                                            <td style="text-align:center;">
                                                     <dl>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg2) && clean.photoImg2 != ''}">
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            	</c:when>
	                                            	<c:otherwise>
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            	</c:otherwise>
                                            	</c:choose>
	                                            		<dd><span id="photoImg2InfoDesc">${clean.photoImg2Info }</span></dd>
	                                            		<dt><button type="button" class="btn btn-success btn-sm" onclick="clean.delImg2('photoImg2')" style="display:block; float:left;margin-left: 45px;">삭제</button></dt>
	                                            	</dl>
                                            </td>
                                            <td style="text-align:center;">
                                                     <dl>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg3) && clean.photoImg3 != ''}">
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            	</c:when>
	                                            	<c:otherwise>
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            	</c:otherwise>
                                            	</c:choose>
	                                            		<dd><span id="photoImg3InfoDesc">${clean.photoImg3Info }</span></dd>
	                                            		<dt><button type="button" class="btn btn-success btn-sm" onclick="clean.delImg2('photoImg3')" style="display:block; float:left;margin-left: 45px;">삭제</button></dt>
	                                            	</dl>
                                            </td>
                                            <td style="text-align:center;">
                                                     <dl>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg4) && clean.photoImg4 != ''}">
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            	</c:when>
	                                            	<c:otherwise>
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            	</c:otherwise>
                                            	</c:choose>
	                                            		<dd><span id="photoImg4InfoDesc">${clean.photoImg4Info }</span></dd>
	                                            		<dt><button type="button" class="btn btn-success btn-sm" onclick="clean.delImg2('photoImg4')" style="display:block; float:left;margin-left: 45px;">삭제</button></dt>
	                                            	</dl>
                                            </td>
                                            <td style="text-align:center;">
                                                     <dl>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg5) && clean.photoImg5 != ''}">
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            		<dd>${clean.photoImg5Info }</dd>
	                                            	</c:when>
	                                            	<c:otherwise>
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            	</c:otherwise>
                                            	</c:choose>
	                                            		<dd><span id="photoImg5InfoDesc">${clean.photoImg5Info }</span></dd>
	                                            		<dt><button type="button" class="btn btn-success btn-sm" onclick="clean.delImg2('photoImg5')" style="display:block; float:left;margin-left: 45px;">삭제</button></dt>
	                                            	</dl>
                                            </td>
                                        </tr>
                                    </table>
                                </div><!-- /.box-body -->								
								<div class="box-footer" style="text-align: center;vertical-align: middle;">
					         		<c:if test="${editMode == 'update'}">
									<button type="button" class="btn btn-danger btn-sm" onclick="javascript:clean.modifyAdmin('cleanFrm');">수정</button>
									<button type="button" class="btn btn-warning btn-sm" onclick="javascript:clean.isDeleteAdmin(${clean.cleanNo}, 'Y');">삭제</button>
					         		</c:if>
					         		<c:if test="${editMode != 'update'}">
									<button type="button" class="btn btn-success btn-sm" onclick="javascript:clean.registAdmin('cleanFrm');">등록</button>
					         		</c:if>
								  <button type="button" class="btn btn-default btn-sm" onclick="javascript:window.location.href='${contextPath}/AdminCleanInfo/cleanList/1'">목록</button>
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
