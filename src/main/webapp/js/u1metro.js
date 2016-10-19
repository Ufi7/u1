var metroloadingcontent = "<img src='img/u1/loading.gif' style='margin-left:400px;margin-top:150px;'/>";
var idsampleprefix = 'u7metrostyle';
function getMetroRandomSedd(){
	var ran = Math.random();
	return ran*999999 - ran*999999%1;
}
var sizetypes=[[1,1],[1,2],[2,2],[2,4]];
			var sizeaClass=['metroaa','metroba','metroca','metroda'];
			var colors=['#A4C400','#60A917','#008A00','#00ABA9','#1BA1E2','#0050EF','#6A00FF','#AA00FF','#F472D0','#D80073','#A20025','#E51400','#FA6800','#F0A30A','#E3C800','#825A2C','#6D8764','#647687','#76608A','#87794E'
];
//			var ads=[['a','1'],['b','2'],['c','3'],['d','4'],['e','5'],['f','6'],['g','7'],['h','8'],['i','9'],['j','10'],['k','11'],['l','12'],['m','13'],['n','14'],['o','15'],['p','16'],['q','17'],['r','18'],['s','19'],['t','20']];
			//var ads=[['a','1'],['b','2'],['c','3'],['d','4'],['e','5'],['f','6'],['g','7'],['h','8'],['i','9'],['j','10'],['k','11'],['l','12'],['m','13'],['n','14'],['o','15']];
			//var ads=[['a','1'],['b','2'],['c','3'],['d','4'],['e','5'],['f','6'],['g','7'],['h','8'],['i','9'],['j','10']];
			//var ads=[['a','1'],['b','2'],['c','3'],['d','4'],['e','5'],['f','6'],['g','7'],['h','8']];
//			runderMetro(8,3, ads, 3, 'metroparent', 400, 200, '虚位以待', false, 'u7metrostyle',555);//u7metrostyle
			
			
			function runderMetro(xsize, ysize, ads, sizetypecount, parentTarget, emtplyShowText,emptyShowFlag, randomseed, selfdefineclassstyle){
				var mysizetypes=[];
				var mysizeDivClass=[];
				var xoffset=parentTarget.position().left;
				var yoffset=parentTarget.position().top;
				if(sizetypecount>sizetypes.length){
					sizetypecount=sizetypes.length;
				}else if(sizetypecount<2){
					sizetypecount=2;
				}
				for(var i=0;i<sizetypecount;i++){
					mysizetypes.push(sizetypes[sizetypecount-1-i]);
					mysizeDivClass.push(sizeaClass[sizetypecount-1-i]);
				}
				var metroSizeOfCount = calMetroZie(xsize,ysize,ads,mysizetypes);
				var startPositions = calStartPositions(ads, metroSizeOfCount, xsize, ysize, mysizetypes);
				var totalindex=0;
				var ahtml;
				var alink;
				var as=[];
				var cellxoffset;
				var cellyoffset;
				var color;
				var ainfos=[];
				var cellXsize=150;
				var cellYsize=50;
				var cellmargin = 2;
				var aids=[];
				for(var i=0;i<metroSizeOfCount.length;i++){
					for(var n=0;n<metroSizeOfCount[i] && (emptyShowFlag || totalindex<ads.length);n++){
						var aid=idsampleprefix+'_invalid'+totalindex;
						if(totalindex<ads.length){
							aid=idsampleprefix+randomseed+ads[totalindex][1];
						}
						aids.push(aid);
						ahtml=emtplyShowText;
						alink='javascript:void();';
						if(totalindex<ads.length){
							ahtml=ads[totalindex][0];
						}
						var str="<a id='"+ aid +"' class='"+ mysizeDivClass[i]+" u7metrostyle "+ selfdefineclassstyle +"' style='top:"+yoffset+"px;left:"+ xoffset + "px;'>"+ahtml+"</a>";
						as.push(str);
						cellxoffset=0;
						cellyoffset=0;
						if(startPositions[totalindex][0]>0){
							cellxoffset=startPositions[totalindex][0]*cellXsize+startPositions[totalindex][0]*cellmargin;
						}
						if(startPositions[totalindex][1]>0){
							cellyoffset=startPositions[totalindex][1]*cellYsize+startPositions[totalindex][1]*cellmargin;
						}
						ainfos.push([cellxoffset, cellyoffset, ranColor()]);
						totalindex++;
					}
				}
				for(var i=0;i<as.length;i++){
					parentTarget.prepend(as[i]);
					$("#"+aids[i]).animate({top:+ainfos[i][1]+'px',left:+ainfos[i][0]+'px', 'background-color':ainfos[i][2], display:'yes'}, 1000);
				}
			}
			
			function calMetroZie(xunit, yunit, ads, sizetypes){
				var cellscount = xunit*yunit;
				var sizeCount;
				if(ads.length>=cellscount){//all use 1*1 and ignore the last ads if ads more than cells
					sizeCount=[];
					for(var i=0;i<sizetypes.length-1;i++){
						sizeCount.push(0);
					}
					sizeCount.push(cellscount);
				}else{//calculate
					sizeCount = getSizeCount(xunit,yunit,sizetypes);
					var calSize = getCalCount(sizeCount);
					if(calSize < ads.length){//
						var cellOfSize = []
						for(var i=0;i<sizetypes.length;i++){
							cellOfSize.push(sizetypes[i][0]* sizetypes[i][1]);
						}
						sizeCount = collapseCells(sizeCount, cellOfSize, ads.length, 0, 1);
					}
				}
				return sizeCount;
			}
			
			function calStartPositions(ads, sizeCount, xcount, ycount,sizetypes ){
				var subCells=[];
				for(var i=0;i<sizeCount.length;i++){
					for(var n=0;n<sizeCount[i];n++){
						subCells.push(sizetypes[i]);
					}
				}
				var calFailFlag=true;
				while(calFailFlag){
					calFailFlag=false;
					var spaces = [];
					for(var y0=0;y0<ycount;y0++){
						var temp=[];
						for(var x0=0;x0<xcount;x0++){
							temp.push('');
						}
						spaces.push(temp);
					}
					var positions = [];
					for(var adc=0;adc<subCells.length;adc++){
						var ad=ads[adc];
						var size=subCells[adc];
						var allowspaces=[];
						for(var x=0;x<xcount-size[0]+1;x++){
							for(var y=0;y<ycount-size[1]+1;y++){
								var allowed=true;
								for(var xindex=x;xindex<x+size[0];xindex++){
									for(var yindex=y;yindex<y+size[1];yindex++){
										if(spaces[yindex][xindex]!=''){
											allowed=false;
											break;
										}
									}
									if(!allowed){
										break;
									}
								}
							if(allowed){
									allowspaces.push([x,y]);
								}
							}
						}
						if(allowspaces.length==0){
							alert('fail......reseting.....');
							alert(subCells[adc]);
							alert(spaces);
							calFailFlag=true;
							break;
						}
						var ran = Math.random();
						var startpoint = allowspaces[ran*allowspaces.length-ran*allowspaces.length%1];
						positions.push(startpoint);
						for(var xx=startpoint[0];xx<startpoint[0]+size[0];xx++){
							for(var yy=startpoint[1];yy<startpoint[1]+size[1];yy++){
								spaces[yy][xx]='1';
							}
						}
					}
				}
				return positions;
			}
			
			function collapseCells(sizeCount, cellOfSize, targetCount, depth, allowedCount){
				if(depth>=sizeCount.length-1){
					//reset
					return collapseCells(sizeCount, cellOfSize,targetCount, 0, 1);
				}else{
					var sizeOffset = cellOfSize[depth]/cellOfSize[depth+1];
					//alert("depth="+depth+" allowedCount="+allowedCount+" sizeOffset="+sizeOffset);
					if(sizeCount[depth]>0){
						for(var i=0;i<allowedCount;i++){
							sizeCount[depth]-=1;
							sizeCount[depth+1]+=sizeOffset;
							if(getCalCount(sizeCount)==targetCount){
								return sizeCount;
							}else{
								continue;
							}
						}
					}
					return collapseCells(sizeCount, cellOfSize, targetCount, depth+1, allowedCount*sizeOffset);
				}
			}
			
			function getCalCount(sizeCount){
				var calSize=0;
				for(var i=0;i<sizeCount.length;i++){
					calSize += sizeCount[i];
				}
				return calSize;
			}
			
			function getSizeCount(xunit, yunit, sizetypes){
				var sizeCount = [];
				var usedcells=0;
				for(var i=0;i<sizetypes.length;i++){
					var count = getMaxCountByUnit(xunit,yunit,sizetypes[i][0],sizetypes[i][1],usedcells);
					if(count<0){
						count=0;
					}
					sizeCount.push(count);
					usedcells += count*sizetypes[i][0]*sizetypes[i][1];
				}
				//alert();
				return sizeCount;
			}
			
			function getMaxCountByUnit(xunit, yunit, sizex, sizey, usedcell){
				var x0=xunit
				var x1=sizex;
				if(x1>1){
					x0=xunit+sizex-1;
					x1=sizex*2-1;
				}
				var y0=yunit;
				var y1=sizey;
				if(y1>1){
					y0=yunit+sizey-1
					y1=sizey*2-1;
				}
				return (x0/x1-x0/x1%1)*(y0/y1-y0/y1%1)-usedcell/(sizex*sizey);
			}
			function ranColor(){
				var r = Math.random();
				return colors[r*colors.length-r*colors.length%1];
			}