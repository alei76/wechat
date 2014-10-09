<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Welcome</title>
		<%@ include file="/common/resource.jsp"%>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/common/jquery.nouislider.css" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/highcharts.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.nouislider.min.js"></script>
		<script type="text/javascript">
			var rootPath = '/stat/link';
			change_iframe_height(720);
			$(function () {
				
				$("#link_summary_tab").tabs({
			    	onSelect : function(title, index){
						if(index == 0){
							day_summary();
							day_summary_grid();
						} else {
							time_summary();
							time_summary_grid();
						}
					}
			    });
				
				day_summary();
				day_summary_grid();
				
				var slider = $("#time_range");
				slider.noUiSlider({
					start: [0, 23],
					connect : true,
					step: 1,
					range: {
						'min': 0,
						'max': 23
					},
					serialization: {
						lower: [
							$.Link({
								target: $("#start_time"),
								format: {
									decimals: 0
								}
							})
						],
						upper:[
							$.Link({
								target: $("#end_time"),
								format: {
									decimals: 0
								}
							})
						]
					}
				});
				
				$("#start_time").keydown(function( e ) {
					var value = slider.val();
					var start = value[0];
					var end = value[1];
					var target = Number( start );
					switch ( e.which ) {
						case 38: slider.val([target + 1, end]);
							break;
						case 40: slider.val([target - 1, end]);
							break;
					}
					e.preventDefault();
				});
				
				$("#end_time").keydown(function( e ) {
					var value = slider.val();
					var start = value[0];
					var end = value[1];
					var target = Number( end );
					switch ( e.which ) {
						case 38: slider.val([start, target + 1]);
							break;
						case 40: slider.val([start, target - 1]);
							break;
					}
					e.preventDefault();
				});
			});
			
			function validate_date(){
				var startDay = $("#startDay").val();
				if(startDay == ""){
					$.messager.alert('提示', "请选择开始日期", 'info');
					return false;
				}
				var endDay = $("#endDay").val();
				if(endDay == ""){
					$.messager.alert('提示', "请选择结束日期", 'info');
					return false;
				}
				var diff = checkEndTime(startDay, endDay);
				if(diff < 0){
					$.messager.alert('提示', "结束日期不能早于开始日期", 'info');
					return false;
				}
				return true;
			}
			
			function day_search_summary(){
				if(validate_date()){
					day_summary();
					var startDay = $("#startDay").val();
					var endDay = $("#endDay").val();
					$('#day_summary_grid').datagrid('load',{
						"startDay" : startDay,
						"endDay" : endDay
					});
				}
			}
			
			function time_search_summary(){
				time_summary();
				var startTime = $("#start_time").val();
				var endTime = $("#end_time").val();
				$('#time_summary_grid').datagrid('load',{
					"startTime" : startTime,
					"endTime" : endTime
				});
			}
			
			function time_summary_grid(){
				var startTime = $("#start_time").val();
				var endTime = $("#end_time").val();
				$('#time_summary_grid').datagrid( {
					url : global_ctxPath + rootPath + '/timegrid',
					view : datagrid_view,
					method : 'post',
					emptyMsg : '没有记录',
					fit : false,
					height : 365,
					border : false,
					fitColumns : true,
					singleSelect : true,
					pagination : true,
					rownumbers : true,
					showHeader : true,
					pageSize : 10,
					remoteSort : true,
					idField : 'MESSAGE_HIS_ID',
					loadMsg : '数据装载中......',
					queryParams : {
						"startTime" : startTime,
						"endTime" : endTime
					},
					columns : [ [ {
						field : 'VISIT_TIME',
						title : '访问时段',
						width : 15,
						align : 'center',
						sortable : true,
						order : 'asc'
					} , {
						field : 'VIEW_NAME',
						title : '页面名称',
						width : 15,
						align : 'center'
					} , {
						field : 'VIEW_HREF',
						title : '访问地址',
						width : 60,
						align : 'center'
					} , {
						field : 'CLICK',
						title : '访问次数',
						width : 10,
						align : 'center',
						sortable : true,
						order : 'asc'
					} , {
						field : 'MEMBER',
						title : '访问人数',
						width : 10,
						align : 'center',
						sortable : true,
						order : 'asc'
					} ] ],
					toolbar : '#time_query'
				});
				
				$('#time_summary_grid').datagrid('getPager').pagination({
					displayMsg : '当前显示从{from}到{to}共{total}记录',
					beforePageText : '第',
					showPageList : false,
					afterPageText : '页 共 {pages} 页',
					onBeforeRefresh : function(pageNumber, pageSize) {
						$(this).pagination('loading');
						$(this).pagination('loaded');
					}
				});
				
				$('#time_summary_grid').datagrid('sort', {
					sortName: 'VISIT_TIME',
					sortOrder: 'desc'
				});
			}
			
			function time_summary(){
				var startTime = $("#start_time").val();
				var endTime = $("#end_time").val();
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/timesummary',
					async : true,
					dataType : "json",
					data : {
						"startTime" : startTime,
						"endTime" : endTime
					},
					success : function(result) {
						$('#time_summary').highcharts({
							chart: {
								type: 'column'
							},
							title: {
								text: null
							},
							subtitle: {
								text: null
							},
							xAxis: {
								categories: result.time,
								title : {
									text : null
								}
							},
							credits: {
								enabled : false
							},
							yAxis: {
								min: 0,
								title: {
									text: null
								}
							},
							legend: {
								layout: 'vertical',
				                align: 'left',
				                verticalAlign: 'top',
				                x: 50,
				                y: 10,
				                floating: true,
				                borderWidth: 1,
				                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
							},
							tooltip: {
								headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
				                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' + '<td style="padding:0"><b>{point.y}</b></td></tr>',
				                footerFormat: '</table>',
				                shared: true,
				                useHTML: true
							},
							series : [{
								name : '访问次数',
								data : result.click,
								dataLabels: {
				                    enabled: true,
				                    rotation: -90,
				                    color: '#FFFFFF',
				                    align: 'right',
				                    x: 4,
				                    y: 10,
				                    style: {
				                        fontSize: '13px',
				                        fontFamily: 'Verdana, sans-serif',
				                        textShadow: '0 0 3px black'
				                    }
				                }
							}, {
								name : '访问人数',
								data : result.member,
								dataLabels: {
				                    enabled: true,
				                    rotation: -90,
				                    color: '#FFFFFF',
				                    align: 'right',
				                    x: 4,
				                    y: 10,
				                    style: {
				                        fontSize: '13px',
				                        fontFamily: 'Verdana, sans-serif',
				                        textShadow: '0 0 3px black'
				                    }
				                }
							}]
						});
					}
				});
			}
			
			function day_summary_grid(){
				var startDay = $("#startDay").val();
				var endDay = $("#endDay").val();
				$('#day_summary_grid').datagrid( {
					url : global_ctxPath + rootPath + '/daygrid',
					view : datagrid_view,
					method : 'post',
					emptyMsg : '没有记录',
					fit : false,
					height : 350,
					border : false,
					fitColumns : true,
					singleSelect : true,
					pagination : true,
					rownumbers : true,
					showHeader : true,
					pageSize : 10,
					remoteSort : true,
					idField : 'MESSAGE_HIS_ID',
					loadMsg : '数据装载中......',
					queryParams : {
						"startDay" : startDay,
						"endDay" : endDay
					},
					columns : [ [ {
						field : 'VISIT_DATE',
						title : '访问日期',
						width : 15,
						align : 'center',
						sortable : true,
						order : 'asc'
					} , {
						field : 'VIEW_NAME',
						title : '页面名称',
						width : 15,
						align : 'center'
					} , {
						field : 'VIEW_HREF',
						title : '访问地址',
						width : 60,
						align : 'center'
					} , {
						field : 'CLICK',
						title : '访问次数',
						width : 10,
						align : 'center',
						sortable : true,
						order : 'asc'
					} , {
						field : 'MEMBER',
						title : '访问人数',
						width : 10,
						align : 'center',
						sortable : true,
						order : 'asc'
					} ] ],
					toolbar : '#day_query'
				});
				
				$('#day_summary_grid').datagrid('getPager').pagination({
					displayMsg : '当前显示从{from}到{to}共{total}记录',
					beforePageText : '第',
					showPageList : false,
					afterPageText : '页 共 {pages} 页',
					onBeforeRefresh : function(pageNumber, pageSize) {
						$(this).pagination('loading');
						$(this).pagination('loaded');
					}
				});
				
				$('#day_summary_grid').datagrid('sort', {
					sortName: 'VISIT_DATE',
					sortOrder: 'desc'
				});
			}
			
			function day_summary(){
				var startDay = $("#startDay").val();
				var endDay = $("#endDay").val();
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/daysummary',
					async : true,
					dataType : "json",
					data : {
						"startDay" : startDay,
						"endDay" : endDay
					},
					success : function(result) {
						$('#day_summary').highcharts({
							chart: {
								type: 'column'
							},
							title: {
								text: null
							},
							subtitle: {
								text: null
							},
							xAxis: {
								categories: result.day,
								title : {
									text : null
								}
							},
							credits: {
								enabled : false
							},
							yAxis: {
								min: 0,
								title: {
									text: null
								}
							},
							legend: {
								layout: 'vertical',
				                align: 'left',
				                verticalAlign: 'top',
				                x: 50,
				                y: 10,
				                floating: true,
				                borderWidth: 1,
				                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
							},
							tooltip: {
								headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
				                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' + '<td style="padding:0"><b>{point.y}</b></td></tr>',
				                footerFormat: '</table>',
				                shared: true,
				                useHTML: true
							},
							series : [{
								name : '访问次数',
								data : result.click,
								dataLabels: {
				                    enabled: true,
				                    rotation: -90,
				                    color: '#FFFFFF',
				                    align: 'right',
				                    x: 4,
				                    y: 10,
				                    style: {
				                        fontSize: '13px',
				                        fontFamily: 'Verdana, sans-serif',
				                        textShadow: '0 0 3px black'
				                    }
				                }
							}, {
								name : '访问人数',
								data : result.member,
								dataLabels: {
				                    enabled: true,
				                    rotation: -90,
				                    color: '#FFFFFF',
				                    align: 'right',
				                    x: 4,
				                    y: 10,
				                    style: {
				                        fontSize: '13px',
				                        fontFamily: 'Verdana, sans-serif',
				                        textShadow: '0 0 3px black'
				                    }
				                }
							}]
						});
					}
				});
			}
		</script>
	</head>
	<body>
		<div id="link_summary_tab" class="easyui-tabs" data-options="fit:true,border:true">
			<div title="时期统计">
		    	<div id="day_query">
		    		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		    			<tr>
		    				<td width="100px">统计时间：</td>
		    				<td width="110px"><input class="Wdate" style="width:100px" value="${covisint:day(-31) }" id="startDay" type="text" onfocus="WdatePicker({maxDate:'%y-%M-{%d-1}',readOnly:true})"/></td>
		    				<td width="20px">--</td>
		    				<td width="110px"><input id="endDay" style="width:100px" value="${covisint:day(-1) }" class="Wdate" type="text" onfocus="WdatePicker({maxDate:'%y-%M-{%d-1}',readOnly:true})"/></td>
		    				<td><a href="javascript:void(0);" onclick="day_search_summary()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
		    			</tr>
		    		</table>
		    	</div>
				<table id="day_summary_grid"></table>
				<div id="day_summary" style="min-width: 500px; height: 320px;"></div>
			</div>
			<div title="时段统计">
				<div id="time_query">
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td width="100px">统计时段：</td>
							<td width="30px"><input readonly="readonly" id="start_time" type="number" size="2"/></td>
							<td width="235px"><div id="time_range" style="width:230px;margin:10px 20px;"></div></td>
							<td width="30px"><input readonly="readonly" id="end_time" type="number" size="2"/></td>
							<td><a href="javascript:void(0);" onclick="time_search_summary()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
						</tr>
					</table>
				</div>
				<table id="time_summary_grid"></table>
				<div id="time_summary" style="min-width: 500px; height: 320px;"></div>
			</div>
		</div>
	</body>
</html>