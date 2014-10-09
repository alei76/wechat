<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- 语音消息模板分页面 --%>
<%-- 添加时addstatic.jsp为总页面，编辑时edit-static.jsp为总页面 --%>
<script type="text/javascript">
	change_iframe_height(650);
	function init(){
		var templateId = $("#template_templateId").val();
		if(templateId != ''){
			//加载原始数据
			$.ajax({
				type : "post",
				url : global_ctxPath + rootPath + '/info/' + templateId,
				async : true,
				dataType : "json",
				success : function(result) {
					$("#resource_" + result.attaId).prop("checked", true);
				}
			});
		}
	}
	//保存模板
	function edit_message_template(){
		save_message_template();
	}
	//保存模板
	function save_message_template(){
		var attaId = $("input[name='resource']:checked").val();
		if(attaId !== undefined){
			var name = $("#template_name").val();
			var type = $("#type").val();
			var content = $("#content").val();
			var templateId = $("#template_templateId").val();
			var template = new Object();
			template.name = name;
			template.content = content;
			template.type = type;
			template.attaId = attaId;
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
	}
	
	function init_pagination(){
		var total = $("#resouces_total").val();//总页数（在分页面中）
		var pageSize = $("#resouces_pagesize").val();//每页显示条数（在分页面中）
		$('#resource_pagination').pagination({
		    total:total,
		    pageSize:pageSize,
		    pageList: [12,24,30],
		    displayMsg : '当前显示从{from}到{to}共{total}记录',
		    beforePageText : '第',
			afterPageText : '页 共 {pages} 页',
			onBeforeRefresh : function(pageNumber, pageSize) {
				$(this).pagination('loading');
				$("#resource_layout").layout('panel', 'center').panel("refresh");
				$(this).pagination('loaded');
			},
			onSelectPage:function(pageNumber, pageSize){
				$(this).pagination('loading');
				var main_panel = $("#resource_layout").layout('panel', 'center');
				var grid_href = global_ctxPath + rootPath + "/attagrid/02?rows=" + pageSize + "&page=" + pageNumber;
				main_panel.panel("refresh", grid_href);
				$(this).pagination('loaded');
			}
		});
		init();
	}
</script>
<input type="hidden" id="type" value="${type }" />
<div class="easyui-layout" data-options="fit:true" id="resource_layout">
    <div data-options="region:'south',border:false" style="height:36px;overflow: hidden;">
    	<div id="resource_pagination" style="border-top:1px solid #ccc;margin-bottom:6px;"></div>
    </div>
    <%-- 页面加载完成触发init_pagination事件，设置分页标签组件 --%>
    <%-- /attagridd/02 中02表示音频 --%>
    <div data-options="onLoad:init_pagination,region:'center',border:false,title:'所有音频',cache:false,href:'${ pageContext.request.contextPath}/content/template/attagrid/02?rows=12&page=1'"></div>
</div>