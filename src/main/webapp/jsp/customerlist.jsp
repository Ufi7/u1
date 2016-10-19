<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="flat-table flat-table-4">
	<THEAD>
		<tr>
			<th>客户名称</th>
			<th>客户描述</th>
		</tr>
	</THEAD>
	<TFOOT>
		<tr>
			<td colspan="3" align="center">
				<c:import url="pagenavigator.jsp"></c:import>
			</td>
		</tr>
	</TFOOT>
	<tbody>
		<tr id="newrow" style="display:none;">
			<td></td>
		    <td></td>
		</tr>
		<c:forEach items="${result}" var="c">
			<TR id="row<c:out value='${c.customerId }'/>" onclick="javascript:detail(<c:out value='${c.customerId }'/>)">
		       	<td>
		       		<c:out value="${c.customerName}"></c:out>
		       	</td>
		       	<td>
		       		<c:out value="${c.customerDesc}"></c:out>
		       	</td>
			</TR>
		</c:forEach>
	</tbody>
</table>
<script>
function page(index){
	clear_popup_content_layout();
	var link= "customerlist/"+index;
	ajax_call_get(link, true, update_list_table);
}
function detail(id){
	set_selected($("#row"+id));
	show_popup_content_layout(600);
	var link= "customerdetail/"+id;
	ajax_call_get(link, false, update_popup_detail_content);
}
</script>