<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="sidebar">
	<ul class="leftmenu">
		<a href="assettype"><li class="selectedli arrow-blue">资产类型</li></a>
		<a href='tasktype'><li class="arrow-grey">工单类型</li></a>
		<a href='customer'><li class="arrow-grey">客户管理</li></a>
		<a href='department'><li class="arrow-grey">客户属下部门</li></a>
	</ul>
</div>
<div class="insidecontent">
	<div class="u1title1"><h2>资产类型</h2></div>
	<div class="contentbox">
		<spring:form commandName="assetType">
			<spring:input path="code" size="20" placeholder="新建资产类型名称" autocomplete="off" cssClass="shadow"/>
			<spring:input path="description" size="20" placeholder="新建资产类型描述" autocomplete="off" cssClass="shadow"/>
			<spring:input path="definedCode" size="20" placeholder="新建资产类型编号前缀" autocomplete="off" cssClass="shadow"/>
			<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only shadow u1background" >
				<span class="ui-button-text">添加</span>
			</button>
		</spring:form>
		<div id="datadiv">
			<c:import url="assettypelist.jsp"></c:import>
		</div>
	</div>
	<SCRIPT type="text/javascript">
	$("#assetType").submit( function() {
		clear_popup_content_layout();
		ajax_call_post("assettypesubmit", $(this).serialize(), true, afteraddnewassettype);
		return false;
	});
	function afteraddnewassettype(data){
		if(!validateText(data)){
			var message = success_add_assettype.replace('~~0', $("#assetType input").eq(0).val());
			var tds=[$("#newrow").children().eq(0), $("#newrow").children().eq(1),  $("#newrow").children().eq(2)];
			var inputs=[$("#assetType input").eq(0), $("#assetType input").eq(1), $("#assetType input").eq(2)];
			var prefill0s=[false, false, false];
			addnewrow($("#newrow"), tds, inputs, prefill0s, data);
			show_message(message);
		}
	}
	</SCRIPT>
</div>