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
 * AskResponseE bean class
 */
public class AskResponseE implements ADBBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8401430244938601035L;

	public static final QName MY_QNAME = new QName("http://www.eastrobot.cn/ws/AskService", "askResponse", "ns1");

	/**
	 * field for AskResponse
	 */

	protected AskResponse localAskResponse;

	/**
	 * Auto generated getter method
	 * 
	 * @return AskResponse
	 */
	public AskResponse getAskResponse() {
		return localAskResponse;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            AskResponse
	 */
	public void setAskResponse(AskResponse param) {

		this.localAskResponse = param;

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

		if (localAskResponse == null) {
			throw new ADBException("askResponse cannot be null!");
		}
		localAskResponse.serialize(MY_QNAME, xmlWriter);

	}

	/**
	 * databinding method to get an XML representation of this object
	 * 
	 */
	public XMLStreamReader getPullParser(QName qName) throws ADBException {

		// We can safely assume an element has only one type associated with it
		return localAskResponse.getPullParser(MY_QNAME);

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
		public static AskResponseE parse(XMLStreamReader reader) throws Exception {
			AskResponseE object = new AskResponseE();

			try {

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				// Note all attributes that were handled. Used to differ normal
				// attributes
				// from anyAttributes.

				while (!reader.isEndElement()) {
					if (reader.isStartElement()) {

						if (reader.isStartElement() && new QName("http://www.eastrobot.cn/ws/AskService", "askResponse").equals(reader.getName())) {

							object.setAskResponse(AskResponse.Factory.parse(reader));

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
