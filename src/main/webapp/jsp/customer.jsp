<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="sidebar">
	<ul class="leftmenu">
		<a href="assettype"><li class="arrow-grey">资产类型</li></a>
		<a href='tasktype'><li class="arrow-grey">工单类型</li></a>
		<a href='customer'><li class="selectedli arrow-blue">客户管理</li></a>
		<a href='department'><li class="arrow-grey">客户属下部门</li></a>
	</ul>
</div>
<div class="insidecontent">
	<div class="u1title1"><h2>客户管理</h2></div>
	<div class="contentbox">
		<form id="newcustomer" name="newcustomer" method="POST">
			<input name="customerName" id="customerName" size="20" placeholder="新建客户名称" autocomplete="off" class="shadow right10margin"/>
			<input name="customerDesc" id="customerDesc" size="20" placeholder="新建客户描述" autocomplete="off" class="shadow right10margin"/>
			<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only shadow u1background" >
				<span class="ui-button-text">添加</span>
			</button>
		</form>
		<div id="datadiv">
			<c:import url="customerlist.jsp"></c:import>
		</div>
	</div>
	<SCRIPT type="text/javascript">
	$("#newcustomer").submit( function() {
		clear_popup_content_layout();
		ajax_call_post('customersubmit', $(this).serialize(), true, function(data){
			if(!validateText(data)){
				var message = success_add_customer.replace('~~0', $("#newcustomer #customerName").val());
				var tds=[$("#newrow").children().eq(0),$("#newrow").children().eq(1)];
				var inputs=[$("#newcustomer #customerName"), $("#newcustomer #customerDesc")];
				var prefill0s=[false, false];
				addnewrow($("#newrow"), tds, inputs, prefill0s, data);
				show_message(message);
			}
		});
		return false;
	});
	</SCRIPT>
</div>