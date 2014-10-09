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
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dict.js"></script>
		<script type="text/javascript">
			var rootPath = '/content/autoreply';
			change_iframe_height(650);//更改当前iframe高度为650
			
			$(function() {
				//获取消息的触发类型
				$.dictList({
					dictCode : "MESSAGE_TIGGER_TYPE",
					success : function(data){
						if(data){
							var count = data.length;
							if(count > 0){
								for(var i = 0; i<count; i++){
									var dict = data[i];
									add_tab_panel(dict.itemDesc,dict.itemCode);//添加的tab控件中
								}
								$('#autoreply_tabs').tabs("select", 0);//默认选中第一个tab
							}
						}
					}
				});
			});
			//添加tab页签
			function add_tab_panel(title, code){
				$('#autoreply_tabs').tabs('add', {
					id : code,
					title : title,
					selected: false
				});
			}
			
			function event_change(title, index) {
				var tab = $('#autoreply_tabs').tabs('getSelected');//获取选中的tab
				var code = tab.panel('options').id;
				var url = global_ctxPath + rootPath + '/content/' + code;
				$("#a_body").layout('panel', 'center').panel("refresh", url);
			}
		</script>
	</head>
	<body class="easyui-layout" id="a_body">
		<div data-options="region:'west',border:true" style="width:149px;overflow: hidden;">
			<div id="autoreply_tabs" style="width:149px;" class="easyui-tabs" data-options="tabPosition:'left',border:false,onSelect:event_change"></div>
		</div>
	    <div data-options="region:'center'"></div>
	</body>
</html>