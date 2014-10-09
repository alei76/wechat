<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- bodytext -->
<p></p>
<h3>
	<span class="mw-headline"><b>第一步：提供微信公众号配置信息</b></span>
</h3>
<p>1、登录 <a href="https://mp.weixin.qq.com/" target="_blank">微信公众平台</a>（https://mp.weixin.qq.com/）。</p>
<p>2、在公众号设置中获取微信号。</p>
<p><img width="875" src="${ pageContext.request.contextPath}/images/wiki/accountNo.png" ></p>
<p>3、在<u>开发者中心</u>中获取<b>AppId</b>及<b>AppSecret</b>。</p>
<p><img width="875" src="${ pageContext.request.contextPath}/images/wiki/appId.png" ></p>
<p>4、提交给微信平台后台管理员。</p>
<h3>
	<span class="mw-headline"><b>第二步、到微信公众平台设置接口。</b></span>
</h3>
<p>1、使用后台管理员分配的账号登陆<a href="http://apigatewayqa.sgmlink.com/wechat/" target="_blank">微信平台</a>。</p>
<p>2、获取接口配置信息。</p>
<p><img width="875" src="${ pageContext.request.contextPath}/images/wiki/info.png" ></p>
<p>3、登录 <a href="https://mp.weixin.qq.com/" target="_blank">微信公众平台</a>（https://mp.weixin.qq.com/）。</p>
<p>4、开发者中心 → 修改配置。</p>
<p><img width="875" src="${ pageContext.request.contextPath}/images/wiki/config.png" ></p>
<p>5、填写URL及Token。</p>
<p><img width="875" src="${ pageContext.request.contextPath}/images/wiki/commit.png" ></p>
<h3>
	<span class="mw-headline"><b>第三步、配置页面授权回调。</b></span>
</h3>
<p>1、配置授权回调页面域名，才可使用OAuth2.0网页授权功能。</p>
<p><img width="875" src="${ pageContext.request.contextPath}/images/wiki/oauth.png" ></p>
<!-- /bodytext -->
