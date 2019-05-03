package com.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "main")
public class XmlMainResponse {

	@XmlElement(name="Success")
	public XmlSuccess success;

	@XmlElement(name="Failure")
	public XmlFailure failure;
	
	public void setSuccess(XmlSuccess success) {
		this.success = success;
	}
	
	public void setFailure(XmlFailure failure) {
		this.failure = failure;
	}

	@Override
	public String toString() {
		return "XmlMainSuccessResponse [success=" + success + ", failure=" + failure + "]";
	}


}
