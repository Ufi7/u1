<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="insidecontent-extend">
	<form id="slastas">
		<div class="u1title1">
			<table style="border-spacing:0px;width:900px;"><tr><td>
				<h2>SLA查询</h2></td><td align="right">
				<c:if test="${accessRights[8]}">
					<select id="customer" name="customer" class="shadow">
						<option value="">-全部客户-</option>
						<option  value="${self[2]}"><c:out value="${self[3] }"/></option>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
					<script>
						$(document).ready(function(){
							$("#slastas #customer").change(function(){
								$("#slastas input").val('');
								$("#slastas select").not($(this)).find("option:first").attr('selected', 'selected');
								$("#slastas").submit();
								$("#slastas #userId option:first").siblings().remove();
								if($("#slastas #customer").val()!=''){
									buildOptionBasedOnAjaxReturn($("#slastas #userId"),'option/assigntolist/'+$("#slastas #customer").val());
								}
							});
							buildOptionBasedOnAjaxReturn($("#slastas #customer"), 'option/customerlist');
						});
					</script>
				</c:if>
			</td></tr></table>
		</div>
		&nbsp;<input id="dateFrom" name="dateFrom" size="20" maxlength=10 placeholder="统计起始时间" class="shadow" autocomplete="off"/>&nbsp;&nbsp;
		<input id="dateTo" name="dateTo" size="20" maxlength=10 placeholder="统计结束时间" class="shadow" autocomplete="off"/>&nbsp;&nbsp;
		<select id='userId' name='userId' class="shadow">
			<option value="">全部用户</option>
		</select>
		<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow">
			<span class="ui-button-text">统计</span>
		</button>
	</form>	
	<div id='slataskcountoverview' style="width:930px;height:300px;" class="bgcolorborder leftbottom5margin"></div>
	<div id='slataskscoreoverview' style="width:930px;height:300px;" class="bgcolorborder leftbottom5margin"></div>
</div>
<script>
$(document).ready(function(){
	show_loading_message("正在为你加载数据");
	$("#slastas").submit(function(){
		ajax_call_post('slacalc', $(this).serialize(), true, function(data){
			if(!validateText(data)){
				drawtaskcount(data, 'slataskcountoverview');
				drawtaskscore(data, 'slataskscoreoverview');
			}
		});
		return false;
	});
	$("#slastas").submit();
	$("#slastas #dateFrom, #slastas #dateTo").datepicker();
	if($("#slastas #customer").val()!=''){
		buildOptionBasedOnAjaxReturn($("#slastas #userId"),'option/assigntolist/'+$("#slastas #customer").val());
	}
});
</script>