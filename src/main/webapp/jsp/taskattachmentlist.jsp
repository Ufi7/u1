<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<table class="flat-table flat-table-4 widetable" id="taskattachmentlisttable">
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
		<c:forEach items="${atmlist}" var="t">
				<TR>
			       	<td nowrap>
			       		<a href="download/${t.atmId}"><c:out value="${t.filename}"></c:out></a>
			       	</td>
			       	<td class="breakrow">
			       		<c:out value="${t.remark}"/>
			       	</td>
			       	<td>
			       		<c:out value="${t.type}"></c:out>
			       	</td>
			       	<td nowrap>
			       		<fmt:formatNumber value="${t.size}" pattern="#,###"/>B
			       	</td>
			       <td nowrap>
			       		<fmt:formatDate value="${t.lastupdatetimestamp}" type="both" pattern="yyyy-MM-dd HH:mm"/>
			       	</td>
			       	<td nowrap>
			       		<c:if test="${t.owner != null }">
			       			<c:out value="${t.owner.givenName}"/>
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
	<form id="taskattachmentform" enctype="multipart/form-data" method="post">
		<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow">
			<span class="ui-button-text">上传</span>
		</button><input id="remark" name="remark" size="30" placeholder="可以在这里输入附件备注" class="shadow"/>
		<input type="file" id="file2" name="file2" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow"/>
	</form>
</div>
<script>
$(document).ready(function(){
	$("#taskattachmentform").submit(function(){
		var filename = $("#taskattachmentform #file2").val();
		if(filename.indexOf('\\')<0){
			show_message(validation_message_no_file);
			return false;
		}
		filename = filename.split('\\');filename = filename[filename.length-1];
		var type = filename.split('.');type = type[type.length-1];
		ajax_call_upload('upload/task/'+<c:out value='${taskid}'/>,$("#taskattachmentform #remark").val(), 'file2', true, function(data){
			if(!validateText(data)){
				var newitem='<tr><td nowrap><a href="download/'+data+'">'+filename+'</a><td>'+ $("#taskattachmentform #remark").val() +'</td><td>'+type+'</td><td></td><td>刚刚</td><td>我</td></tr>';
				$("#taskattachmentlisttable").append(newitem);
				$("#taskattachmentform #remark").val('');
				show_message(message_upload_done);
			}
		});
		return false;
	});
});

</script>