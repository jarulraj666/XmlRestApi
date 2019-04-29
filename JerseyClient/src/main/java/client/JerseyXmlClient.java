package client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.rest.model.XmlFailure;
import com.rest.model.XmlMainErrorResponse;
import com.rest.model.XmlMainSuccessResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyXmlClient {

	// success path variable
	public static final String SUCCESS_PATH = "success";

	// failure path variable
	public static final String FAILURE_PATH = "failure";

	public static void main(String[] args) {

		Client client = Client.create();

		WebResource webResource = client.resource("http://localhost:8080/JerseyRest/rest");

		try {
			// invoke success xml type
			invokeSuccessResponse(webResource);
			invokeFailureResponse(webResource);
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Method responsible to invoke success xml response from rest service
	 * 
	 * @param webResource Contains root resoure url
	 * @throws JAXBException
	 */
	private static void invokeSuccessResponse(WebResource webResource) throws JAXBException {
		ClientResponse response = webResource.path(JerseyXmlClient.SUCCESS_PATH).accept(MediaType.APPLICATION_XML)
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String stringResponse = response.getEntity(String.class);

		XmlMainSuccessResponse successResponseObj = unMarshallXmlData(stringResponse, new XmlMainSuccessResponse());

		// prints response object in blaze format
		System.out.println(successResponseObj.toString());

	}

	/**
	 * Method responsible to invoke failure xml response from rest service
	 * 
	 * @param webResource
	 * @throws JAXBException
	 */
	private static void invokeFailureResponse(WebResource webResource) throws JAXBException {
		ClientResponse response = webResource.path(JerseyXmlClient.FAILURE_PATH).accept(MediaType.APPLICATION_XML)
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String stringResponse = response.getEntity(String.class);

		XmlMainErrorResponse errorResponseObj = unMarshallXmlData(stringResponse, new XmlMainErrorResponse());

		// prints response object in blaze format
		System.out.println(errorResponseObj.toString());
	}

	/**
	 * Method responsible to unmarshall xml string format to blaze format
	 * 
	 * @param responseObj - Response object. Either success or failure
	 * @throws JAXBException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unMarshallXmlData(String stringResponseXml, T t) throws JAXBException {
		StringReader reader = new StringReader(stringResponseXml);
		JAXBContext jc = JAXBContext.newInstance(t.getClass());
		Unmarshaller unMarshaller = jc.createUnmarshaller();
		T responseObj = (T) unMarshaller.unmarshal(reader);
		return responseObj;

	}

}