<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="flat-table flat-table-4">
	<THEAD>
		<tr>
			<th>角色名</th>
			<th>姓名</th>
			<th>电话</th>
			<th>eMail</th>
		</tr>
	</THEAD>
	<TFOOT>
		<tr>
			<td align="center" colspan="4">
				<c:import url="pagenavigator.jsp"></c:import>
			</td>
		</tr>
	</TFOOT>
	<tbody>
		<c:forEach items="${result}" var="user">
			<TR id="row<c:out value='${user.userId }'/>" onclick="javascript:detail(<c:out value='${user.userId }'/>)">
		       	<td>
		       		<c:out value="${user.username}"></c:out>
		       	</td>
		       	<td align="right">
		       		<c:out value="${user.givenName}"></c:out>
		       	</td>
		       	<td align="right">
		       		<c:out value="${user.telephone}"></c:out>
		       	</td>
		       	<td align="right">
		       		<c:out value="${user.email}"></c:out>
		       	</td>
			</TR>
		</c:forEach>
	</tbody>
</table>
<script>
function page(index){
	clear_popup_content_layout();
	var link= "userslist/"+index;
	ajax_call_get(link, true, update_list_table);
}
function detail(id){
	set_selected($("#row"+id));
	show_popup_content_layout(600);
	var link= "usersdetail/"+id;
	ajax_call_get(link, false, update_popup_detail_content);
}
</script>