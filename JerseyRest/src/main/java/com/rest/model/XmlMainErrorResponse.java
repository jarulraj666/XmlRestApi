package com.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "main")
public class XmlMainErrorResponse {

	@XmlElement(name="Failure")
	public XmlFailure failure;

	public void setFailure(XmlFailure failure) {
		this.failure = failure;
	}

	

}
