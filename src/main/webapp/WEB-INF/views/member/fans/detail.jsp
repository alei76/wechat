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
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.dict.js"></script>
		<script type="text/javascript">
			var rootPath = '/info/fans';
			//定义消息记录grid的自定义view
			var msg_his_view = $.extend({},$.fn.datagrid.defaults.view,{
				onAfterRender : function(target){
					$.fn.datagrid.defaults.view.onAfterRender.call(this,target);
					var opts = $(target).datagrid('options');
					var vc = $(target).datagrid('getPanel').children('div.datagrid-view');
					vc.children('div.datagrid-empty').remove();
					if (!$(target).datagrid('getRows').length){
						var d = $('<div class="datagrid-empty"></div>').html(opts.emptyMsg || 'no records').appendTo(vc);
						d.css({
							position:'absolute',
							left:0,
							top:50,
							width:'100%',
							textAlign:'center'
						});
					}
				},
				renderRow : function(target, fields, frozen, rowIndex, rowData){
					//获取datagrid配置参数
					//var opts = $.data(target, "datagrid").options;
					var cc = [];
					for (var i = 0; i < fields.length; i++) {
						var findFiled = fields[i];
			            var col = $(target).datagrid("getColumnOption", findFiled);
			            if(col){
							var strFindStyler = "width:" + (col.boxWidth) + "px;padding:0px 50px;";
							strFindStyler += "text-align:" + (col.align || "left") + ";";
							cc.push("<td style=\"" + strFindStyler + "\">");
							cc.push('<div style="text-align:left;">'+ rowData.SEND_TIME + '&nbsp;&nbsp;&nbsp;' + rowData.NICK_NAME + '：&nbsp;&nbsp;&nbsp;' + rowData.CONTENT+'</div>');
							cc.push('<div style="text-align:right;">'+replaceHyperLink(rowData.REPLY_CONTENT) + '&nbsp;&nbsp;&nbsp;' + rowData.REPLY_MSG_SOURCE_CN + '：&nbsp;&nbsp;&nbsp;' + rowData.REPLY_CREATE_TIME + '</div>');
							cc.push('</td>');
						}
					}
					return cc.join('');
				}
			});
			//将消息内容里的A标签处理，去掉拦截前缀路径，增加点击打开新标签(_blank)
			function replaceHyperLink(content){
				var re = new RegExp("<a\\s.*?href\\s*=\\s*\'?\"?([^(\\s\")]+)\\s*\'?\"?[^>]*>(.*?)</a>","g");
				var arr;
				while((arr = re.exec(content)) !=null){
					var link = arr[0];
					var url = arr[1];
					var text = arr[2];
					var param = parseURL(url);
					var redirect = param.r;
					if(redirect !== undefined){
						redirect = decodeURIComponent(redirect);
						var html = "<a target=\"_blank\" href=\""+redirect+"\">" + text + "</a>";
						content = content.replace(link, html);
					}
				}
				return content;
			}

			$(function() {
				var clear = null;
				//消息汇总页的消息来源下拉框
				$("#message_source").dict({
					dictCode : "MESSAGE_SOURCE",
					onLoadSuccess : function() {
						if (clear == null) {
							clear = new Object();
							clear.catalogCode = "MESSAGE_SOURCE";
							clear.catalogDesc = "消息来源";
							clear.enabled = "01";
							clear.itemCode = "00";
							clear.itemDesc = "请选择";
							clear.itemOrder = 0;
							var store = $("#message_source").combobox('getData');
							store.unshift(clear);
							$("#message_source").combobox('loadData', store);
						}
					}
				});
			});
			//关闭本widow对象
			function close_target() {
				target.window("close");
			}
			//tab 切换时间
			function tab_change(title, index) {
				if (index == 0) {//消息记录
					history_msg();
				} else if (index == 2) {//访问记录
					collect_msg();
				} else if (index == 1) {//消息汇总
					webview_his();
				}
			}
			//消息记录
			function history_msg() {
				var fansId = $("#fansId").val();

				$('#message_his_grid').datagrid({
					url : global_ctxPath + rootPath + '/grid',
					method : 'post',
					emptyMsg : '没有记录',
					fit : true,
					border : false,
					fitColumns : true,
					singleSelect : true,
					pagination : true,
					rownumbers : false,
					showHeader : false,
					pageSize : 10,
					pageList : [ 10, 20, 30 ],
					remoteSort : false,
					idField : 'MESSAGE_HIS_ID',
					loadMsg : '数据装载中......',
					view : msg_his_view,
					queryParams : {
						"fansId" : fansId
					},
					columns : [ [ {
						field : 'MESSAGE_HIS_ID',
						title : '消息内容',
						width : 25
					} ] ]
				});

				$('#message_his_grid').datagrid('getPager').pagination({
					displayMsg : '当前显示从{from}到{to}共{total}记录',
					beforePageText : '第',
					afterPageText : '页 共 {pages} 页',
					onBeforeRefresh : function(pageNumber, pageSize) {
						$(this).pagination('loading');
						$(this).pagination('loaded');
					}
				});
			}
			//访问记录
			function collect_msg() {
				var fansId = $("#fansId").val();
				$('#message_collect_grid').datagrid({
					url : global_ctxPath + rootPath + '/collect',
					view : datagrid_view,
					method : 'post',
					emptyMsg : '没有记录',
					fit : true,
					border : false,
					fitColumns : true,
					singleSelect : true,
					pagination : true,
					rownumbers : true,
					showHeader : true,
					pageSize : 10,
					pageList : [ 10, 20, 30 ],
					remoteSort : false,
					idField : 'MESSAGE_HIS_ID',
					loadMsg : '数据装载中......',
					queryParams : {
						"fansId" : fansId
					},
					columns : [ [ {
						field : 'REPLY_MSG_SOURCE_CN',
						title : '消息来源',
						width : 20,
						align : 'center'
					}, {
						field : 'CONTENT',
						title : '消息内容',
						width : 60,
						align : 'center',
						formatter : function(value, row, index) {
							return replaceHyperLink(value);
						}
					}, {
						field : 'SEND_TIME',
						title : '消息时间',
						width : 20,
						align : 'center'
					} ] ]
				});

				$('#message_collect_grid').datagrid('getPager').pagination({
					displayMsg : '当前显示从{from}到{to}共{total}记录',
					beforePageText : '第',
					afterPageText : '页 共 {pages} 页',
					onBeforeRefresh : function(pageNumber, pageSize) {
						$(this).pagination('loading');
						$(this).pagination('loaded');
					}
				});
			}
			//消息汇总
			function webview_his() {
				var fansId = $("#fansId").val();
				$('#website_his_grid').datagrid({
					url : global_ctxPath + rootPath + '/website',
					view : datagrid_view,
					method : 'post',
					emptyMsg : '没有记录',
					fit : true,
					border : false,
					fitColumns : true,
					singleSelect : true,
					pagination : true,
					rownumbers : true,
					showHeader : true,
					pageSize : 10,
					pageList : [ 10, 20, 30 ],
					remoteSort : false,
					idField : 'MESSAGE_HIS_ID',
					loadMsg : '数据装载中......',
					queryParams : {
						"fansId" : fansId
					},
					columns : [ [ {
						field : 'VISIT_DATE',
						title : '访问日期',
						width : 15,
						align : 'center'
					}, {
						field : 'VIEW_NAME',
						title : '页面名称',
						width : 15,
						align : 'center'
					}, {
						field : 'VIEW_HREF',
						title : '访问地址',
						width : 60,
						align : 'center'
					}, {
						field : 'CLICKS',
						title : '访问次数',
						width : 10,
						align : 'center'
					} ] ]
				});

				$('#website_his_grid').datagrid('getPager').pagination({
					displayMsg : '当前显示从{from}到{to}共{total}记录',
					beforePageText : '第',
					afterPageText : '页 共 {pages} 页',
					onBeforeRefresh : function(pageNumber, pageSize) {
						$(this).pagination('loading');
						$(this).pagination('loaded');
					}
				});
			}
			//消息汇总查询事件
			function search_collect() {
				var msgSource = $("#message_source").combobox("getValue");//消息来源
				var fansId = $("#fansId").val();
				var startDay = $("#startDay").val();//开始日期
				var endDay = $("#endDay").val();//结束日期
				if(validate_date(startDay, endDay)){//日期验证
					var param = {};
					param.fansId = fansId;
					if (msgSource != "00") {
						param.msgSource = msgSource;
					}
					if (startDay != "") {
						param.startDay = startDay;
					}
					if (endDay != "") {
						param.endDay = endDay;
					}
					$('#message_collect_grid').datagrid('load', param);
				}
			}
			//日期验证
			function validate_date(startDay, endDay){
				if(startDay != "" && endDay != ""){
					var diff = checkEndTime(startDay, endDay);
					if(diff < 0){
						$.messager.alert('提示', "结束日期不能早于开始日期", 'info');
						return false;
					}
				}
				return true;
			}
			//访问记录查询事件
			function search_website() {
				var fansId = $("#fansId").val();
				var startDay = $("#view_startDay").val();//开始日期
				var endDay = $("#view_endDay").val();//结束日期
				if(validate_date(startDay, endDay)){//日期验证
					var param = {};
					param.fansId = fansId;
					if (startDay != "") {
						param.startDay = startDay;
					}
					if (endDay != "") {
						param.endDay = endDay;
					}
					$('#website_his_grid').datagrid('load', param);
				}
			}
			//解析url路径上的参数
			function parseURL(url) {
				var a = document.createElement('a');
				a.href = url;
				var ret = {}, seg = a.search.replace(/^\?/, '').split('&'), len = seg.length, i = 0, s;
				for (; i < len; i++) {
					if (!seg[i]) {
						continue;
					}
					s = seg[i].split('=');
					ret[s[0]] = s[1];
				}
				return ret;
			}
			//从腾讯微信同步粉丝的个人资料
			function async_info(){
				$.messager.progress(); 
				var fansId = $("#fansId").val();
				$.ajax({
					type : "post",
					url : global_ctxPath + rootPath + '/asyncfansinfo',
					async : true,
					dataType : "json",
					data : {
						"fansId" : fansId
					},
					success : function(result) {
						if(result.success){
							var fansInfo = result.fans;
							fill_data(fansInfo);
						}
						$.messager.alert('提示', result.message, 'info');
					}
				});
				$.messager.progress('close');
			}
			//填充粉丝信息
			function fill_data(fansInfo){
				$("#fansId").val(fansInfo.fansId);
				$("#headimg_url").attr("src", fansInfo.headimgUrl);
				$("#td_nickname").html(fansInfo.nickName);
				$("#td_openId").html(fansInfo.openId);
				var sex = trans_sex(fansInfo.sex);
				$("#td_sex").html(sex);
				$("#td_country").html(fansInfo.country);
				$("#td_province").html(fansInfo.province);
				$("#td_city").html(fansInfo.city);
				var language = trans_language(fansInfo.language);
				$("#td_language").html(language);
				var subTime = trans_date(fansInfo.subTime);
				$("#td_subtime").html(subTime);
				$("#td_groupCn").html(fansInfo.groupCn);
				var member = fansInfo.wxFansMember;
				$("#td_fullName").html(member.fullName);
				$("#td_mobile").html(member.mobile);
				$("#td_bindTime").html(member.createTime);
			}
			//格式化关注日期
			function trans_date(subTime){
				var time = new Date(subTime*1000);
				return time.format("yyyy-MM-dd HH:mm:ss");
			}
			//转换性别
			function trans_sex(sex){
				if(sex == "1"){
					return "男";
				} else if(sex == "2"){
					return "女";
				} else if(sex == "0"){
					return "未知";
				} else {
					return sex;
				}
			}
			//转换语言
			function trans_language(language){
				if(language == "zh_CN"){
					return "简体中文";
				} else if(language == "zh_TW"){
					return "繁体中文";
				} else if(language == "en"){
					return "英文";
				} else if(language == "ja"){
					return "日语";
				} else {
					return language;
				}
			}
		</script>
	</head>
	<body class="easyui-layout">
		<div data-options="region:'north',border:false,split:false,collapsed:false" style="height: 70px;">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td><a onclick="close_target()" title="返回" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-back'"></a></td>
					<td rowspan="2"><img id="headimg_url" width="45px" height="45px" src="${wxFans.headimgUrl }"/><input type="hidden" id="fansId" value="${fansId }"/></td>
					<td>昵称：</td>
					<td id="td_nickname">${wxFans.nickName }</td>
					<td>OPEN_ID：</td>
					<td id="td_openId">${wxFans.openId }</td>
					<td>性别：</td>
					<td id="td_sex">
						<c:choose>
							<c:when test="${wxFans.sex eq '1'}">男</c:when>
							<c:when test="${wxFans.sex eq '2'}">女</c:when>
							<c:when test="${wxFans.sex eq '0'}">未知</c:when>
							<c:otherwise>${wxFans.sex}</c:otherwise>
						</c:choose>
					</td>
					<td>国家：</td>
					<td id="td_country">${wxFans.country }</td>
					<td>省份：</td>
					<td id="td_province">${wxFans.province }</td>
					<td>城市：</td>
					<td id="td_city">${wxFans.city }</td>
				</tr>
				<tr>
					<td><a onclick="async_info()" title="刷新" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload'"></a></td>
					<td>语言：</td>
					<td id="td_language">
						<c:choose>
							<c:when test="${wxFans.language eq 'zh_CN'}">简体中文</c:when>
							<c:when test="${wxFans.language eq 'zh_TW'}">繁体中文</c:when>
							<c:when test="${wxFans.language eq 'en'}">英文</c:when>
							<c:when test="${wxFans.language eq 'ja'}">日语</c:when>
							<c:otherwise>${wxFans.language }</c:otherwise>
						</c:choose>
					</td>
					<td>关注时间：</td>
					<td id="td_subtime">${covisint:parse(wxFans.subTime, 'yyyy-MM-dd HH:mm:ss') }</td>
					<td>所在分组：</td>
					<td id="td_groupCn">${wxFans.groupCn }</td>
					<td>用户姓名：</td>
					<td id="td_fullName">${wxFans.wxFansMember.fullName }</td>
					<td>手机号：</td>
					<td id="td_mobile">${wxFans.wxFansMember.mobile }</td>
					<td>绑定时间：</td>
					<td id="td_bindTime">${wxFans.wxFansMember.createTime }</td>
				</tr>
			</table>
		</div>
		<div data-options="region:'center',border:false">
			<div class="easyui-tabs" data-options="fit:true,border:false,onSelect:tab_change">
				<div id="user_detail_his_tab" title="消息记录" data-options="border:false,selected:true">
					<table id="message_his_grid"></table>
				</div>
				<div title="访问记录">
					<div class="easyui-layout" data-options="fit:true,border:false">
						<div data-options="region:'north',border:false,split:false,collapsible:false" style="height: 36px;overflow-y: hidden;">
							<table width="100%" id="website_his_tools_bar">
								<tr>
									<td width="350px">访问时间：<input class="Wdate" style="width:100px" id="view_startDay" type="text" onfocus="WdatePicker({maxDate:'%y-%M-{%d}',readOnly:true})"/>&nbsp;--&nbsp;<input id="view_endDay" style="width:100px" class="Wdate" type="text" onfocus="WdatePicker({maxDate:'%y-%M-{%d}',readOnly:true})"/></td>
									<td><a href="javascript:void(0);" onclick="search_website()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
								</tr>
							</table>
						</div>
						<div data-options="region:'center',border:false,cache:false">
							<table id="website_his_grid"></table>
						</div>
					</div>
				</div>
				<div title="消息汇总">
					<div class="easyui-layout" data-options="fit:true,border:false">
						<div data-options="region:'north',border:false,split:false,collapsible:false" style="height: 36px;overflow-y: hidden;">
							<table width="100%" id="collect_tools_bar">
								<tr>
									<td width="350px">消息时间：<input class="Wdate" style="width:100px" id="startDay" type="text" onfocus="WdatePicker({maxDate:'%y-%M-{%d}',readOnly:true})"/>&nbsp;--&nbsp;<input id="endDay" style="width:100px" class="Wdate" type="text" onfocus="WdatePicker({maxDate:'%y-%M-{%d}',readOnly:true})"/></td>
									<td width="300px">消息来源：<input id="message_source" type="text"/></td>
									<td><a href="javascript:void(0);" onclick="search_collect()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
								</tr>
							</table>
						</div>
						<div data-options="region:'center',border:false,cache:false">
							<table id="message_collect_grid"></table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>