<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="insidecontent-extend">
	<div class="u1title1"><h2>新建工单</h2></div>
	<div class="contentbox" id="asstattributes">
		<spring:form commandName="newTask">
			<table class="detailtable" id="newassettable">
				<tbody>
					<tr>
						<th colspan=6>必填信息</th>
					</tr>
					<tr>
						<td class="non-highlight">工单标题</td>
						<td colspan=5>
							<spring:input path="taskName" cssClass="shadow" autocomplete="off" placeholder="请输入工单标题" style="width:97%"/>
						</td>
					</tr>
					<tr>
						<td class="non-highlight">工单类型</td>
						<td>
							<select id="taskType" name="taskType" class="shadow">
								<option value="">-请选择-</option>
								<c:if test="${newTask.taskType != null }">
									<option value="${newTask.taskType.taskTypePid}" selected=selected><c:out value="${newTask.taskType.code}"/></option>
								</c:if>
							</select>
						</td>
						<td class="non-highlight">优先级别</td>
						<td>
							<spring:radiobutton cssClass="shadow" path="piority" value="0" label="低"/>
							<spring:radiobutton cssClass="shadow" path="piority" value="1" label="中"/>
							<spring:radiobutton cssClass="shadow" path="piority" value="2" label="高"/>
						</td>
						<td class="non-highlight">目标耗时</td>
						<td><span id="taskspend" class="bluehighlight">
							<c:if test="${newTask.taskType != null}">
								<c:if test="${newTask.piority=='0'}"><c:out value="${newTask.taskType.lowDefaultTime}"></c:out>小时</c:if>
								<c:if test="${newTask.piority=='1'}"><c:out value="${newTask.taskType.mediumDefaultTime}"></c:out>小时</c:if>
								<c:if test="${newTask.piority=='2'}"><c:out value="${newTask.taskType.highDefaultTime}"></c:out>小时</c:if>	
							</c:if>
						</span></td>
					</tr>
					<c:if test="${accessRights[8]}">
						<tr>
							<td class="non-highlight">所属客户</td>
							<td colspan=5>
								<select id="customer" name="customer" class="shadow">
									<c:choose>
										<c:when test="${newTask.customer != null }">
											<option value="${newTask.customer.customerId}" selected=selected><c:out value="${newTask.customer.customerName}"/></option>
										</c:when>
										<c:otherwise>
											<option value="${self[2]}" selected=selected><c:out value="${self[3]}"/></option>
										</c:otherwise>
									</c:choose>
								</select>
								<script>
									$(document).ready(function(){
										buildOptionBasedOnAjaxReturn($("#newTask #customer"), 'option/customerlist');
									});
								</script>
							</td>
						</tr>
					</c:if>
					<tr>
						<th colspan=6>选填信息</th>
					</tr>
					<tr>
						<td class="non-highlight">所属部门</td>
						<td>
							<c:if test="${!accessRights[8]}">
								<input type="hidden" id="customer" value="${self[2]}"/>
							</c:if>
							<select id="department" name="department" class="shadow">
								<option value="">-请选择-</option>
								<c:if test="${newTask.department != null }">
									<option value="${newTask.department.departmentPid}" selected=selected><c:out value="${newTask.department.departmentName}"/></option>
								</c:if>
							</select>
						</td>
						<td class="non-highlight">联系人</td>
						<td>
							<spring:input path="contact" size="20" cssClass="shadow" autocomplete="off" placeholder="请输入联系人"/>
						</td>
						<td class="non-highlight">指派给</td>
						<td>
							<select id="assignedTo" name="assignedTo" class="shadow">
								<option value="">暂不指派</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="non-highlight">相关资产</td>
						<td colspan=5 class="bgcolorborder shadow">
							<select name="assetList" id="assetList" class="hidden" multiple="multiple">
								<c:forEach items="${newTask.assetList}" var="asset">
									<c:if test="${asset.enabled}">
										<option value="${asset.assetPid}" selected="selected"></option>
									</c:if>
								</c:forEach>
							</select>
							<button id="addnewasssetbtn" type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" style="float:right;" onclick="javascript:addtask_toggle_asset_group_sel_asset_metro();">
								<span class="ui-button-text"><b>+</b>添加新资产</span>
							</button>
							<button id="addnewasssetgroupbtn" type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" style="float:right;" onclick="javascript:addtask_toggle_assetgroup_group_sel_asset_metro('metro/assetgrouplist');">
								<span class="ui-button-text"><b>+</b>按分组添加新资产</span>
							</button>
							<br>
							<div class="entityblockContainer" id="selected_asset_entity_container">
								<c:forEach items="${newTask.assetList}" var="asset">
									<div class="entityblock shadow">
										<b><c:out value="${asset.assetName}"/></b><br>
										<font class=s><c:out value="${asset.assetNum}"/></font><br>
										<c:out value="${asset.assetType.code}"/>/<spr:message code="${asset.status}"/>
										<span class="hidden"><c:out value="${asset.assetPid}"/></span>
									</div>
								</c:forEach>
							</div>
						</td>
					</tr>
					<tr>
						<td class="non-highlight">工单详细信息</td>
						<td colspan=5>
							<textarea name="detail" id="detail" rows="5" class="shadow bgcolorborder" placeholder="请在这里输入工单详细信息" style="width:99%"><c:out value="${newTask.detail}"></c:out></textarea>
						</td>
					</tr>
					<tr>
						<td colspan=6>
							<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow" style="float:right;">
								<span class="ui-button-text">提交</span>
							</button>
						</td>
					</tr>
			</tbody>
		</table>
		<div style="color:#888888">
			注意:<br>
			<ul style="padding-left:20px;">
				<li>创建工单时如果选择暂不指派，工单提交后状态为<u>已提交</u></li>
				<li>创建工单时如果指派给他人，工单提交后状态为<u>已指派</u></li>
				<li>创建工单时如果指派给自己，工单提交后状态立即转为<u>处理中</u></li>
				<li>添加相关资产时，可点击<u>+按分组添加</u>或者<u>+添加新资产</u>打开/关闭弹出窗口</li>
			</ul>
		</div>
		</spring:form>
	</div>
	<div class="uprightarrowdiv shadow hidden" id="addtask_asset_set_metro_layout">
		<form id="addtask_metro_asset_form" action="metro/assetlist" method="post">
			<select name="assetType" id="assetType" class="shadow right10margin">
				<option value=''>全部资产类型</option>
			</select>
			<input name="assetName" size=20 class="shadow right10margin" placeholder="请输入查询资产名字">
			<input name="assetNum" size=20 class="shadow right10margin" placeholder="请输入查询资产编号">
			<select name="status" class="shadow right10margin">
					<option value="">全部状态</option>
					<option value="breakdown">故障中</option>
					<option value="expired">已过期</option>
					<option value="fixing">维修中</option>
					<option value="idle">闲置</option>
					<option value="in_use">使用中</option>
					<option value="orderring">购买中</option>
					<option value="suspended">暂停使用</option>
					<option value="wait_for_fix">等待维修</option>
			</select>&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow">
				<span class="ui-button-text">查找</span>
			</button>
		</form>
		<div class="metro66containerdiv" id="addtask_asset_sel_metro_container"></div>
	</div>
	<div class="uprightarrowdiv2 shadow hidden" id="addtask_assetgroup_set_metro_layout">
		<form id="addtask_metro_assetgroup_form" action="metro/assetgrouplist" method="post">
			<input name="groupName" size=20 class="shadow" placeholder="请输入查询资产分组名字">&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow">
				<span class="ui-button-text">查找</span>
			</button>
		</form>
		<div class="metro66containerdiv" id="addtask_assetgroup_sel_metro_container"></div>
	</div>
</div>
<c:if test="${accessRights[6]}">
	<script>
	$(document).ready(function(){
		buildOptionBasedOnAjaxReturn($("#newTask #assignedTo"),'option/assigntolist/'+$("#newTask #customer").val());
	});
	</script>
</c:if>
<script>
$(document).ready(function(){
	init1stdivs('addtask', 'newtask');
	$("#selected_asset_entity_container .entityblock").click(function(){
		setupEntityClick($(this), $("#taskDetail #assetList"), $("#addtask_asset_sel_metro_container"));
	});
	build_select_pulldown('strlist/taskname', $("#newTask #taskName"));
	build_select_pulldown('strlist/contact', $("#newTask #contact"));
	buildOptionBasedOnAjaxReturn($("#newTask #department"),'option/departmentlist/'+$("#newTask #customer").val());
	buildOptionBasedOnAjaxReturn($("#newTask #taskType"),'option/tasktypelist');
	buildOptionBasedOnAjaxReturn($("#addtask_metro_asset_form #assetType"), 'option/assettypelist');
	$("#newTask #customer").change(function(){
		$("#newTask #department").find("option[value!='']").remove();
		$("#newTask #assignedTo").find("option[value!='']").remove();
		buildOptionBasedOnAjaxReturn($("#newTask #department"),'option/departmentlist/'+$("#newTask #customer").val());
		buildOptionBasedOnAjaxReturn($("#newTask #assignedTo"),'option/assigntolist/'+$("#newTask #customer").val());
		$("#addtask_asset_set_metro_layout").fadeOut();
		$("#addtask_assetgroup_set_metro_layout").fadeOut();
		$("#newTask #assetList").html('');
		$("#selected_asset_entity_container").html('');
		
	});
	$("#newTask #taskType").change(function(){
		check_task_spend();
	});
	$('#newTask input[name="piority"]').change(function(){
		check_task_spend();
	});
	$("#newTask").submit(function(){
		ajax_call_post('newtasksubmit', $(this).serialize(), true, function(data){
			if(!validateText(data)){
				show_loading_message('工单创建成功，正在为你转向工单详情页面...');
				slide_to_show_detail_content(null, 'taskdetail/'+data, 'task', '工单已成功创建', clear_input_after_submitnewtask);
			}
		});
		return false;
	});
});
function clear_input_after_submitnewtask(){
	$("#newTask select, #newTask input[type='text'], #newTask textarea").val('');
	$("#newTask #assetList").children('option').remove();
	$("#newTask assetlist").html('');
	$("#taskspend").html('');
	$("#newTask input:radio[name='piority']").attr("checked",false);
	$("#selected_asset_entity_container").html('');
	//reset pull down
	build_select_pulldown('strlist/taskname', $("#newTask #taskName"));
	build_select_pulldown('strlist/contact', $("#newTask #contact"));
}
function check_task_spend(){
	var level = $('#newTask input[name="piority"]').filter(':checked').val();
	if($("#newTask #taskType").val()!='' && level != undefined ){
		ajax_call_get('addtask/gettaskspend/'+$('#newTask #taskType').val()+'/'+level, false, function(data){
			$("#taskspend").html(data+'小时');
		});
	}
}
function addtask_toggle_asset_group_sel_asset_metro(){
	if($("#addtask_asset_set_metro_layout").is(":visible")){
		$("#addtask_asset_set_metro_layout").fadeOut();
	}else{
		var link = 'metro/assetlist/'+$('#newTask #customer').val();
		$("#addtask_metro_asset_form select,#addtask_metro_asset_form input").val('');
		$("#addtask_assetgroup_set_metro_layout").fadeOut();
		$("#addtask_asset_set_metro_layout").css('top', $("#newassettable #addnewasssetbtn").position().top+35);
		$("#addtask_asset_set_metro_layout").css('left', $("#newassettable #addnewasssetbtn").position().left-800);
		$("#addtask_asset_set_metro_layout").fadeIn();
		addtask_refreshMetro(link);
	}
}
function addtask_refreshMetro(link){
	$("#addtask_asset_sel_metro_container").html(metroloadingcontent);
	$("#addtask_asset_sel_metro_container").show();
	ajax_call_get(link, false, addtask_refreshAssetGroupMetro);	
}
$("#addtask_metro_asset_form").submit(function(){
	$("#addtask_asset_sel_metro_container").html(metroloadingcontent);
	$("#addtask_asset_sel_metro_container").show();
	ajax_call_post_force_jason2html('metro/assetlist/'+$('#newTask #customer').val(), $("#addtask_metro_asset_form").serialize(), false, addtask_refreshAssetGroupMetro);
	return false;
});
function addtask_refreshAssetGroupMetro(data){
	bindMetroToSelectAndEntity(data, $("#addtask_asset_set_metro_layout"), $("#assetList"), $("#selected_asset_entity_container"), 'asset', addtask_refreshMetro);
}
//for group
function addtask_toggle_assetgroup_group_sel_asset_metro(){
	if($("#addtask_assetgroup_set_metro_layout").is(":visible")){
		$("#addtask_assetgroup_set_metro_layout").fadeOut();
	}else{
		link = 'metro/assetgrouplist/'+$('#newTask #customer').val();
		$("#addtask_metro_assetgroup_form select,#addtask_metro_assetgroup_form input").val('');
		$("#addtask_asset_set_metro_layout").fadeOut();
		$("#addtask_metro_asset_form #assetType").val('');
		$("#addtask_assetgroup_set_metro_layout").css('top', $("#newassettable #addnewasssetgroupbtn").position().top+35);
		$("#addtask_assetgroup_set_metro_layout").css('left', $("#newassettable #addnewasssetgroupbtn").position().left-660);
		$("#addtask_assetgroup_set_metro_layout").fadeIn();
		addtask_refreshGroupMetro(link);
	}
}
function addtask_refreshGroupMetro(link){
	$("#addtask_assetgroup_sel_metro_container").html(metroloadingcontent);
	$("#addtask_assetgroup_sel_metro_container").show();
	ajax_call_get(link, false, addtask_refreshAssetGroupGroupMetro);	
}
$("#addtask_metro_assetgroup_form").submit(function(){
	$("#addtask_assetgroup_sel_metro_container").html(metroloadingcontent);
	$("#addtask_assetgroup_sel_metro_container").show();
	ajax_call_post_force_jason2html('metro/assetgrouplist/'+$('#newTask #customer').val(), $("#addtask_metro_assetgroup_form").serialize(), false, addtask_refreshAssetGroupGroupMetro);
	return false;
});
function addtask_refreshAssetGroupGroupMetro(data){
	bindMetroToSelectAndEntity(data, $("#addtask_assetgroup_set_metro_layout"), $("#assetList"), $("#selected_asset_entity_container"), 'assetgroup', addtask_refreshGroupMetro);
}
</script>