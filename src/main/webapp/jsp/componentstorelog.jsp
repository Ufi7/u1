<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="timelinetable" id="componentstorelogtable">
	<c:set var="index">1</c:set>
	<c:forEach items="${componentrecordlist}" var="r">
		<tr>
			<td rowspan=2 nowrap class="time" align="right">
				<table class="timelabel">
					<tr>
						<td class="bgbackgound"><fmt:formatDate value="${r.createdDatetime}" type="date" pattern="yyyy-MM-dd"/></td>
						<td class="bgcolorborder"><fmt:formatDate value="${r.createdDatetime}" type="date" pattern="EEEE"/></td>
						<td class="bgbackgound"><b><fmt:formatDate value="${r.createdDatetime}" type="time" pattern="HH:mm"/></b></td>
					</tr>
				</table>
			</td>
			<td class="icon"><img src="img/u1/cr<c:out value='${r.reason}'/>.png"/></td>
			<td rowspan=2 class="remark">
				<div class="leftarrowdiv bgcolor">
				<b><c:out value="${r.user.givenName}"/></b>&nbsp;<spr:message code="component_log_reason${r.reason}" arguments="${r.quantity>0?r.quantity:0-r.quantity},${r.price}"/><br>
				<c:if test="${r.logQuantity!=null}">操作完成后实时库存为&nbsp;<b><c:out value="${r.logQuantity}"/></b></c:if>
				<c:if test="${r.remark!=null && r.remark!=''}"><br>备注：<c:out value="${r.remark}"/><br></c:if>
				<c:if test="${r.taskLog!=null}">&nbsp;<div class="entityblock shadow">相关工单：<b><c:out value="${r.taskLog.task.taskNum}"/></b>&nbsp;<b><c:out value="${r.taskLog.task.taskName}"/></b><span class=hidden><c:out value="${r.taskLog.task.taskPid}"/></span></div></c:if>
				</div>
			</td>
		</tr>
		<c:if test="${index!=fn:length(componentrecordlist)}">
			<tr>
				<td class="line"></td>
			</tr>
		</c:if>
		<c:set var="index"><c:out value="${index+1 }"/></c:set>
	</c:forEach>
	<c:if test="${fn:length(componentrecordlist)==0}">
		<tr><td align=center><b>该配件没有相关日志记录</b></td></tr>
	</c:if>
</table>
<script>
	$("#componentstorelogtable .entityblock").click(function(){
		var link= "taskdetail/"+$(this).children("span").html();
		slide_to_show_detail_content(null, link, 'task');
	});
</script>
