package com.covisint.wechat.weixin.util;

import java.io.Writer;

import com.covisint.wechat.weixin.model.MusicOutMessage;
import com.covisint.wechat.weixin.model.NewsOutMessage;
import com.covisint.wechat.weixin.model.OutMessage;
import com.covisint.wechat.weixin.model.TextOutMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 微信消息往来xml数据转换类
 */
public class XStreamFactory {
	protected static String PREFIX_CDATA = "<![CDATA[";
	protected static String SUFFIX_CDATA = "]]>";

	public static String toXml(Object obj) {
		XStream xstream = new XStream(new XppDriver() {
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new PrettyPrintWriter(out) {
					protected void writeText(QuickWriter writer, String text) {
						if (!text.startsWith(PREFIX_CDATA)) {
							text = PREFIX_CDATA + text + SUFFIX_CDATA;
						}
						writer.write(text);
					}
				};
			};
		});
		xstream.processAnnotations(obj.getClass());
		xstream.alias("xml", TextOutMessage.class);
		xstream.alias("xml", OutMessage.class);
		xstream.alias("xml", NewsOutMessage.class);
		xstream.alias("xml", MusicOutMessage.class);
		return xstream.toXML(obj);
	}

	public static XStream getStream() {
		XStream xstream = new XStream(new XppDriver());
		xstream.ignoreUnknownElements();
		return xstream;
	}
}
