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
 * RobotResponse bean class
 */
public class RobotResponse implements ADBBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6855528099472249766L;

	/**
	 * field for Commands This was an Array!
	 */

	protected RobotCommand[] localCommands;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localCommandsTracker = false;

	public boolean isCommandsSpecified() {
		return localCommandsTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return RobotCommand[]
	 */
	public RobotCommand[] getCommands() {
		return localCommands;
	}

	/**
	 * validate the array for Commands
	 */
	protected void validateCommands(RobotCommand[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Commands
	 */
	public void setCommands(RobotCommand[] param) {

		validateCommands(param);

		localCommandsTracker = true;

		this.localCommands = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            RobotCommand
	 */
	@SuppressWarnings("unchecked")
	public void addCommands(RobotCommand param) {
		if (localCommands == null) {
			localCommands = new RobotCommand[] {};
		}

		// update the setting tracker
		localCommandsTracker = true;

		List<RobotCommand> list = ConverterUtil.toList(localCommands);
		list.add(param);
		this.localCommands = (RobotCommand[]) list.toArray(new RobotCommand[list.size()]);

	}

	/**
	 * field for Content
	 */

	protected String localContent;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localContentTracker = false;

	public boolean isContentSpecified() {
		return localContentTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return String
	 */
	public String getContent() {
		return localContent;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Content
	 */
	public void setContent(String param) {
		localContentTracker = param != null;

		this.localContent = param;

	}

	/**
	 * field for ModuleId
	 */

	protected String localModuleId;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localModuleIdTracker = false;

	public boolean isModuleIdSpecified() {
		return localModuleIdTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return String
	 */
	public String getModuleId() {
		return localModuleId;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            ModuleId
	 */
	public void setModuleId(String param) {
		localModuleIdTracker = param != null;

		this.localModuleId = param;

	}

	/**
	 * field for NodeId
	 */

	protected String localNodeId;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localNodeIdTracker = false;

	public boolean isNodeIdSpecified() {
		return localNodeIdTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return String
	 */
	public String getNodeId() {
		return localNodeId;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            NodeId
	 */
	public void setNodeId(String param) {
		localNodeIdTracker = param != null;

		this.localNodeId = param;

	}

	/**
	 * field for RelatedQuestions This was an Array!
	 */

	protected String[] localRelatedQuestions;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localRelatedQuestionsTracker = false;

	public boolean isRelatedQuestionsSpecified() {
		return localRelatedQuestionsTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return String[]
	 */
	public String[] getRelatedQuestions() {
		return localRelatedQuestions;
	}

	/**
	 * validate the array for RelatedQuestions
	 */
	protected void validateRelatedQuestions(String[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            RelatedQuestions
	 */
	public void setRelatedQuestions(String[] param) {

		validateRelatedQuestions(param);

		localRelatedQuestionsTracker = true;

		this.localRelatedQuestions = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            String
	 */
	@SuppressWarnings("unchecked")
	public void addRelatedQuestions(String param) {
		if (localRelatedQuestions == null) {
			localRelatedQuestions = new String[] {};
		}

		// update the setting tracker
		localRelatedQuestionsTracker = true;

		List<String> list = ConverterUtil.toList(localRelatedQuestions);
		list.add(param);
		this.localRelatedQuestions = (String[]) list.toArray(new String[list.size()]);

	}

	/**
	 * field for Similarity
	 */

	protected float localSimilarity;

	/**
	 * Auto generated getter method
	 * 
	 * @return float
	 */
	public float getSimilarity() {
		return localSimilarity;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Similarity
	 */
	public void setSimilarity(float param) {

		this.localSimilarity = param;

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
		this.localTags = (String[]) list.toArray(new String[list.size()]);

	}

	/**
	 * field for Type
	 */

	protected int localType;

	/**
	 * Auto generated getter method
	 * 
	 * @return int
	 */
	public int getType() {
		return localType;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Type
	 */
	public void setType(int param) {

		this.localType = param;

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
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", namespacePrefix + ":RobotResponse", xmlWriter);
			} else {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "RobotResponse", xmlWriter);
			}

		}
		if (localCommandsTracker) {
			if (localCommands != null) {
				for (int i = 0; i < localCommands.length; i++) {
					if (localCommands[i] != null) {
						localCommands[i].serialize(new QName("", "commands"), xmlWriter);
					} else {

						writeStartElement(null, "", "commands", xmlWriter);

						// write the nil attribute
						writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
						xmlWriter.writeEndElement();

					}

				}
			} else {

				writeStartElement(null, "", "commands", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();

			}
		}
		if (localContentTracker) {
			namespace = "";
			writeStartElement(null, namespace, "content", xmlWriter);

			if (localContent == null) {
				// write the nil attribute

				throw new ADBException("content cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localContent);

			}

			xmlWriter.writeEndElement();
		}
		if (localModuleIdTracker) {
			namespace = "";
			writeStartElement(null, namespace, "moduleId", xmlWriter);

			if (localModuleId == null) {
				// write the nil attribute

				throw new ADBException("moduleId cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localModuleId);

			}

			xmlWriter.writeEndElement();
		}
		if (localNodeIdTracker) {
			namespace = "";
			writeStartElement(null, namespace, "nodeId", xmlWriter);

			if (localNodeId == null) {
				// write the nil attribute

				throw new ADBException("nodeId cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localNodeId);

			}

			xmlWriter.writeEndElement();
		}
		if (localRelatedQuestionsTracker) {
			if (localRelatedQuestions != null) {
				namespace = "";
				for (int i = 0; i < localRelatedQuestions.length; i++) {

					if (localRelatedQuestions[i] != null) {

						writeStartElement(null, namespace, "relatedQuestions", xmlWriter);

						xmlWriter.writeCharacters(ConverterUtil.convertToString(localRelatedQuestions[i]));

						xmlWriter.writeEndElement();

					} else {

						// write null attribute
						namespace = "";
						writeStartElement(null, namespace, "relatedQuestions", xmlWriter);
						writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
						xmlWriter.writeEndElement();

					}

				}
			} else {

				// write the null attribute
				// write null attribute
				writeStartElement(null, "", "relatedQuestions", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();

			}

		}
		namespace = "";
		writeStartElement(null, namespace, "similarity", xmlWriter);

		if (Float.isNaN(localSimilarity)) {

			throw new ADBException("similarity cannot be null!!");

		} else {
			xmlWriter.writeCharacters(ConverterUtil.convertToString(localSimilarity));
		}

		xmlWriter.writeEndElement();
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
		namespace = "";
		writeStartElement(null, namespace, "type", xmlWriter);

		if (localType == Integer.MIN_VALUE) {

			throw new ADBException("type cannot be null!!");

		} else {
			xmlWriter.writeCharacters(ConverterUtil.convertToString(localType));
		}

		xmlWriter.writeEndElement();

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

		if (localCommandsTracker) {
			if (localCommands != null) {
				for (int i = 0; i < localCommands.length; i++) {

					if (localCommands[i] != null) {
						elementList.add(new QName("", "commands"));
						elementList.add(localCommands[i]);
					} else {

						elementList.add(new QName("", "commands"));
						elementList.add(null);

					}

				}
			} else {

				elementList.add(new QName("", "commands"));
				elementList.add(localCommands);

			}

		}
		if (localContentTracker) {
			elementList.add(new QName("", "content"));

			if (localContent != null) {
				elementList.add(ConverterUtil.convertToString(localContent));
			} else {
				throw new ADBException("content cannot be null!!");
			}
		}
		if (localModuleIdTracker) {
			elementList.add(new QName("", "moduleId"));

			if (localModuleId != null) {
				elementList.add(ConverterUtil.convertToString(localModuleId));
			} else {
				throw new ADBException("moduleId cannot be null!!");
			}
		}
		if (localNodeIdTracker) {
			elementList.add(new QName("", "nodeId"));

			if (localNodeId != null) {
				elementList.add(ConverterUtil.convertToString(localNodeId));
			} else {
				throw new ADBException("nodeId cannot be null!!");
			}
		}
		if (localRelatedQuestionsTracker) {
			if (localRelatedQuestions != null) {
				for (int i = 0; i < localRelatedQuestions.length; i++) {

					if (localRelatedQuestions[i] != null) {
						elementList.add(new QName("", "relatedQuestions"));
						elementList.add(ConverterUtil.convertToString(localRelatedQuestions[i]));
					} else {

						elementList.add(new QName("", "relatedQuestions"));
						elementList.add(null);

					}

				}
			} else {

				elementList.add(new QName("", "relatedQuestions"));
				elementList.add(null);

			}

		}
		elementList.add(new QName("", "similarity"));

		elementList.add(ConverterUtil.convertToString(localSimilarity));
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
		elementList.add(new QName("", "type"));

		elementList.add(ConverterUtil.convertToString(localType));

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
		public static RobotResponse parse(XMLStreamReader reader) throws Exception {
			RobotResponse object = new RobotResponse();

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

						if (!"RobotResponse".equals(type)) {
							// find namespace for the prefix
							String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
							return (RobotResponse) ExtensionMapper.getTypeObject(nsUri, type, reader);
						}

					}

				}

				// Note all attributes that were handled. Used to differ normal
				// attributes
				// from anyAttributes.
				reader.next();

				List<RobotCommand> list1 = new ArrayList<RobotCommand>();

				List<String> list5 = new ArrayList<String>();

				List<String> list7 = new ArrayList<String>();

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new QName("", "commands").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						list1.add(null);
						reader.next();
					} else {
						list1.add(RobotCommand.Factory.parse(reader));
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
							if (new QName("", "commands").equals(reader.getName())) {

								nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
								if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
									list1.add(null);
									reader.next();
								} else {
									list1.add(RobotCommand.Factory.parse(reader));
								}
							} else {
								loopDone1 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setCommands((RobotCommand[]) ConverterUtil.convertToArray(RobotCommand.class, list1));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new QName("", "content").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new ADBException("The element: " + "content" + "  cannot be null");
					}

					String content = reader.getElementText();

					object.setContent(ConverterUtil.convertToString(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new QName("", "moduleId").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new ADBException("The element: " + "moduleId" + "  cannot be null");
					}

					String content = reader.getElementText();

					object.setModuleId(ConverterUtil.convertToString(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new QName("", "nodeId").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new ADBException("The element: " + "nodeId" + "  cannot be null");
					}

					String content = reader.getElementText();

					object.setNodeId(ConverterUtil.convertToString(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new QName("", "relatedQuestions").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						list5.add(null);

						reader.next();
					} else {
						list5.add(reader.getElementText());
					}
					// loop until we find a start element that is not part of
					// this array
					boolean loopDone5 = false;
					while (!loopDone5) {
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
							loopDone5 = true;
						} else {
							if (new QName("", "relatedQuestions").equals(reader.getName())) {

								nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
								if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
									list5.add(null);

									reader.next();
								} else {
									list5.add(reader.getElementText());
								}
							} else {
								loopDone5 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setRelatedQuestions((String[]) list5.toArray(new String[list5.size()]));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new QName("", "similarity").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new ADBException("The element: " + "similarity" + "  cannot be null");
					}

					String content = reader.getElementText();

					object.setSimilarity(ConverterUtil.convertToFloat(content));

					reader.next();

				} // End of if for expected property start element

				else {
					// A start element we are not expecting indicates an invalid
					// parameter was passed
					throw new ADBException("Unexpected subelement " + reader.getName());
				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new QName("", "tags").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						list7.add(null);

						reader.next();
					} else {
						list7.add(reader.getElementText());
					}
					// loop until we find a start element that is not part of
					// this array
					boolean loopDone7 = false;
					while (!loopDone7) {
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
							loopDone7 = true;
						} else {
							if (new QName("", "tags").equals(reader.getName())) {

								nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
								if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
									list7.add(null);

									reader.next();
								} else {
									list7.add(reader.getElementText());
								}
							} else {
								loopDone7 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setTags((String[]) list7.toArray(new String[list7.size()]));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement() && new QName("", "type").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new ADBException("The element: " + "type" + "  cannot be null");
					}

					String content = reader.getElementText();

					object.setType(ConverterUtil.convertToInt(content));

					reader.next();

				} // End of if for expected property start element

				else {
					// A start element we are not expecting indicates an invalid
					// parameter was passed
					throw new ADBException("Unexpected subelement " + reader.getName());
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
