package com.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "failure")
public class XmlFailure {

	@XmlElement(name = "Error")
	public String error;

	@XmlElement(name = "Description")
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
