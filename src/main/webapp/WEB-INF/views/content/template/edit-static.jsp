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
		<script type="text/javascript">
			var rootPath = '/content/template';
			change_iframe_height(660);
			//返回
			function back_list(){
				location.href = global_ctxPath + rootPath + '/list';//返回到消息模板列表页面
			}
			
			$(function(){
				var type = $("#msg_type").val();//消息模板的消息类型
				$("body").layout("panel","center").panel('refresh', global_ctxPath + rootPath + '/content/' + type);//将分页面引入
			});
		</script>
	</head>
	<body class="easyui-layout">
		<div data-options="region:'north'" style="min-width:640px;height:45px;" class="info">
			<table width="100%" style="margin:5px 0px;">
				<tr>
					<td width="60px"><a onclick="back_list()" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-back'"></a></td>
					<td width="80px">模板名称：<input id="msg_type" type="hidden" value="${template.type }"/></td>
					<td width="250px"><input id="template_templateId" type="hidden" value="${template.templateId }" /><input id="template_name" value="${template.name }" style="width: 210px" type="text" /></td>
					<%-- 保存按钮的对应js事件在各个分页面里，由各自页面处理保存逻辑 --%>
					<td align="left"><input type="hidden" id="template_multi" value="${multi }" /><a onclick="edit_message_template()" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a></td>
				</tr>
			</table>
		</div>
		<div data-options="region:'center',cache:false,loadingMessage:'刷新中...'"></div>
	</body>
</html>