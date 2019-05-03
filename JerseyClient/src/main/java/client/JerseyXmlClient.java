package client;

import java.io.StringReader;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.rest.model.XmlMainResponse;
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

		WebResource webResource = client.resource("http://localhost:8080/JerseyRest/rest/response");

		try {
			// invoke xml type, success or error based on rest service
			invokeXmlService(webResource);
			
			// invoke success xml type, for testing success response type
			invokeSuccessResponse(webResource);
			
			// invoke failure xml type, for testing failure response type
			invokeFailureResponse(webResource);
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Method responsible to invoke xml response from rest service
	 * 
	 * @param webResource Contains root resoure url
	 * @throws JAXBException
	 */
	private static void invokeXmlService(WebResource webResource) throws JAXBException {
		// TODO Auto-generated method stub
		ClientResponse response = webResource.accept(MediaType.APPLICATION_XML)
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String stringResponse = response.getEntity(String.class);

		XmlMainResponse responseObj = unMarshallXmlData(stringResponse, new XmlMainResponse());

		// prints response object in blaze format
		System.out.println(responseObj.toString());
	}

	/**
	 * Method responsible to invoke success xml response from rest service
	 * 
	 * @param webResource Contains root resoure url
	 * @throws JAXBException
	 */
	private static void invokeSuccessResponse(WebResource webResource) throws JAXBException {
		ClientResponse response = webResource.path(SUCCESS_PATH).accept(MediaType.APPLICATION_XML)
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String stringResponse = response.getEntity(String.class);

		XmlMainResponse successResponseObj = unMarshallXmlData(stringResponse, new XmlMainResponse());

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
		ClientResponse response = webResource.path(FAILURE_PATH).accept(MediaType.APPLICATION_XML)
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String stringResponse = response.getEntity(String.class);

		XmlMainResponse errorResponseObj = unMarshallXmlData(stringResponse, new XmlMainResponse());

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
		
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
		 
        //Print XML String to Console
		marshaller.marshal(responseObj, System.out);
		
		return responseObj;

	}

}