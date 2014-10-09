<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%-- 登陆页面 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
		<link href="<%=request.getContextPath()%>/images/favicon.ico" rel="Shortcut Icon" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login/page_login.css"/>
		<title>公众平台</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.11.0.min.js"></script>
		<!--[if gte IE 9]>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.placeholder.plugin.js"></script>
		<script type="text/javascript">
			$(function(){$('input[placeholder]').placeholder();});
		</script>
		<![endif]-->
		<script type="text/javascript">
			var global_ctxPath = '<%=request.getContextPath()%>'; 
		</script>
		<script type="text/javascript">
			$(function(){
				$("#j_username").focus().keydown(function(event) {
					if (event.which == 13) {//改回车事件
						$("#j_password").focus();//用户名回车，密码获得焦点
					}
				});
				
				$("#j_password").keydown(function(event) {
					if (event.which == 13) {//改回车事件
						$("#j_captcha").focus();//密码回车，验证码获得焦点
					}
				});
				
				$("#j_captcha").keydown(function(event) {
					if (event.which == 13) {//改回车事件
						submitForm();//验证码回车，提交表单
					}
				});
				//如果在iframe中，则跳转上层页面
				if (self != top) {
					window.parent.location.href = global_ctxPath + "/login";
				}
			});
			//获取验证码
			function changeValidate(){
				$("#validateCode").attr("src", global_ctxPath + "/captcha?abc=" + Math.random());
			}
			//去除空格（左右包括全角半角）
			function newtrim(text) {
				var strTrim = text.replace(/(^\s*)|(\s*$)/g, "");
				strTrim = strTrim.replace(/^[\s　\t]+|[\s　\t]+$/, "");
				var strf = strTrim;
				strf = strf.replace(/(^\s*)|(\s*$)/g, "");
				strf = strf.replace(/^[\s　\t]+|[\s　\t]+$/, "");
				return strf;
			}
			//提交表单，登陆操作
			function submitForm() {
				//开始非空验证
				var unv = $('#j_username').val() != "";
				var pwv = $('#j_password').val() != "";
				var cap = $('#j_captcha').val() != "";
				if(!unv){
					$('#j_username').focus();
					return false;
				}
				if(!pwv){
					$('#j_password').focus();
					return false;
				}
				if(!cap){
					$('#j_captcha').focus();
					return false;
				}
				$("#j_loginform").submit();//表单提交
			}
		</script>
	</head>
<body>
	<div id="header">
		<div class="wrapper">
			<a href="<%=request.getContextPath()%>/index" class="dib">
				<img src="<%=request.getContextPath()%>/images/wechat/logo.png"/>
			</a>
		</div>
	</div>
	<div id="banner">
		<div class="wrapper">
			<div class="login-panel">
				<h3>登录</h3>
				<div class="login-mod">
					<c:if test="${not empty message }">
						<div class="login-err-panel">
							<span class="vm">${message }</span>
						</div>
					</c:if>
					<form:form modelAttribute="credentials" method="post" action="${ pageContext.request.contextPath}/login" class="login-form login_on_un" id="j_loginform">
						<div class="login-un">
							<span class="icon-wrapper"><i class="icon_login un"></i></span>
							<form:input type="text" path="username" id="j_username" placeholder="用户名" tabindex="1" onblur="this.value=newtrim(this.value);"/>
						</div>
						<div class="login-pwd">
							<span class="icon-wrapper"><i class="icon_login pwd"></i></span>
							<form:input type="password" path="password" id="j_password" tabindex="2" placeholder="密码" />
						</div>
						<div class="login-code-panel" style="border:0px;">
							<form:input type="text" maxlength="4" path="captcha" id="j_captcha" tabindex="3" placeholder="验证码" onblur="this.value=newtrim(this.value);"/>
							<span class="icon-wrapper"><img	id="validateCode" src="${ pageContext.request.contextPath}/captcha" onclick="changeValidate();" /></span>
							<a class="login-change-code" href="javascript:;" onclick="changeValidate();">换一张</a>
						</div>
						<div class="login-btn-panel" style="border:0px;">
							<a tabindex="4" class="login-btn" title="点击登录" href="javascript:;" id="login_button" onclick="submitForm();">登录</a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<span>Copyright&nbsp;© 2012-2014 Tencent. All Rights Reserved.</span>
	</div>
</body>
</html>