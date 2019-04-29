package com.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.rest.adaptor.AdapterCDATA;

@XmlRootElement(name = "failure")
public class XmlFailure {

	@XmlElement(name = "Error")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String error;

	@XmlElement(name = "Description")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String description;

	public void setError(String error) {
		this.error = error;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "XmlFailure [error=" + error + ", description=" + description + "]";
	}

}
