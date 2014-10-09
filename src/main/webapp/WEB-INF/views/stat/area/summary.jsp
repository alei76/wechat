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
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/highcharts.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			var rootPath = '/stat/area';
			change_iframe_height(650);
			$(function () {
				var startDay = $("#startDay").val();
				var endDay = $("#endDay").val();
				
				$('#area_summary_grid').datagrid( {
					url : global_ctxPath + rootPath + '/areagrid',
					view : datagrid_view,
					method : 'post',
					emptyMsg : '没有记录',
					fit : false,
					height : 365,
					border : true,
					fitColumns : true,
					singleSelect : true,
					pagination : true,
					rownumbers : true,
					showHeader : true,
					pageSize : 10,
					remoteSort : true,
					idField : 'PROVINCE',
					loadMsg : '数据装载中......',
					queryParams : {
						"startDay" : startDay,
						"endDay" : endDay
					},
					columns : [ [ {
						field : 'PROVINCE',
						title : '地域',
						width : 25,
						align : 'center'
					} , {
						field : 'CLICK',
						title : '访问次数',
						width : 25,
						align : 'center',
						sortable : true,
						order : 'asc'
					} , {
						field : 'MEMBER',
						title : '访问人数',
						width : 25,
						align : 'center',
						sortable : true,
						order : 'asc'
					} , {
						field : 'ALL_MEMBER',
						title : '访问人数占比',
						width : 25,
						align : 'center',
						formatter : function(value, row, index) {
							var a = Number(row.MEMBER);
							var b = Number(value);
							return (Math.round(a / b * 10000) / 100.00 + "%");
						}
					} ] ],
					toolbar : '#area_query'
				});

				$('#area_summary_grid').datagrid('getPager').pagination({
					displayMsg : '当前显示从{from}到{to}共{total}记录',
					beforePageText : '第',
					showPageList : false,
					afterPageText : '页 共 {pages} 页',
					onBeforeRefresh : function(pageNumber, pageSize) {
						$(this).pagination('loading');
						$(this).pagination('loaded');
					}
				});
			});
			
			function area_search_summary(){
				if(validate_date()){
					var startDay = $("#startDay").val();
					var endDay = $("#endDay").val();
					$('#area_summary_grid').datagrid('load',{
						"startDay" : startDay,
						"endDay" : endDay
					});
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
		</script>
	</head>
	<body>
		<div id="area_query">
    		<table cellpadding="0" cellspacing="0" border="0" width="100%">
    			<tr>
    				<td width="100px">统计时间：</td>
    				<td width="110px"><input class="Wdate" style="width:100px" value="${covisint:day(-31) }" id="startDay" type="text" onfocus="WdatePicker({maxDate:'%y-%M-{%d-1}',readOnly:true})"/></td>
    				<td width="20px">--</td>
    				<td width="110px"><input id="endDay" style="width:100px" value="${covisint:day(-1) }" class="Wdate" type="text" onfocus="WdatePicker({maxDate:'%y-%M-{%d-1}',readOnly:true})"/></td>
    				<td><a href="javascript:void(0);" onclick="area_search_summary()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
    			</tr>
    		</table>
    	</div>
		<table id="area_summary_grid"></table>
	</body>
</html>