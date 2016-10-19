<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="insidecontent-extend">
	<spring:form commandName='componentSelect'>
		<div class="u1title1"><h2>选择配件</h2></div>
		<div>
		<table class="flat-table flat-table-4 widetable fixedtable breakrow" id="componentselecttable">
			<THEAD>
			<th class=u1headline><spring:input path="field1" size="12" placeholder="类型" cssClass="shadow right10margin fieldclass" autocomplete="off"></spring:input></th>
			<th class=u1headline><spring:input path="field2" size="12" placeholder="厂商" cssClass="shadow right10margin fieldclass" autocomplete="off"></spring:input></th>
			<th class=u1headline><spring:input path="field3" size="12" placeholder="型号" cssClass="shadow right10margin fieldclass" autocomplete="off"></spring:input></th>
			<th class=u1headline><spring:input path="field4" size="12" placeholder="容量/频率" cssClass="shadow right10margin fieldclass" autocomplete="off"></spring:input></th>
			<th class=u1headline><spring:input path="field5" size="12" placeholder="接口" cssClass="shadow right10margin fieldclass" autocomplete="off"></spring:input></th>
			<th class=u1headline><spring:input path="field6" size="12" placeholder="功耗" cssClass="shadow right10margin fieldclass" autocomplete="off"></spring:input></th>
			<th class=u1headline><spring:input path="field7" size="12" placeholder="出厂年份" cssClass="shadow right10margin fieldclass" autocomplete="off"></spring:input></th>
			<th class=u1headline><spring:input path="field8" size="12" placeholder="备注" cssClass="shadow right10margin fieldclass" autocomplete="off"></spring:input></th>
<%-- 			<th class=u1headline><spring:input path="field9" size="10" placeholder="" cssClass="shadow right10margin" autocomplete="off"></spring:input></th> --%>
<%-- 			<th class=u1headline><spring:input path="field10" size="10" placeholder="" cssClass="shadow right10margin" autocomplete="off"></spring:input></th> --%>
<!-- 			<th class=u1headline><button disabled=disabled type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only shadow u1background" > -->
<!-- 				<span class="ui-button-text">添加配件类型</span> -->
<!-- 			</button></th> -->
			<th>请求/库存</th>
			</tr></THEAD>
			<tfoot><tr><td colspan="9" align="center"></td></tr></tfoot>
			<tbody></tbody>
			</table>
			<table width=98%><tr><td align=right>
				<input name="remark" id="remark" class="shadow" size=60 placeholder="为本次更改添加备注">
				<button id="submitselectcomponentbtn" type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow">
					<span class="ui-button-text">提交</span>
				</button>
			</td></tr></table>
		</div>
	</spring:form>
	<script>
	var tobeselectedcomponentmplate = "<tr id=row{componentStoreId}><td>{component.field1}</td><td>{component.field2}</td><td>{component.field3}</td><td>{component.field4}</td><td>{component.field5}</td><td>{component.field6}</td><td>{component.field7}</td><td>{component.field8}</td><td class=quantitytd align=left><input type=hidden id='componentStoreId' name='componentStoreId' value={componentStoreId}><input maxlength=3 size=3 id='quantity' name='quantity' value='{input}'>/<span>{count}</span></td></tr>";
	function updateComponentSelectTable(jsonreturn){
		clear_popup_content_layout();
		if(!validateText(jsonreturn)){
			var rows = $("#componentselecttable tbody tr");
			var selectedcomponent = [];
			for(var i=rows.length-1;i>-1;i--){
				if(rows.eq(i).find("input:last").val()=='' || rows.eq(i).find("input:last").val()<1){
					rows.eq(i).remove();
				}else{
					rows.eq(i).addClass("bgbackgound").find("td:last").addClass("ticked");;
					selectedcomponent[rows.eq(i).find("input:first").val()] = rows.eq(i);
				}
			}
			var newrows='';
			if(jsonreturn.result!=null){
				for(var i=0;i<jsonreturn.result.length;i++){
					var matchrow = selectedcomponent[jsonreturn.result[i].componentStoreId];
					if(matchrow!=undefined){
						jsonreturn.result[i].input=matchrow.find("input:last").val();
						matchrow.remove();
					}
					newrows += tobeselectedcomponentmplate.format(jsonreturn.result[i]);
				}
				$("#componentselecttable tbody").append(newrows);
			}
			$("#componentselecttable tfoot tr td").html(buildpageindex(jsonreturn));
			$("#componentselecttable tbody td").unbind('click');
			$("#componentselecttable tbody td:not(.quantitytd)").click(function(){
 				var parentrow = $(this).parent("tr");
				var id = parentrow.attr('id').substr(3);//componentstoreId or componentId
				var link= "componentstoredetail/"+id;
				slide_to_show_detail_content(parentrow, link, 'component');
			});
			$("#componentselecttable tbody input[type!='hidden']").spinner();
			$("#componentselecttable tfoot tr td a").click(function(){
				ajax_call_get_force_json('componentselectlist/'+$(this).html(), true, function(data){
					updateComponentSelectTable(data);
				});
			});
		}
	}
	$(document).ready(function(){
		$("#componentSelect").submit(function(){
			ajax_call_post('componentselectsearch',$(this).serialize(), true, function(data){
				updateComponentSelectTable(data);
			});
			return false;
		});
		$("#componentSelect").submit();//init page
		for(var i=1;i<=8;i++){
			build_select_pulldown('strlist/componentfield/'+i, $("#componentSelect #field"+i), function(){$("#componentSelect").submit();});	
		}
		$("#submitselectcomponentbtn").click(function(){
			var rows = $("#componentselecttable tbody tr");
			var error='';
			for(var i=0;i<rows.length;i++){
				var input = $(rows[i]).find("input:last").val();
				var limit = $(rows[i]).find("span:last").html();
				if(input != '' && (limit-input)<0){
					error += '配件【'+$(rows[i]).find("td").eq(0).html()+'/'+$(rows[i]).find("td").eq(1).html()+'/'+$(rows[i]).find("td").eq(2).html()+'】 请求个数超出库存数,请校对！<br>';
				}
			}
			if(error!=''){
				show_message(error);
				return false;
			}
			ajax_call_post('taskcomponentupdate/'+<c:out value="${task.taskPid}"/>, $("#componentSelect").serialize(), true, function(data){
				if(!validateText(data)){
					refreshpreviouspage('使用配件登记成功');
				}
			});
		});
	});
	</script>
</div>