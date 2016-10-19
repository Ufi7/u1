<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="insidecontent-extend">
	<div class="u1title1"><h2>工单评分设定</h2></div>
	<div class="contentbox">
		<spring:form commandName="sla">
			<table class="detailtable" id="newassettable">
				<tr>
					<td><span class="fixtext"><spring:radiobutton cssClass="shadow" path="seloption" value="1" label="规则#1"/></span></td>
					<td colspan=2><span class="fixtext">按超出小时扣分</span></td>
				</tr>
				<tr>
					<td width="10%"></td>
					<td>每超<spring:input path="option1inteval" size="3" cssClass="shadow" autocomplete="off"/>小时，扣除<spring:input path="option1deduction" size="3" cssClass="shadow" autocomplete="off"/>分，直至扣完为止</td>
				</tr>
				<tr>
					<td></td>
					<td colspan=2 class="non-highlight-content">例如选择此方式，按照每超过2小时扣10分为基准，超时3小时为80分，超时10小时为50分，超时20小时以上为零分
						<br>优点是对所有任务采取统一计时扣分方式，<br>缺点是对比较耗工时的任务不公平。以1个20小时的任务和100小时的工单，超2小时都为90分，</td>
				</tr>
				<tr>
					<td><span class="fixtext"><spring:radiobutton cssClass="shadow" path="seloption" value="2" label="规则#2"/></span></td>
					<td colspan=2><span class="fixtext">按超时百分比扣分</span></td>
				</tr>
				<tr>
					<td></td>
					<td colspan=2>每超工单总时长<spring:input path="option2inteval" size="3" cssClass="shadow" autocomplete="off"/>%，扣除<spring:input path="option2deduction" size="3" cssClass="shadow" autocomplete="off"/>分，直至扣完为止</td>
				</tr>
				<tr>
					<td width=10%></td>
					<td colspan=2 class="non-highlight-content">例如选择此方式，按照每超过总时长10%内扣10分为基准，1个30小时的工单超时3小时为90分，超时10小时为60分，超时30小时或以上为零分
						<br>优点是对所有任务根据总时长来计算扣分，1个50工时的工单超5小时和1个30工时的工单超3小时最后的得分都是90分
						<br>缺点是对于短工时的任务超时要求非常严格</td>
				</tr>
				<tr>
					<td colspan=2></td>
					<td width="10%">
						<button type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow">
							<span class="ui-button-text">提交更改</span>
						</button>
					</td>
				</tr>
			</table>
		</spring:form>
	</div>
</div>
<script>
$(document).ready(function(){
	$("#sla input:radio").change(function(){
		if($(this).val()=='1'){
			$("#sla #option1inteval").removeAttr('disabled');
			$("#sla #option1deduction").removeAttr('disabled');
			$("#sla #option2inteval").attr('disabled', 'disabled');
			$("#sla #option2deduction").attr('disabled', 'disabled');
		}else{
			$("#sla #option2inteval").removeAttr('disabled');
			$("#sla #option2deduction").removeAttr('disabled');
			$("#sla #option1inteval").attr('disabled', 'disabled');
			$("#sla #option1deduction").attr('disabled', 'disabled');
		}
	});
	$("#sla input:radio[checked='checked']").change();
	$("#sla").submit(function(){
		ajax_call_post('slasetupsubmit', $(this).serialize(), true, function(data){
			if(!validateText(data)){
				show_message("规则更改成功！");
			}
		});
		return false;
	});
});
</script>