<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%-- 主页面 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<title>公众平台</title>
		<link href="<%=request.getContextPath()%>/images/favicon.ico" rel="Shortcut Icon" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/frameset/head.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/frameset/base.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui/easyui.css"/>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.easyui.min.js"></script>
		<script type="text/javascript">
			var global_ctxPath = '<%=request.getContextPath()%>';
		</script>
		<script type="text/javascript">
		
			var _temp_account_id = null;//临时微信号
			//左边菜单点击事件
			function view_menu(obj, url){
				$(".menu_item").removeClass("selected");//去除其他菜单选中样式
				$(obj).parent().addClass("selected");//增加当前菜单选中样式
				$("#mainframe").attr('src', url);//iframe路径跳转
			}
			//当前微信号更改事件
			function onAccountSelect(newValue, oldValue){
				$.messager.defaults = { ok: "确定", cancel: "取消" };//设置easyui对话框按钮文字
				if(_temp_account_id != newValue){//比较临时微信号跟选中的微信号，如果不相等
					$.messager.confirm('确认','是否要切换当前管理的微信公众号？',function(r) {//弹出对话框
						if(r){//确认更换
							$.messager.progress(); 
							//更改当前微信号
							$.ajax({
								type : "post",
								url : global_ctxPath + '/kernel/account/changeaccount',
								async : true,
								data : {
									"accountId" : newValue
								},
								dataType : "json",
								success : function(result) {
									if(result.success){
										location.href = global_ctxPath;//更改成功，刷新整页
									}
								}
							});
						}else{//取消更换当前微信号
							_temp_account_id = oldValue;//将临时微信号设置为之前选中的号
							$("#account_select").combobox("setValue", oldValue);//选择之前选中的微信号
						}
					});
				}//如果相等，什么都不做
			}
			
			$(function(){
				var first_menu = $("#menuBar dl:first dd:first a:first");//选中第一个菜单
				var url = first_menu.attr("href");
				var obj = first_menu.eq(0);
				view_menu(obj, url);//跳转路径
				//菜单的点击事件
				$('.menu_item').on('click', 'a', function(){
					$(".menu_item").removeClass("selected");
					$(this).parent().addClass("selected");
				});
			});
		</script>
	</head>
	<body>
		<!-- header -->
		<div class="head" id="header">
			<div class="head_box">
				<div class="inner wrp">
					<h1 class="logo">
						<a href="<%=request.getContextPath()%>/index" title="微信平台"></a>
					</h1>
					<div class="account">
						<div class="account_meta account_info">
							<a href="javascript:;" class="nickname">${currentUser.fullName}</a>
						</div>
						<c:if test="${currentUser.userType eq '02' }">
						<div class="account_meta account_inbox" id="accountArea">
							<select id="account_select" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,onChange:onAccountSelect">
						        <c:forEach items="${currentUser.accountList }" var="account">
						            <option value="${account.accountId }" <c:if test="${account.accountId eq currentUser.currentAccount}">selected</c:if>>${account.name }</option>
						        </c:forEach>
					        </select>
                        </div>
                        </c:if>
						<div class="account_meta account_logout">
							<a id="logout" href="<%=request.getContextPath()%>/logout">退出</a><!-- 登出 -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- body -->
		<div id="body" class="body page_index">
			<div id="js_container_box" class="container_box cell_layout side_l">
				<!-- left -->
				<div class="col_side">
					<!-- 菜单 -->
					<%-- 获取用户菜单 --%>
					<c:set var="allMenu" value="${covisint:getMenu() }"/>
					<div class="menu_box" id="menuBar">
						<%-- 循环菜单 --%>
						<c:forEach items="${allMenu }" var="parent" varStatus="p_index">
							<c:if test="${empty parent.parentId }">
								<c:set var="parentId" value="${parent.menuId }"/>
									<dl class="wxmenu <c:if test="${p_index.first }">no_extra</c:if>">
										<dt class="menu_title">
											<i class="icon_menu function"></i>${parent.menuName }
										</dt>
										<c:forEach items="${allMenu }" var="child" varStatus="c_index">
											<c:if test="${not empty child.parentId }">
												<c:if test="${parentId eq child.parentId }">
													<dd class="menu_item ">
														<a target="mainframe" href="${ pageContext.request.contextPath}${child.menuUrl }">${child.menuName }</a>
													</dd>
												</c:if>
											</c:if>
										</c:forEach>
									</dl>
							</c:if>
						</c:forEach>
					</div>
				</div>
				<!-- right -->
				<div class="col_main">
					<iframe id="mainframe" name="mainframe" src="" frameborder="0" height="100%" width="100%" allowTransparency="true" align="center" scrolling="auto"></iframe>
				</div>
			</div>
		</div>
		<!-- footer -->
		<div class="foot" id="footer">
			<ul class="links ft">
				<li class="links_item">
					<p class="copyright">Copyright © 2012-2014 Shanghai GM. All Rights Reserved.</p>
				</li>
			</ul>
		</div>
	</body>
</html>