<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- 消息模板列表页面 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Welcome</title>
		<%@ include file="/common/resource.jsp"%>
		<style type="text/css">
			.del_focus{color:#ff0000;}
		</style>
		<script type="text/javascript">
			var rootPath = '/content/template';
			change_iframe_height(650);
			$(function() {
				//定义grid列表
				$('#template_grid').datagrid({
					view : datagrid_view,
					emptyMsg : '没有记录',
					url : global_ctxPath + rootPath + '/grid',
					method : 'post',
					fit : true,
					border : false,
					title : '消息模板列表',
					fitColumns : true,
					singleSelect : true,
					pagination : true,
					rownumbers : true,
					pageSize : 10,
					pageList : [ 10, 20, 30 ],
					remoteSort : false,
					idField : 'templateId',
					loadMsg : '数据装载中......',
					columns : [ [ {
						field : 'name',
						title : '模板名称',
						width : 30,
						align : 'center',
						formatter : function(value, row, index) {
							if(row.resourceType == '01'){
								return "<a href=\"###\" onclick=\"view_dynamic('" + row.templateId + "')\">" + value + "</a>";
							}else{
								return "<a href=\"###\" onclick=\"view_static('" + row.templateId + "')\">" + value + "</a>";
							}
						}
					}, {
						field : 'resourceTypeCn',
						title : '资源类型',
						width : 30,
						align : 'center'
					}, {
						field : 'typeCn',
						title : '消息类型',
						width : 30,
						align : 'center'
					}, {
						field : 'resourceName',
						title : '接口地址',
						width : 30,
						align : 'center',
						formatter : function(value, row, index) {
							if(row.resourceType == '01'){//动态消息
								if(row.resourceStatus == '02'){
									return "<span class=\"del_focus\">" + value + "(已删除)</span>";
								} else {
									return value;
								}
							} else {
								return "";
							}
						}
					}, {
						field : 'status',
						title : '操作',
						width : 30,
						align : 'center',
						formatter : function(value, row, index) {
							return "<a href=\"###\" onclick=\"change_status('" + row.templateId + "')\">删除</a>";
						}
					} ] ]
				});
				//定义grid分页配置
				$('#template_grid').datagrid('getPager').pagination({
					displayMsg : '当前显示从{from}到{to}共{total}记录',
					beforePageText : '第',
					afterPageText : '页 共 {pages} 页',
					onBeforeRefresh : function(pageNumber, pageSize) {
						$(this).pagination('loading');
						$(this).pagination('loaded');
					}
				});
				//定义动态消息弹出框
				$('#dynamic_template_win').dialog({
					title : '动态消息模板',
					width : 380,
					height : 160,
					cache : false,
					modal : true,
					closed : true,
					inline : false,
					buttons : [ {
						text : '保存',
						handler : save_message_template
					}, {
						text : '关闭',
						handler : function() {
							$('#dynamic_template_win').dialog('close');
						}
					} ],
					onClose : function() {
						//清空弹出框内的值
						$("#template_name").val("");
						$("#link_resource").combobox('clear');
						$("#template_templateId").val("");
					},
					onOpen : function(){
						$(this).dialog('move',{
							top : document.body.offsetHeight / 2 - $(this).dialog("options").height / 2,
							left : document.body.offsetWidth / 2 - $(this).dialog("options").width / 2
						});
					}
				});
				//定义所有接口地址
				$("#link_resource").combobox({
		        	url : global_ctxPath + rootPath + "/linkresource",
		    		valueField : 'resourceId',
		    		textField : 'name',
		    		required : false,
		    		panelHeight : '200',
		    		editable : false,
		    		onBeforeLoad : function (param){
		    			param.type = "02";
		    		}
		        });
			});
			//搜索框搜索事件
			function searcher_box(value, name){
				$('#template_grid').datagrid('load', {
					"keyword" : value
				});
			}
			//查看静态模板
			function view_static(templateId){
				location.href = global_ctxPath + rootPath + '/info/' + templateId;//当前页面跳转到编辑静态模板页面
			}
			//查看动态模板
			function view_dynamic(templateId){
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/info/' + templateId,
					async : true,
					dataType : "json",
					success : function(result) {
						//$("#link_resource").combobox('select',result.resourceId);
						//循环遍历如果存在该接口，则选中该接口
						var store = $("#link_resource").combobox('getData');
						var resourceId = null;
						for ( var i = 0; i < store.length; i++) {
							var temp = store[i];
							if(temp.resourceId == result.resourceId){
								resourceId = temp.resourceId;
								break;
							}
						}
						if(resourceId == null){
							$("#link_resource").combobox('clear');
						} else {
							$("#link_resource").combobox('select',resourceId);
						}
						
						$("#template_name").val(result.name);//模板名称
						$("#template_templateId").val(result.templateId);//ID
						$('#dynamic_template_win').dialog('open');//打开对话框
					}
				});
			}
			//保存动态模板
			function save_message_template(){
				var name = $("#template_name").val();//名称
				var resourceId = $("#link_resource").combobox('getValue');//接口地址
				var type = '01'; //动态消息默认为文本消息，
				var templateId = $("#template_templateId").val();//ID 也许为空
				var template = new Object();
				if(templateId != ""){
					template.templateId = templateId;
				}
				template.name = name;
				template.resourceId = resourceId;
				template.type = type;
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/saveDynamicMsg',
					async : true,
					data : template,
					dataType : "json",
					success : function(result) {
						$.messager.alert('提示', result.message, 'info',function() {
							if(result.success){//保存成功
								$('#dynamic_template_win').dialog('close');//关闭对话框
								$('#template_grid').datagrid('reload');//重新加载grid
							}
						});
					}
				});
			}
			//创建模板菜单选项点击事件
			function create_template(item){
				if(item.name == '01'){//动态消息
					$('#dynamic_template_win').dialog('open');//打开对话框
				} else {//静态消息
					location.href = global_ctxPath + rootPath + '/addstatic';//跳转到添加静态消息页面
				}
			}
			//删除模板
			function change_status(templateId){
				$.messager.confirm('确认','删除该消息模板，那么使用该模板的消息回复也将无法正常使用，是否继续？',function(r) {
					if(r){
						$.ajax({
							type : "post",
							url : global_ctxPath + rootPath + '/changestatus',
							async : true,
							data : {
								"templateId" : templateId
							},
							dataType : "json",
							success : function(result) {
								$.messager.alert('提示', result.message, 'info', function() {
									if (result.success) {//删除成功
										$('#template_grid').datagrid('reload');//grid重新加载
									}
								});
							}
						});
					}
				});
			}
		</script>
	</head>
	<body class="easyui-layout">
		<div data-options="region:'north',border:true,title:'消息模板',split:false,collapsible:false" style="height: 80px; padding: 10px; overflow-y: hidden;">
			<table width="100%">
				<tr>
					<td width="350px"><input class="easyui-searchbox" style="width: 300px" data-options="prompt:'请输入模板名称',searcher:searcher_box" /></td>
					<td>
					    <a href="javascript:void(0)" class="easyui-menubutton" data-options="menu:'#mm',iconCls:'icon-add'">创建消息模板</a>
					    <div id="mm" style="width:150px;" data-options="onClick:create_template">
						    <div data-options="name:'01',iconCls:'icon-add'">动态消息</div>
						    <div data-options="name:'02',iconCls:'icon-add'">静态消息</div>
					    </div>
					</td>
				</tr>
			</table>
		</div>
		<div data-options="region:'center',border:true,cache:false">
			<table id="template_grid"></table>
			
			<div id="dynamic_template_win" class="info">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
			    	<tr>
			    		<td width="100px">模板名称：</td>
			    		<td><input id="template_templateId" type="hidden" /><input id="template_name" type="text" /></td>
			    	</tr>
			    	<tr>
			    		<td>资源地址：</td>
			    		<td><input id="link_resource"/></td>
			    	</tr>
			    </table>
			</div>
		</div>
	</body>
</html>