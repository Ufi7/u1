<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<table class="timelinetable" id="tasklogtable">
		<c:set var="index">1</c:set>
		<c:forEach items="${tasklogList}" var="tasklog">
			
				<tr>
					<td rowspan=2 nowrap class="time" align="right">
						<table class="timelabel">
							<tr>
								<td class="bgbackgound"><fmt:formatDate value="${tasklog.createdtimestamp}" type="date" pattern="yyyy-MM-dd"/></td>
								<td class="bgcolorborder"><fmt:formatDate value="${tasklog.createdtimestamp}" type="date" pattern="EEEE"/></td>
								<td class="bgbackgound"><b><fmt:formatDate value="${tasklog.createdtimestamp}" type="time" pattern="HH:mm"/></b></td>
							</tr>
						</table>
					</td>
					<td class="icon"><img src="img/u1/ac<c:out value='${tasklog.actionCode}'/>.png"/></td>
					<td rowspan=2 class="remark">
						<div class="leftarrowdiv">
						<spr:message code="message_${tasklog.actionCode}" arguments="${tasklog.owner.givenName},${tasklog.assignedTo.givenName}, ${tasklog.autoremark}"/><br>
						<c:if test="${tasklog.remark!=null && tasklog.remark!=''}">备注：<c:out value="${tasklog.remark}"/><br></c:if>
						<c:if test="${fn:length(tasklog.recordList)>0}">
							<c:forEach items="${tasklog.recordList}" var="log">
								<div class="entityblock shadow">『<c:out value="${log.componentstore.component.field1}"/>/<c:out value="${log.componentstore.component.field2}"/>/<c:out value="${log.componentstore.component.field3}"/>/<c:out value="${log.componentstore.component.field4}"/>』 × <c:out value="${log.quantity}"/><span class=hidden><c:out value="${log.componentstore.componentStoreId}"/></span></div>
							</c:forEach>
						</c:if>
						</div>
					</td>
				</tr>
				<c:if test="${index!=fn:length(tasklogList)}">
					<tr>
						<td class="line"></td>
					</tr>
				</c:if>
				<c:set var="index"><c:out value="${index+1 }"/></c:set>
		</c:forEach>
		<c:if test="${fn:length(tasklogList)==0}">
			<tr><td align=center><b>该工单没有相关日志记录</b></td></tr>
		</c:if>
	</table>
	<script>
	$("#tasklogtable .entityblock").click(function(){
		var id = $(this).children("span").html();
		slide_to_show_detail_content(null, 'componentstoredetail/'+id, 'componentstore');
	});
	</script>