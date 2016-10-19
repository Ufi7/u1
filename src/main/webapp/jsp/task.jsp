<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="insidecontent-extend">
	<spring:form commandName='tasksearch'>
		<div class="u1title1">
			<table style="border-spacing:0px;width:900px;"><tr><td>
				<h2>工单信息管理</h2></td><td align="right">
				<c:if test="${accessRights[8]}">
					<spring:select path="customer" cssClass="shadow right10margin">
						<spring:option value="">--全部客户--</spring:option>
						<c:choose>
							<c:when test="${tasksearch.customer!=null}">
								<spring:option value="${tasksearch.customer.customerId}"><c:out value="${tasksearch.customer.customerName}"/></spring:option>
							</c:when>
							<c:otherwise>
								<spring:option value="${self[2]}"><c:out value="${self[3]}"/></spring:option>
							</c:otherwise>
						</c:choose>
					</spring:select>
					<script>
						$("#tasksearch #customer").change(function(){
							$("#tasksearch input").val('');
							$("#tasksearch select").not($(this)).find("option:first").attr('selected', 'selected');
							$("#tasksearch").submit();
						});
						$(document).ready(function(){
							buildOptionBasedOnAjaxReturn($("#tasksearch #customer"), 'option/customerlist');
						});
					</script>
				</c:if>
			</td></tr></table>
		</div>
		<div class="contentbox">
			<spring:input path="taskNum" size="20" placeholder="请输入查询工单编号" cssClass="shadow right10margin" autocomplete="off"/>
			<spring:input path="taskName" size="20" placeholder="请输入查询工单简述" cssClass="shadow right10margin" autocomplete="off"/>
			<spring:select path="taskType" cssClass="shadow right10margin">
				<spring:option value="">所有工单类型</spring:option>
			</spring:select>
			<spring:select path="status" cssClass="shadow right10margin">
				<spring:option value="">所有状态</spring:option>
				<spring:option value="created">已提交</spring:option>
				<spring:option value="pending-reassigned">待重新指派</spring:option>
				<spring:option value="assigned">已指派</spring:option>
				<spring:option value="in_progress">处理中</spring:option>
				<spring:option value="done">处理完成</spring:option>
				<spring:option value="rejected">被拒绝</spring:option>
			</spring:select>
			<select id="assignedto" name="assignedto" class="shadow right10margin">
				<option value="">所有指派条件</option>
				<option value="unassigned">所有无指派</option>
				<option value="assigned">所有已指派</option>
				<option value="" disabled="disabled">--下列为指派人--</option>
				<c:if test="${tasksearch.assignedTo!=null}">
					<option value="${tasksearch.assignedTo.userId}" selected=selected></option>
				</c:if>
			</select>
			<select name="createdDatePerid" class="shadow right10margin">
				<option value="0">所有</option>
				<option value="3">近三天内创建</option>
				<option value="7">近一周内创建</option>
				<option value="30">近一个月内创建</option>
				<option value="365">近一年内创建</option>
			</select>
			<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only shadow u1background" >
				<span class="ui-button-text">查询</span>
			</button>
			<div id="datadiv">
				
			</div>
		</div>
	</spring:form>
</div>
<script type="text/javascript">
$("#tasksearch").submit(function(){
	clear_popup_content_layout();
	ajax_call_post('tasksearch',$(this).serialize(), true, update_list_table);
	return false;
});
$(document).ready(function(){
	$("#tasksearch").submit();
	buildOptionBasedOnAjaxReturn($("#tasksearch #taskType"), 'option/tasktypelist');
	buildOptionBasedOnAjaxReturn($("#tasksearch #assignedto"), 'option/userlist');
});
</script>