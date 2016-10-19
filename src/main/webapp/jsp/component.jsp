<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="insidecontent-extend">
	<spring:form commandName='componentSearch'>
		<div class="u1title1">
			<table style="border-spacing:0px;width:900px;"><tr><td>
				<h2>配件管理</h2></td><td align="right">
				<c:if test="${accessRights[8]}">
					查看视图
					<select id="customer" name="customer">
						<option value="${self[2]}"></option>
					</select>
					<script>
						$("#componentSearch #customer").change(function(){$("#componentSearch").submit();});
						$(document).ready(function(){
							buildOptionBasedOnAjaxReturn($("#componentSearch #customer"), 'option/customerlist');
						});
					</script>
				</c:if>
			</td></tr></table>
		</div>
		<ul class="u1list2" id="componentlisttab">
			<li id="componentlistcurrenttab" class="selected"><a>当前库存</a></li>
			<li id="componentlistaddtab"><a>添加新类</a></li>
		</ul>
		<div>
		<table class="flat-table flat-table-4 widetable fixedtable breakrow" id="componenttable">
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
			<th>库存</th>
			</tr></THEAD>
			<tfoot><tr><td colspan="9" align="center"></td></tr></tfoot>
			<tbody></tbody>
			</table>
		</div>
	</spring:form>
	<script>
	var storerowtemplate = "<tr id=row{componentStoreId}><td>{component.field1}</td><td>{component.field2}</td><td>{component.field3}</td><td>{component.field4}</td><td>{component.field5}</td><td>{component.field6}</td><td>{component.field7}</td><td>{component.field8}</td><td align=center>{count}</td></tr>";
	var typerowtemplate = "<tr id=row{componentId}><td>{field1}</td><td>{field2}</td><td>{field3}</td><td>{field4}</td><td>{field5}</td><td>{field6}</td><td>{field7}</td><td>{field8}</td><td align=center>--</td></tr>";
	var typepopupcontent = "<form><div style='padding:10px;'><input type=hidden id='customerId' name='customerId' value='{customerId}'/><input type=hidden id='component' name='component' value='{component}'/><select class='shadow right10margin' name=reason id=reason><option value=1>进货</option><option value=2>回收</option><option value=5>调入</option></select><input size=10 class='shadow right10margin' id='quantity' name='quantity' placeholder='数量'/><input size=10  id='price' name='price' placeholder='单价' class='shadow right10margin'/><input size=30 class='shadow right10margin' id='remark' name='remark' placeholder='备注'/></input><button type='submit' class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only u1background shadow right10margin'><span class='ui-button-text'>提交</span></button></div></form>";
	function updateComponentTableFooter(jsonreturn){
		if(!$("#componentlistcurrenttab").hasClass("selected") && jsonreturn.result==null){
			$("#componenttable tfoot tr td").html('该类别不存在，<span class="redlink" id="addnewcomponenttype">点击这里</span>添加新类别');
			$("#addnewcomponenttype").click(function(){
				ajax_call_post('addnewcomponenttype',$("#componentSearch").serialize(), true, function(data){
					if(!validateText(data)){
						show_message("新类型添加成功");
						$("#componenttable tbody").html(typerowtemplate.format(data));
						$("#componenttable tfoot tr td").html('');
						//$("#componenttable input").val('');
						for(var i=1;i<=8;i++){
							build_select_pulldown('strlist/componentfield/'+i, $("#componentSearch #field"+i), function(){$("#componentSearch").submit();});	
						}
					};
				});
			});
		}else{
			$("#componenttable tfoot tr td").html(buildpageindex(jsonreturn));
		}
	}
	function updateComponentTable(jsonreturn){
		clear_popup_content_layout();
		if(!validateText(jsonreturn)){
			var content='';
			if(jsonreturn.result!=null){
				if($("#componentlistcurrenttab").hasClass("selected")){
					for(var i=0;i<jsonreturn.result.length;i++){
						content+=storerowtemplate.format(jsonreturn.result[i]);
					}
				}else{
					for(var i=0;i<jsonreturn.result.length;i++){
						content+=typerowtemplate.format(jsonreturn.result[i]);
					}
				}
			}
			$("#componenttable tbody").html(content);
			updateComponentTableFooter(jsonreturn);
			$("#componenttable tbody tr").click(function(){
				set_selected($(this));
				var id = $(this).attr('id').substr(3);//componentstoreId or componentId
				if($("#componentlistcurrenttab").hasClass("selected")){
					var link= "componentstoredetail/"+id;
					slide_to_show_detail_content($(this), link, 'componentstore');
				}else{
					show_popup_content_layout(600);
					update_popup_detail_content(typepopupcontent.format({customerId:$("#componentSearch #customer").val(), component:id}));
					$("#popupcontent #reason").change(function(){
						if($(this).val()==1){
							$("#popupcontent #price").slideDown();
						}else{
							$("#popupcontent #price").val('');
							$("#popupcontent #price").slideUp();
						}
					});
					$("#popupcontent form").submit(function(){
						//submit add store handling
						ajax_call_post('newcomponentstore', $(this).serialize(), true, function(data){
							if(!validateText(data)){
								clear_popup_content_layout();
								show_message("操作成功");
							}
						});
						return false;
					});
				}
			});
			$("#componenttable tfoot tr td a").click(function(){
				if($("#componentlistcurrenttab").hasClass("selected")){
					ajax_call_get_force_json('componentlist/'+$(this).html(), true, function(data){
						updateComponentTable(data);
					});
				}else{
					ajax_call_get_force_json('componenttypelist/'+$(this).html(), true, function(data){
						updateComponentTable(data);
					});
				}
			});
		}
	}
	$(document).ready(function(){
		$("#componentlistcurrenttab").click(function(){
			if(!$(this).hasClass("selected")){
				$(this).siblings().removeClass("selected");
				$(this).addClass("selected");
				$("#componenttable thead tr th:last").html('库存');
				$("#componentSearch input").val('');
				$("#componentSearch").submit();
			}
		});
		$("#componentlistaddtab").click(function(){
			if(!$(this).hasClass("selected")){
				$(this).siblings().removeClass("selected");
				$(this).addClass("selected");
				$("#componenttable thead tr th:last").html('--');
				$("#componentSearch input").val('');
				$("#componentSearch").submit();
			}
		});
		$("#componentSearch").submit(function(){
			if($("#componentlistcurrenttab").hasClass("selected")){
				ajax_call_post('componentsearch',$(this).serialize(), true, function(data){
					updateComponentTable(data);
				});
			}else{
				ajax_call_post('componenttypesearch',$(this).serialize(), true, function(data){
					updateComponentTable(data);
				});
			}
			return false;
		});
		$("#componentSearch").submit();
		for(var i=1;i<=8;i++){
			build_select_pulldown('strlist/componentfield/'+i, $("#componentSearch #field"+i), function(){$("#componentSearch").submit();});	
		}
	});
	</script>
</div>