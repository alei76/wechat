package com.covisint.wechat.irobot.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.axiom.om.OMDataSource;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axis2.databinding.ADBBean;
import org.apache.axis2.databinding.ADBDataSource;
import org.apache.axis2.databinding.ADBException;
import org.apache.axis2.databinding.utils.BeanUtil;
import org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl;

/**
 * AskResponse bean class
 */
public class AskResponse implements ADBBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8789840243963686241L;

	/**
	 * field for RobotResponseEx
	 */

	protected RobotResponse localRobotResponseEx;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localRobotResponseExTracker = false;

	public boolean isRobotResponseExSpecified() {
		return localRobotResponseExTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return RobotResponse
	 */
	public RobotResponse getRobotResponseEx() {
		return localRobotResponseEx;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            RobotResponseEx
	 */
	public void setRobotResponseEx(RobotResponse param) {
		localRobotResponseExTracker = param != null;

		this.localRobotResponseEx = param;

	}

	/**
	 * 
	 * @param parentQName
	 * @param factory
	 * @return OMElement
	 */
	public OMElement getOMElement(final QName parentQName, final OMFactory factory) throws ADBException {

		OMDataSource dataSource = new ADBDataSource(this, parentQName);
		return factory.createOMElement(dataSource, parentQName);

	}

	public void serialize(final QName parentQName, XMLStreamWriter xmlWriter) throws XMLStreamException, ADBException {
		serialize(parentQName, xmlWriter, false);
	}

	public void serialize(final QName parentQName, XMLStreamWriter xmlWriter, boolean serializeType) throws XMLStreamException, ADBException {

		String prefix = null;
		String namespace = null;

		prefix = parentQName.getPrefix();
		namespace = parentQName.getNamespaceURI();
		writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

		if (serializeType) {

			String namespacePrefix = registerPrefix(xmlWriter, "http://www.eastrobot.cn/ws/AskService");
			if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", namespacePrefix + ":askResponse", xmlWriter);
			} else {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "askResponse", xmlWriter);
			}

		}
		if (localRobotResponseExTracker) {
			if (localRobotResponseEx == null) {
				throw new ADBException("robotResponseEx cannot be null!!");
			}
			localRobotResponseEx.serialize(new QName("", "robotResponseEx"), xmlWriter);
		}
		xmlWriter.writeEndElement();

	}

	private static String generatePrefix(String namespace) {
		if (namespace.equals("http://www.eastrobot.cn/ws/AskService")) {
			return "ns1";
		}
		return BeanUtil.getUniquePrefix();
	}

	/**
	 * Utility method to write an element start tag.
	 */
	private void writeStartElement(String prefix, String namespace, String localPart, XMLStreamWriter xmlWriter) throws XMLStreamException {
		String writerPrefix = xmlWriter.getPrefix(namespace);
		if (writerPrefix != null) {
			xmlWriter.writeStartElement(namespace, localPart);
		} else {
			if (namespace.length() == 0) {
				prefix = "";
			} else if (prefix == null) {
				prefix = generatePrefix(namespace);
			}

			xmlWriter.writeStartElement(prefix, localPart, namespace);
			xmlWriter.writeNamespace(prefix, namespace);
			xmlWriter.setPrefix(prefix, namespace);
		}
	}

	/**
	 * Util method to write an attribute with the ns prefix
	 */
	private void writeAttribute(String prefix, String namespace, String attName, String attValue, XMLStreamWriter xmlWriter) throws XMLStreamException {
		if (xmlWriter.getPrefix(namespace) == null) {
			xmlWriter.writeNamespace(prefix, namespace);
			xmlWriter.setPrefix(prefix, namespace);
		}
		xmlWriter.writeAttribute(namespace, attName, attValue);
	}

	/**
	 * Register a namespace prefix
	 */
	private String registerPrefix(XMLStreamWriter xmlWriter, String namespace) throws XMLStreamException {
		String prefix = xmlWriter.getPrefix(namespace);
		if (prefix == null) {
			prefix = generatePrefix(namespace);
			NamespaceContext nsContext = xmlWriter.getNamespaceContext();
			while (true) {
				String uri = nsContext.getNamespaceURI(prefix);
				if (uri == null || uri.length() == 0) {
					break;
				}
				prefix = BeanUtil.getUniquePrefix();
			}
			xmlWriter.writeNamespace(prefix, namespace);
			xmlWriter.setPrefix(prefix, namespace);
		}
		return prefix;
	}

	/**
	 * databinding method to get an XML representation of this object
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public XMLStreamReader getPullParser(QName qName) throws ADBException {

		List elementList = new ArrayList();
		List attribList = new ArrayList();

		if (localRobotResponseExTracker) {
			elementList.add(new QName("", "robotResponseEx"));

			if (localRobotResponseEx == null) {
				throw new ADBException("robotResponseEx cannot be null!!");
			}
			elementList.add(localRobotResponseEx);
		}

		return new ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

	}

	/**
	 * Factory class that keeps the parse method
	 */
	public static class Factory {

		/**
		 * static method to create the object Precondition: If this object is an
		 * element, the current or next start element starts this object and any
		 * intervening reader events are ignorable If this object is not an
		 * element, it is a complex type and the reader is at the event just
		 * after the outer start element Postcondition: If this object is an
		 * element, the reader is positioned at its end element If this object
		 * is a complex type, the reader is positioned at the end element of its
		 * outer element
		 */
		public static AskResponse parse(XMLStreamReader reader) throws Exception {
			AskResponse object = new AskResponse();

			try {

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
					String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
					if (fullTypeName != null) {
						String nsPrefix = null;
						if (fullTypeName.indexOf(":") > -1) {
							nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
						}
						nsPrefix = nsPrefix == null ? "" : nsPrefix;

						String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

						if (!"askResponse".equals(type)) {
							// find namespace for the prefix
							String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
							return (AskResponse) ExtensionMapper.getTypeObject(nsUri, type, reader);
						}

					}

				}

				// Note all attributes that were handled. Used to differ normal
				// attributes
				// from anyAttributes.
				reader.next();

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new QName("", "robotResponseEx").equals(reader.getName())) {

					object.setRobotResponseEx(RobotResponse.Factory.parse(reader));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement())
					// A start element we are not expecting indicates a trailing
					// invalid property
					throw new ADBException("Unexpected subelement " + reader.getName());

			} catch (XMLStreamException e) {
				throw new Exception(e);
			}

			return object;
		}

	}// end of factory class

}
