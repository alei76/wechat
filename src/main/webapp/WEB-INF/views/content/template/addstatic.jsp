<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- 添加静态消息模板外部总页面 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Welcome</title>
		<%@ include file="/common/resource.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dict.js"></script>
		<style type="text/css">.tabs-panels{display:none;}</style>
		<script type="text/javascript">
			var rootPath = '/content/template';
			
			$(function() {
				//获取消息类型的种类
				$.dictList({
					dictCode : "MESSAGE_TYPE",
					success : function(data){
						if(data){
							var count = data.length;
							if(count > 0){
								for(var i = 0; i<count; i++){//将消息类型循环添加到tab组件中
									var dict = data[i];
									add_tab_panel(dict.itemDesc,dict.itemCode);//添加tab页签
								}
								$('#message_type_tabs').tabs("select",0);//默认选中第一个
							}
						}
					}
				});
			});
			
			//返回
			function back_list(){
				location.href = global_ctxPath + rootPath + '/list';//返回到消息模板列表页面
			}
			//为tab组件添加一个页签
			function add_tab_panel(title, code){
				$('#message_type_tabs').tabs('add', {
					id : code,
					title : title,
					selected: false
				});
			}
			//定义tab组件及点击事件
			function type_change(title, index) {
				var tab = $('#message_type_tabs').tabs('getSelected');//获取选中的tab
				var code = tab.panel('options').id;
				var url = global_ctxPath + rootPath + '/content/' + code;
				$("#t_body").layout('panel', 'center').panel("refresh", url);
			}
		</script>
	</head>
	<body class="easyui-layout" id="t_body">
		<div data-options="region:'north'" style="border-bottom:0px;min-width:640px;height:73px;" class="info">
			<table width="100%" style="margin:5px 0px;">
				<tr>
					<td width="60px"><a onclick="back_list()" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-back'"></a></td>
					<td width="80px">模板名称：</td>
					<td width="250px"><input id="template_templateId" type="hidden" /><input id="template_name" style="width: 210px" type="text" /></td>
					<%-- 保存按钮的对应js事件在各个分页面里，由各自页面处理保存逻辑 --%>
					<td align="left"><a onclick="save_message_template()" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a></td>
				</tr>
			</table>
			<div id="message_type_tabs" style="height:30px;" class="easyui-tabs" data-options="border:false,onSelect:type_change"></div>
		</div>
		<div data-options="region:'center'"></div>
	</body>
</html>