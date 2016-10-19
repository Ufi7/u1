<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="flat-table flat-table-4">
	<THEAD>
		<tr>
			<th>任务类型</th>
			<th>描述</th>
			<th>权重值</th>
			<th>高时限</th>
			<th>中时限</th>
			<th>低时限</th>
			<th>自编号</th>
		</tr>
	</THEAD>
	<TFOOT>
		<tr>
			<td colspan="7" align="center">
				<c:import url="pagenavigator.jsp"></c:import>
			</td>
		</tr>
	</TFOOT>
	<tbody>
		<tr id="newrow" style="display:none;">
			<td></td>
		    <td></td>
		    <td></td>
		    <td></td>
		    <td></td>
		    <td></td>
			<td></td>
		</tr>
		<c:forEach items="${result}" var="tt">
			<TR id="row<c:out value='${tt.taskTypePid }'/>" onclick="javascript:detail(<c:out value='${tt.taskTypePid }'/>)">
		       	<td>
		       		<c:out value="${tt.code}"></c:out>
		       	</td>
		       	<td>
		       		<c:out value="${tt.description}"></c:out>
		       	</td>
		       	<td>
		       		<c:out value="${tt.weight}"></c:out>
		       	</td>
		       	<td>
		       		<c:out value="${tt.highDefaultTime}"></c:out>
		       	</td>
		       	<td>
		       		<c:out value="${tt.mediumDefaultTime}"></c:out>
		       	</td>
		       	<td>
		       		<c:out value="${tt.lowDefaultTime}"></c:out>
		       	</td>
		       	<td>
		       		<c:out value="${tt.definedCode}"></c:out>
		       	</td>
			</TR>
		</c:forEach>
	</tbody>
</table>
<script>
function page(index){
	clear_popup_content_layout();
	var link= "tasktypelist/"+index;
	ajax_call_get(link, true, update_list_table);
}
function detail(id){
	set_selected($("#row"+id));
	show_popup_content_layout(600);
	var link= "tasktypedetail/"+id;
	ajax_call_get(link, false, update_popup_detail_content);
}
</script>