<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- 文本消息模板分页面 --%>
<%-- 添加时addstatic.jsp为总页面，编辑时edit-static.jsp为总页面 --%>
<script type="text/javascript">
	change_iframe_height(650);
	
	$(function() {
		var templateId = $("#template_templateId").val();
		if(templateId != ''){
			//加载原始数据
			$.ajax({
				type : "post",
				url : global_ctxPath + rootPath + '/info/' + templateId,
				async : true,
				dataType : "json",
				success : function(result) {
					$("#content").val(result.content);
				}
			});
		}
	});
	//保存模板
	function edit_message_template(){
		save_message_template();
	}
	//保存模板
	function save_message_template(){
		var name = $("#template_name").val();
		var type = $("#type").val();
		var content = $("#content").val();
		var templateId = $("#template_templateId").val();
		var template = new Object();
		template.name = name;
		template.content = content;
		template.type = type;
		if(templateId != ''){
			template.templateId = templateId;
		}
		$.ajax({
			type : "post",
			url : global_ctxPath + rootPath + '/savetemplate',
			async : true,
			data : template,
			dataType : "json",
			success : function(result) {
				$.messager.alert('提示', result.message, 'info', function(){
					if(result.success){
						back_list();//保存成功，返回到列表页面
					}
				});
			}
		});
	}
</script>
<input type="hidden" id="type" value="${type }" />
<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
	<tr>
		<td width="360px"><textarea id="content" style="width:320px;height:240px;"></textarea></td>
		<td valign="top">
			\${openId }:获取粉丝ID<br/>
			\${keyword }:获取用户输入的关键字
		</td>
	</tr>
</table>