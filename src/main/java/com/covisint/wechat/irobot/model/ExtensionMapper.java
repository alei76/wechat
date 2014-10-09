package com.covisint.wechat.irobot.model;

import javax.xml.stream.XMLStreamReader;

import org.apache.axis2.databinding.ADBException;

/**
 * ExtensionMapper class
 */
public class ExtensionMapper {

	public static Object getTypeObject(String namespaceURI, String typeName, XMLStreamReader reader) throws Exception {

		if ("http://www.eastrobot.cn/ws/AskService".equals(namespaceURI) && "UserAttribute".equals(typeName)) {

			return UserAttribute.Factory.parse(reader);

		}

		if ("http://www.eastrobot.cn/ws/AskService".equals(namespaceURI) && "ask".equals(typeName)) {

			return Ask.Factory.parse(reader);

		}

		if ("http://www.eastrobot.cn/ws/AskService".equals(namespaceURI) && "RobotCommand".equals(typeName)) {

			return RobotCommand.Factory.parse(reader);

		}

		if ("http://www.eastrobot.cn/ws/AskService".equals(namespaceURI) && "RobotRequest".equals(typeName)) {

			return RobotRequest.Factory.parse(reader);

		}

		if ("http://www.eastrobot.cn/ws/AskService".equals(namespaceURI) && "askResponse".equals(typeName)) {

			return AskResponse.Factory.parse(reader);

		}

		if ("http://www.eastrobot.cn/ws/AskService".equals(namespaceURI) && "RobotResponse".equals(typeName)) {

			return RobotResponse.Factory.parse(reader);

		}

		throw new ADBException("Unsupported type " + namespaceURI + " " + typeName);
	}

}
