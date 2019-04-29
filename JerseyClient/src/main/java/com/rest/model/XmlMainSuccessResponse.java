package com.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "main")
public class XmlMainSuccessResponse {

	@XmlElement(name="Success")
	public XmlSuccess success;

	public void setSuccess(XmlSuccess success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "XmlMainSuccessResponse [success=" + success + "]";
	}
	
	

}
