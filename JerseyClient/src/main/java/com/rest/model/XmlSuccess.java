package com.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.rest.adaptor.AdapterCDATA;

public class XmlSuccess {

	@XmlElement(name="ABC")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String fieldOne;

	@XmlElement(name="DEF")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String fieldTwo;

	@XmlElement(name="XYZ")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String fieldThree;

	public void setFieldOne(String fieldOne) {
		this.fieldOne = fieldOne;
	}

	public void setFieldTwo(String fieldTwo) {
		this.fieldTwo = fieldTwo;
	}

	public void setFieldThree(String fieldThree) {
		this.fieldThree = fieldThree;
	}

	@Override
	public String toString() {
		return "XmlSuccess [fieldOne=" + fieldOne + ", fieldTwo=" + fieldTwo + ", fieldThree=" + fieldThree + "]";
	}
}
