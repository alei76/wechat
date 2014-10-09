<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- bodytext -->
<p><font style="color:red;font-weight:bold;">此接口不推荐使用。</font>调用本平台的其他接口时，无需access_token参数，access_token的状态推荐由本平台管理。</p>
<p>access_token是公众号的全局唯一票据，公众号调用各接口时都需使用access_token。正常情况下<b>access_token有效期为7200秒</b>，重复获取将导致上次获取的access_token失效。<b>由于获取access_token的api调用次数非常有限，建议开发者全局存储与更新access_token，频繁刷新access_token会导致api调用受限，影响自身业务。</b></p>
<p>公众号可以使用AccountID调用本接口来获取access_token。AccountID可在系统管理员处获得。</p>
<p><b>接口调用请求说明</b></p>
<pre>
http请求方式: GET
<a rel="nofollow" class="external free" href="http://apigatewayqa.sgmlink.com/wechat/api-wechat/cgi-bin/token?accountId=ACCOUNT_ID" target="_blank">http://apigatewayqa.sgmlink.com/wechat/api-wechat/cgi-bin/token?accountId=ACCOUNT_ID</a>
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
	</tbody>
</table>
<p><b>返回说明</b></p>
<p>正常情况下，微信会返回下述JSON数据包给公众号：</p>
<pre>{"access_token":"ACCESS_TOKEN","expires_in":7200}</pre>
<table width="640px" border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 240px">参数</th>
			<th>说明</th>
		</tr>
		<tr>
			<td>access_token</td>
			<td>获取到的凭证</td>
		</tr>
		<tr>
			<td>expires_in</td>
			<td>凭证有效时间，单位：秒</td>
		</tr>
	</tbody>
</table>
<p><br> 错误时微信会返回错误码等信息，JSON数据包示例如下（该示例为AccountID无效错误）:</p>
<pre>{"errcode":40013,"errmsg":"AccountId 不存在"}</pre>
<!-- /bodytext -->