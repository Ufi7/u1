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
	<TFOOT>
		<tr>
			<td colspan="2" align="center">
				<c:import url="pagenavigator.jsp"></c:import>
			</td>
		</tr>
	</TFOOT>
	<tbody>
		<c:forEach items="${result}" var="ag">
			<TR id="row<c:out value='${ag.assetGroupPid }'/>" onclick="javascript:detail(<c:out value='${ag.assetGroupPid }'/>)">
		       	<td>
		       		<c:out value="${ag.groupName}"></c:out>
		       	</td>
		       	<td>
		       		<c:out value="${ag.description}"></c:out>
		       	</td>
			</TR>
		</c:forEach>
	</tbody>
</table>
<script>
function page(index){
	clear_popup_content_layout();
	var link= "assetgrouplist/"+index;
	ajax_call_get(link, true, update_list_table);
}
function detail(id){
// 	set_selected($("#row"+id));
	var link= "assetgroupdetail/"+id;
	slide_to_show_detail_content($("#row"+id), link, 'assetgroup');
// 	ajax_call_get(link, true, update_tobeslide_content);
// 	show_right_popup_content_layout();
// 	var link= "assetgroupdetail/"+id;
// 	ajax_call_get(link, false, update_popup_detail_content);
}
</script>