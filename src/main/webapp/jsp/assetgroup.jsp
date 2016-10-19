<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="insidecontent-extend">
	<form id="assetGroup">
		<div class="u1title1">
			<table style="border-spacing:0px;width:900px;"><tr><td>
				<h2>资产分组管理</h2></td><td align="right">
				<c:choose>
	 				<c:when test="${accessRights[8]}">
	 					查看视图<select id="customer" name="customer" class="shadow">
	 						<option value="">-全部客户-</option>
							<option value="${self[2]}"><c:out value="${self[3]}"></c:out></option>
	 					</select>&nbsp;&nbsp;
	 					<script>
	 					$("#assetGroup #customer").change(function(){
	 						$("#assetGroup input").val('');
	 						$("#assetGroup").submit();
		 				});
						$(document).ready(function(){
	 						buildOptionBasedOnAjaxReturn($("#assetGroup #customer"), 'option/customerlist');
	 					});
	 					</script>
	 				</c:when>
	 				<c:otherwise>
	 					<input type="hidden" id="customer" value="${self[2]}"/>
	 				</c:otherwise>
	 			</c:choose>
			</td></tr></table>
		</div>
		<div class="contentbox">
			<input name="groupName" id="groupName" size="20" placeholder="请输入查询分组名称" class="shadow" autocomplete="off"/>&nbsp;&nbsp;
			<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only shadow u1background" >
				<span class="ui-button-text">查询</span>
			</button>
			<button id="addnewassetgroupbtn" type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only shadow u1background" style="float:right;" onclick="javascript:toggle_add_new_assetgroup();">
				<span class="ui-button-text">+添加新分组</span>
			</button>
			<div id="datadiv">
				
			</div>
		</div>
	</form>
	<div class="uprightarrowdiv shadow hidden" id="newAssetGroupFormContainer" style="padding:10px;width:880px;">
		<form name="newAssetGroup" id="newAssetGroup">
			<input id="customer" name="customer" type="hidden"/>
			<input name="groupName" id="newgroupname" class="shadow" placeholder="分组名称"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<input name="description" id="newdescription" class="shadow" placeholder="分组描述"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only shadow u1background">
				<span class="ui-button-text">添加</span>
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="non-highlight">*.分组成员可在添加完成后进行编辑。</span>
		</form>
	</div>
</div>
<script type="text/javascript">
$("#assetGroup").submit( function() {
	clear_popup_content_layout();
	ajax_call_post('assetgroupsearch',$(this).serialize(), true, update_list_table);
	return false;
});
$("#assetGroup #customer").change(function(){
	$("#assetGroup").submit();
});
$("#newAssetGroup").submit(function(){
	if($("#assetGroup #customer").length>0){
		$("#newAssetGroup #customer").val($("#assetGroup #customer").val());
	}
	ajax_call_post('addnewassetgroup',$(this).serialize(), true, after_add_assetgroup);
	return false;
});
function after_add_assetgroup(data){
	if(!validateText(data)){
		var entityname=$("#newgroupname").val();
		message = success_add_assetgroup.replace('~~0', entityname);
		appendnewrow([$("#newgroupname").val(), $("#newdescription").val()], $("#datadiv table"), data, detail);
		$("#newAssetGroup input").val('');
		$("#newAssetGroupFormContainer").fadeOut();
		show_message(message);
	}
}
function toggle_add_new_assetgroup(){
	if($("#newAssetGroupFormContainer").is(":visible")){
		$("#newAssetGroupFormContainer").fadeOut();
	}else{
		$("#newAssetGroupFormContainer").css('top', $("#addnewassetgroupbtn").position().top+35);
		$("#newAssetGroupFormContainer").css('left', $("#addnewassetgroupbtn").position().left-800);
		$("#newAssetGroupFormContainer").fadeIn();
	}
}
$(document).ready(function(){
	$("#assetGroup").submit();
});
</script>