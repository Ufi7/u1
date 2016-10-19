<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<table class="flat-table flat-table-4">
	<THEAD>
		<tr>
			<th>属性名称</th>
			<th>属性类型</th>
			<th>分组</th>
			<th width="40">必填</th>
			<th width="40" align="right">状态</th>
		</tr>
	</THEAD>
	<TFOOT>
		<tr>
			<td colspan="5" align="center">
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
		    <td align="center"><img src="img/confirm.png"></img></td>
		</tr>
		<c:forEach items="${result}" var="aa">
			<TR id="row<c:out value='${aa.assetAttrTemplatePid }'/>" onclick="javascript:detail(<c:out value='${aa.assetAttrTemplatePid }'/>)">
		       	<td>
		       		<c:out value="${aa.attributeName}"></c:out>
		       	</td>
		       	<td>
		       		<spr:message code="${aa.attributeType}"/>
		       	</td>
		       	<td>
		       		<c:out value="${aa.section}"></c:out>
		       	</td>
		       	<td>
		       		<c:choose>
	                	<c:when test="${aa.required == true }">
	                    	<spr:message code="true"/>
	                	</c:when>
	                	<c:otherwise>
	                    	<spr:message code="false"/>
	               		</c:otherwise>
	                </c:choose>
		       	</td>
		       	<td align="center">
		       		<c:choose>
	                	<c:when test="${aa.enabled == true}">
	                    	<img src="img/confirm.png"></img>
	                	</c:when>
	                	<c:otherwise>
	                    	<img src="img/cross.png"></img>
	                	</c:otherwise>
	                </c:choose>
		       	</td>
			</TR>
		</c:forEach>
	</tbody>
</table>
<script>
function page(index){
	clear_popup_content_layout();
	var link= "assetattributeslist/"+$("#assettype_sel").val()+'/'+index;
	ajax_call_get(link, true, update_list_table);
}
function detail(id){
	set_selected($("#row"+id));
	show_popup_content_layout(700);
	var link= "assetattributesdetail/"+id;
	ajax_call_get(link, false, update_popup_detail_content);
}
</script>