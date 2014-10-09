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
import org.apache.axis2.databinding.utils.ConverterUtil;
import org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl;

/**
 * RobotRequest bean class
 */
public class RobotRequest implements ADBBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5531767627129293829L;

	/**
	 * field for Attributes This was an Array!
	 */

	protected UserAttribute[] localAttributes;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localAttributesTracker = false;

	public boolean isAttributesSpecified() {
		return localAttributesTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return UserAttribute[]
	 */
	public UserAttribute[] getAttributes() {
		return localAttributes;
	}

	/**
	 * validate the array for Attributes
	 */
	protected void validateAttributes(UserAttribute[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Attributes
	 */
	public void setAttributes(UserAttribute[] param) {

		validateAttributes(param);

		localAttributesTracker = true;

		this.localAttributes = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            UserAttribute
	 */
	@SuppressWarnings("unchecked")
	public void addAttributes(UserAttribute param) {
		if (localAttributes == null) {
			localAttributes = new UserAttribute[] {};
		}

		// update the setting tracker
		localAttributesTracker = true;

		List<UserAttribute> list = ConverterUtil.toList(localAttributes);
		list.add(param);
		this.localAttributes = list.toArray(new UserAttribute[list.size()]);

	}

	/**
	 * field for MaxReturn
	 */

	protected int localMaxReturn;

	/**
	 * Auto generated getter method
	 * 
	 * @return int
	 */
	public int getMaxReturn() {
		return localMaxReturn;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            MaxReturn
	 */
	public void setMaxReturn(int param) {

		this.localMaxReturn = param;

	}

	/**
	 * field for Modules This was an Array!
	 */

	protected String[] localModules;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localModulesTracker = false;

	public boolean isModulesSpecified() {
		return localModulesTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return String[]
	 */
	public String[] getModules() {
		return localModules;
	}

	/**
	 * validate the array for Modules
	 */
	protected void validateModules(String[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Modules
	 */
	public void setModules(String[] param) {

		validateModules(param);

		localModulesTracker = true;

		this.localModules = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            String
	 */
	@SuppressWarnings("unchecked")
	public void addModules(String param) {
		if (localModules == null) {
			localModules = new String[] {};
		}

		// update the setting tracker
		localModulesTracker = true;

		List<String> list = ConverterUtil.toList(localModules);
		list.add(param);
		this.localModules = list.toArray(new String[list.size()]);

	}

	/**
	 * field for Question
	 */

	protected String localQuestion;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localQuestionTracker = false;

	public boolean isQuestionSpecified() {
		return localQuestionTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return String
	 */
	public String getQuestion() {
		return localQuestion;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Question
	 */
	public void setQuestion(String param) {
		localQuestionTracker = param != null;

		this.localQuestion = param;

	}

	/**
	 * field for SessionId
	 */

	protected String localSessionId;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localSessionIdTracker = false;

	public boolean isSessionIdSpecified() {
		return localSessionIdTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return String
	 */
	public String getSessionId() {
		return localSessionId;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            SessionId
	 */
	public void setSessionId(String param) {
		localSessionIdTracker = param != null;

		this.localSessionId = param;

	}

	/**
	 * field for Tags This was an Array!
	 */

	protected String[] localTags;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localTagsTracker = false;

	public boolean isTagsSpecified() {
		return localTagsTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return String[]
	 */
	public String[] getTags() {
		return localTags;
	}

	/**
	 * validate the array for Tags
	 */
	protected void validateTags(String[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Tags
	 */
	public void setTags(String[] param) {

		validateTags(param);

		localTagsTracker = true;

		this.localTags = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            String
	 */
	@SuppressWarnings("unchecked")
	public void addTags(String param) {
		if (localTags == null) {
			localTags = new String[] {};
		}

		// update the setting tracker
		localTagsTracker = true;

		List<String> list = ConverterUtil.toList(localTags);
		list.add(param);
		this.localTags = list.toArray(new String[list.size()]);

	}

	/**
	 * field for UserId
	 */

	protected String localUserId;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localUserIdTracker = false;

	public boolean isUserIdSpecified() {
		return localUserIdTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return String
	 */
	public String getUserId() {
		return localUserId;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            UserId
	 */
	public void setUserId(String param) {
		localUserIdTracker = param != null;

		this.localUserId = param;

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
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", namespacePrefix + ":RobotRequest", xmlWriter);
			} else {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "RobotRequest", xmlWriter);
			}

		}
		if (localAttributesTracker) {
			if (localAttributes != null) {
				for (int i = 0; i < localAttributes.length; i++) {
					if (localAttributes[i] != null) {
						localAttributes[i].serialize(new QName("", "attributes"), xmlWriter);
					} else {

						writeStartElement(null, "", "attributes", xmlWriter);

						// write the nil attribute
						writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
						xmlWriter.writeEndElement();

					}

				}
			} else {

				writeStartElement(null, "", "attributes", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();

			}
		}
		namespace = "";
		writeStartElement(null, namespace, "maxReturn", xmlWriter);

		if (localMaxReturn == Integer.MIN_VALUE) {

			throw new ADBException("maxReturn cannot be null!!");

		} else {
			xmlWriter.writeCharacters(ConverterUtil.convertToString(localMaxReturn));
		}

		xmlWriter.writeEndElement();
		if (localModulesTracker) {
			if (localModules != null) {
				namespace = "";
				for (int i = 0; i < localModules.length; i++) {

					if (localModules[i] != null) {

						writeStartElement(null, namespace, "modules", xmlWriter);

						xmlWriter.writeCharacters(ConverterUtil.convertToString(localModules[i]));

						xmlWriter.writeEndElement();

					} else {

						// write null attribute
						namespace = "";
						writeStartElement(null, namespace, "modules", xmlWriter);
						writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
						xmlWriter.writeEndElement();

					}

				}
			} else {

				// write the null attribute
				// write null attribute
				writeStartElement(null, "", "modules", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();

			}

		}
		if (localQuestionTracker) {
			namespace = "";
			writeStartElement(null, namespace, "question", xmlWriter);

			if (localQuestion == null) {
				// write the nil attribute

				throw new ADBException("question cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localQuestion);

			}

			xmlWriter.writeEndElement();
		}
		if (localSessionIdTracker) {
			namespace = "";
			writeStartElement(null, namespace, "sessionId", xmlWriter);

			if (localSessionId == null) {
				// write the nil attribute

				throw new ADBException("sessionId cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localSessionId);

			}

			xmlWriter.writeEndElement();
		}
		if (localTagsTracker) {
			if (localTags != null) {
				namespace = "";
				for (int i = 0; i < localTags.length; i++) {

					if (localTags[i] != null) {

						writeStartElement(null, namespace, "tags", xmlWriter);

						xmlWriter.writeCharacters(ConverterUtil.convertToString(localTags[i]));

						xmlWriter.writeEndElement();

					} else {

						// write null attribute
						namespace = "";
						writeStartElement(null, namespace, "tags", xmlWriter);
						writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
						xmlWriter.writeEndElement();

					}

				}
			} else {

				// write the null attribute
				// write null attribute
				writeStartElement(null, "", "tags", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();

			}

		}
		if (localUserIdTracker) {
			namespace = "";
			writeStartElement(null, namespace, "userId", xmlWriter);

			if (localUserId == null) {
				// write the nil attribute

				throw new ADBException("userId cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localUserId);

			}

			xmlWriter.writeEndElement();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public XMLStreamReader getPullParser(QName qName) throws ADBException {

		List elementList = new ArrayList();
		List attribList = new ArrayList();

		if (localAttributesTracker) {
			if (localAttributes != null) {
				for (int i = 0; i < localAttributes.length; i++) {

					if (localAttributes[i] != null) {
						elementList.add(new QName("", "attributes"));
						elementList.add(localAttributes[i]);
					} else {

						elementList.add(new QName("", "attributes"));
						elementList.add(null);

					}

				}
			} else {

				elementList.add(new QName("", "attributes"));
				elementList.add(localAttributes);

			}

		}
		elementList.add(new QName("", "maxReturn"));

		elementList.add(ConverterUtil.convertToString(localMaxReturn));
		if (localModulesTracker) {
			if (localModules != null) {
				for (int i = 0; i < localModules.length; i++) {

					if (localModules[i] != null) {
						elementList.add(new QName("", "modules"));
						elementList.add(ConverterUtil.convertToString(localModules[i]));
					} else {

						elementList.add(new QName("", "modules"));
						elementList.add(null);

					}

				}
			} else {

				elementList.add(new QName("", "modules"));
				elementList.add(null);

			}

		}
		if (localQuestionTracker) {
			elementList.add(new QName("", "question"));

			if (localQuestion != null) {
				elementList.add(ConverterUtil.convertToString(localQuestion));
			} else {
				throw new ADBException("question cannot be null!!");
			}
		}
		if (localSessionIdTracker) {
			elementList.add(new QName("", "sessionId"));

			if (localSessionId != null) {
				elementList.add(ConverterUtil.convertToString(localSessionId));
			} else {
				throw new ADBException("sessionId cannot be null!!");
			}
		}
		if (localTagsTracker) {
			if (localTags != null) {
				for (int i = 0; i < localTags.length; i++) {

					if (localTags[i] != null) {
						elementList.add(new QName("", "tags"));
						elementList.add(ConverterUtil.convertToString(localTags[i]));
					} else {

						elementList.add(new QName("", "tags"));
						elementList.add(null);

					}

				}
			} else {

				elementList.add(new QName("", "tags"));
				elementList.add(null);

			}

		}
		if (localUserIdTracker) {
			elementList.add(new QName("", "userId"));

			if (localUserId != null) {
				elementList.add(ConverterUtil.convertToString(localUserId));
			} else {
				throw new ADBException("userId cannot be null!!");
			}
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
		public static RobotRequest parse(XMLStreamReader reader) throws Exception {
			RobotRequest object = new RobotRequest();

			String nillableValue = null;
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

						if (!"RobotRequest".equals(type)) {
							// find namespace for the prefix
							String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
							return (RobotRequest) ExtensionMapper.getTypeObject(nsUri, type, reader);
						}

					}

				}

				// Note all attributes that were handled. Used to differ normal
				// attributes
				// from anyAttributes.

				reader.next();

				List<UserAttribute> list1 = new ArrayList<UserAttribute>();

				List<String> list3 = new ArrayList<String>();

				List<String> list6 = new ArrayList<String>();

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new QName("", "attributes").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						list1.add(null);
						reader.next();
					} else {
						list1.add(UserAttribute.Factory.parse(reader));
					}
					// loop until we find a start element that is not part of
					// this array
					boolean loopDone1 = false;
					while (!loopDone1) {
						// We should be at the end element, but make sure
						while (!reader.isEndElement())
							reader.next();
						// Step out of this element
						reader.next();
						// Step to next element event.
						while (!reader.isStartElement() && !reader.isEndElement())
							reader.next();
						if (reader.isEndElement()) {
							// two continuous end elements means we are exiting
							// the xml structure
							loopDone1 = true;
						} else {
							if (new QName("", "attributes").equals(reader.getName())) {

								nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
								if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
									list1.add(null);
									reader.next();
								} else {
									list1.add(UserAttribute.Factory.parse(reader));
								}
							} else {
								loopDone1 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setAttributes((UserAttribute[]) ConverterUtil.convertToArray(UserAttribute.class, list1));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new QName("", "maxReturn").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new ADBException("The element: " + "maxReturn" + "  cannot be null");
					}

					String content = reader.getElementText();

					object.setMaxReturn(ConverterUtil.convertToInt(content));

					reader.next();

				} // End of if for expected property start element

				else {
					// A start element we are not expecting indicates an invalid
					// parameter was passed
					throw new ADBException("Unexpected subelement " + reader.getName());
				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new QName("", "modules").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						list3.add(null);

						reader.next();
					} else {
						list3.add(reader.getElementText());
					}
					// loop until we find a start element that is not part of
					// this array
					boolean loopDone3 = false;
					while (!loopDone3) {
						// Ensure we are at the EndElement
						while (!reader.isEndElement()) {
							reader.next();
						}
						// Step out of this element
						reader.next();
						// Step to next element event.
						while (!reader.isStartElement() && !reader.isEndElement())
							reader.next();
						if (reader.isEndElement()) {
							// two continuous end elements means we are exiting
							// the xml structure
							loopDone3 = true;
						} else {
							if (new QName("", "modules").equals(reader.getName())) {

								nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
								if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
									list3.add(null);

									reader.next();
								} else {
									list3.add(reader.getElementText());
								}
							} else {
								loopDone3 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setModules((String[]) list3.toArray(new String[list3.size()]));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new QName("", "question").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new ADBException("The element: " + "question" + "  cannot be null");
					}

					String content = reader.getElementText();

					object.setQuestion(ConverterUtil.convertToString(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new QName("", "sessionId").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new ADBException("The element: " + "sessionId" + "  cannot be null");
					}

					String content = reader.getElementText();

					object.setSessionId(ConverterUtil.convertToString(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new QName("", "tags").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						list6.add(null);

						reader.next();
					} else {
						list6.add(reader.getElementText());
					}
					// loop until we find a start element that is not part of
					// this array
					boolean loopDone6 = false;
					while (!loopDone6) {
						// Ensure we are at the EndElement
						while (!reader.isEndElement()) {
							reader.next();
						}
						// Step out of this element
						reader.next();
						// Step to next element event.
						while (!reader.isStartElement() && !reader.isEndElement())
							reader.next();
						if (reader.isEndElement()) {
							// two continuous end elements means we are exiting
							// the xml structure
							loopDone6 = true;
						} else {
							if (new QName("", "tags").equals(reader.getName())) {

								nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
								if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
									list6.add(null);

									reader.next();
								} else {
									list6.add(reader.getElementText());
								}
							} else {
								loopDone6 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setTags((String[]) list6.toArray(new String[list6.size()]));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new QName("", "userId").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new ADBException("The element: " + "userId" + "  cannot be null");
					}

					String content = reader.getElementText();

					object.setUserId(ConverterUtil.convertToString(content));

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
