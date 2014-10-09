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
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/ajaxdownload.js"></script>
		<style type="text/css">
			.ztree li ul{ margin:0; padding:0}
			.ztree li {line-height:30px;}
			.ztree li a {width:185px;height:30px;padding-top: 0px;}
			.ztree li a:hover {text-decoration:none; background-color: #E7E7E7;}
			.ztree li a.curSelectedNode {background-color:#D4D4D4;border:0;height:30px;}
			.ztree li span {line-height:30px;}
			.ztree li span.button {margin-top: -7px;}
			.ztree li span.button.switch {width: 16px;height: 16px;}
			.ztree li a.level0 span {font-size: 150%;font-weight: bold;}
			.ztree li span.button.switch.level0 {display:none;}
			.ztree li span.button.switch.level1 {width: 20px; height:20px}
		</style>
		<script type="text/javascript">
			var rootPath = '/member/fans';
			change_iframe_height(650);
			$(function() {
				$.fn.zTree.init($("#group_tree"), setting);//初始化粉丝分组树形控件
				//定义粉丝grid
				$('#fans_grid').datagrid( {
					view : datagrid_view,
					emptyMsg : '没有记录',
					url : global_ctxPath + rootPath + '/grid',
					method : 'post',
					fit : true,
					border : false,
					title : '粉丝列表',
					fitColumns : true,
					singleSelect : false,
					pagination : true,
					rownumbers : false,
					pageSize : 10,
					pageList : [ 10, 20, 30 ],
					remoteSort : false,
					idField : 'fansId',
					loadMsg : '数据装载中......',
					columns : [ [ {
						field : 'fansId',
						checkbox : true
					}, {
						field : 'nickName',
						title : '昵称',
						width : 25,
						align : 'center'
					}, {
						field : 'openId',
						title : '微信号',
						width : 40,
						align : 'center'
					}, {
						field : 'sex',
						title : '性别',
						width : 10,
						align : 'center',
						formatter : function(value, row, index) {
							if(value == "1"){
								return "男";
							} else if(value == "2"){
								return "女";
							} else if(value == "0"){
								return "未知";
							} else {
								return "";
							}
						}
					}, {
						field : 'groupCn',
						title : '所在分组',
						width : 15,
						align : 'center'
					}, {
						field : 'province',
						title : '省份',
						width : 15,
						align : 'center'
					}, {
						field : 'city',
						title : '城市',
						width : 15,
						align : 'center'
					}, {
						field : 'mobile',
						title : '手机号',
						width : 20,
						align : 'center',
						formatter : function(value, row, index) {
							return row.wxFansMember.mobile;
						}
					}, {
						field : 'fullName',
						title : '姓名',
						width : 15,
						align : 'center',
						formatter : function(value, row, index) {
							return row.wxFansMember.fullName;
						}
					}, {
						field : 'status',
						title : '操作',
						width : 30,
						align : 'center',
						formatter : function(value, row, index) {
							return "<a href=\"###\" onclick=\"sync_info('" + row.fansId + "')\">更新资料</a>&nbsp;&nbsp;<a href=\"###\" onclick=\"detail_info('" + row.fansId + "')\">详情</a>";
						}
					} ] ],
					onSelect : function(rowIndex, rowData){
						$('#target_group').combobox("enable");//勾选了某个粉丝后，让粉丝分组下拉框可用
					},
					onUnselect : function(rowIndex, rowData){
						var rows = $(this).datagrid("getSelections");//取消选择某个粉丝后，获取还剩余勾选的粉丝数量
						if(rows.length == 0){//勾选数量为0时
							$('#target_group').combobox("disable");//粉丝分组下拉框不可用
						}
					},
					onSelectAll : function(rows){
						var rows = $(this).datagrid("getSelections");//选中所有时，获取当前勾选的粉丝数量
						if(rows.length > 0){//勾选数量大于0时
							$('#target_group').combobox("enable");//粉丝分组下拉框可用
						}
					},
					onUnselectAll : function(rows){
						$('#target_group').combobox("disable");//取消勾选所有时，粉丝分组下拉框不可用
					}
				});
				//定义grid的分页配置
				$('#fans_grid').datagrid('getPager').pagination({
					displayMsg : '当前显示从{from}到{to}共{total}记录',
					beforePageText : '第',
					afterPageText : '页 共 {pages} 页',
					onBeforeRefresh : function(pageNumber, pageSize) {
						$(this).pagination('loading');
						$(this).pagination('loaded');
					}
				});
				//定义粉丝分组下拉框
				$('#target_group').combobox({
				    url : global_ctxPath + rootPath + '/listgroup',
				    valueField : 'groupId',
				    textField : 'groupName',
				    width : 'auto',
	        		panelHeight : '200',
	        		method : 'post',
	        		editable : false,
	        		multiple : false,
	        		readonly : false,
	        		required : false,
	        		disabled : true,
	        		onSelect : function(record){
	        			//选择某个粉丝分组
	        			$.messager.confirm('确认','是否要将所选用户移动至该分组',function(r) {
	    					if (r) {
	    						change_group(record.groupId);//将粉丝移动到选择的分组
	    					} else {
	    						$('#target_group').combobox("clear");
	    					}
	    				});
	        		}
				});
				//定义创建分组的弹出对话框
				$('#group_info_win').dialog({
					title : '分组信息',
					width : 380,
					height : 120,
					cache : false,
					modal : true,
					closed : true,
					inline : false,
					buttons : [ {
						text : '保存',
						handler : create_group
					}, {
						text : '关闭',
						handler : function() {
							$('#group_info_win').dialog('close');
						}
					} ],
					onClose : function() {
						//清空弹出对话框中的值
						$("#wxgroup_groupId").val("");
						$("#wxgroup_groupName").val("");
					},
					onOpen : function(){
						$(this).dialog('move',{
							top : document.body.offsetHeight / 2 - $(this).dialog("options").height / 2,
							left : document.body.offsetWidth / 2 - $(this).dialog("options").width / 2
						});
					}
				});
				//性别选择下拉框
				$('#sex_select').combobox({
					valueField: 'value',
					textField: 'label',
					panelHeight: 'auto',
					editable: false,
					required: false,
					width: 100,
					data: [{
						label: '全部',
						value: ''
					},{
						label: '未知',
						value: '0'
					},{
						label: '男',
						value: '1'
					},{
						label: '女',
						value: '2'
					}]
				});
				
				//省份选择下拉框
				$('#province_select').combobox({
					url : global_ctxPath + rootPath + '/listprov',
				    width : 'auto',
				    valueField: 'value',
					textField: 'label',
	        		panelHeight : '200',
	        		method : 'post',
	        		editable : false,
	        		multiple : false,
	        		readonly : false,
	        		required : false,
	        		loadFilter : function(data){//数据加载完成的过滤事件
	        			var store = new Array();
	        			var row = new Object();
        				row.label = '全部';
        				row.value = '';
        				store.push(row);//添加一个'全部'选项
	        			$.each(data,function(i,j){
	        				var row = new Object();
	        				row.label = j;
	        				row.value = j;
	        				store.push(row);
	        			});
	        			return store;
	        		}
				});
			});
			//定义粉丝分组ztree设置
			var setting = {
				view: {
					showLine: false,
					showIcon: false,
					selectedMulti: false,
					dblClickExpand: false,
					addDiyDom: addDiyDom,//增加自定义html
					addHoverDom : addHoverDom,//鼠标悬停事件
					removeHoverDom : removeHoverDom//移出鼠标事件
				},
				data: {
					simpleData: {
						enable: true,
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
					url : global_ctxPath + rootPath + '/grouptree',
					dataType : "json"
				},
				callback : {
					onClick: list_fans,//点击事件
					onAsyncSuccess : zTreeOnAsyncSuccess//数据加载完成触发事件
				}
			};
			//增加自定义html
			function addDiyDom(treeId, treeNode) {
				var spaceWidth = 5;
				var switchObj = $("#" + treeNode.tId + "_switch"),
				icoObj = $("#" + treeNode.tId + "_ico");
				switchObj.remove();
				icoObj.before(switchObj);
				if (treeNode.level > 1) {
					//添加一个dom，使第二级与第一级提现层级，目前算法是5px乘以层级数。
					var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
					switchObj.before(spaceStr);
				}
			}
			//鼠标悬停时，增加自定义html
			function addHoverDom(treeId, treeNode) {
				if (treeNode.level == 0 ) return;//第一级，根节点，不添加html
				if(treeNode.menuUrl == '01')  return;//menuUrl表示分组类型，01为系统分组，无法删除与改名
				if ($("#diyBtn_"+treeNode.menuId).length>0) {
					return;//如果悬停的跟点击的是同一个节点，不添加html
				} else{
					var aObj = $("#" + treeNode.tId + "_a");
					var edit = "<span style=\"margin-top:3px;\" onclick='edit_group(\""+ treeNode.menuId +"\",\""+ treeNode.menuName +"\")' class='button edit' title='编辑'>&nbsp;</span>";
					var del = "<span style=\"margin-top:3px;\" onclick='delete_group(\""+ treeNode.menuId +"\")' class='button delete' title='删除'>&nbsp;</span>";
					var btnHtml = edit + del;
					//添加改名与删除的html
					aObj.append("<div style=\"display:inline;padding-left:10px;\" id=\"diyBtn_" + treeNode.menuId + "\">"+btnHtml+"</div>");
				}
			}
			//鼠标移出时事件
			function removeHoverDom(treeId, treeNode) {
				$("#diyBtn_"+treeNode.menuId).remove();//删除自定义按钮所在的div
			}
			//ztree树形控件加载完成触发事件
			function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
				var treeObj = $.fn.zTree.getZTreeObj(treeId);
				var node = treeObj.getNodeByParam("menuId", "0", null);
				treeObj.selectNode(node);//选择跟节点
				treeObj.expandAll(true);//展开所有
			}
			//点击某个树形节点，查询该分组下的粉丝
			function list_fans(event, treeId, treeNode, clickFlag){
				var groupId = treeNode.menuId;
				if(groupId == '0'){
					groupId = null;
				}
				reload_grid(groupId);//粉丝grid重新加载
			}
			//从腾讯微信获取粉丝列表
			function sync_fans_list(){
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/syncfanslist',
					async : true,
					dataType : "json",
					success : function(result) {
						$.messager.alert('提示', result.message, 'info', function() {
							$('#fans_grid').datagrid('load');//刷新grid，清除所有查询条件
						});
					}
				});
			}
			//添加分组
			function add_group(){
				$('#group_info_win').dialog("open");//打开对话框
			}
			//编辑粉丝分组
			function edit_group(groupId, name){
				$("#wxgroup_groupId").val(groupId);//赋值
				$("#wxgroup_groupName").val(name);//赋值
				$('#group_info_win').dialog("open");//打开对话框
			}
			//从腾讯微信同步粉丝的个人资料
			function sync_info(fansId){
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/asyncfansinfo',
					data : {
						"fansId" : fansId
					},
					dataType : "json",
					success : function(result) {
						$.messager.alert('提示', result.message, 'info', function() {
							if (result.success) {//同步成功
								$("#fans_grid").datagrid("reload");//重新加载grid
							}
						});
					}
				});
			}
			//删除分组
			function delete_group(groupId){
				$.messager.confirm('确认','删除该分组时，该分组下所有粉丝都想移动至未分组，是否要继续？',function(r) {
					if (r) {
						$.ajax({
							type : "post",
							url : global_ctxPath + rootPath + '/deletegroup',
							async : true,
							data : {
								"groupId" : groupId
							},
							dataType : "json",
							success : function(result) {
								$.messager.alert('提示', result.message, 'info', function() {
									if (result.success) {
										$.fn.zTree.getZTreeObj("group_tree").reAsyncChildNodes(null, "refresh");//删除了分组，ztree重新刷新
										$('#target_group').combobox('reload');//粉丝分组下拉框重新加载数据
										reload_grid(null);//刷新粉丝grid
									}
								});
							}
						});
					}
				});
			}
			//将分组移动到分组
			function change_group(groupId){
				var rows = $("#fans_grid").datagrid("getSelections");//获取选中的粉丝列表
				var fansArray = new Array();
				$.each(rows, function(i, j) {
					fansArray.push(j.fansId);//循环放入集合
				});
				var fansId = $.toJSON(fansArray);//将粉丝id集合以json形式表示
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/changegroup',
					async : true,
					data : {
						"fansId" : fansId,
						"groupId" : groupId
					},
					dataType : "json",
					success : function(result) {
						$.messager.alert('提示', result.message, 'info',function() {
							$("#fans_grid").datagrid("reload");//粉丝grid重新加载
							$("#fans_grid").datagrid("unselectAll");//取消所有粉丝勾选
							$('#target_group').combobox("clear");//粉丝分组下拉框清空
						});
					}
				});
			}
			//创建粉丝分组
			function create_group() {
				var group = new Object();
				var groupId = $("#wxgroup_groupId").val();//id
				var groupName = $("#wxgroup_groupName").val();//名称
				if (groupId != "") {
					group.groupId = groupId;
				}
				group.groupName = groupName;
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/creategroup',
					async : true,
					data : group,
					dataType : "json",
					success : function(result) {
						$.messager.alert('提示', result.message, 'info',function() {
							if (result.success) {//创建成功
								$.fn.zTree.getZTreeObj("group_tree").reAsyncChildNodes(null,"refresh");//ztree重新刷新
								$('#group_info_win').dialog("close");//关闭对话框
								$('#target_group').combobox('reload');//粉丝分组下拉框重新加载数据
								reload_grid(null);//刷新粉丝grid
							}
						});
					}
				});
			}
			//显示粉丝详细资料
			function detail_info(fansId){
				var target = global_ctxPath + "/info/fans/list/" + fansId;
				var addWin = $('<div id="detail_win" style="overflow: hidden;"/>');
				var upladoer = $('<iframe/>').attr({'src':target,width:'100%',height:'100%',frameborder:'0',scrolling:'no'});
				//用window组件，结合iframe显示
				//最大化覆盖当前页面
				addWin.window({
					title : '粉丝详情',
					collapsible : false,
					minimizable : false,
					maximizable : false,
					cache : false,
					content : upladoer,
					draggable : false,
					resizable : false,
					border : false,
					doSize : true,
					maximized : true,
					modal : true,
					inline : false,
					onClose : function() {
						$(this).window('destroy');
					},
					onOpen:function(){
						var target = $(this);
						setTimeout(function(){
							var fw = GetFrameWindow(upladoer[0]);
							fw.target = target;
						},100);//将当前window组件的对象赋值到iframe中
					}
				});
			}
			
			function GetFrameWindow(frame){
				return frame && typeof(frame)=='object' && frame.tagName == 'IFRAME' && frame.contentWindow;
			}
			//只按分组ID查询粉丝
			function reload_grid(groupId){
				reset_query();//重置其他查询条件，只按照分组查询
				var param = new Object();
				if(groupId != null){
					param.groupId = groupId;
				}
				$('#fans_grid').datagrid('load',param);//粉丝grid数据重新加载
			}
			//刷新粉丝grid
			function refresh_grid(){
				var param = getQueryParam();//获取查询条件
				$('#fans_grid').datagrid('load', param);//grid按条件查询
			}
			//获取页面的查询条件
			function getQueryParam(){
				var param = new Object();
				var treeObj = $.fn.zTree.getZTreeObj("group_tree");
				var nodes = treeObj.getSelectedNodes();
				var groupId = nodes[0].menuId;//获取分组id
				if(groupId != '0'){//不是根节点
					param.groupId = groupId;
				}
				var keyword = $('#nickName_searchbox').searchbox('getValue');//输入框的值，做模糊匹配
				if(keyword != ''){
					param.keyword = keyword;
				}
				var sex = $("#sex_select").combobox('getValue');//性别
				if(sex != ''){
					param.sex = sex;
				}
				var province = $("#province_select").combobox('getValue');//省份
				if(province != ''){
					param.province = province;
				}
				return param;
			}
			//重置查询条件
			function reset_query(){
				$('#nickName_searchbox').searchbox('clear');//搜索框清空
				$("#sex_select").combobox('setValue','');//性别选择全部
				$("#province_select").combobox('setValue','');//省份选择全部
			}
			//导出数据
			function export_data(){
				var param = getQueryParam();//获取导出条件
				//使用iframe做页面无跳转下载
				$.ajaxDownload({
					url : global_ctxPath + rootPath + "/export",
					param : param,
					target : 'temp_frame'
				});
			}
		</script>
	</head>
	<body class="easyui-layout">
		<div data-options="region:'west',split:false,collapsed:false" style="width: 200px;">
			<ul id="group_tree" class="ztree"></ul>
			<div style="text-align:center;padding:5px;border-top:1px solid #E7E7E7;">
				<a href="javascript:void(0);" onclick="add_group()" style="border:none;font-weight: bold;width:120px;" class="easyui-linkbutton">新建分组</a>
			</div>
		</div>
		<div data-options="region:'center',border:false">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'north',border:true,title:'微信粉丝',split:false,collapsible:false" style="height: 110px; padding: 10px; overflow-y: hidden;">
					<table style="width:100%;"  border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>移动到：</td>
							<td><input id="target_group" type="text"/></td>
							<td>省份：</td>
							<td><input id="province_select"/></td>
							<td>性别：</td>
							<td><input id="sex_select"/></td>
						</tr>
						<tr>
					        <td>关键字查询：</td>
							<td><input id="nickName_searchbox" class="easyui-searchbox" data-options="prompt:'请输入昵称',searcher:refresh_grid" /></td>
							<td colspan="2" align="center">
								<a href="javascript:void(0);" onclick="refresh_grid()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
								&nbsp;&nbsp;
								<a href="javascript:void(0);" onclick="reset_query()" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">重置</a>
								&nbsp;&nbsp;
								<a href="javascript:void(0);" onclick="export_data()" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">导出</a>
							</td>
							<td colspan="2">
								<a href="javascript:void(0);" onclick="sync_fans_list()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">同步微信粉丝</a>
							</td>
						</tr>
					</table>
				</div>
				<div data-options="region:'center',border:true,cache:false">
					<table id="fans_grid"></table>
				</div>
			</div>
		</div>
		<!-- 创建粉丝分组对话框 -->
		<div id="group_info_win">
			<table style="width: 100%; padding: 5px;" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="right" width="80px">分组名称:</td>
					<td>
						<input id="wxgroup_groupId" type="hidden" />
						<input id="wxgroup_groupName" style="width: 210px;" type="text" />
					</td>
				</tr>
			</table>
		</div>
		<%-- 下载使用的iframe --%>
		<iframe src="" name="temp_frame" id="temp_frame" style="display:none;"></iframe>
	</body>
</html>