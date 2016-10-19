<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="insidecontent-extend">
	<div class="u1title1">
		<table style="border-spacing:0px;width:900px;"><tr><td>
		<h2>首页</h2></td><td align="right">
		<c:if test="${accessRights[8]}">
			<form id="switchCustomer" method="POST">
				查看视图
				<select id="id" name="id">
					<option value="${currentCustomerID}"></option>
					<option>--全部客户--</option>
				</select>
			</form>
			<script>
				$(document).ready(function(){
					buildOptionBasedOnAjaxReturn($("#switchCustomer #id"), 'option/customerlist');
				});
			</script>
		</c:if>
		</td></tr></table>
	</div>
	<div class="contentbox" id="homepage">
		<div id='hometaskoverview' style="width:618px;height:303px;float:left;text-align:center;" class="bgcolorborder bottom5margin bgcolor">
			<h1>Loading...</h1>
		</div>
		<div class="u1metroicon leftbottom5margin">
			<span>本月已完成工单&nbsp;<br><font id='hometaskdncount'>?</font></span>
		</div>
		<div class="u1metroicon leftbottom5margin">
			<span>本月未指派工单&nbsp;<br><font id='hometaskpacount'>?</font></span>
		</div>
		<div class="u1metroicon leftbottom5margin">
			<span>本月已指派工单&nbsp;<br><font id='hometaskascount'>?</font></span>
		</div>
		<div class="u1metroicon leftbottom5margin">
			<span>本月正在处理中的工单&nbsp;<br><font id='hometaskipcount'>?</font></span>
		</div>
		<div id="pendingTasklist" style="width:618px;height:303px;float:left;" class="bgcolorborder bottom5margin autooverflow">
			<div class="copybtn" style="position:absolute;">
				<button id='refreshpdtaskbtn' type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow"><img src="img/u1/f5.png"></button>
			</div>
			<div class="copybtn" style="position:absolute;left:568px;">
				<button id='homepageaddtaskbtn' type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow"><img src="img/u1/acc.png"></button>
			</div>
			<table id="homepagependingtasklist" class="flat-table flat-table-4 width600">
				<thead>
					<tr><th nowrap>尚未指派工单号</th><th nowrap>类型/优先级</th><th>任务简述</th></tr>
				</thead>
			</table>
		</div>
		<div id="homerootcausepie" style="width:303px;height:303px;float:right;text-align:center;" class="bgcolorborder bgcolor bottom5margin">
			<h1>Loading...</h1>
		</div>
		<c:if test="${accessRights[5] && currentCustomerID==self[2]}">
			<div id="homepagemytask" style="width:773px;height:458px;float:right;" class="bgcolorborder bottom5margin">
				<div class="copybtn" style="position:absolute;">
					<button id='refreshmytaskbtn' type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow"><img src="img/u1/f5.png"></button>
				</div>
				<div style="float:right">
					<ul class="u1list2" id="homepagemytaskul" style="float:right;">
						<li class='selected'><a>指派给我的任务</a></li>
						<li><a>处理中的任务</a></li>
					</ul>
				</div>
				<div id="myassignedtaskdiv" style="width:770px;height:395px;" class="autooverflow">
					<table id="myassignedtasktable" class="flat-table flat-table-4 width750" >
						<thead>
							<tr><th nowrap>工单号</th><th nowrap>类型/优先级</th><th>任务简述</th></tr>
						</thead>
					</table>
				</div>
				<div id="myinprogresstaskdiv"style="width:770px;height:395px;" class="autooverflow hidden">
					<table id="myinprogresstasktable" class="flat-table flat-table-4 width750">
						<thead>
							<tr><th nowrap>工单号</th><th nowrap>类型/优先级</th><th>任务简述</th></tr>
						</thead>
					</table>
				</div>
			</div>
			<a class="u1metroicon bottom5margin" href='myfinishtask'>
				<span>个人本月已完成工单&nbsp;<br><font id='mydonetaskcount'>?</font></span>
			</a>
			<div class="u1metroicon bottom5margin">
				<span >指派给我待处理工单数&nbsp;<br><font id='myassignedtaskcount'>?</font>
				</span>
			</div>
			<div class="u1metroicon bottom5margin">
				<span>我正在处理的工单&nbsp;<br><font id='myinprogresstaskcount'>?</font></span>
			</div>
			<div style="color:#888888">
				注意:<br>
				<ul style="padding-left:20px;">
					<li>页面中的图表数据每2个小时更新</li>
					<li>任务列表数据会自动更新，或者你也可以点击列表左上角的<u>刷新图标按钮</u>进行手动更新，也可点击列表的右上角的<u>添加工单按钮</u>添加新工单</li>
				</ul>
			</div>
			<script>
			$(document).ready(function(){
				tabsbindurl($("#homepagemytaskul li"),['',''], [$("#myassignedtaskdiv"), $("#myinprogresstaskdiv")],"selected");
				refreshmytasklist();
			});
			</script>
		</c:if>
	</div>
</div>
<script>
$(document).ready(function(){
	init1stdivs('', 'home');
	refreshTaskOverview();
	refreshpendingtasklist();
	$('#homepageaddtaskbtn').click(function(){
		slide_to_show_detail_content($(this), 'addtask', 'newtask');
	});
	$("#refreshpdtaskbtn").click(function(){
		refreshpendingtasklist('列表已刷新');
	});
	$("#refreshmytaskbtn").click(function(){
		refreshmytasklist('列表已刷新');
	});
	$("#switchCustomer #id").change(function(){
		ajax_call_post('homeswitch', $(this).serialize(), true, function(data){
			if(!validateText(data)){
				$('#hometaskoverview').html('<h1>Loading...</h1>');
				$('#homerootcausepie').html('<h1>Loading...</h1>');
				$("#hometaskpacount").html('0');
				$("#hometaskascount").html('0');
				$("#hometaskipcount").html('0');
				$("#hometaskdncount").html('0');
				refreshTaskOverview();
				refreshpendingtasklist();
			}
		});
		return false;
	});
});
function refreshTaskOverview(){
	ajax_call_get('home/taskoverview', true, function(data){
		if(!validateText(data)){
			var jsondata = JSON.parse(data);
			if(jsondata[0].length==0){
				$('#hometaskoverview').html('<h1>无相关数据</h1>');
			}else{
				drawtaskcount(jsondata[0], 'hometaskoverview');
			}
			if(jsondata[1].length==0){
				$('#homerootcausepie').html('<h1>无相关数据</h1>');
			}else{
				drawtaskpie(jsondata[1],"homerootcausepie");
			}
			$("#hometaskpacount").html(jsondata[2][0]);
			$("#hometaskascount").html(jsondata[2][1]);
			$("#hometaskipcount").html(jsondata[2][2]);
			$("#hometaskdncount").html(jsondata[2][3]);
		}
	});
}
function refresh2taskitem(){
	refreshpendingtasklist();
	refreshmytasklist();
}
function refreshpendingtasklist(message){
	ajax_call_get('home/listpendingtask', true, function(data){
		if(!validateText(data)){
			homepagerefreshtasklist(JSON.parse(data), "homepagependingtasklist");
			if(message!=undefined)show_message_5s(message);
		}
	});
}
function refreshmytasklist(message){
	if($("#mydonetaskcount").length>0){
		ajax_call_get('home/mytask', true, function(data){
			if(!validateText(data)){
				var jsondata = JSON.parse(data);
				$("#mydonetaskcount").html(jsondata[2]);
				homepagerefreshtasklist(jsondata[0], "myassignedtasktable", "myassignedtaskcount");
				homepagerefreshtasklist(jsondata[1], "myinprogresstasktable", "myinprogresstaskcount");
				if(message!=undefined)show_message_5s(message);
			}
		});
	}
}
function homepagerefreshtasklist(jsondata, tableid, counterid){
	$("#"+tableid+" tbody tr").remove();
	$("#"+tableid+" tfoot tr").remove();
	if(jsondata.length>0){
		var datacontent = '';
		var macount = 0;
		for(var i=0;i<jsondata.length;i++){
			macount++;
			datacontent += '<tr><td nowrap>'+jsondata[i].taskNum+'<span class=hidden>'+jsondata[i].taskPid+'</span></td><td nowrap>'+jsondata[i].taskType.code+'/'+task_piority[jsondata[i].piority]+'</td><td>'+jsondata[i].taskName+'</td></tr>';
		}
		if(counterid!=undefined){
			$("#"+counterid).html(macount);
		}
		//TODO update ptcount;
		$("#"+tableid).prepend(datacontent);
		$("#"+tableid+" tbody tr").click(function(){
			var id = $(this).children('td').eq(0).children('span').html();
			slide_to_show_detail_content($(this), 'taskdetail/'+id, 'task');
		});
	}else{
		if(counterid!=undefined){
			$("#"+counterid).html(0);
		}
		$("#"+tableid).prepend('<tfoot><tr><td colspan=3>无相关工单</td></tr></tfoot>');
	}
}
</script>