<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<table class="timelinetable">
		<c:set var="index">1</c:set>
		<c:forEach items="${assetlogList}" var="assetlog">
			<c:if test="${index!=fn:length(assetlogList)}">
				<tr>
					<td rowspan=2 nowrap class="time" align="right">
						<table class="timelabel">
							<tr>
								<td class="bgbackgound"><fmt:formatDate value="${assetlog.createdtimestamp}" type="date" pattern="yyyy-MM-dd"/></td>
								<td class="bgcolorborder"><fmt:formatDate value="${assetlog.createdtimestamp}" type="date" pattern="EEEE"/></td>
								<td class="bgbackgound"><b><fmt:formatDate value="${assetlog.createdtimestamp}" type="time" pattern="HH:mm"/></b></td>
							</tr>
						</table>
					</td>
					<td class="icon"><img src="img/u1/ac<c:out value='${assetlog.actionCode}'/>.png"/></td>
					<td rowspan=2 class="remark">
						<div class="leftarrowdiv">
						<c:set var="param2nd">不变</c:set>
						<c:if test="${assetlog.actionCode!='f' &&assetlog.autoremark != null && assetlog.autoremark!=''}">
							<c:set var="param2nd"><spr:message code='${assetlog.autoremark}'/></c:set>
						</c:if>
						<c:if test="${assetlog.actionCode=='f'}">
							<c:set var="param2nd"><c:out value='${assetlog.autoremark}'/></c:set>
						</c:if>
						<spr:message code="asset_message_${assetlog.actionCode}" arguments="${assetlog.owner.givenName}, ${param2nd}"/>
						<c:if test="${assetlog.remark!=null && assetlog.remark!=''}"><br>备注：<c:out value="${assetlog.remark}"/></c:if>
						</div>
					</td>
				</tr>
				<tr>
					<td class="line"></td>
				</tr>
			</c:if>
			<c:if test="${index==fn:length(assetlogList)}">
				<td nowrap class="time" align="right">
					<table class="timelabel">
						<tr>
							<td class="bgbackgound"><fmt:formatDate value="${assetlog.createdtimestamp}" type="date" pattern="yyyy-MM-dd"/></td>
							<td class="bgcolorborder"><fmt:formatDate value="${assetlog.createdtimestamp}" type="date" pattern="EEEE"/></td>
							<td class="bgbackgound"><b><fmt:formatDate value="${assetlog.createdtimestamp}" type="time" pattern="HH:mm"/></b></td>
						</tr>
					</table>
				</td>
				<td class="icon"><img src="img/u1/ac<c:out value='${assetlog.actionCode}'/>.png"/></td>
				<td class="remark">
					<div class="leftarrowdiv">
						<c:set var="param2nd">不变</c:set>
						<c:if test="${assetlog.actionCode!='f' &&assetlog.autoremark != null && assetlog.autoremark!=''}">
							<c:set var="param2nd"><spr:message code='${assetlog.autoremark}'/></c:set>
						</c:if>
						<c:if test="${assetlog.actionCode=='f'}">
							<c:set var="param2nd"><c:out value='${assetlog.autoremark}'/></c:set>
						</c:if>
					<spr:message code="asset_message_${assetlog.actionCode}" arguments="${assetlog.owner.givenName}, ${param2nd}"/>
					<c:if test="${assetlog.autoremark!=null && assetlog.autoremark!=''}"><br>备注：<c:out value="${assetlog.remark}"/></c:if>
					</div>
				</td>
			</c:if>
			<c:set var="index"><c:out value="${index+1 }"/></c:set>
		</c:forEach>
		<c:if test="${fn:length(assetlogList)==0}">
			<tr><td align=center><b>该工单没有相关日志记录</b></td></tr>
		</c:if>
	</table>
