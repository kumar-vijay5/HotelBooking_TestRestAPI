package ReusableMethods;

import java.io.FileNotFoundException;
import baseconfig.BaseSetup;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestResponsebuilder extends BaseSetup{
		
	public RequestSpecification getTokenIDRequest() {
		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(prop.getProperty("baseurl")).setContentType(ContentType.JSON).build();
		return requestSpecification;
	}
	
	public RequestSpecification createBookingRequest() throws FileNotFoundException {
		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(prop.getProperty("baseurl")).setAccept("application/json").setContentType(ContentType.JSON).build();
		return requestSpecification;
	}
	
	public ResponseSpecification createBookingResponse() {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return responseSpecification;
	}
	
	public RequestSpecification updateBookingRequest(String tokenID) {
		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(prop.getProperty("baseurl")).setAccept("application/json").setContentType(ContentType.JSON).addCookie("token",tokenID).build();
		return requestSpecification;
	}
	
	public ResponseSpecification updateBookingResponse() {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return responseSpecification;
	}
	
	public RequestSpecification getBookingByIdRequest() {
		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(prop.getProperty("baseurl")).setAccept("application/json").build();
		return requestSpecification;
	}
	
	public ResponseSpecification getBookingByIdResponse() {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).build();
		return responseSpecification;
	}
	
	public RequestSpecification getBookingByAllIdsRequest() {
		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(prop.getProperty("baseurl")).build();
		return requestSpecification;
	}
	
	public ResponseSpecification getBookingByAllIdsResponse() {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).build();
		return responseSpecification;
	}
	
	public RequestSpecification getBookingByNameRequest() {
		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(prop.getProperty("baseurl")).addQueryParams(Payloads.getBookingByNamePayLoad()).build();
		return requestSpecification;
	}
	
	public ResponseSpecification getBookingByNameResponse() {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).build();
		return responseSpecification;
	}
	
	public RequestSpecification deleteBookingIdRequest(String tokenID) {		
		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(prop.getProperty("baseurl")).addCookie("token",tokenID).build();
		return requestSpecification;
	}
	
	public ResponseSpecification deleteBookingIdResponse() {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(201).build();
		return responseSpecification;
	}
}
