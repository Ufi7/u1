//remove loading div
//global var
function remove_loading() {
	$("#maskerlayer").hide(0);
	$("#loader_container").hide(0);
}

//remove message div if have, show loading div
function show_loading() {
	if(!$("#loader_container").is(':visible')){
		remove_message();
		$("#loader_container").find("font").html('页面加载中……');
		$("#maskerlayer").show(0);
		$("#loader_container").show(0);
	}
}

function show_loading_message(message) {
	remove_message();
	$("#loader_container").find("font").html(message);
	$("#maskerlayer").show(0);
	$("#loader_container").show(0);
}

//remove message
function remove_message() {
	$("#messagecontent").html('');
	$("#messagebox").hide(200);
}

//show message, and remove loading
function show_message(message) {
	myclearTimeout(outEvent['systemessage']);
	outEvent['systemessage']=[];
	$("#messagecontent").html(message);
	remove_loading();
	$("#messagebox").css('left', $("#nav").offset().left+200);
	$("#messagebox").show(200);
}

function show_message_5s(message){
	show_message(message);
	if(outEvent['systemessage']==undefined){
		outEvent['systemessage']=[];
	}
	outEvent['systemessage'].push(setTimeout(remove_message, 5000));
}

function show_system_error_message(){
	show_message("系统超时，请稍后重试！<br>或点击<a href='javascript:refreshPage();'><font color='red'>这里</font></a>刷新页面, 或者点击<a href='javascript:refreshpreviouspage();'><font color='red'>这里</font></a>返回上一也面。");
}

//validate ajax response
function validateText(text){
	remove_message();
	if(typeof(text)=='object'){
		text = JSON.stringify(text);
	}
	if(text.length>12){
		if(text.substr(0, 12)==("<!--login-->")){
			//location.reload();
			//document.execCommand('Refresh');
			//window.navigate(location);
			location.replace(window.location.href);
		}else if(text.substr(0, 12)==("<!--error-->")){
			if(text.length>12){
				show_message(text);
			}else{
				show_system_error_message();
			}
			return true;
		}
	}
	return false;
}

function validateTextWithOutMessage(text){
	if(typeof(text)=='object'){
		text = JSON.stringify(text);
	}
	if(text.length>12){
		if(text.substr(0, 12)==("<!--login-->")){
			location.reload();
		}else if(text.substr(0, 12)==("<!--error-->")){
			return true;
		}
	}
	return false;
}

//handle 3 select template select
function multi_select_switch(option, outtarget, intarget,hiddentarget){
	var value;
	var outtarget_id=outtarget.attr("id");
	var intargetn_id=intarget.attr("id");
	var hiddentarget_id=hiddentarget.attr("id");
	var selected;
	if(option.parent("select").attr("id")==outtarget_id){
		option.appendTo(intarget);
		selected = true;
	}else if(option.parent("select").attr("id")==intargetn_id){
		option.appendTo(outtarget);
		selected = false;
	}
	$.each(option, function(key,element){
		value=$(this).attr("value");
		$("#"+hiddentarget_id+" option[value='"+value+"']").attr("selected", selected);
	});
}
//manually add a new low by id and empty row
function addnewrow(hiddenrowobject, tds, inputs, prefill0s, id){
	var temp;
	for(var i=0;i<inputs.length;i++){
		temp = inputs[i].val();
		if(temp=='' && prefill0s[i]){
			temp='0';
		}
		tds[i].html('<font color=blue>'+temp+'</font>');
		inputs[i].val('');
	}
	var newone = hiddenrowobject.clone();
	newone.attr('id','row'+id);
	newone.click(function(){
		detail(id);
	});
	hiddenrowobject.parent("tbody").prepend(newone);
	newone.fadeIn(2000);
}
function appendnewrow(values, table, newid, onclickfunction){
	var rowstr='<tr>';
	for(var i=0;i<values.length;i++){
		rowstr+='<td>'+values[i]+'</td>';
	}
	rowstr+='</tr>';
	var $outputtag=$(rowstr);
	$outputtag.prependTo(table.children("tbody"));
	$outputtag.click(function(){
		onclickfunction.apply(null, [newid]);
	});
	////////////////////////////////
}
//manually prefill, and clear all inputs
function prefilldata(tds, inputs, prefill0s){
	for(var i=0;i<tds.length;i++){
		temp=inputs[i].val();
		if(temp==''&& prefill0s[i]){
			temp='0';
		}
		tds[i].html(temp);
		inputs[i].val('');
	}
}
//manually prefill with values
function prefillvalue(tds, values, prefill0s){
	for(var i=0;i<tds.length;i++){
		temp=values[i];
		if(temp==''&& prefill0s[i]){
			temp='0';
		}
		tds[i].html(temp);
	}
}

/////////////////////////////////////////////////////////////////
function ajax_call_get(link, withloading, nextstep){
	if(withloading){
		show_loading();
	}
	$.ajax({ 
		type: 'get', 
		url : link,
		async:true,
		cache:false,
		dataType: 'html',
		success : nextstep,
		error: show_system_error_message,
		complete : remove_loading
	}); 
}

function ajax_call_get_force_json(link, withloading, nextstep){
	if(withloading){
		show_loading();
	}
	$.ajax({ 
		type: 'get', 
		url : link,
		async:true,
		cache:false,
		success : nextstep,
		error: show_system_error_message,
		complete : remove_loading
	}); 
}

function ajax_call_post(link, formdata, withloading, nextstep){
	if(withloading){
		if(!$("#loader_container").is(':visible')){
			show_loading();
		}
	}
	$.ajax({ 
		type: 'post', 
		async : true, 
		url : link,
		data: formdata,
		cache:false,
		success : nextstep,
		error: show_system_error_message,
		complete : remove_loading
	});
}

function ajax_call_post_force_jason2html(link, formdata, withloading, nextstep){
	if(withloading){
		show_loading();
	}
	$.ajax({ 
		type: 'post', 
		dataType: 'html',
		async : true, 
		url : link,
		data: formdata,
		cache:false,
		success : nextstep,
		error: show_system_error_message,
		complete : remove_loading
	});
}

function ajax_call_upload(url, remarkvalue, label, withloading, nextstep)
{	
	if(withloading){
		show_loading();
	}
	$.ajaxFileUpload
    (
        {
            url:url, 
            secureuri:false,
            fileElementId:label,
            dataType: 'json',
            data:{remark:remarkvalue},
            success : nextstep,
    		error: show_system_error_message,
    		complete : remove_loading
        }
    );
}

//below are for ajax call back use
//update list table based on ajax return
function update_list_table(data){
	if(!validateText(data)){
		$("#datadiv").html(data);
	}
}

function show_popup_content_layout(popupwidth){
	clear_popup_content_layout();
	$("#popuplayout").css("left", lastobject.offset().left+5);
	$("#popuplayout").css("top", lastobject.offset().top+30);
	$("#popuplayout").css("width", popupwidth);
	$("#popuplayout").fadeIn();
}

//function show_right_popup_content_layout(){
//	clear_popup_content_layout();
//	$("#popuplayout").css("left", $(".maincontent").offset().left+50);
//	$("#popuplayout").css("top", $(".maincontent").offset().top+50);
//	$("#popuplayout").fadeIn();
//}


function update_popup_detail_content(data){
	if(!validateText(data)){
		$("#popupcontent").html(data);
		$("#popuploadingimg").hide(0);
		$("#popupcontent").fadeIn();
	}
}

function clear_popup_content_layout(){
	$("#popuplayout").fadeOut(0);
	$("#popupcontent").hide();
	$("#popupcontent").html('');
	$("#popuploadingimg").show();
}

function show_upper_content_layout(){
	clear_upper_content_layout();
	$("#popuplayout").css("left", $(".maincontent").offset().left+300);
	$("#popuplayout").css("top", lastobject.offset().top-10);
	$("#popuplayout").css("width", 48);
	$("#popuplayout").fadeIn();
}

function update_upper_detail_content(data){
	if(!validateText(data)){
		$("#popuplayout").animate({
			left: $(".maincontent").offset().left+45,
			top: $(".maincontent").offset().top+50,
			width:900,
			height:"auto"
			},200, function(){
				$("#popuploadingimg").hide(0);
				$("#popuplayout .headline").show();
				$("#popupcontent").html(data);
				$("#popupcontent").fadeIn();
		});
	}
}

function clear_upper_content_layout(){
	$("#popuplayout .headline").hide();
	$("#popuplayout").fadeOut(0);
	$("#popupcontent").hide();
	$("#popupcontent").html('');
	$("#popuploadingimg").show();
}

function set_selected(target){
	if(target!=undefined){
		target.siblings().removeClass("selectedobject");
	}
	lastobject=target;
	lastobject.addClass("selectedobject");
}

function refreshRow(){
	lastobject.hide();
	lastobject.fadeIn(2000);
}

function cleansametypebrotherdiv(type){
	for(var i=0;i<divs.length;i++){
		var cd = divs[i];
		if(type==cd[3]){
			cd[2].html('');
		}
	}
}

function init1stdivs(link, type, currentContent){
	if(divs.length==0){
		save_current_to_list(link, type, currentContent);
	}
}

function save_current_to_list(link, type, currentContent){
	if(currentContent == null){
		currentContent = $(".content>.container>.maincontent:visible");
	}
	//save current as 1st node, put[clickentity, url, divtarget, type]
	divs.push([null, link, currentContent, type]);
}

function slide_to_show_detail_content(clickentity, link, type, message, callback, formOrNot, formdata){
	if(clickentity!=undefined){
		clickentity.siblings().removeClass("selectedobject");
		clickentity.addClass("selectedobject");
	}
	show_loading();
	if(divs.length==0){
		//save current as 1st node, put[clickentity, url, divtarget, type]
		save_current_to_list('', '');
	}
	var currentM = $(".content>.container>.maincontent:visible");
	var $newtarget = $('<div class="maincontent hidden tobeslide"><div class="insidecontent-extend"></div></div>');
	$newtarget.insertAfter(currentM);
	cleansametypebrotherdiv(type);
	divs.push([clickentity, link, $newtarget, type]);
	if(formOrNot){
		ajax_call_post(link, formdata, true, function(data){
			if(!validateText(data)){
				$newtarget.html(data);
				if(message!=undefined){setTimeout(function(){show_message_5s(message);},100);}
				if(callback!=undefined)callback();
			}
		});
	}else{
		ajax_call_get(link, true, function(data){
			if(!validateText(data)){
				$newtarget.html(data);
				if(message!=undefined){setTimeout(function(){show_message_5s(message);},100);}
				if(callback!=undefined)callback();
			}
		});
	}
	currentM.animate({
		'margin-left':-50,
		opacity:0,
		width:'-=50px'
		},100, function(){
			currentM.addClass("hidden");
			$newtarget.removeClass("hidden");
			$newtarget.animate({
				'margin-left':0,
				opacity:1,
				width:960
				},100, function(){}
			);
	});
	$("#backbtn").css('left', currentM.offset().left);
	$("#backbtn").fadeIn(1000);
}

function refresh_current_content(message){
	if(divs.length>0){
		var currentd = divs[divs.length-1];
		if(currentd[1] != undefined && currentd[2] != undefined){
			ajax_call_get(currentd[1], true, function(data){
				if(!validateText(data)){
					currentd[2].html(data);
					if(message!=undefined){
						setTimeout(function(){show_message_5s(message);},100);
					}
				}
			});
		}
	}
}

function slide_back_to_show_list_content(message){
	if(divs.length<2){
		return false;
	}
	var current = divs.pop();
	var previous = divs[divs.length-1];
	while((previous[3]=='newtask' || previous[3]=='newasset') && divs.length>1){
		var removeone = divs.pop();
		removeone[2].remove();
		previous = divs[divs.length-1];
	}
	if(divs.length<2){
		$("#backbtn").hide();
	}
	var previous = divs[divs.length-1];
	if(previous[3]=='home'){
		refresh2taskitem();
	}
	if(previous[2].html()=='' || $(previous[2]).find(".insidecontent-extend").html()==''  ){
		ajax_call_get(previous[1], true, function(data){
			if(!validateText(data)){
				previous[2].html(data);
				if(message!=undefined)
					setTimeout(function(){show_message_5s(message);},100);
			}
		});
	}
	current[2].animate({
		'margin-left':50,
		opacity:0,
		width:'-=50px'
		},100, function(){
			current[2].addClass("hidden");
			previous[2].removeClass("hidden");
			previous[2].animate({
				'margin-left':0,
				opacity:1,
				width:960
				},100, function(){}
			);
		}
	);
	setTimeout(function(){current[2].remove();}, 200);
}

function refreshPage(){
	if(divs.length>1){
		refresh_current_content();
	}else{
		javascript:location.reload();
	}
}

function refreshpreviouspage(message){
	if(divs.length<3){
		return false;
	}
	var previous = divs[divs.length-2];
	if(previous[3] == 'task' || previous[3]=='asset'){//only allow task & asset to force refresh
		previous[2].html('<div class="insidecontent-extend"></div>');//force refresh
	}
	slide_back_to_show_list_content(message)
}

//
function build_select_pulldown(link, inputtarget, changecallback){
	//check and remove existing
	var newGen = true;
	var name = inputtarget.next("span").html();
	if(name!=undefined && name.indexOf('u1stylejsselpulldown')==0){
		newGen = false;
	}
	var ranNum;
	if(newGen){
		ranNum=''+Math.random();
		ranNum=ranNum.substr(2, ranNum.length);
		name='u1stylejsselpulldown'+ranNum;
	}else{
		ramNum=name.substr(20, name.length);
	}
	var ulname=name+'_ul';
	var loadingname = name+'_loading';
	if(newGen){
		var outputcontent='<div id="'+ name+'" class="pulldowndiv shadow"><ul id="'+ulname+'" class="u1list" style=display:none;"></ul><table id="'+loadingname+'" width="100%"><tr><td align="center"><img src="img/u1/loading.gif"></img></td></tr></table></div>';
		$(outputcontent).insertAfter(inputtarget.closest('div'));
		$('<span class="hidden">'+name+'</span>').insertAfter(inputtarget);
	}else{//remove existing option
//		var count=$("#"+ulname).children().length;
//		for(var n=0;n<count;n++){
//			$("#"+ulname).children().eq(0).remove();
//		}
		$("#"+ulname).children().remove();
		inputtarget.unbind();//clear existing events
	}
	inputtarget.focus(function(){
		inputtarget.unbind();
		$("#"+name).css('left', inputtarget.position().left+2);
		$("#"+name).css('top', inputtarget.position().top+32);
		$("#"+name).css('width', inputtarget.width()+4);
		$("#"+name).slideDown();
		ajax_call_get(link, false, function(data){
			build_input_option_list_based_on_ajax_return(data, inputtarget, $("#"+name), $("#"+ulname), $("#"+loadingname), changecallback);
		});
	});
}

function build_input_option_list_based_on_ajax_return(data, input, outterdiv, listul, loadingtarget, changecallback){
	var index = getRandom(999999);
	outEvent[index]=[];
	if(!validateText(data)){
		//add new options
		var jsonreturn = JSON.parse(data);
		if(jsonreturn!=undefined && jsonreturn.length>=0){
			var licontent = '';
			for(var i=0;i<jsonreturn.length;i++){
//				var newli = sampleli.clone();
//				newli.html(jsonreturn[i]);
//				newli.attr('id', '');
//				listul.prepend(newli);
//				if(newli.html().indexOf(input.val())>=0){
//					newli.show();
//				}
				licontent += '<li>'+jsonreturn[i]+'</li>';
			}
			listul.prepend(licontent);
			loadingtarget.fadeOut();
			listul.fadeIn();
		}
		input.attr('autocomplete', false);
		input.keypress(function(event){
			if(event.keyCode == 13||event.keyCode == 40 || event.keyCode==38){//avoid up/down/enter
				return false;
			}
		});
		input.keyup(function(event){
			var previousone = $("#"+listul.attr('id')+" li.hovered");
			if(event.keyCode == 40 || event.keyCode==38){//handle up/down
				if(previousone!= undefined && previousone.html() != undefined){
					var selectedone;
					if(event.keyCode==38){
						selectedone = previousone.prevAll(':visible').first();
					}else{
						selectedone = previousone.nextAll(':visible').first();
					}
					if(selectedone!=undefined && selectedone.html()!=undefined){
						if(selectedone.offset().top - outterdiv.offset().top >= 140 || selectedone.offset().top - outterdiv.offset().top<10)
							outterdiv.scrollTop(selectedone.offset().top - outterdiv.offset().top + outterdiv.scrollTop());
						selectedone.addClass("hovered");
						previousone.removeClass("hovered");
						input.val(selectedone.html());
					}
				}else{
					var allvisible= $("#"+listul.attr('id')+" li:visible");
					if(allvisible!=undefined){
						allvisible.eq(0).addClass("hovered");
						input.val(allvisible.eq(0).html());
					}
				}
			}else if(event.keyCode == 13){//handle enter
				//if(previousone!= undefined && previousone.html() != undefined){
					outterdiv.slideUp();
					//console.log('enter trigger');
					//refreshList(input.val(), listul,changecallback);//will trigger change event
					input.click(function(){
						input.unbind('click');
						input.focus();
					});
					input.change();
				//}
			}else{
				refreshList(input.val(), listul, null);
			}
		});
		
		input.bind("focus",function(){
			myclearTimeout(outEvent[index]);
			outterdiv.slideDown();
			refreshList(input.val(), listul);
		});
		input.bind("change", function(){
			//console.log('change trigger');
			refreshList(input.val(), listul, changecallback);
		});
		input.blur(function(){
			outEvent[index].push(setTimeout(function(){
				outterdiv.slideUp();
			}, 1000));
		});
		outterdiv.scroll(function(){
			myclearTimeout(outEvent[index]);
		});
		outterdiv.mouseleave(function(){
			if(!input.is(":focus")){
				outEvent[index].push(setTimeout(function(){
					outterdiv.slideUp();
				}, 1000));
				
			}
		});
		listul.children().click(function(){
			input.val($(this).html());
			outterdiv.slideUp();
			//console.log('click trigger');
			refreshList(input.val(), listul, changecallback);
		});
		listul.children().mouseenter(function(){
			listul.children().removeClass('hovered');
			$(this).addClass('hovered');
		});
	}
}
function refreshList(value, listul, changecallback){
	var list = listul.children();
	var currentli;
	for(var i=0;i<list.length;i++){
		currentli = list.eq(i);
		currentli.removeClass("hovered");
		if(currentli.html().toLowerCase().indexOf(value.toLowerCase())>=0){
			currentli.show();
		}else{
			currentli.hide();
		}
	}
	if(changecallback!=undefined){
		changecallback.apply();
	}
}

function getRandom(seed){
	var ran = Math.random();
	var val  = ran*seed - ran*seed%1;
	return seed/val>10?(seed+1)/10+val:val;
}

function myclearTimeout(target){
	if(target!=undefined){
		var count = target.length;
		for(var i=0;i<count;i++){
			clearTimeout(target.pop());
		}
	}
}
function tabsbindurl(lis, urls, existingeddivs, selectedCss, autoinit){
	var ram = getRandom(99999);
	var count = lis.length;
	var tdivs=[];
	for(var i=0;i<lis.length;i++){
		lis.eq(i).attr('id', 'tabsli_'+ram+i);
	}
	for(var n=0;n<existingeddivs.length;n++){
		//existingedtdivs[n].attr('id', 'tabsdiv_'+ram+n);
		tdivs.push(existingeddivs[n]);
	}
	for(var i=existingeddivs.length;i<count;i++){
		var newdiv = tdivs[0].clone();
		newdiv.html('');
		newdiv.attr('id', 'tabsdiv_'+ram+i);
		newdiv.hide();
		newdiv.insertAfter(tdivs[tdivs.length-1]);
		tdivs.push(newdiv);
	}
	lis.click(function(){
		if(lis.hasClass(selectedCss)){
			lis.removeClass(selectedCss);
			$(this).addClass(selectedCss);
			var current = $(this).attr('id').substr(12, $(this).attr('id').length);
			if($("#tabsdiv_"+ram+current).html()=='' && urls[current]!=''){
//				ajax_call_getUpdate(urls[current], tdivs[current]);
				ajax_call_get(urls[current], true, function(data){
					if(!validateText(data)){
						tdivs[current].html(data);
					}
				});
			}
			for(var i=0;i<tdivs.length;i++){
				if(i!=current){
					tdivs[i].hide();
				}
			}
			tdivs[current].fadeIn();
		}
	});
	if(autoinit && $.trim(tdivs[0].html())==''){
		lis.eq(0).click();
	}
}
function buildpagenavigation(cssclass, result, seed, target, margintop, navaction, refreshlinkprefix){
	var totalcount=result.totalCount;
	var totalpage=result.totalPage;
	var currentpage=result.currentPage;
	var range = 3;
	var pages=[];
	if(totalcount==0){
		//nothing to do;
	}else{
		if(currentpage-range>1){
			pages.push([1, true]);
		}
		var startpage = currentpage-range<1?1:currentpage-range;
		var lastpage = currentpage+range>totalpage?totalpage:currentpage+range;
		for(var i=startpage; i<=lastpage;i++){
			if(i==currentpage){
				pages.push([i, false]);
			}else{
				pages.push([i, true]);
			}
		}
		if(currentpage+range<totalpage){
			pages.push([totalpage, true]);
		}
	}
	var outputcontent = "<div class='metronavdiv hidden' id='"+ "metronavdiv"+seed +"' style='margin-top:"+margintop+"px;'>";
	var lastindex=0;
	if(pages.length==0){
		outputcontent+="<span class='metropagenavcurrent'>找不到相关记录</span>";
	}else{
		for(var i=0;i<pages.length;i++){
			var page=pages[i];
			if(page[0]-lastindex>1){
				outputcontent+="...&nbsp;&nbsp;";
			}
			if(page[1]){
				outputcontent+="<a class='metropagenavlink metropagenavlink"+seed+"' id='"+cssclass+seed+page[0]+"'>"+page[0]+"</a>&nbsp;&nbsp;";
			}else{
				outputcontent+="<span class='metropagenavcurrent'>"+page[0]+"</span>&nbsp;&nbsp;";
			}
			lastindex=page[0];
		}
	}
	outputcontent+="<div>";
	var $outputtag = $(outputcontent);
	target.append($outputtag);
	$outputtag.css('left', target.offset().left);
	$outputtag.css('top', target.offset().top+100);
	$outputtag.css('width', 500);
	$outputtag.fadeIn(2000);
	$outputtag.children('.metropagenavlink').click(function(){
		link = refreshlinkprefix+$(this).html();
		navaction.apply(this, [link]);
	});
}
function checkMetroOption(metrocontainer, select, entitycontainer){
	var selectoptions = select.children("option");
	var metros = metrocontainer.children("a");
	for(var i=0;i<metros.length;i++){
		var id = metros.eq(i).children("span").eq(0).html();
		var selected = false;
		for(var n=0;n<selectoptions.length;n++){
			if(id==selectoptions.eq(n).val()){
				selected = true;
				break;
			}
		}
		if(!selected){
			metros.eq(i).addClass("metroselectable");
		}else{
			metros.eq(i).addClass("metrounselectable");
		}
	}
	metros.click(function(){
		if($(this).hasClass("metrounselectable")){//
			selectRemoveOption(select, $(this).children("span").eq(0).html());
			removeEntity(entitycontainer,$(this).children("span").eq(0).html());
		}else{
			selectAddOption(select, $(this).children("span").eq(0).html());
			AddEntityToContainer(entitycontainer, $(this).children("span").eq(0).html(),$(this).html(), select, metrocontainer);
		}
		$(this).toggleClass("metroselectable");
		$(this).toggleClass("metrounselectable");
	});
}
function checkMetroGroupOption(metrocontainer, select, entitycontainer){
	var selectoptions = select.children("option");
	var metros = metrocontainer.children("a");
	for(var i=0;i<metros.length;i++){
		metros.eq(i).addClass("metroselectable");
	}
	metros.click(function(){
		ajax_call_get('metro/assetlistingroup/'+$(this).children("span").eq(0).html(), true, function(data){
			handleMetroGroupJSONResponse(data, select, metrocontainer,entitycontainer);
		});
		$(this).toggleClass("metroselectable");
		$(this).toggleClass("metrounselectable");
		$(this).unbind('click');
	});
}

function handleMetroGroupJSONResponse(data,select, metrocontainer,entitycontainer){
	var jsondata = JSON.parse(data);
	var count=0;
	for(var i=0;i<jsondata.length;i++){
		var entity = jsondata[i];
		var htmlcontent = '<b>'+entity[1]+'</b><br><font class=s>'+entity[2]+'</font><br>'+entity[3]+'/'+asset_status[entity[4]]+'<span class="hidden">'+entity[0]+'</span>';
		selectAddOption(select, entity[0]);
		count+=AddEntityToContainer(entitycontainer,entity[0],htmlcontent, select, metrocontainer);
	}
	var message = '成功添加该分组中的&nbsp;<span class=messagehl>'+count+'</span>&nbsp;个资产!';
	if(jsondata.length>count){
		var leaving = jsondata.length-count;
		message += '&nbsp;另外该分组中的 &nbsp;<span class=messagehl>'+leaving+'</span>&nbsp;个资产已经存在!';
	}
	show_message_5s(message);
}

function setupEntityClick(entity, select, metrocontainer){
	id = entity.children("span").eq(0).html();
	selectRemoveOption(select, id);
	var metros = metrocontainer.children("a");
	for(var i=0;i<metros.length;i++){
		if(metros.eq(i).html()==entity.html()){
			if(!metros.eq(i).hasClass("metroselectable")){
				metros.eq(i).addClass("metroselectable");
			}
			metros.eq(i).removeClass("metrounselectable");
		}
	}
	entity.remove();
}
function selectAddOption(select, optionvalue){
	var options = select.children('option');
	var optionexisted = false;
	for(var i=0;i<options.length;i++){
		if(options.eq(i).val()==optionvalue){
			optionexisted = true;
			break;
		}
	}
	if(!optionexisted){
		var selectoptions=select.children("option");
		var selectStr = "<option value='"+ optionvalue +"' selected=selected>"+optionvalue+"</option>";
		select.append(selectStr);
	}
}
function selectRemoveOption(select, optionvalue){
	var options = select.children('option');
	var deleteoption = null;
	for(var i=0;i<options.length;i++){
		if(options.eq(i).val()==optionvalue){
			deleteoption = options.eq(i);
			break;
		}
	}
	if(deleteoption){
		deleteoption.remove();
	}
}
function AddEntityToContainer(container, id,content,select, metrocontainer){
	var entities =  container.children("div");
	var entityexisted = false;
	for(var i=0;i<entities.length;i++){
		if(entities.eq(i).children('span').eq(0).html()==id){
			entityexisted = true;
			break;
		}
	}
	if(!entityexisted){
		var $eneity = $("<div class='entityblock entityblockedit shadow'>"+content+"</div>");
		container.append($eneity);
		$eneity.click(function(){//bind click option
			setupEntityClick($(this), select, metrocontainer);
		});
		return 1;
	}
	return 0;
}
function removeEntity(container, id){
	var entities =  container.children("div");
	var deleateentity = null;
	for(var i=0;i<entities.length;i++){
		if(entities.eq(i).children("span").eq(0).html()==id){
			deleateentity = entities.eq(i);
			break;
		}
	}
	if(deleateentity){
		deleateentity.remove();
	}
}
function bindMetroToSelectAndEntity(jsondata, metrodiv, select, eneitycontainer, type, refreshmetrocallback){
	if(!validateText(jsondata)){
		var result = JSON.parse(jsondata);
		var metroseed = getMetroRandomSedd();
		var metros = [];
		metrodiv.children(".metro66containerdiv").html('');
		if(result.result!=null){
			for(var i=0;i<result.result.length;i++){
				if(type=='asset'){
					metros.push(['<b>'+result.result[i].assetName+'</b><br><font class=s>'+result.result[i].assetNum+'</font><br>'+result.result[i].assetType.code+'/'+asset_status[result.result[i].status]+'<span class="hidden">'+ result.result[i].assetPid+'</span>',result.result[i].assetPid]);
				}
				else if(type=='assetgroup'){
					metros.push(['<br><f class="metrobigsize">'+result.result[i].groupName+'</f><span class="hidden">'+result.result[i].assetGroupPid+'</span>'])
				}
			}
			runderMetro(6,6, metros, 3, metrodiv.children(".metro66containerdiv").eq(0), '', false, metroseed, 'metro_'+metroseed);
			if(type=='asset'){
				checkMetroOption(metrodiv.children(".metro66containerdiv").eq(0), select, eneitycontainer);
			}else if(type=='assetgroup'){
				checkMetroGroupOption(metrodiv.children(".metro66containerdiv").eq(0), select, eneitycontainer);
			}
		}
		var link;
		if(type=='asset'){
			link='metro/assetlist/page/';
		}else if(type=='assetgroup'){
			link='metro/assetgrouplist/page/';
		}
		buildpagenavigation('metropagenav', result, metroseed, metrodiv.children(".metro66containerdiv").eq(0), 320,refreshmetrocallback, link);
	}
}
function buildSelectPullDown(select, link){
	addDummyOptionToExtend(select);
	select.focus(function(){
		select.unbind('focus');
		ajax_call_get(link, true, function(data){
			removeDummyOption(select);
			addOption(data, select);
		});
	});
}
function addDummyOptionToExtend(select){
	var optionstr = '<option value=""></option>';
	for(var i=0;i<10;i++){
		select.append(optionstr);
	}
}
function removeDummyOption(select){
	var options = select.children('option');
	var removeflag = [];
	for(var i = 0;i<options.length;i++){
		if(options.eq(i).html()==''){
			removeflag.push(true);
		}else{
			removeflag.push(false);
		}
	}
	for(var i=removeflag.length-1;i>=0;i--){
		if(removeflag[i]==true){
			options.eq(i).remove();
		}
	}
}
function buildOptionBasedOnAjaxReturn(select, link, nextstep){
	ajax_call_get(link, false, function(data){
		if(!validateTextWithOutMessage(data)){
			addOption(data, select);
			if(nextstep!=undefined){
				nextstep.apply(null, []);
			}
		}
	});
}
function addOption(data, select){
	var optionstr='';
	var existedOption = select.children('option');
	var existedLength = existedOption.length;
	var jsondata = JSON.parse(data);
	var add;
	for(var i=0;i<jsondata.length;i++){
		add = true;
		for(var n=0;n<existedLength;n++){
			if(existedOption.eq(n).val()==jsondata[i][0]){
				existedOption.eq(n).html(jsondata[i][1]);
				add = false;
				break;
			}
		}
		if(add){
			optionstr += '<option value="'+jsondata[i][0]+'">'+jsondata[i][1]+'</option>';
		}
	}
	if(optionstr!=''){
		select.append(optionstr);
	}
}
function updatePreviousListRow(index, value){
	var lastrow = divs[divs.length-1][0];
	if(lastrow != undefined ){
		var tdlist = lastrow.children('td');
		if(tdlist.length>index+1){
			tdlist.eq(index).html(value);
		}
	}
}
String.prototype.format=function(json)
{
  if(json==undefined) return this;
  	return this.replace(/\{\w*(\.\w*)*\}/g, function (w) { 
  	  r = w.substr(1,w.length-2);//去除{}
	  var strs=r.split(".");
	  var val=json;
	  for(var i=0;i<strs.length;i++)
		  val=val[strs[i]];
	  return (val===0)?0:(val ? val : "");//o[r]===0这句是为了实现当值为0时输出0而不是空。
	  //return (json[r]===0)?0:(json[r] ? json[r] : "");//o[r]===0这句是为了实现当值为0时输出0而不是空。 
	}); 
};
function buildpageindex(result){
	var totalcount=result.totalCount;
	var totalpage=result.totalPage;
	var currentpage=result.currentPage;
	var range = 3;
	var pages=[];
	if(totalcount==0){
		//nothing to do;
	}else{
		if(currentpage-range>1){
			pages.push([1, true]);
		}
		var startpage = currentpage-range<1?1:currentpage-range;
		var lastpage = currentpage+range>totalpage?totalpage:currentpage+range;
		for(var i=startpage; i<=lastpage;i++){
			if(i==currentpage){
				pages.push([i, false]);
			}else{
				pages.push([i, true]);
			}
		}
		if(currentpage+range<totalpage){
			pages.push([totalpage, true]);
		}
	}
	var outputcontent='';
	var lastindex=0;
	if(pages.length==0){
		outputcontent="找不到相关记录";
	}else{
		for(var i=0;i<pages.length;i++){
			var page=pages[i];
			if(page[0]-lastindex>1){
				outputcontent+="...&nbsp;&nbsp;";
			}
			if(page[1]){
				outputcontent+="<a class=navlink>"+page[0]+"</a>&nbsp;&nbsp;";
			}else{
				outputcontent+="<span class='navtext navcurrent'>"+page[0]+"</span>&nbsp;&nbsp;";
			}
			lastindex=page[0];
		}
	}
	return outputcontent;
}