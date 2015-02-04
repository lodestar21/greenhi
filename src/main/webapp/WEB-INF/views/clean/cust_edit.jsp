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
							<input type="hidden" name="cleanNo" id="cleanNo" value="${clean.cleanNo}"/>
							<c:set value="<%= cons.VIEW_IMG_URL %>" var="viewImgUrl"/>
							<div class="box-body">
								<div class="form-group">
									<label style="width: 80px;">작업일</label>
									<input type="text" class="form-control" value="${clean.cleanDate}" style="display: table-caption;;width:100px;" readonly="readonly">
								</div>
                                <div class="box-body table-responsive no-padding">
                                    <table class="table table-hover">
                                        <tr>
                                            <th style="text-align:center;">은행명</th>
                                            <th style="text-align:center;">Site명</th>
                                            <th style="text-align:center;">시작시간</th>
                                            <th style="text-align:center;">종료시간</th>
                                            <th style="text-align:center;">작업자</th>
                                            <th style="text-align:center;">천정 조명</th>
                                            <th style="text-align:center;">기기조명</th>
                                            <th style="text-align:center;">대기선</th>
                                            <th style="text-align:center;">인터폰</th>
                                            <th style="text-align:center;">냉난방</th>
                                        </tr>
                                        <tr>
                                            <td style="text-align:center;vertical-align: middle;">${clean.bankCodeName}</td>
                                            <td style="text-align:center;vertical-align: middle;">${clean.siteName}</td>
                                            <td style="text-align:center;vertical-align: middle;">${clean.startTime}</td>
                                            <td style="text-align:center;vertical-align: middle;">${clean.endTime}</td>
                                            <td style="text-align:center;vertical-align: middle;">${clean.cleanUserName}</td>
                                            <td style="text-align:center;vertical-align: middle;">
												<c:forEach var="item" items="${cleanCodeList}" varStatus="status">
													<c:if test="${item.codeId == clean.stateCode1}">
														<span class="label label-${clean.stateCode1 == '701' ? 'success' : clean.stateCode1 == '702' ? 'warning' : 'danger'}">${item.codeName}</span>
													</c:if>
												</c:forEach>
                                            </td>
                                            <td style="text-align:center;vertical-align: middle;">
												<c:forEach var="item" items="${cleanCodeList}" varStatus="status">
													<c:if test="${item.codeId == clean.stateCode2}">
														<span class="label label-${clean.stateCode2 == '701' ? 'success' : clean.stateCode2 == '702' ? 'warning' : 'danger'}">${item.codeName}</span>
													</c:if>
												</c:forEach>
                                            </td>
                                            <td style="text-align:center;vertical-align: middle;">
												<c:forEach var="item" items="${cleanCodeList}" varStatus="status">
													<c:if test="${item.codeId == clean.stateCode3}">
														<span class="label label-${clean.stateCode3 == '701' ? 'success' : clean.stateCode3 == '702' ? 'warning' : 'danger'}">${item.codeName}</span>
													</c:if>
												</c:forEach>
                                            </td>
                                            <td style="text-align:center;vertical-align: middle;">
												<c:forEach var="item" items="${cleanCodeList}" varStatus="status">
													<c:if test="${item.codeId == clean.stateCode4}">
														<span class="label label-${clean.stateCode4 == '701' ? 'success' : clean.stateCode4 == '702' ? 'warning' : 'danger'}">${item.codeName}</span>
													</c:if>
												</c:forEach>
                                            </td>
                                            <td style="text-align:center;vertical-align: middle;">
												<c:forEach var="item" items="${cleanCodeList}" varStatus="status">
													<c:if test="${item.codeId == clean.stateCode5}">
														<span class="label label-${clean.stateCode5 == '701' ? 'success' : clean.stateCode5 == '702' ? 'warning' : 'danger'}">${item.codeName}</span>
													</c:if>
												</c:forEach>
                                            </td>
                                        </tr>
                                    </table>
                                </div><!-- /.box-body -->
								
								<div class="input-group margin">
                                       <div class="input-group-btn">
                                           <button type="button" class="btn btn-danger" >기타</button>
                                       </div><!-- /btn-group -->
									<input type="text" class="form-control" name="remark" id="remark" value="${clean.remark}" placeholder="특이 사항 기입...." style="display: table-caption;width:100%;" readonly="readonly">
								</div>
								<div class="form-group" style="margin-top: 30px;">
									<label style="width: 80px;">등록일자</label>
									<fmt:formatDate value="${clean.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
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
	                                            	<c:when test="${!empty(clean.photoImg1) && clean.photoImg1 != ''}"><img src="${viewImgUrl}${clean.photoImg1 }" alt="..." class='margin' style="height:100px;width:100px;"/></c:when>
	                                            	<c:otherwise><img src="http://placehold.it/100x150" alt="..." class='margin' style="height:100px;width:100px;" /></c:otherwise>
                                            	</c:choose>
                                            </td>
                                            <td>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg2) && clean.photoImg2 != ''}"><img src="${viewImgUrl}${clean.photoImg2 }" alt="..." class='margin' style="height:100px;width:100px;" /></c:when>
	                                            	<c:otherwise><img src="http://placehold.it/100x150" alt="..." class='margin' style="height:100px;width:100px;" /></c:otherwise>
                                            	</c:choose>
                                            </td>
                                            <td>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg3) && clean.photoImg3 != ''}"><img src="${viewImgUrl}${clean.photoImg3 }" alt="..." class='margin' style="height:100px;width:100px;" /></c:when>
	                                            	<c:otherwise><img src="http://placehold.it/100x150" alt="..." class='margin' style="height:100px;width:100px;" /></c:otherwise>
                                            	</c:choose>
                                            </td>
                                            <td>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg4) && clean.photoImg4 != ''}"><img src="${viewImgUrl}${clean.photoImg4 }" alt="..." class='margin' style="height:100px;width:100px;" /></c:when>
	                                            	<c:otherwise><img src="http://placehold.it/100x150" alt="..." class='margin' style="height:100px;width:100px;" /></c:otherwise>
                                            	</c:choose>
                                            </td>
                                            <td>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg5) && clean.photoImg5 != ''}"><img src="${viewImgUrl}${clean.photoImg5 }" alt="..." class='margin' style="height:100px;width:100px;" /></c:when>
	                                            	<c:otherwise><img src="http://placehold.it/100x150" alt="..." class='margin' style="height:100px;width:100px;" /></c:otherwise>
                                            	</c:choose>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="text-align:center;">
                                                     <dl>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg1) && clean.photoImg1 != ''}">
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            		<dd>${clean.photoImg1Info }</dd>
	                                            	</c:when>
	                                            	<c:otherwise>
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            	</c:otherwise>
                                            	</c:choose>
                                            </td>
                                            <td style="text-align:center;">
                                                     <dl>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg2) && clean.photoImg2 != ''}">
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            		<dd>${clean.photoImg2Info }</dd>
	                                            	</c:when>
	                                            	<c:otherwise>
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            	</c:otherwise>
                                            	</c:choose>
                                            </td>
                                            <td style="text-align:center;">
                                                     <dl>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg3) && clean.photoImg3 != ''}">
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            		<dd>${clean.photoImg3Info }</dd>
	                                            	</c:when>
	                                            	<c:otherwise>
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            	</c:otherwise>
                                            	</c:choose>
                                            </td>
                                            <td style="text-align:center;">
                                                     <dl>
                                            	<c:choose>
	                                            	<c:when test="${!empty(clean.photoImg4) && clean.photoImg4 != ''}">
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            		<dd>${clean.photoImg4Info }</dd>
	                                            	</c:when>
	                                            	<c:otherwise>
	                                            		<dt>EXIF Data(촬영 일자)</dt>
	                                            	</c:otherwise>
                                            	</c:choose>
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
                                            </td>
                                        </tr>
                                    </table>
                                </div><!-- /.box-body -->
								
								<div class="box-footer" style="text-align: center;vertical-align: middle;">
								  <button type="button" class="btn btn-default btn-sm" onclick="javascript:history.back();">목록</button>
								</div>
								
							</div>
						</div>
					</div>
				</div>
		<c:import url="../import/cfooter.jsp" />
				
            </section><!-- /.content -->
        </aside><!-- /.right-side -->
    </div><!-- ./wrapper -->
		
		
	</body>
</html>
