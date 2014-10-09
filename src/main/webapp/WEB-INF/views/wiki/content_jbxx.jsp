<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- bodytext -->
<p>在关注者与公众号产生消息交互后，公众号可获得关注者的OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的。对于不同公众号，同一用户的openid不同）。公众号可通过本接口来根据OpenID获取用户基本信息，包括昵称、头像、性别、所在城市、语言和关注时间。</p>
<p><b>请注意，如果开发者有在多个公众号，或在公众号、移动应用之间统一用户帐号的需求，需要前往微信开放平台（open.weixin.qq.com）绑定公众号后，才可利用UnionID机制来满足上述需求。</b></p>
<p>UnionID机制说明：</p>
<pre>开发者可通过OpenID来获取用户基本信息。特别需要注意的是，如果开发者拥有多个移动应用、网站应用和公众帐号，可通过获取用户基本信息中的unionid来区分用户的唯一性，因为只要是同一个微信开放平台帐号下的移动应用、网站应用和公众帐号，用户的unionid是唯一的。换句话说，同一用户，对同一个微信开放平台下的不同应用，unionid是相同的。</pre>
<h3>
	<span class="mw-headline"><b>获取用户基本信息（包括UnionID机制）</b></span>
</h3>
<p>开发者可通过OpenID来获取用户基本信息。请使用https协议。</p>
<p><b>接口调用请求说明</b></p>
<pre>
http请求方式: GET
<a rel="nofollow" class="external free" href="http://apigatewayqa.sgmlink.com/wechat/api-wechat/cgi-bin/user/info?accountId=ACCOUNT_ID&amp;openid=OPENID" target="_blank">http://apigatewayqa.sgmlink.com/wechat/api-wechat/cgi-bin/user/info?accountId=ACCOUNT_ID&amp;openid=OPENID</a>
</pre>
<p><b>参数说明</b></p>
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
			<td>openid</td>
			<td>是</td>
			<td>普通用户的标识，对当前公众号唯一</td>
		</tr>
	</tbody>
</table>
<p>
	<b>返回说明</b>
</p>
<p>正常情况下，微信会返回下述JSON数据包给公众号：</p>
<pre>{
    "subscribe": 1, 
    "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M", 
    "nickname": "Band", 
    "sex": 1, 
    "language": "zh_CN", 
    "city": "广州", 
    "province": "广东", 
    "country": "中国", 
    "headimgurl": "<a rel="nofollow" class="external free"
		href="http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0"
		target="_blank">http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0</a>", 
   "subscribe_time": 1382694957,
   "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
}
</pre>
<p><b>参数说明</b></p>
<table width="640px" border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 240px">参数</th>
			<th>说明</th>
		</tr>
		<tr>
			<td>subscribe</td>
			<td>用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。</td>
		</tr>
		<tr>
			<td>openid</td>
			<td>用户的标识，对当前公众号唯一</td>
		</tr>
		<tr>
			<td>nickname</td>
			<td>用户的昵称</td>
		</tr>
		<tr>
			<td>sex</td>
			<td>用户的性别，值为1时是男性，值为2时是女性，值为0时是未知</td>
		</tr>
		<tr>
			<td>city</td>
			<td>用户所在城市</td>
		</tr>
		<tr>
			<td>country</td>
			<td>用户所在国家</td>
		</tr>
		<tr>
			<td>province</td>
			<td>用户所在省份</td>
		</tr>
		<tr>
			<td>language</td>
			<td>用户的语言，简体中文为zh_CN</td>
		</tr>
		<tr>
			<td>headimgurl</td>
			<td>
				用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
			</td>
		</tr>
		<tr>
			<td>subscribe_time</td>
			<td>用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间</td>
		</tr>
		<tr>
			<td>unionid</td>
			<td>只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：<a rel="nofollow"
				class="external text"
				href="https://open.weixin.qq.com/cgi-bin/frame?t=resource/res_main_tmpl&amp;lang=zh_CN&amp;target=res/app_wx_login"
				target="_blank">获取用户个人信息（UnionID机制）</a>
			</td>
		</tr>
	</tbody>
</table>
<p>错误时微信会返回错误码等信息，JSON数据包示例如下（该示例为AppID无效错误）:</p>
<pre>{"errcode":40013,"errmsg":"invalid appid"}</pre>
<!-- /bodytext -->
