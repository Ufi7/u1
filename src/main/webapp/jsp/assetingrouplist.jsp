<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<table class="flat-table flat-table-4 widetable">
	<THEAD>
		<tr>
			<th>分组名称</th>
			<th>分组描述</th>
		</tr>
	</THEAD>
	<tbody>
		<c:set var="index">0</c:set>
		<c:forEach items="${result}" var="ag">
			<c:if test="${ag.enabled}">
				<TR id="row<c:out value='${ag.assetGroupPid }'/>" onclick="javascript:assetgroupdetail(<c:out value='${ag.assetGroupPid }'/>)">
			       	<td>
			       		<c:out value="${ag.groupName}"></c:out>
			       	</td>
			       	<td>
			       		<c:out value="${ag.description}"></c:out>
			       	</td>
				</TR>
				<c:set var="index"><c:out value="${index+1 }"/></c:set>
			</c:if>
		</c:forEach>
	</tbody>
	<TFOOT>
		<c:if test="${index==0 }">
			<tr><td align=center colspan=2>该资产没有属于任何分组</td></tr>
		</c:if>
	</TFOOT>
</table>
<script>
function assetgroupdetail(id){
	var link= "assetgroupdetail/"+id;
	slide_to_show_detail_content($("#row"+id), link, 'assetgroup');
}
</script>