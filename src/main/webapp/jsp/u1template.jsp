<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><tiles:getAsString name="title" /></title>
	<link rel="stylesheet" href="css/excite-bike/jquery-ui-1.10.3.custom.min.css">
	<link rel="stylesheet" href="css/u1.css">
	<link rel="stylesheet" href="css/u1metro.css">
<!-- 	<script src="js/jquery-1.10.2.js"></script> -->
	<script src="js/jquery-1.10.2.min.js"></script>
	<script src="js/jquery-ui-1.10.3.custom.min.js"></script>
	<script src="js/flotr2.js"></script>
	<script src="js/myflotr2.js"></script>
	<script src="js/ajaxfileupload.js"></script>
	<script src="js/u1.js"></script>
	<script src="js/u1metro.js"></script>
	<script src="js/u1message.js"></script>
	<script src="js/jquery.ui.datepicker-zh-CN.js"></script>
	<script>
	var outEvent = [];
	var lastobject;
	var divs=[];
	</script>
</head>
<body>
<div class="maskerlayer" id="maskerlayer"></div>
<div class="loader_container" id="loader_container">
	<div class="loader">
			<div align="center"><img src="img/u1/loading.gif" alt="loading" /></div>
			<div align="center"><font style="color:#50b7dc">页面加载中……</font></div>
	</div>
</div>	
<div class="messagebox" id="messagebox" style="display:none">
	<span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span><span class="ui-icon ui-icon-closethick" style="float:right;" onclick='javascript:$("#messagebox").fadeOut()'></span>
	<div class="messagecontent" id="messagecontent"></div>
</div>
<div id="popuplayout" class="uparrowdiv shadow" style="display:none;">
	<div class="u1title1">
			<h3><span class="ui-icon ui-icon-closethick" style="float:right;" onclick='javascript:$("#popuplayout").fadeOut()'></span>
			详情</h3>
	</div>
	<table id="popuploadingimg" width="100%"><tr><td align="center"><img src="img/u1/loading.gif" style="align:center;"/></td></tr></table>
	<div id="popupcontent" style="display:none;">
		
	</div>
</div>
<div class="page">
	<div class="header">
			<jsp:include page="header.jsp"></jsp:include>
	</div>
	<div class="content">
				<div class="container">
				 		<div class="maincontent">
				    		<tiles:insertAttribute name="content" />
						</div>
			  </div>
			  <a class="backbtn hidden noprint" id='backbtn' onclick="javascript:slide_back_to_show_list_content();"><img src="img/u1/back.png"/></a>
	</div><!--end content-->
	<div class="footer noprint">
		  <jsp:include page="footer.jsp"></jsp:include>
	</div>
</div><!--end of page-->
<script>
$( "#nav" ).menu({position: {at: "left bottom"}});
$(document).ready(function(){
	$("#nav").show();
	remove_loading();
});
</script>
</body>