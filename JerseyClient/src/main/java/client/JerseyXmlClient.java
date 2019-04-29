package client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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

		// invoke success xml type
		invokeSuccessResponse(webResource);

		// invoke failure xml type
		invokeFailureResponse(webResource);

	}

	/**
	 * Method responsible to invoke success xml response from rest service
	 * 
	 * @param webResource Contains root resoure url
	 */
	private static void invokeSuccessResponse(WebResource webResource) {
		ClientResponse response = webResource.path(JerseyXmlClient.SUCCESS_PATH).accept(MediaType.APPLICATION_XML)
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		XmlMainSuccessResponse xmlResponse = response.getEntity(XmlMainSuccessResponse.class);

		try {
			marshallXMLData(xmlResponse);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		XmlMainSuccessResponse blazeXml;
		try {
			blazeXml = (XmlMainSuccessResponse) unMarshallXmlData(xmlResponse);
			System.out.println(blazeXml.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Method responsible to invoke failure xml response from rest service
	 * 
	 * @param webResource
	 */
	private static void invokeFailureResponse(WebResource webResource) {
		ClientResponse response = webResource.path(JerseyXmlClient.FAILURE_PATH).accept(MediaType.APPLICATION_XML)
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		XmlMainErrorResponse xmlResponse = response.getEntity(XmlMainErrorResponse.class);

		try {
			marshallXMLData(xmlResponse);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}

		XmlMainErrorResponse blazeXml;
		try {
			blazeXml = (XmlMainErrorResponse) unMarshallXmlData(xmlResponse);
			System.out.println("Blaze failure xml:" + blazeXml.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	/**
	 * Method responsible to marshall xml data to blaze format
	 * 
	 * @param responseObj - Response object. Either success or failure
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */
	private static void marshallXMLData(Object responseObj) throws JAXBException, FileNotFoundException {
		JAXBContext jc = JAXBContext.newInstance(responseObj.getClass());

		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		marshaller.marshal(responseObj, new OutputStreamWriter(System.out));

		// Marshalling xml object into file to unmarshall later
		marshaller.marshal(responseObj, new OutputStreamWriter(new FileOutputStream("result.xml")));

	}

	public static Object unMarshallXmlData(Object responseObj) throws JAXBException, FileNotFoundException {

		JAXBContext jc = JAXBContext.newInstance(responseObj.getClass());
		// File to store xml response
		FileInputStream file = new FileInputStream("result.xml");

		Unmarshaller unMarshaller = jc.createUnmarshaller();

		// Unmarshall file to Object instance
		Object unMarshalledObj = unMarshaller.unmarshal(file);

		return unMarshalledObj;

	}

}