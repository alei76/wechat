<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- bodytext -->
<p>
	如果用户在微信中（Web微信除外）访问公众号的第三方网页，公众号开发者可以通过此接口获取当前用户基本信息（包括昵称、性别、城市、国家）。利用用户信息，可以实现体验优化、用户来源统计、帐号绑定、用户身份鉴权等功能。<b>请注意，“获取用户基本信息接口是在用户和公众号产生消息交互时，才能根据用户OpenID获取用户基本信息，而网页授权的方式获取用户基本信息，则无需消息交互，只是用户进入到公众号的网页，就可弹出请求用户授权的界面，用户授权后，就可获得其基本信息（此过程甚至不需要用户已经关注公众号。）”</b>
</p>
<p>微信OAuth2.0授权登录让微信用户使用微信身份安全登录第三方应用或网站，在微信用户授权登录已接入微信OAuth2.0的第三方应用后，第三方可以获取到用户的接口调用凭证（access_token），通过access_token可以进行微信开放平台授权关系接口调用，从而可实现获取微信用户基本开放信息和帮助用户实现基础开放功能等。
</p>
<p>具体而言，网页授权流程分为四步：</p>
<ol>
	<li>引导用户进入授权页面同意授权，获取code</li>
	<li>通过code换取网页授权access_token（与基础支持中的access_token不同）</li>
	<li>如果需要，开发者可以刷新网页授权access_token，避免过期</li>
	<li>通过网页授权access_token和openid获取用户基本信息</li>
</ol>
<table class="toc" id="toc">
	<tbody>
		<tr>
			<td>
				<div id="toctitle">
					<h2>目录</h2>
				</div>
				<ul>
					<li class="toclevel-1 tocsection-1">
						<a href="javascript:;"><span class="tocnumber">1</span><span class="toctext">第一步：用户同意授权，获取code</span></a>
					</li>
					<li class="toclevel-1 tocsection-2">
						<a href="javascript:;"><span class="tocnumber">2</span><span class="toctext">第二步：通过code换取网页授权access_token</span></a>
					</li>
					<li class="toclevel-1 tocsection-4">
						<a href="javascript:;"><span class="tocnumber">3</span><span class="toctext">第三步：拉取用户信息(需scope为snsapi_userinfo)</span></a>
					</li>
				</ul>
			</td>
		</tr>
	</tbody>
</table>
<h3>
	<span class="mw-headline"><b>第一步：用户同意授权，获取code</b></span>
</h3>
<p>在确保微信公众账号拥有授权作用域（scope参数）的权限的前提下（服务号获得高级接口后，默认带有scope参数中的snsapi_base和snsapi_userinfo），引导关注者打开页面。
</p>
<p>
	<b>用户同意授权后</b>
</p>
<p>如果用户同意授权，页面将跳转至
	redirect_uri/?code=CODE&amp;state=STATE。若用户禁止授权，则重定向后不会带上code参数。
</p>
<pre>code说明 ：
code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
</pre>
<h3>
	<span class="mw-headline"><b>第二步：通过code换取网页授权access_token</b></span>
</h3>
<p>首先请注意，这里通过code换取的网页授权access_token,与基础支持中的access_token不同。公众号可通过下述接口来获取网页授权access_token。如果网页授权的作用域为snsapi_base，则本步骤中获取到网页授权access_token的同时，也获取到了openid，snsapi_base式的网页授权流程即到此为止。
</p>
<p>
	<b>请求方法</b>
</p>
<pre>获取code后，请求以下链接获取access_token： 
<a rel="nofollow" class="external free" href="http://apigatewayqa.sgmlink.com/wechat/api-wechat/sns/oauth2/access_token?accountId=ACCOUNT_ID&amp;code=CODE" target="_blank">http://apigatewayqa.sgmlink.com/wechat/api-wechat/sns/oauth2/access_token?accountId=ACCOUNT_ID&amp;code=CODE</a>
</pre>
<p>
	<b>参数说明</b>
</p>
<table width="640px" border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 120px">参数</th>
			<th style="width: 120px">是否必须</th>
			<th>说明</th>
		</tr>
		<tr>
			<td>accountId</td>
			<td>是</td>
			<td>SGM微信平台账户唯一凭证</td>
		</tr>
		<tr>
			<td>code</td>
			<td>是</td>
			<td>填写第一步获取的code参数</td>
		</tr>
	</tbody>
</table>
<p>
	<b>返回说明</b>
</p>
<p>正确时返回的JSON数据包如下：</p>
<pre>{
   "access_token":"ACCESS_TOKEN",
   "expires_in":7200,
   "refresh_token":"REFRESH_TOKEN",
   "openid":"OPENID",
   "scope":"SCOPE"
}
</pre>
<table border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 180px">参数</th>
			<th style="width: 470px">描述</th>
		</tr>
		<tr>
			<td>access_token</td>
			<td>网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同</td>
		</tr>
		<tr>
			<td>expires_in</td>
			<td>access_token接口调用凭证超时时间，单位（秒）</td>
		</tr>
		<tr>
			<td>refresh_token</td>
			<td>用户刷新access_token</td>
		</tr>
		<tr>
			<td>openid</td>
			<td>用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID</td>
		</tr>
		<tr>
			<td>scope</td>
			<td>用户授权的作用域，使用逗号（,）分隔</td>
		</tr>
	</tbody>
</table>
<p>
	<br> 错误时微信会返回JSON数据包如下（示例为Code无效错误）:
</p>
<pre>{"errcode":40029,"errmsg":"invalid code"}</pre>
<h3>
	<span class="mw-headline"><b>第三步：拉取用户信息(需scope为 snsapi_userinfo)</b></span>
</h3>
<p>如果网页授权作用域为snsapi_userinfo，则此时开发者可以通过access_token和openid拉取用户信息了。</p>
<p>
	<b>请求方法</b>
</p>
<pre>http：GET（请使用https协议）
<a rel="nofollow" class="external free" href="http://apigatewayqa.sgmlink.com/wechat/api-wechat/sns/userinfo?access_token=ACCESS_TOKEN&amp;openid=OPENID" target="_blank">http://apigatewayqa.sgmlink.com/wechat/api-wechat/sns/userinfo?access_token=ACCESS_TOKEN&amp;openid=OPENID</a>
</pre>
<p>
	<b>参数说明</b>
</p>
<table border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 180px">参数</th>
			<th style="width: 470px">描述</th>
		</tr>
		<tr>
			<td>access_token</td>
			<td>网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同</td>
		</tr>
		<tr>
			<td>openid</td>
			<td>用户的唯一标识</td>
		</tr>
	</tbody>
</table>
<p>
	<b>返回说明</b>
</p>
<p>正确时返回的JSON数据包如下：</p>
<pre>{
   "openid":" OPENID",
   " nickname": NICKNAME,
   "sex":"1",
   "province":"PROVINCE"
   "city":"CITY",
   "country":"COUNTRY",
    "headimgurl":    "<a rel="nofollow" class="external free"
		href="http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46"
		target="_blank">http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46</a>", 
	"privilege":[
	"PRIVILEGE1"
	"PRIVILEGE2"
    ]
}
</pre>
<table border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 180px">参数</th>
			<th style="width: 470px">描述</th>
		</tr>
		<tr>
			<td>openid</td>
			<td>用户的唯一标识</td>
		</tr>
		<tr>
			<td>nickname</td>
			<td>用户昵称</td>
		</tr>
		<tr>
			<td>sex</td>
			<td>用户的性别，值为1时是男性，值为2时是女性，值为0时是未知</td>
		</tr>
		<tr>
			<td>province</td>
			<td>用户个人资料填写的省份</td>
		</tr>
		<tr>
			<td>city</td>
			<td>普通用户个人资料填写的城市</td>
		</tr>
		<tr>
			<td>country</td>
			<td>国家，如中国为CN</td>
		</tr>
		<tr>
			<td>headimgurl</td>
			<td>
				用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
			</td>
		</tr>
		<tr>
			<td>privilege</td>
			<td>用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）</td>
		</tr>
	</tbody>
</table>
<p>
	<br> 错误时微信会返回JSON数据包如下（示例为openid无效）:
</p>
<pre>{"errcode":40003,"errmsg":" invalid openid "}
</pre>
<!-- /bodytext -->
