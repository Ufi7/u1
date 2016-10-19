<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<table class="flat-table flat-table-4 widetable" id="assetattachmentlisttable">
	<THEAD>
		<tr>
			<th nowrap>附件名称</th>
			<th >备注</th>
			<th nowrap>类型</th>
			<th nowrap>大小</th>
			<th nowrap>上传时间</th>
			<th nowrap>上传用户</th>
		</tr>
	</THEAD>
	<tbody>
		<c:forEach items="${atmlist}" var="a">
				<TR>
			       	<td nowrap>
			       		<a href="download/${a.atmId}"><c:out value="${a.filename}"/></a>
			       	</td>
			       	<td class="breakrow">
			       		<c:out value="${a.remark}"/>
			       	</td>
			       	<td>
			       		<c:out value="${a.type}"></c:out>
			       	</td>
			       	<td nowrap>
			       		<fmt:formatNumber value="${a.size}" pattern="#,###"/>B
			       	</td>
			       <td nowrap>
			       		<fmt:formatDate value="${a.lastupdatetimestamp}" type="both" pattern="yyyy-MM-dd HH:mm"/>
			       	</td>
			       	<td nowrap>
			       		<c:if test="${a.owner != null }">
			       			<c:out value="${a.owner.givenName}"/>
			       		</c:if>
			       	</td>
				</TR>
		</c:forEach>
	</tbody>
	<c:if test="${fn:length(atmlist)==0}">
		<tfoot>
			<tr><td align=center colspan=7>该资产没有相关附件</td></tr>
		</tfoot>
	</c:if>
</table>
<div class="commandline">
	<form id="assetattachmentform" enctype="multipart/form-data" method="post">
		<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow">
			<span class="ui-button-text">上传</span>
		</button><input id="remark" name="remark" size="30" placeholder="可以在这里输入附件备注" class="shadow"/>
		<input type="file" id="file1" name="file1" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow"/>
	</form>
</div>
<script>
$(document).ready(function(){
	$("#assetattachmentform").submit(function(){
		var filename = $("#assetattachmentform #file1").val();
		if(filename.indexOf('\\')<0){
			show_message(validation_message_no_file);
			return false;
		}
		filename = filename.split('\\');filename = filename[filename.length-1];
		var type = filename.split('.');type = type[type.length-1];
		ajax_call_upload('upload/asset/'+<c:out value='${assetid}'/>,$("#assetattachmentform #remark").val(), 'file1', true, function(data){
			if(!validateText(data)){
				var newitem='<tr><td nowrap><a href="download/'+data+'">'+filename+'</a><td>'+ $("#assetattachmentform #remark").val() +'</td><td>'+type+'</td><td></td><td>刚刚</td><td>我</td></tr>';
				$("#assetattachmentlisttable").append(newitem);
				$("#assetattachmentform #remark").val('');
				show_message(message_upload_done);
			}
		});
		return false;
	});
});
</script>