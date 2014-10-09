/**
 * AskService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.covisint.wechat.irobot.remoting;

import java.rmi.RemoteException;

import com.covisint.wechat.irobot.model.AskE;
import com.covisint.wechat.irobot.model.AskResponseE;

/*
 *  AskService java interface
 */

public interface AskService {

	/**
	 * Auto generated method signature
	 * 
	 * @param ask
	 */

	public AskResponseE ask(AskE ask) throws RemoteException;

	//
}
