<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- 图文消息模板分页面 --%>
<%-- 添加时addstatic.jsp为总页面，编辑时edit-static.jsp为总页面 --%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/wechat/wechat.css" />

<script type="text/javascript">
	change_iframe_height(650);
	
	$(function(){
		var templateId = $("#template_templateId").val();
		if(templateId != ''){//有ID，表示是编辑
			var multi = $("#template_multi").val();//获取是单图文，还是多图文
			$("body").layout("panel","center").panel('refresh', global_ctxPath  + "/content/newsmessage/create/" + multi);//直接跳转至相应页面
		}
	});

	function appmsg_create(type){
		var url = global_ctxPath  + "/content/newsmessage/create/" + type;
		$("#t_body").layout('panel', 'center').panel("refresh", url);//将当前页面跳转到创建图文消息页面
	}
</script>
<input type="hidden" id="type" value="${type }" />
<div class="appmsg_list">
	<div class="appmsg_col">
		<span class="create_access">
		<i class="icon42_common add_gray"></i>
		<a href="javascript:void(0)" onclick="appmsg_create('single')">
			<i class="icon_appmsg_create"></i><strong>单图文消息</strong>
		</a>
		<a href="javascript:void(0)" onclick="appmsg_create('multi')">
			<i class="icon_appmsg_create multi"></i><strong>多图文消息</strong>
		</a>
		</span>
	</div>
</div>