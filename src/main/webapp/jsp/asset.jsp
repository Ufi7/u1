<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="insidecontent-extend">
	<spring:form commandName="simpleAsset">
		<div class="u1title1">
			<table style="border-spacing:0px;width:900px;"><tr><td>
				<h2>资产管理</h2></td><td align="right">
				<c:if test="${accessRights[8]}">
					<spring:select path="customer" multiple="false" cssClass="shadow">
						<spring:option  value="">-全部客户-</spring:option>
						<spring:option  value="${self[2]}"><c:out value="${self[3] }"/></spring:option>
					</spring:select>&nbsp;&nbsp;&nbsp;&nbsp;
					<script>
						$("#simpleAsset #customer").change(function(){
							$("#simpleAsset input").val('');
							$("#simpleAsset select").not($(this)).find("option:first").attr('selected', 'selected');
							$("#simpleAsset").submit();
						});
						$(document).ready(function(){
							buildOptionBasedOnAjaxReturn($("#simpleAsset #customer"), 'option/customerlist');
						});
					</script>
				</c:if>
			</td></tr></table>
		</div>
		<div class="contentbox">
			<spring:select path="assetType" multiple="false" cssClass="shadow">
				<spring:option value="">--全部类型--</spring:option>
			</spring:select>
			<spring:select path="status" cssClass="shadow">
				<spring:option value="">--全部状态--</spring:option>
				<option value="breakdown">故障中</option>
				<option value="expired">已过期</option>
				<option value="fixing">维修中</option>
				<option value="idle">闲置</option>
				<option value="in_use">使用中</option>
				<option value="orderring">购买中</option>
				<option value="suspended">暂停使用</option>
				<option value="wait_for_fix">等待维修</option>
			</spring:select>
			<spring:input path="assetName" size="16" placeholder="请输入查询资产名称" cssClass="shadow" autocomplete="off"/>
			<spring:input path="assetNum" size="16" placeholder="请输入查询资产编号" cssClass="shadow" autocomplete="off"/>
			<spring:input path="location" size="16" placeholder="请输入查询资产位置" cssClass="shadow" autocomplete="off"/>
			<input id="expiryDateFrom" name="expiryDateFrom" size="15" maxlength=10 placeholder="资产过期时间起始" class="shadow" autocomplete="off"/>
			<input id="expiryDateTo" name="expiryDateTo" size="15" maxlength=10 placeholder="资产过期时间结束" class="shadow" autocomplete="off"/>
			<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only shadow u1background" >
				<span class="ui-button-text">查询</span>
			</button>
		
			<div id="datadiv">
				
			</div>
		</div>
	</spring:form>
</div>
<SCRIPT type="text/javascript">
$("#simpleAsset").submit( function() {
	clear_popup_content_layout();
	ajax_call_post('assetsearch',$(this).serialize(), true, update_list_table);
	return false;
});
$(document).ready(function(){
	$("#simpleAsset").submit();
	buildOptionBasedOnAjaxReturn($("#simpleAsset #assetType"), 'option/assettypelist');
	$("#expiryDateFrom").datepicker();
	$("#expiryDateTo").datepicker();
});
</SCRIPT>