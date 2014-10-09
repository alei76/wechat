/**
 * AskServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package com.covisint.wechat.irobot.remoting;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.OperationClient;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.client.Stub;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.databinding.ADBBean;
import org.apache.axis2.databinding.ADBException;
import org.apache.axis2.description.AxisOperation;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.description.OutInAxisOperation;
import org.apache.axis2.description.WSDL2Constants;
import org.apache.axis2.wsdl.WSDLConstants;

import com.covisint.wechat.irobot.model.AskE;
import com.covisint.wechat.irobot.model.AskResponseE;

public class AskServiceStub extends Stub implements AskService {
	protected AxisOperation[] _operations;

	private static int counter = 0;

	private static synchronized String getUniqueSuffix() {
		if (counter > 99999) {
			counter = 0;
		}
		counter = counter + 1;
		return Long.toString(System.currentTimeMillis()) + "_" + counter;
	}

	private void populateAxisService() throws AxisFault {
		_service = new AxisService("AskService" + getUniqueSuffix());
		addAnonymousOperations();
		AxisOperation __operation;
		_operations = new AxisOperation[1];
		__operation = new OutInAxisOperation();
		__operation.setName(new QName("http://www.eastrobot.cn/ws/AskService", "ask"));
		_service.addOperation(__operation);
		_operations[0] = __operation;
	}

	/**
	 * Constructor that takes in a configContext
	 */

	private AskServiceStub(ConfigurationContext configurationContext, String targetEndpoint) throws AxisFault {
		this(configurationContext, targetEndpoint, false);
	}

	/**
	 * Constructor that takes in a configContext and useseperate listner
	 */
	private AskServiceStub(ConfigurationContext configurationContext, String targetEndpoint, boolean useSeparateListener) throws AxisFault {
		populateAxisService();
		_serviceClient = new ServiceClient(configurationContext, _service);
		_serviceClient.getOptions().setTo(new EndpointReference(targetEndpoint));
		_serviceClient.getOptions().setUseSeparateListener(useSeparateListener);
	}

	/**
	 * Constructor taking the target endpoint
	 */
	public AskServiceStub(String targetEndpoint) throws AxisFault {
		this(null, targetEndpoint);
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see com.covisint.wechat.irobot.remoting.AskService#ask
	 * @param ask0
	 */

	public AskResponseE ask(AskE ask0) throws RemoteException {
		MessageContext _messageContext = null;
		try {
			OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
			_operationClient.getOptions().setAction("http://www.eastrobot.cn/ws/AskService/ask");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient, WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new MessageContext();

			// create SOAP envelope with that payload
			SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), ask0, optimizeContent(new QName("http://www.eastrobot.cn/ws/AskService", "ask")), new QName("http://www.eastrobot.cn/ws/AskService", "ask"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			MessageContext _returnMessageContext = _operationClient.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
			Object object = fromOM(_returnEnv.getBody().getFirstElement(), AskResponseE.class, getEnvelopeNamespaces(_returnEnv));
			return (AskResponseE) object;
		} catch (AxisFault f) {
			throw f;
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * A utility method that copies the namepaces from the SOAPEnvelope
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> getEnvelopeNamespaces(SOAPEnvelope env) {
		Map<String, String> returnMap = new HashMap<String, String>();
		Iterator<OMNamespace> namespaceIterator = env.getAllDeclaredNamespaces();
		while (namespaceIterator.hasNext()) {
			OMNamespace ns = namespaceIterator.next();
			returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
		}
		return returnMap;
	}

	private QName[] opNameArray = null;

	private boolean optimizeContent(QName opName) {
		if (opNameArray == null) {
			return false;
		}
		for (int i = 0; i < opNameArray.length; i++) {
			if (opName.equals(opNameArray[i])) {
				return true;
			}
		}
		return false;
	}

	private SOAPEnvelope toEnvelope(SOAPFactory factory, AskE param, boolean optimizeContent, QName methodQName) throws AxisFault {
		try {
			SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(AskE.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (ADBException e) {
			throw AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private Object fromOM(OMElement param, Class<? extends ADBBean> type, Map<String, String> extraNamespaces) throws AxisFault {
		try {
			if (AskE.class.equals(type)) {
				return AskE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
			}
			if (AskResponseE.class.equals(type)) {
				return AskResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
			}
		} catch (Exception e) {
			throw AxisFault.makeFault(e);
		}
		return null;
	}

}
