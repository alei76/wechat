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
		<style type="text/css">
			.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
			.ztree li ul.level0 {padding:0; background:none;}
			#default_tips{font-size:24px;padding:10px;}
		</style>
		<script type="text/javascript">
			var rootPath = '/content/menu';
			//定义ztree配置
			var setting = {
				view : {
					showLine : true,
					selectedMulti : false,
					dblClickExpand : false,
					addHoverDom : addHoverDom,//鼠标悬停到节点事件
					removeHoverDom : removeHoverDom,//鼠标移出节点事件
					addDiyDom: addDiyDom//添加自定义html
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
					url : global_ctxPath + rootPath + '/wxmenu',
					dataType : "json",
					dataFilter : ajaxDataFilter//对加载的数据进行处理
				},
				callback: {
					onClick: viewMenuInfo//点击菜单节点的事件
				}
			};
			
			var templateData = null;//消息模板数据
			var linksource = null;//所有页面数据
			$.ajax({
				type : "post",
				url : global_ctxPath + rootPath + '/template',
				async : true,
				dataType : "json",
				success : function(result) {
					templateData = result;//初始化消息模板数据
				}
			});
			$.ajax({
				type : "post",
				url : global_ctxPath + rootPath + '/linkresource',
				async : true,
				data : {
					"type" : "01"
				},
				dataType : "json",
				success : function(result) {
					linksource = result;//初始化页面数据
				}
			});
			
			function ajaxDataFilter(treeId, parentNode, responseData) {
				if (responseData) {
					for ( var i = 0; i < responseData.length; i++) {
						var current = responseData[i];
						var parentId = current.parentId;
						if (parentId == "0") {//如果父节点ID是0，则展开该节点，且图标为parent
							current.open = true;
							current.iconSkin = "parent";
						} else {
							current.iconSkin = "leaf";//图标设置为leaf
						}
					}
					var rootNode = { menuId:"0", parentId:null, menuName:"微信菜单", menuDesc:"微信菜单", open:true, iconSkin:'root'};//添加跟节点
					responseData.push(rootNode);
				}
				return responseData;
			};
			//鼠标悬停在节点上时，增加操作按钮
			function addHoverDom(treeId, treeNode) {
				if (treeNode.level == 0 ) return;//第一级，根，不需要该事件
				if ($("#diyBtn_"+treeNode.menuId).length>0) {//悬停的节点正好是选中的节点，则不需要添加html
					return;
				} else{
					var parentId = treeNode.parentId;
					var aObj = $("#" + treeNode.tId + "_a");
					var btnHtml = "";
					var moveUp = "<span onclick='move_up(\""+ treeNode.menuId +"\")' class='button up' title='向上'>&nbsp;</span>";//向上移动html
					var moveDown = "<span onclick='move_down(\""+ treeNode.menuId +"\")' class='button down' title='向下'>&nbsp;</span>";//向下移动html
					if(!treeNode.isFirstNode){//如果不是第一个node
						btnHtml += moveUp;
					}
					if(!treeNode.isLastNode){//如果不是最后一个node
						btnHtml += moveDown;
					}
					if (parentId == "0") {//如果是父菜单
						var add = "<span onclick='add_sub_menu(\""+ treeNode.menuId +"\")' class='button add' title='添加'>&nbsp;</span>";//添加子菜单事件html
						btnHtml = add + btnHtml;
					}
					aObj.append("<div style=\"display:inline;padding-left:10px;\" id=\"diyBtn_" + treeNode.menuId + "\">"+btnHtml+"</div>");
				}
			}
			//鼠标移出节点时，删除自定义的按钮
			function removeHoverDom(treeId, treeNode) {
				$("#diyBtn_"+treeNode.menuId).remove();//将按钮所在的html删除
			}
			//增加自定义按钮
			function addDiyDom(treeId, treeNode) {
				if (treeNode.level > 0 ) return;//不是根节点的不添加
				var aObj = $("#" + treeNode.tId + "_a");
				var add = "<span onclick='add_p_menu()' class='button add' title='添加'>&nbsp;</span>";//添加一级菜单事件html
				aObj.append(add);//将添加按钮只加到根节点上
			}
			
			change_iframe_height(650);//更改iframe高度
			
			$(function() {
				$.fn.zTree.init($("#weixin_menu"), setting);//微信菜单初始化数据
				//定义添加微信菜单对话框
				$('#create_menu_win').dialog({
					title : '添加菜单',
					width : 380,
					height : 120,
					cache : false,
					modal : true,
					closed : true,
					inline : false,
					buttons : [ {
						text : '保存',
						handler : create_menu
					}, {
						text : '关闭',
						handler : function() {
							$('#create_menu_win').dialog('close');
						}
					} ],
					onClose : function() {
						$("#menu_parentId").val("0");//初始化为根菜单
						$("#menu_name").val("");//菜单名称为空
					}
				});
				//定义菜单类型下拉框
				$("#info_type").dict({
					dictCode : "MENU_TYPE",
					width : 206,
					onChange : function(newValue, oldValue){//菜单类型更改触发事件
						$('#info_target').combobox('clear');//事件地址清空
						$('#info_anon_target').combobox('clear');//未绑定事件地址清空
						//$("#info_oauth_scope").prop('checked',false);//网页oauth权限认证不勾选
						$("#oauth_scope").combobox('clear');//网页oauth权限认证不勾选
						if(newValue == '01'){//网页类型
							$("#info_target").combobox('options').valueField = 'resourceId';//更改事件地址下拉框的配置项
							$("#info_anon_target").combobox('options').valueField = 'resourceId';//更改未绑定事件地址下拉框的配置项
							$('#info_target').combobox('loadData',linksource);//事件地址下拉框数据加载所有页面数据
							$('#info_anon_target').combobox('loadData',linksource);//未绑定事件地址下拉框数据加载所有页面数据
							$("#btn_preview").hide();//隐藏预览按钮
							$("#scope_label").show();//scope选项显示
							$("#check_label").hide();//绑定功能隐藏
							$("#info_anon_target_tr").hide();//隐藏未绑定事件选择
							hide_i_message("info_target");//隐藏小i机器人的输入框
							hide_i_message("info_anon_target");//隐藏小i机器人的输入框
						} else if(newValue == '02'){//消息类型
							$("#info_target").combobox('options').valueField = 'templateId';//更改事件地址下拉框的配置项
							$("#info_anon_target").combobox('options').valueField = 'templateId';//更改未绑定事件地址下拉框的配置项
							$('#info_target').combobox('loadData',templateData);//事件地址下拉框数据加载所有模板数据
							$('#info_anon_target').combobox('loadData',templateData);//未绑定事件地址下拉框数据加载所有模板数据
							$("#btn_preview").show();//消息预览按钮显示
							$("#scope_label").hide();//scope选项隐藏
							$("#check_label").show();//绑定功能显示
							$("#info_anon_target_tr").show();//显示未绑定事件选择
						}
					}
				});
				
				
				$("#oauth_scope").dict({
					dictCode : "MEUN_OAUTH_SCOPE",
					width : 206
				});
				
				//定义事件地址的下拉框
			    $('#info_target').combobox({
			        width : 206,
			        textField : 'name',
			        panelHeight : '200',
	        		method : 'post',
	        		editable : false,
	        		multiple : false,
	        		readonly : false,
	        		required : false,
	        		onSelect : target_change
			    });
			    //定义未绑定事件地址的下拉框
			    $('#info_anon_target').combobox({
			        width : 206,
			        textField : 'name',
			        panelHeight : '200',
	        		method : 'post',
	        		editable : false,
	        		multiple : false,
	        		readonly : false,
	        		required : false,
	        		onSelect : target_change
			    });
			    
			    //验证绑定事件
			    $("#info_checkBind").click(function(){
					var checked = this.checked;
					if(checked){//如果需要绑定验证
						$("#info_anon_target").combobox("clear").combobox("enable");//下拉框可以选择
					} else {
						$("#info_anon_target").combobox("clear").combobox("disable");//下拉框不能选择
						hide_i_message("info_anon_target");//隐藏小i机器人关键字输入框
					}
				});
			});
			//更改事件地址，判断是否为小I机器人
			function target_change(record){
				var type = $("#info_type").combobox("getValue");//菜单类型
				if(type == "02"){//消息类型菜单
					var resourceType = record.resourceType;
					if(resourceType == '03'){//如果是小i机器人模板
						$("#"+this.id+"_label").show();//关键字输入框显示
						$("#"+this.id+"_keyword").prop("disabled",false);
					}else{
						hide_i_message(this.id);//关键字输入框隐藏
					}
				}else{
					hide_i_message(this.id);//关键字输入框隐藏
				}
			}
			//隐藏小I机器人关键字输入框
			function hide_i_message(id){
				$("#"+id+"_keyword").val("").prop("disabled",true);//输入框不允许输入
				$("#"+id+"_label").hide();//dom隐藏
			}
			
			//添加一级菜单
			function add_p_menu() {
				var treeObj = $.fn.zTree.getZTreeObj("weixin_menu");//ztree对象
				var nodes = treeObj.getNodesByFilter(parent_filter); // 查找节点集合，查找出所有的一级菜单
				if(nodes.length >= 3){//数量判断
					$.messager.alert('提示', '一级菜单最多只能三个', 'info');
				}else{
					$('#create_menu_win').dialog('open');//打开对话框
				}
			}
			//一级菜单过滤器
			function parent_filter(node) {
			    return (node.level == 1);
			}
			
			//添加二级菜单
			function add_sub_menu(parentId) {
				var treeObj = $.fn.zTree.getZTreeObj("weixin_menu");//ztree对象
				var nodes = treeObj.getNodesByFilter(function sub_filter(node) {
				    return (node.level == 2 && node.parentId == parentId);
				}); // 查找节点集合，在该父节点下的二级菜单
				if(nodes.length >=5){//数量判断
					$.messager.alert('提示', '二级菜单最多只能五个', 'info');
				}else{
					$("#menu_parentId").val(parentId);
					var node = treeObj.getNodeByParam("menuId", parentId, null);//获取到该父节点
					if(node.children === undefined){//如果该父菜单下没有其他子菜单，说明是第一次创建子菜单，需要提示。
						$.messager.confirm('提示','使用二级菜单后，当前编辑的菜单信息将会被清除。确定使用二级菜单？',function(r) {
							if (r) {
								$('#create_menu_win').dialog('open');//打开对话框
							}
						});
					} else {
						$('#create_menu_win').dialog('open');//打开对话框
					}
				}
			}
			//创建菜单
			function create_menu() {
				var parentId = $("#menu_parentId").val();
				var name = $("#menu_name").val();
				var menu = new Object();//菜单对象
				menu.parentId = parentId;
				menu.name = name;
				if(name!=""){
					$.ajax({
						type : "post",
						url : global_ctxPath + rootPath + '/create',
						async : true,
						data : menu,
						dataType : "json",
						success : function(result) {
							$.messager.alert('提示', result.message, 'info',function() {
								if (result.success) {//创建成功
									$('#create_menu_win').dialog('close');//关闭对话框
									$.fn.zTree.getZTreeObj("weixin_menu").reAsyncChildNodes(null, "refresh");//菜单树 重新加载数据
								}
							});
						}
					});
				}
			}
			//向上移动
			function move_up(menuId){
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/moveup',
					async : true,
					data : {
						"menuId" : menuId
					},
					dataType : "json",
					success : function(result) {
						$.fn.zTree.getZTreeObj("weixin_menu").reAsyncChildNodes(null, "refresh");//菜单树 重新加载数据
					}
				});
			}
			//向下移动
			function move_down(menuId){
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/movedown',
					async : true,
					data : {
						"menuId" : menuId
					},
					dataType : "json",
					success : function(result) {
						$.fn.zTree.getZTreeObj("weixin_menu").reAsyncChildNodes(null, "refresh");//菜单树 重新加载数据
					}
				});
			}
			//点击查看菜单详情
			function viewMenuInfo(event, treeId, treeNode, clickFlag){
				if(treeNode.level > 0){
					$("#default_tips").hide();//默认提示div隐藏
					$("#info_table").show();//菜单设置table显示
					//读取菜单数据
					$.ajax({
						type : "post",
						url : global_ctxPath + rootPath + '/info',
						async : true,
						data : {
							"menuId" : treeNode.menuId
						},
						dataType : "json",
						success : function(result) {
							$("#info_menuId").val(result.menuId);//id
							$("#info_name").val(result.name);//名称
							var type = result.type;//类型
							var subCount = result.subCount;//子菜单数量
							var target = result.target;//事件地址
							var eventKey = result.eventKey;//消息菜单eventKey
							var parentId = result.parentId;//父菜单id
							$("#info_parentId").val(parentId);
							if(parentId == "0" && subCount > 0){
								initParentMenu();//如果是父菜单，且有子菜单，则初始化为一级菜单
							}else{
								$("#info_target_keyword").val(result.robotKeyword);//小i机器人关键字
								$("#info_anon_target_keyword").val(result.anonRobotKeyword);//小i机器人关键字
								
								$("#info_type").combobox("reset").combobox("enable");//菜单类型选择框启用，重置。
								$("#info_target").combobox("clear").combobox("enable");//事件地址选择框启用，清空
								$("#info_checkBind").prop('disabled',false);//验证绑定勾选取用
								$("#info_type").combobox("setValue", type);//设置菜单类型
								$("#info_eventKey").val(eventKey);
								$("#oauth_scope").combobox("enable");//oauth2.0启用
								
								if(type == '01'){//如果是网页类型
									//循环判断是否存在该网页，如果存在则事件地址勾选
									var store = $("#info_target").combobox("getData");
									for ( var i = 0; i < store.length; i++) {
										var temp = store[i];
										if(temp.resourceId == target){
											$("#info_target").combobox("select", target);
											break;
										}
									}
								} else {//消息类型
									//循环判断是否存在该消息模板，如果存在则事件地址勾选
									var store = $("#info_target").combobox("getData");
									for ( var i = 0; i < store.length; i++) {
										var temp = store[i];
										if(temp.templateId == target){
											$("#info_target").combobox("select", target);
											break;
										}
									}
									//$("#info_target").combobox("select", target);
								}
								
								if(result.checkBind == '01'){//01为需要验证会员绑定
									$("#info_checkBind").prop('checked',true);//勾选
									$("#info_anon_target").combobox("enable");//下拉框可用
									//循环判断是否存在该消息模板，如果存在则事件地址勾选
									var store = $("#info_anon_target").combobox("getData");
									for ( var i = 0; i < store.length; i++) {
										var temp = store[i];
										if(temp.templateId == result.anonTarget){
											$("#info_anon_target").combobox("select", result.anonTarget);
											break;
										}
									}
								}else{
									$("#info_checkBind").prop('checked',false);//取消勾选
									$("#info_anon_target").combobox("clear").combobox("disable");//下拉框不可用
									hide_i_message("info_anon_target");//隐藏小i机器人关键字输入框
								}
								$("#oauth_scope").combobox("select", result.oauthScope);
								/* 
								if(result.oauthScope == '02'){//菜单是否需要高级scope 02为需要
									$("#info_oauth_scope").prop('checked',true);
								} else {
									$("#info_oauth_scope").prop('checked',false);
								} 
								*/
							}
						}
					});
				} else {
					$("#default_tips").show();//默认提示div显示
					$("#info_table").hide();//菜单设置table隐藏
				}
			}
			//一级菜单且有子菜单
			function initParentMenu() {
				$("#info_type").combobox("clear").combobox("disable");//菜单类型不可编辑
				$("#info_eventKey").val("");//eventKey不可填写
				$("#info_target").combobox("clear").combobox("disable");//事件地址不可编辑
				$("#info_checkBind").prop('checked',false);//绑定验证不可勾选，编辑
				$("#info_checkBind").prop('disabled',true);
				$("#info_anon_target").combobox("clear").combobox("disable");//未绑定事件地址不可编辑
				hide_i_message("info_target");//隐藏小i机器人关键字输入框
				hide_i_message("info_anon_target");//隐藏小i机器人关键字输入框
				$("#oauth_scope").combobox("clear").combobox("disable");//oauth2.0禁用
			}
			//删除菜单
			function del_menu() {
				$.messager.confirm('确认','是否确定要删除该菜单？',function(r) {
					if (r) {
						var menuId = $("#info_menuId").val();
						$.ajax({
							type : "post",
							url : global_ctxPath + rootPath + '/delMenu',
							async : true,
							data : {
								"menuId" : menuId
							},
							dataType : "json",
							success : function(result) {
								$.messager.alert('提示', result.message, 'info', function() {
									if (result.success) {
										$.fn.zTree.getZTreeObj("weixin_menu").reAsyncChildNodes(null, "refresh");//菜单树 重新加载数据
									}
								});
							}
						});
					}
				});
			}
			//验证保存合法性
			function validate_form(){
				if (!$("#info_type").combobox('options').disabled) {//一级菜单没有类型
					var type = $("#info_type").combobox('getValue');//菜单类型必须勾选
					if(type == ""){
						$.messager.alert('提示', '请选择菜单类型', 'info');
						return false;
					}
				}
				if (!$("#info_target").combobox('options').disabled) {//一级菜单没有事件地址
					var target = $("#info_target").combobox('getValue');//事件地址必须勾选
					if(target == ""){
						$.messager.alert('提示', '请选择事件地址', 'info');
						return false;
					}
				}
				if(!document.getElementById("info_target_keyword").disabled){//小i机器人关键字输入框如果为可输入，则必须输入。
					var iMsgKey = document.getElementById("info_target_keyword").value;
					if(iMsgKey == ""){
						$.messager.alert('提示', '请输入小i关键字', 'info');
						return false;
					}
				}
				if(!document.getElementById("info_anon_target_keyword").disabled){//小i机器人关键字输入框如果为可输入，则必须输入。
					var iMsgKey = document.getElementById("info_anon_target_keyword").value;
					if(iMsgKey == ""){
						$.messager.alert('提示', '请输入小i关键字', 'info');
						return false;
					}
				}
				var check = document.getElementById("info_checkBind").checked;//验证绑定勾选框
				if(check){
					var anonTarget = $("#info_anon_target").combobox('getValue');//需要验证绑定必须选择事件地址
					if(anonTarget == ""){
						$.messager.alert('提示', '请选择未绑定事件地址', 'info');
						return false;
					}
				}
				return true;
			}
			//保存菜单
			function save_menu_info() {
				var check = validate_form();//验证数据合法性
				if(check){
					var menu = new Object();//菜单对象
					menu.menuId = $("#info_menuId").val();//ID
					menu.name = $("#info_name").val();//名称
					menu.eventKey = $("#info_eventKey").val();//eventKey
					menu.parentId = $("#info_parentId").val();//所在菜单
					if (!$("#info_type").combobox('options').disabled) {//菜单类型（一级菜单没有）
						menu.type = $("#info_type").combobox('getValue');
					}
					if (!$("#info_target").combobox('options').disabled) {//事件对象（一级菜单没有）
						menu.target = $("#info_target").combobox('getValue');
					}
					var check = document.getElementById("info_checkBind").checked;//验证绑定
					if(check){
						menu.checkBind = '01';//需要验证
						var anonTarget = $("#info_anon_target").combobox('getValue');//未绑定事件 
						menu.anonTarget = anonTarget;
					} else {
						menu.checkBind = '02';//不需要验证
					}
					if(!document.getElementById("info_target_keyword").disabled){
						menu.robotKeyword = $("#info_target_keyword").val();//小i关键词
					}else{
						menu.robotKeyword = "";
					}
					if(!document.getElementById("info_anon_target_keyword").disabled){
						menu.anonRobotKeyword = $("#info_anon_target_keyword").val();//小i关键词
					}else{
						menu.anonRobotKeyword = "";
					}

					/* var userinfo = document.getElementById("info_oauth_scope").checked;//scope权限
					if(userinfo){
						menu.oauthScope = '02';//高级
					} else {
						menu.oauthScope = '01';//普通
					} */
					var userinfo = $("#oauth_scope").combobox("getValue");//scope权限
					menu.oauthScope = userinfo;
					
					$.ajax({
						type : "post",
						url : global_ctxPath + rootPath + '/update',
						async : true,
						data : menu,
						dataType : "json",
						success : function(result) {
							$.messager.alert('提示', result.message, 'info',function() {
								if (result.success) {
									$.fn.zTree.getZTreeObj("weixin_menu").reAsyncChildNodes(null, "refresh");//菜单树 重新加载数据
								}
							});
						}
					});
				}
			}
			//同步至腾讯微信
			function async_weixin(){
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/async',
					async : true,
					dataType : "json",
					success : function(result) {
						$.messager.alert('提示', result.message, 'info');
					}
				});
			}
			//消息预览
			function preview_message(){
				var templateId = $("#info_target").combobox('getValue');//消息模板
				var store = $("#info_target").combobox("getData");
				var template = null;
				//判断是否存在该消息模板
				$.each(store, function(i, j) {
					if(j.templateId == templateId){
						template = j;
					}
				});
				
				if(template != null){
					if(template.resourceType == '03'){//如果是小I机器人
						var keyword = $("#info_target_keyword").val();//获取关键词
						if(keyword != ""){
							kernel_preview_msg(keyword, templateId);//消息预览
						} else {
							$.messager.alert('提示', '请输入关键字', 'info');
						}
					}else{//普通消息模板
						kernel_preview_msg("", templateId);//消息预览
					}
				}
			}
		</script>
	</head>
	
	<body class="easyui-layout">
		<div data-options="region:'west',title:'菜单编辑',split:false,collapsible:false" style="width:300px; overflow-x:hidden;">
			<div class="easyui-panel" style="width:288px;text-align:right;padding:10px 10px 0px 0px;overflow: hidden;" data-options="noheader:true,border:false,doSize:false">  
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="async_weixin();">更新至微信</a>
			</div>
			<hr />
			<ul id="weixin_menu" class="ztree"></ul>
		</div>  
		<div data-options="region:'center',title:'菜单信息',border:true,cache:false" class="info">
			<div id="default_tips">请先在左边选择一个菜单，然后开始为其设置响应动作。</div>
		
			<table id="info_table" style="width: 100%; padding: 5px;display:none;" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td><a href="javascript:void(0)" data-options="iconCls:'icon-save'" onclick="save_menu_info();" class="easyui-linkbutton">保存</a>&nbsp;&nbsp;<a href="javascript:void(0)" data-options="iconCls:'icon-remove'" class="easyui-linkbutton" onclick="del_menu();">删除</a>&nbsp;&nbsp;<a id="btn_preview" href="javascript:void(0)" data-options="iconCls:'icon-tip'" class="easyui-linkbutton" onclick="preview_message();">消息预览</a></td>
				</tr>
				<tr>
					<td align="right" width="120px"><label for="info_name">菜单名称:</label></td>
					<td>
						<input id="info_menuId" type="hidden" />
						<input id="info_eventKey" type="hidden"/>
						<input id="info_parentId" type="hidden"/>
						<input id="info_name" style="width: 200px;" type="text" />
					</td>
				</tr>
				<tr>
					<td align="right"><label for="info_type">菜单类型:</label></td>
					<td>
						<input id="info_type" />
					</td>
				</tr>
				<tr>
					<td align="right"><label for="info_target">事件地址:</label></td>
					<td>
						<input id="info_target" style="width: 200px;" type="text" />
					</td>
				</tr>
				<tr id="info_target_label">
					<td align="right"><label for="info_target_keyword">小i机器人关键字：</label></td>
					<td>
						<input id="info_target_keyword" style="width: 200px;" type="text" />
					</td>
				</tr>
				
				<tr id="scope_label">
					<td align="right"><label for="oauth_scope">OAuth2.0：</label></td>
					<td>
						<!-- <label for="info_oauth_scope" id="scope_label"><input id="info_oauth_scope" type="checkbox"/>使用Scope为snsapi_userinfo</label> -->
						<input id="oauth_scope" />
					</td>
				</tr>
				<tr id="check_label">
					<td align="right">&nbsp;</td>
					<td>
						<label for="info_checkBind"><input id="info_checkBind" type="checkbox"/>验证会员绑定</label>
					</td>
				</tr>
				<tr id="info_anon_target_tr">
					<td align="right"><label for="info_anon_target">未绑定事件地址:</label></td>
					<td>
						<input id="info_anon_target" style="width: 200px;" type="text" />
					</td>
				</tr>
				<tr id="info_anon_target_label">
					<td align="right"><label for="info_anon_target_keyword">小i机器人关键字：</label></td>
					<td>
						<input id="info_anon_target_keyword" style="width: 200px;" type="text" />
					</td>
				</tr>
			</table>
			<div id="create_menu_win">
				<table style="width: 100%; padding: 5px;" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td align="right" width="80px">菜单名称:</td>
						<td>
							<input id="menu_parentId" type="hidden" value="0"/>
							<input id="menu_name" style="width: 210px;" type="text" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>