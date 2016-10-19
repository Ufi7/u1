<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="flat-table flat-table-4">
	<THEAD>
		<tr>
			<th>资产类型</th>
			<th>资产描述</th>
			<th>自编号</th>
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
		    <td></td>
		</tr>
		<c:forEach items="${result}" var="at">
			<TR id="row<c:out value='${at.assetTypePid }'/>" onclick="javascript:detail(<c:out value='${at.assetTypePid }'/>)">
		       	<td>
		       		<c:out value="${at.code}"></c:out>
		       	</td>
		       	<td>
		       		<c:out value="${at.description}"></c:out>
		       	</td>
		       	<td>
		       		<c:out value="${at.definedCode}"></c:out>
		       	</td>
			</TR>
		</c:forEach>
	</tbody>
</table>
<script>
function page(index){
	clear_popup_content_layout();
	var link= "assettypelist/"+index;
	ajax_call_get(link, true, update_list_table);
}
function detail(id){
	set_selected($("#row"+id));
	show_popup_content_layout(620);
	var link= "assettypedetail/"+id;
	ajax_call_get(link, false, update_popup_detail_content);
}
</script>