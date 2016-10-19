<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="sidebar">
	<ul class="leftmenu">
		<a href="addassetattributes"><li class="arrow-grey">新建资产属性</li></a>
		<a href='assetattributes'><li class="selectedli arrow-blue">资产属性管理</li></a>
	</ul>
</div>
<div class="insidecontent">
	<div class="u1title1"><h2>资产属性管理</h2></div>
	<div class="contentbox">
		请选择资产类型查看相应资产属性<br>
		<select id="assettype_sel" class="shadow">
			<option id="option_0">--请选择--</option>
			<c:forEach items="${assetTypeList}" var="at">
				<option value="<c:out value='${at.assetTypePid}'/>"><c:out value='${at.code}'/></option>
			</c:forEach>
		</select>
		<div id="datadiv">
		</div>
	</div>
	<SCRIPT type="text/javascript">
	$("#assettype_sel").change( function() {
		clear_popup_content_layout();
		if($("#option_0").length!=0){$("#option_0").remove();}
		var link= 'assetattributeslist/'+$(this).val()+'/1';
		ajax_call_get(link, true, update_list_table);
	});
	</SCRIPT>
</div>