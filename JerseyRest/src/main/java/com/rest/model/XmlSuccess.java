package com.rest.model;

import javax.xml.bind.annotation.XmlElement;


public class XmlSuccess {

	@XmlElement(name="ABC")
	public String fieldOne;

	@XmlElement(name="DEF")
	public String fieldTwo;

	@XmlElement(name="XYZ")
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
