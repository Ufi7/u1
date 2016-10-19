<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="insidecontent-extend">
	<div class="u1title1"><h2>库存配件详情</h2></div>
	<div id="componentstoredetailtapdiv">
		<ul class="u1list2" id="componentstoredetailtaps" style="float:left;width:800px;">
		  <li class="selected"><a>配件总览</a></li>
		  <li><a>配件日志</a></li>
		</ul>
	</div>
	<div class="contentbox" id='componentstoreoverview'>
		<div style="width:618px;float:left;line-height:">
		<table class="detailtable" style="line-height:42px;">
			<tr>
				<td class="non-highlight bigtext" width="8%">类型</td>
				<td class="text"><c:out value="${componentstoreDetail.component.field1}" /></td>
				<td width="2%">&nbsp;</td>
				<td class="non-highlight" width="8%">厂商</td>
				<td class="text"><c:out value="${componentstoreDetail.component.field2}" /></td>
				<td width="2%">&nbsp;</td>
				<td class="non-highlight" width="8%">型号</td>
				<td class="text"><c:out value="${componentstoreDetail.component.field3}" /></td>
			</tr>
			<tr>
				<td class="non-highlight">容量/频率</td>
				<td class="text"><c:out value="${componentstoreDetail.component.field4}" /></td>
				<td>&nbsp;</td>
				<td class="non-highlight">接口</td>
				<td class="text"><c:out value="${componentstoreDetail.component.field5}" /></td>
				<td>&nbsp;</td>
				<td class="non-highlight">功耗</td>
				<td class="text"><c:out value="${componentstoreDetail.component.field6}" /></td>
			</tr>
			<tr>
				<td class="non-highlight">出厂年份</td>
				<td class="text"><c:out value="${componentstoreDetail.component.field7}" /></td>
				<td>&nbsp;</td>
				<td class="non-highlight">备注</td>
				<td class="text"><c:out value="${componentstoreDetail.component.field8}" /></td>
				<td colspan=3></td>
			</tr>
		</table></div>
		<div class="u1metroicon leftbottom5margin">
			<span>配件库存总数&nbsp;<br><br><font id='currentstore'><c:out value="${componentstoreDetail.count}" /></font></span>
		</div>
		<div class="u1metroicon leftbottom5margin">
			<span>最新批次进货单价&nbsp;<br><br><font class='f4em' id='latestprice'>￥<br>N/A</font></span>
		</div>
		<div id='componentstorepriceview' style="width:458px;height:300px;float:left;text-align:center;" class="bgcolorborder bottom5margin bgcolor">
			<h1>Loading...</h1>
		</div>
		<div id='componentstoreusageview' style="width:458px;height:300px;float:right;text-align:center;" class="bgcolorborder bottom5margin bgcolor">
			<h1>Loading...</h1>
		</div>
	</div>
	<div style="float:right" class="noprint">
		<div style="width:800px;">
			<ul class="u1list2" id="componentstoreaul" style="float:right;">
				<li><a>库存变更登记</a></li>
			</ul>
		</div>
		<div class="commandline hidden" id="componentrecordfromdiv">
			<form method="POST" id="componentrecordform">
				<table style="width:680px;">
					<tr><td>
						<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow">
							<span class="ui-button-text">提交</span>
						</button>
						<input id="remark" name="remark" class="shadow" placeholder="请输入备注，该项仅作为日志参考备注" style="width:41%">
						<input id="price" name="price" size=5 class="shadow" placeholder="单价">
						<input id="quantity" name="quantity" size=5 class="shadow" placeholder="数量">
						<select id="reason" name="reason" class="shadow">
							<option value=1>进货</option>
							<option value=2>回收</option>
							<option value=4>调出</option>
							<option value=5>调入</option>
							<option value=6>修正+</option>
							<option value=7>修正-</option>
						</select>
					</td></tr>
				</table>
			</form>
		</div>
	</div>
	<script>
	$(document).ready(function(){
		$("#componentstoreaul li").click(function(){
			if(!$(this).hasClass("selected")){
				$(this).siblings().removeClass("selected");
				$(this).addClass("selected");
				$("#componentrecordfromdiv").slideDown();
			}else{
				$(this).removeClass("selected");
				$("#componentrecordfromdiv").slideUp();
			}
		});
		$("#componentrecordform").submit(function(){
			ajax_call_post('updatecomponentstore/<c:out value="${componentstoreDetail.componentStoreId}"/>',$(this).serialize(), true, function(data){
				if(!validateText(data)){
					show_loading_message('配件更新成功，正在为你更新配件详情...');
					refresh_current_content('配件已成功更新');
				}
			})
			return false;
		});
		$("#componentrecordform #reason").change(function(){
			if($(this).val()==1){
				$("#componentrecordform #price").val('');
				$("#componentrecordform #price").fadeIn();
			}else{
				$("#componentrecordform #price").fadeOut();
			}
		});
		tabsbindurl($("#componentstoredetailtaps li"),['','componentstorelog/<c:out value="${componentstoreDetail.componentStoreId}"/>'], [$("#componentstoreoverview")],"selected");
		setTimeout(function(){ajax_call_get('componentstorestatistic/'+<c:out value="${componentstoreDetail.componentStoreId}"/>, false, function(data){
			if(!validateText(data)){
				var jsondata = JSON.parse(data);
				draw(jsondata[0], 'componentstorepriceview', true, '近12个月配件单价', '单价', '月份');
				draw(jsondata[1], 'componentstoreusageview', false, '近12个月配件用量', '用量', '月份');
			}
		});}, 80);
		ajax_call_get('componentstorelatestprice/'+<c:out value="${componentstoreDetail.componentStoreId}"/>, false, function(data){
			if(!validateText(data)){
				$("#latestprice").html('￥<br>'+(data==0?'N/A':Math.ceil(data)));
			}
		});
	});
	</script>
</div>