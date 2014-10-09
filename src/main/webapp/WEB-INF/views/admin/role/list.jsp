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
			var rootPath = '/admin/role';
			change_iframe_height(650);//更改当前iframe高度为650
			//定义ztree控件的设置
			var setting = {
				check : {
					enable : true
				},
				view : {
					showLine : true,
					selectedMulti : false,
					dblClickExpand : false
				},
				data : {
					simpleData : {
						enable : true,
						idKey : "menuId",
						pIdKey : "parentId",
						rootPId : null
					},
					key : {
						name : "menuName",
						title : "menuDesc"
					}
				},
				async : {
					enable : true,
					url : global_ctxPath + '/kernel/menu/resource',
					dataType : "json"
				},
				callback : {
					onClick : zTreeOnClick,//定义树形节点的点击事件
					onAsyncSuccess : zTreeOnAsyncSuccess//定义数据加载完成后的事件
				}
			};
			
			$(function() {
				//定义角色grid
				$('#role_grid').datagrid( {
					view : datagrid_view,
					emptyMsg : '没有记录',
					url : global_ctxPath + rootPath + '/grid',
					method : 'post',
					fit : true,
					border : false,
					title : '角色列表',
					fitColumns : true,
					singleSelect : true,
					pagination : true,
					rownumbers : true,
					pageSize : 10,
					pageList : [ 10, 20, 30 ],
					remoteSort : false,
					idField : 'roleId',
					loadMsg : '数据装载中......',
					columns : [ [ {
						field : 'roleName',
						title : '角色名称',
						width : 30,
						align : 'center',
						formatter : function(value, row, index) {
							return "<a href=\"javascript:void(0);\" onclick=\"view_info('" + row.roleId + "')\">" + value + "</a>";
						}
					}, {
						field : 'roleDesc',
						title : '角色描述',
						width : 40,
						align : 'center'
					}, {
						field : 'roleId',
						title : '功能列表',
						width : 20,
						align : 'center',
						formatter : function(value, row, index) {
							return "<a href=\"javascript:void(0);\" onclick=\"edit_resource('" + value + "')\">编辑</a>";
						}
					}, {
						field : 'status',
						title : '操作',
						width : 30,
						align : 'center',
						formatter : function(value, row, index) {
							/* 
							var text = "";//根据状态，显示启用还是禁用
							if(value == '02'){
								text = "启用";
							} else {
								text = "禁用";
							} 
							*/
							var text = "删除";
							return "<a href=\"###\" onclick=\"change_status('" + row.roleId + "')\">" + text + "</a>";
						}
					} ] ]
				});
				//定义grid的分页
				$('#role_grid').datagrid('getPager').pagination({
					displayMsg : '当前显示从{from}到{to}共{total}记录',
					beforePageText : '第',
					afterPageText : '页 共 {pages} 页',
					onBeforeRefresh : function(pageNumber, pageSize) {
						$(this).pagination('loading');
						$(this).pagination('loaded');
					}
				});
				//定义创建角色的弹出对话框
				$('#role_win').dialog({
					title : '创建角色',
					width : 360,
					height : 160,
					cache : false,
					modal : true,
					closed : true,
					inline : false,
					buttons : [ {
						text : '保存',
						handler : save_role
					}, {
						text : '关闭',
						handler : function() {
							$('#role_win').dialog('close');
						}
					} ],
					onClose : function() {
						reset_role_win();
					},
					onOpen : function(){
						//定义打开时置屏幕中央
						$(this).dialog('move',{
							top : document.body.offsetHeight / 2 - $(this).dialog("options").height / 2,
							left : document.body.offsetWidth / 2 - $(this).dialog("options").width / 2
						});
					}
				});
				//定义编辑角色功能列表的弹出对话框
				$('#role_resource_win').dialog( {
					title : '功能列表',
					width : 320,
					height : 340,
					cache : false,
					modal : true,
					closed : true,
					inline : false,
					buttons : [ {
						text : '保存',
						handler : save_role_resource
					}, {
						text : '关闭',
						handler : function() {
							$('#role_resource_win').dialog('close');
						}
					} ],
					onClose : function() {
						$.fn.zTree.getZTreeObj("role_resource_tree").checkAllNodes(false);//将所有节点设置为未勾选
						$("#current_role_id").val("");//当前编辑的角色id设为空
					},
					onOpen : function(){
						//定义打开时置屏幕中央
						$(this).dialog('move',{
							top : document.body.offsetHeight / 2 - $(this).dialog("options").height / 2,
							left : document.body.offsetWidth / 2 - $(this).dialog("options").width / 2
						});
					}
				});
		
				$.fn.zTree.init($("#role_resource_tree"), setting);//初始化ztree控件
			});
			//编辑角色的功能列表
			function edit_resource(roleId) {
				$("#current_role_id").val(roleId);//设置当前编辑的角色id
				//读取目前设置的功能列表
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/listResource',
					async : true,
					data : {
						"roleId" : roleId
					},
					dataType : "json",
					success : function(result) {
						var treeObj = $.fn.zTree.getZTreeObj("role_resource_tree");//ztree对象
						$.each(result, function(i, j) {//循环勾选已设置的功能
							var node = treeObj.getNodeByParam("menuId", j.resourceId, null);
							treeObj.checkNode(node, true, true, true);
						});
						$('#role_resource_win').dialog('open');//打开对话框
					}
				});
			}
			//添加角色，打开对话框
			function add_role() {
				$('#role_win').dialog('open');
			}
			//保存角色
			function save_role() {
				var role = new Object();//角色对象
				$("#role_win input[id^='role_']").each(function(i, j) {
					var id = this.id.substring(5, this.id.length);
					role[id] = this.value;//循环赋角色对象的属性和值
				});
				//ajax提交保存
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/save',
					data : role,
					dataType : "json",
					success : function(result) {
						$.messager.alert('提示', result.message, 'info', function() {
							if (result.success) {//创建角色成功
								$('#role_win').dialog('close');//关闭对话框
								$('#role_grid').datagrid('reload');//grid重新加载
							}
						});
					}
				});
			}
			//重置添加角色对话框内的值
			function reset_role_win() {
				$("#role_win input[id^='role_']").val("");
			}
			//ztree数据加载完成后事件
			function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
				$.fn.zTree.getZTreeObj(treeId).expandAll(true);//展开ztree
			}
			//ztree中每个node节点的点击事件
			function zTreeOnClick(event, treeId, treeNode) {
				$.fn.zTree.getZTreeObj(treeId).checkNode(treeNode, null, true, true);//点击即触发勾选事件
				return true;
			}
			//保存当前编辑角色勾选的功能列表
			function save_role_resource() {
				var treeObj = $.fn.zTree.getZTreeObj("role_resource_tree");//ztree对象
				var nodes = treeObj.getCheckedNodes(true);//所有勾选的节点
				var resourceArray = new Array();//定义功能集合
				$.each(nodes, function(i, j) {//循环勾选的ztree节点
					if (j.parentId != null) {//跳过父节点
						var resourceId = j.menuId;
						resourceArray.push(resourceId);//将所有不是父节点，且勾选的ztree中的功能保存到集合中
					}
				});
				var roleId = $("#current_role_id").val();//获取当前编辑的角色id
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/saveResource',
					data : {
						"resourceArray" : $.toJSON(resourceArray),//将集合转换为json字符串表示
						"roleId" : roleId
					},
					dataType : "json",
					success : function(result) {
						$.messager.alert('提示', result.message, 'info', function() {
							if (result.success) {
								$('#role_resource_win').dialog('close');//编辑成功，关闭对话框。
							}
						});
					}
				});
			}
			//定义搜索框搜索事件
			function searcher_box(value, name){
				//按关键字让grid重新加载数据
				$('#role_grid').datagrid('load', {
					"keyword" : value
				});
			}
			//查看角色详情
			function view_info(roleId){
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/viewinfo',
					data : {
						"roleId" : roleId
					},
					dataType : "json",
					success : function(result) {
						//将值填入html
						$("#role_roleId").val(result.roleId);
						$("#role_roleName").val(result.roleName);
						$("#role_roleDesc").val(result.roleDesc);
						$('#role_win').dialog('open');//打开对话框
					}
				});
			}
			//更改角色状态
			function change_status(roleId){
				$.messager.confirm('确认','删除该角色，所有用户都无法使用该角色下的功能。是否要继续？',function(r) {
					if(r){
						$.ajax({
							type : "post",
							url : global_ctxPath + rootPath + '/changestatus',
							data : {
								"roleId" : roleId
							},
							dataType : "json",
							success : function(result) {
								$.messager.alert('提示', result.message, 'info', function() {
									if (result.success) {
										$('#role_grid').datagrid('reload');//更改成功，grid重新加载。
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
		<div data-options="region:'north',border:true,title:'角色管理',split:false,collapsible:false" style="height: 80px; padding: 10px; overflow-y: hidden;">
			<table width="100%">
				<tr>
					<td width="350px"><input class="easyui-searchbox" style="width: 300px" data-options="prompt:'请输入角色名称',searcher:searcher_box" />
					</td>
					<td><a href="javascript:void(0);" onclick="add_role()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">创建角色</a>
					</td>
				</tr>
			</table>
		</div>
		<div data-options="region:'center',border:true,cache:false">
			<table id="role_grid"></table>
			<!-- 添加角色对话框 -->
			<div id="role_win">
				<table style="width: 100%; padding: 5px;" border="0" cellpadding="0"
					cellspacing="0">
					<tr>
						<td align="right" width="80px">角色名称:</td>
						<td>
							<input id="role_roleId" type="hidden" />
							<input id="role_roleName" style="width: 200px;" type="text" />
						</td>
					</tr>
					<tr>
						<td align="right" width="80px">角色描述:</td>
						<td><input id="role_roleDesc" style="width: 200px;" type="text" /></td>
					</tr>
				</table>
			</div>
			<!-- 角色功能对话框 -->
			<div id="role_resource_win">
				<input id="current_role_id" type="hidden" />
				<!-- ztree -->
				<ul id="role_resource_tree" class="ztree"></ul>
			</div>
		</div>
	</body>
</html>