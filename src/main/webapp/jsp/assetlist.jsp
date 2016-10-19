<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<table class="flat-table flat-table-4 widetable" id="assetlisttable">
	<THEAD>
		<tr>
			<th>名称</th>
			<th>编号</th>
			<th>类型</th>
			<th>位置</th>
			<th>过期时间</th>
			<th width="40" align="right">状态</th>
		</tr>
	</THEAD>
	<TFOOT>
		<tr>
			<td colspan="6" align="center">
				<c:import url="pagenavigator.jsp"></c:import>
			</td>
		</tr>
	</TFOOT>
	<tbody>
		<c:forEach items="${result}" var="sa">
			<TR id="row<c:out value='${sa.assetPid }'/>" onclick="javascript:detail(<c:out value='${sa.assetPid }'/>)">
		       	<td>
		       		<c:out value="${sa.assetName}"></c:out>
		       	</td>
		       	<td>
		       		<c:out value="${sa.assetNum}"/>
		       	</td>
		       	<td>
		       		<c:out value="${sa.assetType.code}"></c:out>
		       	</td>
		       	<td>
		       		<c:out value="${sa.location}"/>
		       	</td>
		       	<td>
		       		<c:out value="${sa.expiredDate}"></c:out>
		       	</td>
		       	<td align="center" nowrap>
		       		<spr:message code="${sa.status}"/>
		       	</td>
			</TR>
		</c:forEach>
	</tbody>
</table>
<script>
function page(index){
	clear_popup_content_layout();
	var link= "assetlist/"+index;
	ajax_call_get(link, true, update_list_table);
}
function detail(id){
// 	set_selected($("#row"+id));
// 	show_right_popup_content_layout();
// 	var link= "assetdetail/"+id;
// 	ajax_call_get(link, false, update_popup_detail_content);
	slide_to_show_detail_content($("#assetlisttable #row"+id), 'assetdetail/'+id, 'asset');
}
</script>