<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="sidebar">
	<ul class="leftmenu">
		<a href="assettype"><li class="arrow-grey">资产类型</li></a>
		<a href='tasktype'><li class="arrow-grey">工单类型</li></a>
		<a href='customer'><li class="arrow-grey">客户管理</li></a>
		<a href='department'><li class="selectedli arrow-blue">客户属下部门</li></a>
	</ul>
</div>
<div class="insidecontent">
	<div class="u1title1"><h2>客户属下部门管理</h2></div>
	<div class="contentbox">
		<spring:form commandName="department">
			<span class="non-highlight">所属客户：</span><select name="customer" id="customer" class="shadow">
				<option value=''>-请选择客户-</option>
			</select>
			<spring:input path="departmentName" size="20" placeholder="新建部门名称" autocomplete="off" cssClass="shadow hidden addnewdpm"/>
			<spring:input path="departmentDesc" size="20" placeholder="新建部门描述" autocomplete="off" cssClass="shadow hidden addnewdpm"/>
			<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only shadow u1background hidden addnewdpm" >
				<span class="ui-button-text">添加</span>
			</button>
		</spring:form>
		<div id="datadiv">
			
		</div>
	</div>
	<SCRIPT type="text/javascript">
	$(document).ready(function(){
		buildOptionBasedOnAjaxReturn($("#department #customer"),'option/customerlist', function(){
			if($("#department #customer").find("option").length>1){
				$("#department #customer").find("option[value!='']:first").attr('selected', 'selected');
				$("#department #customer").change();
			}
		});
		$("#department #customer").change(function(){
			clear_popup_content_layout();
			var link= "departmentlist/"+$("#department #customer").val();
			var sel = $(this);
			ajax_call_get(link, true,  function(data){
				if(!validateText(data)){
					$("#datadiv").html(data);
					$(".addnewdpm").show();
					sel.find("option[value='']").remove();
				}
			});
		});
		$("#department").submit( function() {
			clear_popup_content_layout();
			ajax_call_post('departmentsubmit', $(this).serialize(), true, afteraddnewdepartment);
			return false;
		});
	});
	function afteraddnewdepartment(data){
		if(!validateText(data)){
			var message = success_add_department.replace('~~0',$("#department input").eq(0).val());
			var tds=[$("#newrow").children().eq(0), $("#newrow").children().eq(1)];
			var inputs=[$("#department input").eq(0), $("#department input").eq(1)];
			var prefill0s=[false, false];
			addnewrow($("#newrow"), tds, inputs, prefill0s, data);
			show_message(message);
		}
	}
	</SCRIPT>
</div>