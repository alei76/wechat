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
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui/portal.css"/>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.portal.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/highcharts.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			var rootPath = '/stat/user';
			change_iframe_height(650);
			$(function () {
			    $("#user_summary_tab").tabs({
			    	onSelect : function(title, index){
						if(index == 1){
							sex_summary();
							lang_summary();
						} else {
							trend_summary(0);
						}
					}
			    });
			    
			    $("#trend_summary_tab").tabs({
			    	onSelect : function (title, index){
						trend_summary(index);
					}
			    });
			    
			    trend_summary(0);
			    
			    init_last_data();
			    
			    var portal = $('#portal');
			    portal.portal({
					border:false,
					fit: true
				});
				var panels = portal.portal('getPanels');
				$.each(panels, function(i, j) {
					portal.portal('disableDragging', j);
				});
			});

			function init_last_data() {
				var countItem = [ "lastsub", "lastunsub", "lastpure", "lasttotal" ];
				$.each(countItem, function(i, path) {
					$.ajax({
						type : "post",
						url : global_ctxPath + rootPath + '/' + path,
						async : true,
						dataType : "json",
						success : function(result) {
							var dl = $("#" + path + "_collect").children("dd");
							dl.eq(0).css("font-size","30px").html(result.COUNT);
						}
					});
				});
			}

			function search_summary() {
				if(validate_date()){
					var tab = $('#trend_summary_tab').tabs('getSelected');
					var index = $('#trend_summary_tab').tabs('getTabIndex', tab);
					var startDay = $("#startDay").val();
					var endDay = $("#endDay").val();
					$("#temp_startDay").val(startDay);
					$("#temp_endDay").val(endDay);
					trend_summary(index);
				}
			}
			
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

			//新增关注统计
			function trend_summary(type) {
				var startDay = $("#temp_startDay").val();
				var endDay = $("#temp_endDay").val();
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/trendsummary/' + type,
					data : {
						"startDay" : startDay,
						"endDay" : endDay
					},
					async : true,
					dataType : "json",
					success : function(result) {
						if (result.success) {
							$('#trend_summary').highcharts({
								chart : {
									type : 'area'
								},
								title : {
									text : null
								},
								subtitle : {
									text : null
								},
								xAxis : {
									categories : $.evalJSON(result.date),
									tickInterval : 2
								},
								yAxis : {
									title : {
										text : null
									},
									labels : {
										formatter : function() {
											return this.value;
										}
									}
								},
								tooltip : {
									formatter : function() {
										return this.x + '<br>' + this.series.name + ' ' + this.y;
									}
								},
								plotOptions : {
									area : {
										fillOpacity : 0.5
									}
								},
								credits : {
									enabled : false
								},
								series : [ {
									name : result.title,
									data : $.evalJSON(result.count)
								} ]
							});
						} else {
							$('#trend_summary').html("<div class=\"no_records\">暂无数据</div>");
						}
					}
				});
			}

			//性别统计
			function sex_summary() {
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/sexsummary',
					async : true,
					dataType : "json",
					success : function(result) {
						$('#sex_summary').highcharts({
							exporting : {
								enabled : false
							},
							credits : {
								enabled : false
							},
							chart : {
								type : 'bar'
							},
							title : {
								text : '性别分布图'
							},
							xAxis : {
								categories : [ result.tips ]
							},
							yAxis : {
								min : 0,
								title : {
									text : null
								}
							},
							legend : {
								backgroundColor : '#FFFFFF',
								reversed : true
							},
							plotOptions : {
								series : {
									stacking : 'normal'
								}
							},
							series : result.series
						});
					}
				});
			}

			//语言统计
			function lang_summary() {
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/langsummary',
					async : true,
					dataType : "json",
					success : function(result) {
						$('#lang_summary').highcharts({
							exporting : {
								enabled : false
							},
							credits : {
								enabled : false
							},
							chart : {
								type : 'bar'
							},
							title : {
								text : '语言分布图'
							},
							xAxis : {
								categories : [ result.tips ]
							},
							yAxis : {
								min : 0,
								title : {
									text : null
								}
							},
							legend : {
								backgroundColor : '#FFFFFF',
								reversed : true
							},
							plotOptions : {
								series : {
									stacking : 'normal'
								}
							},
							series : result.series
						});
					}
				});
			}
		</script>
		
		<style type="text/css">
			.ui_trendgrid_item {
			    overflow: hidden;
			    text-align: center;
			}
			.ui_trendgrid_number {
				font-size: 30px;
			    height: 24px;
			    position: relative;
			    margin: 5px 0px;
			}
		</style>
	</head>
	<body>
		<div id="user_summary_tab" class="easyui-tabs" data-options="fit:true">
			<div title="用户增长">
			    <div class="easyui-layout" data-options="fit:true">
				    <div data-options="region:'north',border:false" style="height:160px;">
				    	<div id="portal" style="position:relative;height:160px;overflow: hidden;" >
							<div style="width:25%;">
								<div class="ui_trendgrid_item" title="昨日新关注人数" collapsible="false" closable="false" border="false">
									<dl id="lastsub_collect">
										<dd style="font-size:12px;" class="ui_trendgrid_number">无数据或计算中...</dd>
										<dd>日&nbsp;&nbsp;&nbsp;--</dd>
										<dd>周&nbsp;&nbsp;&nbsp;--</dd>
										<dd>月&nbsp;&nbsp;&nbsp;--</dd>
									</dl>
								</div>
							</div>
							<div style="width:25%;">
								<div class="ui_trendgrid_item" title="昨日取消关注人数" collapsible="false" closable="false" border="false">
									<dl id="lastunsub_collect">
										<dd style="font-size:12px;" class="ui_trendgrid_number">无数据或计算中...</dd>
										<dd>日&nbsp;&nbsp;&nbsp;--</dd>
										<dd>周&nbsp;&nbsp;&nbsp;--</dd>
										<dd>月&nbsp;&nbsp;&nbsp;--</dd>
									</dl>
								</div>
							</div>
							<div style="width:25%;">
								<div class="ui_trendgrid_item" title="昨日净增关注人数" collapsible="false" closable="false" border="false">
									<dl id="lastpure_collect">
										<dd style="font-size:12px;" class="ui_trendgrid_number">无数据或计算中...</dd>
										<dd>日&nbsp;&nbsp;&nbsp;--</dd>
										<dd>周&nbsp;&nbsp;&nbsp;--</dd>
										<dd>月&nbsp;&nbsp;&nbsp;--</dd>
									</dl>
								</div>
							</div>
							<div style="width:25%;">
								<div class="ui_trendgrid_item" title="昨日累积关注人数" collapsible="false" closable="false" border="false">
									<dl id="lasttotal_collect">
										<dd style="font-size:12px;" class="ui_trendgrid_number">无数据或计算中...</dd>
										<dd>日&nbsp;&nbsp;&nbsp;--</dd>
										<dd>周&nbsp;&nbsp;&nbsp;--</dd>
										<dd>月&nbsp;&nbsp;&nbsp;--</dd>
									</dl>
								</div>
							</div>
						</div>
				    </div>
				    <div data-options="region:'center',border:false">
				    	<div id="day_query" style="border:0px;padding-left:20px;">统计时间：<input class="Wdate" style="width:100px" value="${covisint:day(-31) }" id="startDay" type="text" onfocus="WdatePicker({maxDate:'%y-%M-{%d-1}',readOnly:true})"/>&nbsp;--&nbsp;<input id="endDay" style="width:100px" value="${covisint:day(-1) }" class="Wdate" type="text" onfocus="WdatePicker({maxDate:'%y-%M-{%d-1}',readOnly:true})"/>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="search_summary()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></div>
						<input id="temp_startDay" value="${covisint:day(-31) }" type="hidden"/>
						<input id="temp_endDay" value="${covisint:day(-1) }" type="hidden"/>
						<div id="trend_summary_tab" class="easyui-tabs" style="height: 30px;" data-options="toolPosition:'left',border:false,tools:'#day_query'">
							<div title="新增人数"></div>
							<div title="取消关注人数"></div>
							<div title="净增人数"></div>
							<div title="累计人数"></div>
						</div>
						<div id="trend_summary" style="min-width:700px;height:300px"></div>
				    </div>
			    </div>
			</div>
			<div title="用户属性">
				<div id="sex_summary" style="min-width:300px;height:200px"></div>
				<div id="lang_summary" style="min-width:300px;height:200px"></div>
			</div>
		</div>
	</body>
</html>