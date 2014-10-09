<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- bodytext -->
<table class="toc" id="toc">
	<tbody>
		<tr>
			<td>
				<div id="toctitle">
					<h2>目录</h2>
				</div>
				<ul>
					<li class="toclevel-1 tocsection-1">
						<a href="javascript:;"><span class="tocnumber">1</span><span class="toctext">关注事件</span></a>
					</li>
					<li class="toclevel-1 tocsection-2">
						<a href="javascript:;"><span class="tocnumber">2</span><span class="toctext">自定义菜单事件</span></a>
					</li> 
					<li class="toclevel-1 tocsection-3">
						<a href="javascript:;"><span class="tocnumber">3</span><span class="toctext">点击菜单拉取消息时的事件推送</span></a>
					</li>
				</ul>
			</td>
		</tr>
	</tbody>
</table>
<h2>
	<span class="mw-headline"><b>关注事件</b></span>
</h2>
<p>用户在关注与取消关注公众号时，微信会把这个事件推送到开发者填写的URL。方便开发者给用户下发欢迎消息或者做帐号的解绑。</p>
<p><b>微信服务器在五秒内收不到响应会断掉连接，并且重新发起请求，总共重试三次</b></p>
<p>关于重试的消息排重，推荐使用FromUserName + CreateTime 排重。</p>
<p><b>假如服务器无法保证在五秒内处理并回复，可以直接回复空串，微信服务器不会对此作任何处理，并且不会发起重试。</b></p>
<p>推送XML数据包示例：</p>
<pre>
&lt;xml&gt;
	&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
	&lt;FromUserName&gt;&lt;![CDATA[FromUser]]&gt;&lt;/FromUserName&gt;
	&lt;CreateTime&gt;123456789&lt;/CreateTime&gt;
	&lt;MsgType&gt;&lt;![CDATA[event]]&gt;&lt;/MsgType&gt;
	&lt;Event&gt;&lt;![CDATA[subscribe]]&gt;&lt;/Event&gt;
&lt;/xml&gt;
</pre>
<p>参数说明：</p>
<table border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 180px">参数</th>
			<th style="width: 470px">描述</th>
		</tr>
		<tr>
			<td>ToUserName</td>
			<td><b>开发者</b>微信号</td>
		</tr>
		<tr>
			<td>FromUserName</td>
			<td>发送方帐号（一个OpenID）</td>
		</tr>
		<tr>
			<td>CreateTime</td>
			<td>消息创建时间 （整型）</td>
		</tr>
		<tr>
			<td>MsgType</td>
			<td>消息类型，event</td>
		</tr>
		<tr>
			<td>Event</td>
			<td>事件类型，subscribe(订阅)</td>
		</tr>
	</tbody>
</table>
<h2>
	<span class="mw-headline"><b>自定义菜单事件</b></span>
</h2>
<p>用户点击自定义菜单后，微信会把点击事件推送给开发者，请注意，点击菜单弹出子菜单，不会产生上报。</p>
<h2>
	<span class="mw-headline"> 点击菜单拉取消息时的事件推送 </span>
</h2>
<p>推送XML数据包示例：</p>
<pre>
&lt;xml&gt;
	&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
	&lt;FromUserName&gt;&lt;![CDATA[FromUser]]&gt;&lt;/FromUserName&gt;
	&lt;CreateTime&gt;123456789&lt;/CreateTime&gt;
	&lt;MsgType&gt;&lt;![CDATA[event]]&gt;&lt;/MsgType&gt;
	&lt;Event&gt;&lt;![CDATA[CLICK]]&gt;&lt;/Event&gt;
	&lt;EventKey&gt;&lt;![CDATA[EVENTKEY]]&gt;&lt;/EventKey&gt;
&lt;/xml&gt;
</pre>
<p>参数说明：</p>
<table border="1" align="center" cellspacing="0" cellpadding="4">
	<tbody>
		<tr>
			<th style="width: 180px">参数</th>
			<th style="width: 470px">描述</th>
		</tr>
		<tr>
			<td>ToUserName</td>
			<td><b>开发者</b>微信号</td>
		</tr>
		<tr>
			<td>FromUserName</td>
			<td>发送方帐号（一个OpenID）</td>
		</tr>
		<tr>
			<td>CreateTime</td>
			<td>消息创建时间 （整型）</td>
		</tr>
		<tr>
			<td>MsgType</td>
			<td>消息类型，event</td>
		</tr>
		<tr>
			<td>Event</td>
			<td>事件类型，CLICK</td>
		</tr>
		<tr>
			<td>EventKey</td>
			<td>事件KEY值，与自定义菜单接口中KEY值对应</td>
		</tr>
	</tbody>
</table>
<!-- /bodytext -->
