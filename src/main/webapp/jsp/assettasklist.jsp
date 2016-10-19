<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<table class="flat-table flat-table-4 widetable" id="tasklisttable">
	<THEAD>
		<tr>
			<th nowrap>工单号</th>
			<th nowrap>工单标题</th>
			<th nowrap>工单类型</th>
			<th nowrap>状态</th>
			<th nowrap>创建日期</th>
			<th nowrap>目标结束时间</th>
			<th nowrap>指派给</th>
		</tr>
	</THEAD>
	<tbody>
		<c:set var="index">0</c:set>
		<c:forEach items="${result}" var="t">
			<c:if test="${t.enabled}">
				<TR id="row<c:out value='${t.taskPid }'/>" onclick="javascript:taskdetail(<c:out value='${t.taskPid }'/>)">
			       	<td nowrap>
			       		<c:out value="${t.taskNum}"></c:out>
			       	</td>
			       	<td>
			       		<c:out value="${t.taskName}"></c:out>
			       	</td>
			       	<td nowrap>
			       		<c:out value="${t.taskType.code}"></c:out>
			       	</td>
			       	<td nowrap>
			       		<spr:message code="${t.status}"/>
			       	</td>
			       	<td nowrap>
			       		<fmt:formatDate value="${t.startDatetime}" type="both" pattern="yyyy-MM-dd HH:mm"/><br>
			       	</td>
			       	<td nowrap>
			       		<fmt:formatDate value="${t.dueDatetime}" type="both" pattern="yyyy-MM-dd HH:mm"/>&nbsp;
			       	</td>
			       	<td nowrap>
			       		<c:if test="${t.assignedTo != null }">
			       			<c:out value="${t.assignedTo.givenName}"/>
			       		</c:if>
			       	</td>
				</TR>
				<c:set var="index"><c:out value="${index+1 }"/></c:set>
			</c:if>
		</c:forEach>
	</tbody>
	<tfoot>
		<c:if test="${index==0 }">
			<tr><td align=center colspan=7>该资产没有相关工单</td></tr>
		</c:if>
	</tfoot>
</table>
<script>
function taskdetail(id){
	var link= "taskdetail/"+id;
	slide_to_show_detail_content($("#tasklisttable #row"+id), link, 'task');
// 	show_right_popup_content_layout();
// 	var link= "assetgroupdetail/"+id;
// 	ajax_call_get(link, false, update_popup_detail_content);
}
</script>