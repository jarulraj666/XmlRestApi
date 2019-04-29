package com.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rest.model.XmlFailure;
import com.rest.model.XmlMainErrorResponse;
import com.rest.model.XmlMainSuccessResponse;
import com.rest.model.XmlSuccess;

@Path("")
public class JerseyXmlService
{	
	@GET
	@Path("/success")
	@Produces(MediaType.APPLICATION_XML)
	public XmlMainSuccessResponse getSuccessResponse() {
		XmlMainSuccessResponse response = new XmlMainSuccessResponse();
		XmlSuccess success = new XmlSuccess();
		success.setFieldOne("First");
		success.setFieldTwo("Second Line");
		success.setFieldThree("");
		response.setSuccess(success);
		return response;
	}
	
	@GET
	@Path("/failure")
	@Produces(MediaType.APPLICATION_XML)
	public XmlMainErrorResponse getFailureResponse() {
		XmlMainErrorResponse response = new XmlMainErrorResponse();
		XmlFailure failure = new XmlFailure();
		failure.setError("First");
		failure.setDescription("Second Line");
		response.setFailure(failure);
		return response;
	}
}
