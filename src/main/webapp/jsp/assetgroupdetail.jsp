<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="insidecontent-extend">
		<div class="u1title1"><h2>编辑资产分组</h2></div>
		<div class="contentbox">
			<table class="detailtable" id="assetgroupdetailtable">
				<tr>
					<td>
						<spring:form commandName="assetGroupDetail">
						<span class="non-highlight">所属分组：</span><span class="fixtext right20margin"><c:out value="${assetGroupDetail.customer.customerName}"/></span>
						<span class="non-highlight"><img  src="img/u1/mandatory.png" class="hidden"/>分组名称:</span>
						<span class="text right20margin"><c:out value="${assetGroupDetail.groupName}" /></span>
						<spring:input path="groupName" size="20" cssClass="shadow hidden edit right20margin" autocomplete="off"/>
						<span class="non-highlight">分组描述:</span>
						<span class="text"><c:out value="${assetGroupDetail.description}" /></span>
						<spring:input path="description" size="20" cssClass="shadow hidden edit" autocomplete="off"/>
						<button id="addnewasssetbtn" type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow hidden edit" style="float:right;" onclick="javascript:ag_toggle_asset_group_sel_asset_metro();">
							<span class="ui-button-text"><b>+</b>添加新资产</span>
						</button>
						<select name='assetList' id='assetList' class='hidden' multiple=multiple>
							<c:forEach items="${assetGroupDetail.assetList}" var="asset">
								<c:if test="${asset.enabled}">
									<option value="${asset.assetPid }" selected=selected><c:out value="${asset.assetPid }"></c:out></option>
								</c:if>
							</c:forEach>
						</select>
						</spring:form>
					</td>
				</tr>
				<tr>
					<td>
						<span class="non-highlight">分组资产:</span>
						<span class="">
							<div class="entityblockContainer" id="ag_selected_asset_entity_container">
								<c:forEach items="${assetGroupDetail.assetList}" var="asset">
									<c:if test="${asset.enabled}">
										<div class="entityblock shadow">
											<b><c:out value="${asset.assetName}"/></b><br>
											<font class=s><c:out value="${asset.assetNum}"/></font><br>
											<c:out value="${asset.assetType.code}"/>/<spr:message code="${asset.status}"/>
											<span class="hidden"><c:out value="${asset.assetPid}"/></span>
										</div>
									</c:if>
								</c:forEach>
							</div>
						</span>
					</td>
				</tr>
				<tr>
					<td >
<!-- 						<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" style="float:left;" onclick="javascript:slide_back_to_show_list_content();"> -->
<!-- 							<span class="ui-button-text">返回</span> -->
<!-- 						</button> -->
						<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow staticbtn" style="float:right;" onclick="javascript:edit_asset_group();">
							<span class="ui-button-text">编辑</span>
						</button>
						<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow edit hidden" style="float:right;" onclick="javascript:delete_asset_group(<c:out value='${assetGroupDetail.assetGroupPid}'/>);">
							<span class="ui-button-text">删除</span>
						</button>
						<button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow edit hidden" style="float:right;" onclick="javascript:update_asset_group(<c:out value='${assetGroupDetail.assetGroupPid}'/>);">
							<span class="ui-button-text">更新</span>
						</button>
					</td>
				</tr>
			</table>
		</div>
		<div class="uprightarrowdiv shadow hidden" id="ag_asset_set_metro_outter_layout">
			<form id="ag_metro_asset_form" action="metro/assetlist" method="post">
				<select name="assetType" id="assetType" class="shadow right20margin">
					<option value=''>全部资产类型</option>
				</select>
				<select name="status" class="shadow right20margin">
					<option value="">全部状态</option>
					<option value="breakdown">故障中</option>
					<option value="expired">已过期</option>
					<option value="fixing">维修中</option>
					<option value="idle">闲置</option>
					<option value="in_use">使用中</option>
					<option value="orderring">购买中</option>
					<option value="suspended">暂停使用</option>
					<option value="wait_for_fix">等待维修</option>
				</select>
				<input name="assetName" size=20 class="shadow right20margin" placeholder="请输入查询资产名字">
				<input name="assetNum" size=20 class="shadow right20margin" placeholder="请输入查询资产编号">
				<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" >
					<span class="ui-button-text">查找</span>
				</button>
			</form>
			<div class="metro66containerdiv" id="ag_asset_sel_metro_style_metro">
		</div>
</div>
<script>
$("#ag_selected_asset_entity_container .entityblock").click(function(){
	var id = $(this).children("span").eq(0).html();
	slide_to_show_detail_content(null, 'assetdetail/'+id, 'asset');
});
$("#ag_metro_asset_form").submit(function(){
	$("#ag_asset_sel_metro_style_metro").html(metroloadingcontent);
	$("#ag_asset_sel_metro_style_metro").show();
	ajax_call_post_force_jason2html('metro/assetlist/'+<c:out value='${assetGroupDetail.customer.customerId}'/>, $("#ag_metro_asset_form").serialize(), false, ag_refreshAssetGroupMetro);
	return false;
});
function edit_asset_group(){
	$("#assetgroupdetailtable .text").hide();
	$("#assetgroupdetailtable .staticbtn").hide();
	$("#assetgroupdetailtable .edit").fadeIn();
	$("#ag_selected_asset_entity_container .entityblock").addClass("entityblockedit");
	$("#ag_selected_asset_entity_container .entityblock").unbind();
	$("#ag_selected_asset_entity_container .entityblock").click(function(){
		setupEntityClick($(this), $("#assetGroupDetail #assetList"), $("#ag_asset_sel_metro_style_metro"));
	});
	buildOptionBasedOnAjaxReturn($("#ag_metro_asset_form #assetType"), 'option/assettypelist');
}
function update_asset_group(id){
	var link="assetgroupupdate/"+id;
	ajax_call_post(link, $("#assetGroupDetail").serialize(), true, after_update_asset_gruop);
}
function after_update_asset_gruop(data){
	if(!validateText(data)){
		entityName = $("#assetGroupDetail #groupName").val();
		var message = success_update_assetgroup.replace('~~0', entityName);
		var lastrow = divs[divs.length-1][0];
		if(lastrow != undefined ){
			var tdlist = lastrow.children('td');
			if(tdlist.length>0){
				tdlist.eq(0).html($("#assetGroupDetail #groupName").val());
				tdlist.eq(1).html($("#assetGroupDetail #description").val());
			}
		}
		refresh_current_content(message);
	}
}
function delete_asset_group(id){
	var link="assetgroupdelete/"+id;
	ajax_call_post(link, $("#assetGroupDetail").serialize(), true, after_delete_asset_group);
}
function after_delete_asset_group(data){
	if(!validateText(data)){
		var entityName = $("#assetGroupDetail #groupName").val();
		var message = success_delete_assetgroup.replace('~~0', entityName);
		var lastrow = divs[divs.length-1][0];
		lastrow.slideUp(500, function(){lastobject.remove();});
		slide_back_to_show_list_content(message);
	}
}
function ag_toggle_asset_group_sel_asset_metro(){
	if($("#ag_asset_set_metro_outter_layout").is(":visible")){
		$("#ag_asset_set_metro_outter_layout").fadeOut();
	}else{
		var link = 'metro/assetlist/'+<c:out value='${assetGroupDetail.customer.customerId}'/>;
		$("#ag_metro_asset_form select,#ag_metro_asset_form input").val('');
		$("#ag_asset_set_metro_outter_layout").css('top', $("#assetgroupdetailtable #addnewasssetbtn").position().top+35);
		$("#ag_asset_set_metro_outter_layout").css('left', $("#assetgroupdetailtable #addnewasssetbtn").position().left-800);
		$("#ag_asset_set_metro_outter_layout").fadeIn();
		ag_refreshMetro(link);
	}
}
function ag_refreshMetro(link){
	$("#ag_asset_sel_metro_style_metro").html(metroloadingcontent);
	$("#ag_asset_sel_metro_style_metro").show();
	ajax_call_get(link, false, ag_refreshAssetGroupMetro);	
}
function ag_refreshAssetGroupMetro(data){
	bindMetroToSelectAndEntity(data, $("#ag_asset_set_metro_outter_layout"), $("#assetGroupDetail #assetList"), $("#ag_selected_asset_entity_container"), 'asset', ag_refreshMetro);
}
</script>