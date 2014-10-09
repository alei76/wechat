package com.covisint.wechat.irobot.model;

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

/**
 * AskE bean class
 */
public class AskE implements ADBBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3804934207449234595L;

	public static final QName MY_QNAME = new QName("http://www.eastrobot.cn/ws/AskService", "ask", "ns1");

	/**
	 * field for Ask
	 */

	protected Ask localAsk;

	/**
	 * Auto generated getter method
	 * 
	 * @return Ask
	 */
	public Ask getAsk() {
		return localAsk;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Ask
	 */
	public void setAsk(Ask param) {

		this.localAsk = param;

	}

	/**
	 * 
	 * @param parentQName
	 * @param factory
	 * @return OMElement
	 */
	public OMElement getOMElement(final QName parentQName, final OMFactory factory) throws ADBException {

		OMDataSource dataSource = new ADBDataSource(this, MY_QNAME);
		return factory.createOMElement(dataSource, MY_QNAME);

	}

	public void serialize(final QName parentQName, XMLStreamWriter xmlWriter) throws XMLStreamException, ADBException {
		serialize(parentQName, xmlWriter, false);
	}

	public void serialize(final QName parentQName, XMLStreamWriter xmlWriter, boolean serializeType) throws XMLStreamException, ADBException {

		// We can safely assume an element has only one type associated with it

		if (localAsk == null) {
			throw new ADBException("ask cannot be null!");
		}
		localAsk.serialize(MY_QNAME, xmlWriter);

	}

	/**
	 * databinding method to get an XML representation of this object
	 * 
	 */
	public XMLStreamReader getPullParser(QName qName) throws ADBException {

		// We can safely assume an element has only one type associated with it
		return localAsk.getPullParser(MY_QNAME);

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
		public static AskE parse(XMLStreamReader reader) throws Exception {
			AskE object = new AskE();

			try {

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				// Note all attributes that were handled. Used to differ normal
				// attributes
				// from anyAttributes.

				while (!reader.isEndElement()) {
					if (reader.isStartElement()) {

						if (reader.isStartElement() && new QName("http://www.eastrobot.cn/ws/AskService", "ask").equals(reader.getName())) {

							object.setAsk(Ask.Factory.parse(reader));

						} // End of if for expected property start element

						else {
							// A start element we are not expecting indicates an
							// invalid parameter was passed
							throw new ADBException("Unexpected subelement " + reader.getName());
						}

					} else {
						reader.next();
					}
				} // end of while loop

			} catch (XMLStreamException e) {
				throw new Exception(e);
			}

			return object;
		}

	}// end of factory class

}
