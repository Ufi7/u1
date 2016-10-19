<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="flat-table flat-table-4">
	<THEAD>
		<tr>
			<th>角色名称</th>
		</tr>
	</THEAD>
	<TFOOT>
		<tr>
			<td align="center">
				<c:import url="pagenavigator.jsp"></c:import>
			</td>
		</tr>
	</TFOOT>
	<tbody>
		<c:forEach items="${result}" var="role">
			<TR id="row<c:out value='${role.roleId }'/>" onclick="javascript:detail(<c:out value='${role.roleId }'/>)">
		       	<td>
		       		<c:out value="${role.roleName}"></c:out>
		       	</td>
			</TR>
		</c:forEach>
	</tbody>
</table>
<script>
function page(index){
	clear_popup_content_layout();
	var link= "roleslist/"+index;
	ajax_call_get(link, true, update_list_table);
}
function detail(id){
	set_selected($("#row"+id));
	show_popup_content_layout(600);
	var link= "rolesdetail/"+id;
	ajax_call_get(link, false, update_popup_detail_content);
}
</script>