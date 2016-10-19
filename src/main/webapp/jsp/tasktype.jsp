<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="sidebar">
	<ul class="leftmenu">
		<a href="assettype"><li class="arrow-grey">资产类型</li></a>
		<a href='tasktype'><li class="selectedli arrow-blue">工单类型</li></a>
		<a href='customer'><li class="arrow-grey">客户管理</li></a>
		<a href='department'><li class="arrow-grey">客户属下部门</li></a>
	</ul>
</div>
<div class="insidecontent">
	<div class="u1title1"><h2>工单类型</h2></div>
	<div class="contentbox">
		<spring:form commandName="taskType">
			<spring:input path="code" size="20" placeholder="新建工单类型名称" autocomplete="off" cssClass="shadow"/>&nbsp;
			<spring:input path="description" size="20" placeholder="新建工单类型描述" autocomplete="off" cssClass="shadow"/>&nbsp;
			<spring:input path="weight" size="20" placeholder="新建工单类型权重值" autocomplete="off" cssClass="shadow"/>&nbsp;
			<spring:input path="definedCode" size="20" placeholder="新建工单类型编号前缀" autocomplete="off" cssClass="shadow"/>
			<br>
			优先级缺省时限(小时):
			<spring:input path="highDefaultTime" size="5" placeholder="高" autocomplete="off" cssClass="shadow"/>
			<spring:input path="mediumDefaultTime" size="5" placeholder="中" autocomplete="off" cssClass="shadow"/>
			<spring:input path="lowDefaultTime" size="5" placeholder="低" autocomplete="off" cssClass="shadow"/>
			<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only shadow u1background" >
				<span class="ui-button-text">添加</span>
			</button>
		</spring:form>
		<div id="datadiv">
			<c:import url="tasktypelist.jsp"></c:import>
		</div>
	</div>
	<SCRIPT type="text/javascript">
	$("#taskType").submit( function() {
		clear_popup_content_layout();
		ajax_call_post('tasktypesubmit', $(this).serialize(), true, afteraddnewtasktype);
		return false;
	});
	function afteraddnewtasktype(data){
		if(!validateText(data)){
			var message = success_add_tasktype.replace('~~0', $("#code").val());
			var tds=[$("#newrow").children().eq(0),$("#newrow").children().eq(1), $("#newrow").children().eq(2),$("#newrow").children().eq(3),$("#newrow").children().eq(4),$("#newrow").children().eq(5),$("#newrow").children().eq(6)];
			var inputs=[$("#code"), $("#description"), $("#weight"),$("#highDefaultTime"),$("#mediumDefaultTime"),$("#lowDefaultTime"), $("#definedCode")];
			var prefill0s=[false, false, true, true, true, true, false];
			addnewrow($("#newrow"), tds, inputs, prefill0s, data);
			show_message(message);
		}
	}
	</SCRIPT>
</div>