package com.rest.model;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rest.model.XmlFailure;
import com.rest.model.XmlMainResponse;
import com.rest.model.XmlSuccess;

@Path("")
public class JerseyXmlService {

	@GET
	@Path("/response")
	@Produces(MediaType.APPLICATION_XML)
	public XmlMainResponse getResponse() {
		XmlMainResponse response = new XmlMainResponse();
		XmlSuccess success = new XmlSuccess();
		success.setFieldOne("First");
		success.setFieldTwo("Second Line");
		success.setFieldThree("");
		response.setSuccess(success);
		return response;
	}

	@GET
	@Path("/response/{type}")
	@Produces(MediaType.APPLICATION_XML)
	public XmlMainResponse getDynamicResponse(@PathParam("type") String type) {
		XmlMainResponse response = new XmlMainResponse();
		if (type.equals("success")) {
			XmlSuccess success = new XmlSuccess();
			success.setFieldOne("First");
			success.setFieldTwo("Second Line");
			success.setFieldThree("");
			response.setSuccess(success);
		} else if (type.equals("failure")) {
			XmlFailure failure = new XmlFailure();
			failure.setError("First");
			failure.setDescription("Second Line");
			response.setFailure(failure);
		}

		return response;
	}

}
