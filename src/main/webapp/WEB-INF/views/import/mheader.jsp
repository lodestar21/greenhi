<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
	
    <meta charset="UTF-8">
    <title>Greenhi system</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

	<script type="text/javascript">
		var contextPath = "${contextPath}";
		var resourcesPath = "${contextPath}/resources/";
	</script>
	
    <!-- bootstrap 3.0.2 -->
    <link href="${contextPath}/resources/bootstrap/css/bootstrap.min.css?2014082101" rel="stylesheet" type="text/css" />
    <!-- font Awesome -->
    <link href="${contextPath}/resources/bootstrap/css/font-awesome.min.css?2014082101" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="${contextPath}/resources/bootstrap/css/ionicons.min.css?2014082101" rel="stylesheet" type="text/css" />
    <!-- Morris chart -->
    <link href="${contextPath}/resources/bootstrap/css/morris/morris.css?2014082101" rel="stylesheet" type="text/css" />
    <!-- jvectormap -->
    <link href="${contextPath}/resources/bootstrap/css/jvectormap/jquery-jvectormap-1.2.2.css?2014082101" rel="stylesheet" type="text/css" />
    <!-- fullCalendar -->
    <link href="${contextPath}/resources/bootstrap/css/fullcalendar/fullcalendar.css?2014082101" rel="stylesheet" type="text/css" />
    <!-- Daterange picker -->
    <link href="${contextPath}/resources/bootstrap/css/daterangepicker/daterangepicker-bs3.css?2014082101" rel="stylesheet" type="text/css" />
    <!-- bootstrap wysihtml5 - text editor -->
    <link href="${contextPath}/resources/bootstrap/css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css?2014082101" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="${contextPath}/resources/bootstrap/css/AdminLTE.css?2014082101" rel="stylesheet" type="text/css" />
	
	<link href="${contextPath}/resources/css/jquery-ui-1.10.4.custom.css?2014082101" rel="stylesheet" type="text/css" />
	<link href="${contextPath}/resources/css/jquery-ui-1.10.4.custom.min.css?2014082101" rel="stylesheet" type="text/css" />
	
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
	
    <!-- jQuery 2.0.2 -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
    <!-- jQuery UI 1.10.3 -->
    <script src="${contextPath}/resources/bootstrap/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
    <!-- Bootstrap -->
    <script src="${contextPath}/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- Morris.js charts -->
    <!-- script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script-->
    <!--script src="${contextPath}/resources/bootstrap/js/plugins/morris/morris.min.js" type="text/javascript"></script-->
    <!-- Sparkline -->
    <script src="${contextPath}/resources/bootstrap/js/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
    <!-- jvectormap -->
    <script src="${contextPath}/resources/bootstrap/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/bootstrap/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script>
    <!-- fullCalendar -->
    <script src="${contextPath}/resources/bootstrap/js/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
    <!-- jQuery Knob Chart -->
    <script src="${contextPath}/resources/bootstrap/js/plugins/jqueryKnob/jquery.knob.js" type="text/javascript"></script>
    <!-- daterangepicker -->
    <script src="${contextPath}/resources/bootstrap/js/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
    <!-- Bootstrap WYSIHTML5 -->
    <script src="${contextPath}/resources/bootstrap/js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>
    <!-- iCheck -->
    <script src="${contextPath}/resources/bootstrap/js/plugins/iCheck/icheck.min.js" type="text/javascript"></script>

    <!-- AdminLTE App -->
    <script src="${contextPath}/resources/bootstrap/js/AdminLTE/app.js" type="text/javascript"></script>
    
    <!-- AdminLTE for demo purposes
    <script src="${contextPath}/resources/bootstrap/js/AdminLTE/demo.js" type="text/javascript"></script>
     -->
    <!-- CK Editor -->
    <script src="${contextPath}/resources/bootstrap/js/plugins/ckeditor/ckeditor.js" type="text/javascript"></script>
	
	<script src="${contextPath}/resources/js/jquery.form.js"></script>
	<script src="${contextPath}/resources/js/jquery-ui-1.10.4.custom.js"></script>
	<script src="${contextPath}/resources/js/jquery-ui-1.10.4.custom.min.js"></script>
	<script src="${contextPath}/resources/js/ajaxfileupload.js"></script>
    
	<script src="${contextPath}/resources/js/util.js?2014082101"></script>
	<script src="${contextPath}/resources/js/user.js?2014082101"></script>
	<script src="${contextPath}/resources/js/code.js?2014082101"></script>
	<script src="${contextPath}/resources/js/bankBranch.js?2014082101"></script>
    