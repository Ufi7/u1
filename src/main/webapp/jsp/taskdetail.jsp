<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<div class="insidecontent-extend">
		<div class="u1title1"><h2>查看工单详情</h2></div>
		<div class="copybtn noprint">
			<form id="copytask" action="addtask" method="POST">
				<input name="id" type="hidden" value="<c:out value='${taskDetail.taskPid}'/>"/>
				<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow"><img src="img/u1/copytask.png"></button>
			</form>
		</div>
		<c:if test="${accessRights[6]}">
			<div class="copybtn noprint">
				<form id="deletetaskform" method="POST">
					<input name="id" type="hidden" value="<c:out value='${taskDetail.taskPid}'/>"/>
					<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow"><img  src="img/u1/deletetask.png"></button>
				</form>
			</div>
		</c:if>
		<ul class="u1list2" id="taskdetailtabsul">
			<li class="selected"><a>工单详细</a></li>
			<li><a>工单日志</a></li>
			<li><a>相关附件</a></li>
		</ul>
		<div class="contentbox" id="taskdetailinformationdiv">
			<spring:form commandName="taskDetail">
				<table class="detailtable">
					<tr>
						<td class="non-highlight">工单号</td>
						<td class="text" nowrap><span><c:out value="${taskDetail.taskNum}" /></span></td>
						<td class="non-highlight" nowrap>状态</td>
						<td class="text" nowrap><span><spr:message code="${taskDetail.status}" /></span></td>
					<c:choose>
						<c:when test="${taskDetail.status=='done'}">
							<td class="non-highlight">任务评分</td>
							<td class="text"><span class="bluehighlight"><c:out value="${taskDetail.score}" /></span></td>
						</c:when>
						<c:otherwise>
							<td class="non-highlight">指派给</td>
							<td class="text"><span><c:out value="${taskDetail.assignedTo.givenName}" /></span></td>
						</c:otherwise>
					</c:choose>
						
					</tr>
					<tr>
						<td class="non-highlight">工单标题</td>
						<td colspan=5 class="text">
							<span><c:out value="${taskDetail.taskName}" /></span>
						</td>
					</tr>
					<tr>
						<td class="non-highlight">所属客户</td>
						<td class="text">
							<span><c:out value="${taskDetail.customer.customerName}" /></span>
						</td>
						<td class="non-highlight">所属部门</td>
						<td class="text"><span><c:out value="${taskDetail.department.departmentName}" /></span></td>
						<td class="non-highlight">联系人</td>
						<td class="text"><span><c:out value="${taskDetail.contact}" /></span></td>
					</tr>
					<tr>
						<td class="non-highlight">工单类型</td>
						<td class="text"><span>
							<c:out value="${taskDetail.taskType.code}" />
						</span></td>
						<td class="non-highlight">优先级</td>
						<td class="text"><span>
							<c:if test="${taskDetail.piority=='0'}">低</c:if><c:if test="${taskDetail.piority=='1'}">中</c:if><c:if test="${taskDetail.piority=='2'}">高</c:if>
						</span></td>
						<td class="non-highlight">创建人</td>
						<td class="text"><span><c:out value="${taskDetail.createdBy.givenName}" /></span></td>
					</tr>
					<tr>
						<td class="non-highlight">提交时间</td>
						<td class="text" nowrap><span><fmt:formatDate value="${taskDetail.startDatetime}" type="both" pattern="yyyy-MM-dd HH:mm"/></span></td>
						<td class="non-highlight">实际完成时间</td>
						<td class="text" nowrap><span><fmt:formatDate value="${taskDetail.endDatetime}" type="both" pattern="yyyy-MM-dd HH:mm"/></span></td>
						<td class="non-highlight">目标完成时间</td>
						<td class="text" nowrap><span><fmt:formatDate value="${taskDetail.dueDatetime}" type="both" pattern="yyyy-MM-dd HH:mm"/></span></td>
					</tr>
					<tr>
						<td nowrap class="non-highlight">关联资产</td>
						<td colspan=5 class="text">
							<div class="entityblockContainer" id="taskdetail_asset_entity_container">
								<c:forEach items="${taskDetail.assetList}" var="asset">
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
						</td>
					</tr>
					<tr>
						<td nowrap class="non-highlight">工单详细信息</td>
						<td colspan=5 class="text">
							<p><c:out value="${taskDetail.detail}"></c:out></p>
						</td>
					</tr>
					<tr>
						<td nowrap class="non-highlight">消耗配件</td>
						<td colspan=5 class="text">
							<div class="entityblockContainer" id="taskdetail_component_entity_container">
								loading...
							</div>
						</td>
					</tr>
					<c:if test="${taskDetail.status=='done'}">
						<tr>
							<td class="non-highlight">工单原因归类</td>
							<td colspan=5 class="text">
								<span><c:out value="${taskDetail.rootcause}" /></span>
							</td>
						</tr>
						<tr>
							<td nowrap class="non-highlight">工单解决方案</td>
							<td colspan=5 class="text">
								<p><c:out value="${taskDetail.solution}"></c:out></p>
							</td>
						</tr>
					</c:if>
				</table>
			</spring:form>
		</div>
		<c:if test="${accessRights[5]}">
			<div style="float:right" class="noprint">
				<div style="width:800px;">
					<ul class="u1list2" id="taskactionul" style="float:right;">
						<li><a>追加日志备注</a><span class="hidden">n</span></li>
						<c:if test="${accessRights[6] && taskDetail.status=='created'}">
							<li><a>&nbsp;&nbsp;拒&nbsp;&nbsp;绝&nbsp;&nbsp;</a><span class="hidden">r</span></li>
						</c:if>
						<c:if test="${(taskDetail.status=='assigned' || taskDetail.status=='in_progress' ) && taskDetail.assignedTo.userId == self[0]}">
							<li><a>请求重新指派</a><span class="hidden">p</span></li>
						</c:if>
						<c:if test="${accessRights[6] && taskDetail.status!='done' && taskDetail.status!='rejected' && taskDetail.status!='in_progress'}">
							<li><a>&nbsp;&nbsp;指&nbsp;&nbsp;派&nbsp;&nbsp;</a><span class="hidden">a</span></li>
							<script>
								$(document).ready(function(){
									buildOptionBasedOnAjaxReturn($("#taskactionform #assignedTo"),'option/assigntolist/'+<c:out value="${taskDetail.customer.customerId}"/>);
								});
							</script>
						</c:if>
						<c:if test="${taskDetail.status=='created' || taskDetail.status=='pending-reassigned' || ( taskDetail.assignedTo.userId == self[0] && taskDetail.status=='assigned')}">
							<li><a>开始处理</a><span class="hidden">i</span></li>
						</c:if>
						<c:if test="${taskDetail.assignedTo.userId == self[0] && taskDetail.status=='in_progress'}">
							<li><a>处理完成</a><span class="hidden">d</span></li>
						</c:if>
						<c:if test="${accessRights[6] && taskDetail.status=='done' && revaluable}">
							<li><a>重新评估</a><span class="hidden">v</span></li>
						</c:if>
					</ul>
				</div>
				<div class="commandline" id="taskactionremarkdiv">
					<form method="POST" id="taskactionform">
						<table style="width:680px;">
							<tr><td>
								<input type="hidden" id="actionCode" name="actionCode" id="actionCode"/>
								<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow hidden">
									<span class="ui-button-text">提交</span>
								</button>
								<input id="score" name="score" size=5 class="shadow hidden" placeholder="分数">
								<input id="remark" name="remark" class="shadow hidden" placeholder="请输入备注，该项仅作为日志参考备注" style="width:41%">
								<input id="rootcause" name="rootcause" class="shadow hidden" autocomplete="off" placeholder="请输入工单原因归类" style="width:41%">
								<select name="assignedTo" id="assignedTo" class="shadow hidden">
								</select>
							</td></tr>
							<tr>
								<td>
									<textarea id="solution" name="solution"class="shadow hidden" placeholder="请在这里输入具体解决方案" Style="width:96%;height:100px;"></textarea>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</c:if>
		<div class="noprint" style="float:right;color:#888888">
			注意:<br>
			<ul style="padding-left:20px;">
				<li><u>追加日志备注</u>可以为该工单追加日志记录，不改变该工单本身</li>
				<li><u>拒绝</u>工单需要工单指派角色，且必须在待指派状态的工单才能被拒绝</li>
				<li><u>请求重新指派</u>必须为被指派用户才能进强的操作，将工单打回缓冲池，待重新分配</li>
				<li><u>指派</u>工单要求用户具有工单指派角色</li>
				<li><u>开始处理</u>为被指派角色或者工单处于未被指派是才能进行的操作，将工单转为处理中状态</li>
				<li><u>处理完陈</u>是开始处理后的下一个动作，完成后要求用户填写工单原因分类和解决方案</li>
				<li><u>重新评估</u>同样需要工单指派角色，可以在工单关闭后一周时间内对工单的评分进行修改</li>
				<li>以上所有操作都会被记录到工单日志中，可以在工单日志进行查询</li>
			</ul>
		</div>
</div>

<script>
$(document).ready(function(){
	//var componetenity = '<table class=timelabel><tr><td class=bgbackgound>XXXX</td><td class=bgcolorborder>YYY</td></td></tr></table>';
	var componetenity ='<div class="entityblock shadow"><b>【{component.field1}/{component.field2}/{component.field3}/{component.field4}/{component.field5}/{component.field6}/{component.field7}/{component.field8}】</b>&nbsp;×&nbsp;<b>{usage}</b><span class=hidden>{componentStoreId}</span></div>';
	ajax_call_get('taskconsumedcomponent/'+<c:out value="${taskDetail.taskPid}"/>, false, function(data){
		if(!validateText(data)){
			var jsondata = JSON.parse(data);
			var components = '';
			for(var i =0;i<jsondata.length;i++){
				var c = jsondata[i][0];
				c.usage = jsondata[i][1];
				components += componetenity.format(c);
			}
			$("#taskdetail_component_entity_container").html('').append(components);
			$("#taskdetail_component_entity_container .entityblock").click(function(){
				var id = $(this).children("span").html();
				slide_to_show_detail_content(null, 'componentstoredetail/'+id, 'componentstore');
			});
		}
	});
	$('#copytask').submit(function(){
		slide_to_show_detail_content(null, 'addtask','newtask', null, null, true, $(this).serialize());
		return false;
	});
	$('#deletetaskform').submit(function(){
		ajax_call_post('deletetask', $(this).serialize(), true, function(data){
			if(!validateText(data)){
				divs[divs.length-1][0].remove();
				slide_back_to_show_list_content('工单已删除');
			}
		});
		return false;
	});
	$("#taskdetail_asset_entity_container .entityblock").click(function(){
		var id = $(this).children("span").eq(0).html();
		slide_to_show_detail_content(null, 'assetdetail/'+id, 'asset');
	});
	tabsbindurl($("#taskdetailtabsul li"),['','tasklog/'+<c:out value="${taskDetail.taskPid}"/>,'taskattachment/'+<c:out value="${taskDetail.taskPid}"/>], [$("#taskdetailinformationdiv")],"selected");
	build_select_pulldown('strlist/taskrootcause', $("#taskactionform #rootcause"));
	$("#taskactionul li").click(function(){
		$(this).siblings().removeClass("selected");
		$(this).addClass("selected");
		$("#taskactionform #actionCode").val($(this).children("span").html());
		$("#taskactionform #remark, #taskactionform button").fadeIn();
		if($("#taskactionform #actionCode").val()=='a'){
			$("#taskactionform select").fadeIn();
		}else{
			$("#taskactionform select").hide(0);
		}
		if($("#taskactionform #actionCode").val()=='v'){
			$("#taskactionform #score").fadeIn();
		}else{
			$("#taskactionform #score").hide(0);
		}
		if($("#taskactionform #actionCode").val()=='d'){
			
			$("#taskactionform #rootcause").fadeIn();
			$("#taskactionform #solution").fadeIn();
		}else{
			$("#taskactionform #solution").hide();
			$("#taskactionform #rootcause").hide();
		}
	});
	<c:if test="${taskDetail.assignedTo.userId == self[0] && taskDetail.status=='in_progress'}">
		var $componentLi = $('<li><a>消耗配件登记</a></li>');
		$("#taskactionul").append($componentLi);
		$componentLi.click(function(){
			slide_to_show_detail_content(null, 'taskselectcomponent/'+<c:out value="${taskDetail.taskPid}"/>, 'componentselect');
		});
	</c:if>
	$("#taskactionform").submit(function(){
		ajax_call_post("taskupdate/"+<c:out value="${taskDetail.taskPid}"/>, $(this).serialize(), true, function(data){
			if(!validateText(data)){
				if($("#taskactionform #actionCode").val()!='n' && $("#taskactionform #actionCode").val()!='v'){
					updatePreviousListRow(3, task_actioin_2_status[$("#taskactionform #actionCode").val()]);
				}
				if($("#taskactionform #actionCode").val()=='a'){
					updatePreviousListRow(6, $('#taskactionform #assignedTo option:selected').html());
				}else if($("#taskactionform #actionCode").val()=='i'){
					updatePreviousListRow(6, '我');
				}else if($("#taskactionform #actionCode").val()=='p'){
					updatePreviousListRow(6, '');
				}
				show_loading_message('工单更新成功，正在为你更新工单详情...');
				refresh_current_content('工单已成功更新');
			}
		});
		return false;
	});
});
</script>