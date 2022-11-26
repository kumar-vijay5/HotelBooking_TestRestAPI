package testscenario;

import static io.restassured.RestAssured.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;
import ReusableMethods.Payloads;
import ReusableMethods.RequestResponsebuilder;
import ReusableMethods.Verification;
import baseconfig.BaseSetup;
import io.restassured.response.Response;

public class CreateBooking extends BaseSetup {
	private static int bookingidFromResponse =0;
	RequestResponsebuilder rbuilder;
	public static String getTokenID;
	private String bookingEndpoint = prop.getProperty("booking")+ "/" + bookingidFromResponse;
	
	public CreateBooking() throws FileNotFoundException {
		rbuilder = new RequestResponsebuilder();
	}
	
	
	public void getResponse_Postauthorization() {
		System.out.println("*********Authorize by token Test case***********");
		Response authorizeCredentialsResponse = given().spec(rbuilder.getTokenIDRequest()).log().all()
				.body(Payloads.payLoadForAuthorizeToken())
				.when().post(prop.getProperty("auth"))
				.then().log().all().extract().response();
		getTokenID = authorizeCredentialsResponse.jsonPath().get("token");
		assertNotNull(getTokenID);
	}
	
	@Test
	public void testcreateNewBooking() throws IOException {
		System.out.println("*********Create new booking Test case***********");
		getResponse_Postauthorization();
		Response newBookingresponse = given().spec(rbuilder.createBookingRequest()).log().all()
				.body(Payloads.newBookingPayload())
				.when().post(prop.getProperty("booking"))
				.then().spec(rbuilder.createBookingResponse()).log().all().extract().response();
		bookingidFromResponse = Verification.parseAsJson(newBookingresponse).getInt("bookingid");
		assertNotNull(bookingidFromResponse);
	}

	@Test
	public void testpartialUpdateBooking() {
		System.out.println("*********partial Update new booking Test case***********");		
		Response updateBookingresponse = given().spec(rbuilder.updateBookingRequest(getTokenID)).log().all()
				.body(Payloads.updateBookingPartialDetailsPayLoad())
				.when().patch(bookingEndpoint)
				.then().spec(rbuilder.updateBookingResponse()).log().all().extract().response();
		assertThat(Verification.parseAsJson(updateBookingresponse).getString("firstname"), equalTo("James"));
		assertThat(Verification.parseAsJson(updateBookingresponse).getString("lastname"), equalTo("Brown"));
	}

	@Test
	public void testgetBookingIds() {
		System.out.println("*********get All bookings of all Ids Test case***********");
		given().spec(rbuilder.getBookingByAllIdsRequest()).log().all()
				.when().get(prop.getProperty("booking"))
				.then().spec(rbuilder.getBookingByAllIdsResponse()).log().all().extract().response();
	}	 

	@Test
	public void testgetBookingById() {
		System.out.println("*********Get a booking By Id Test case***********");		
		Response bookingbyIdResponse = given().spec(rbuilder.getBookingByIdRequest()).log().all()
				.when().get(bookingEndpoint)
				.then().spec(rbuilder.getBookingByIdResponse()).log().all().extract().response();
		assertThat(Verification.parseAsJson(bookingbyIdResponse).getString("firstname"), equalTo("James"));
	}

	@Test
	public void testgetBookingByName() {
		System.out.println("*********Get a booking By Name Test case***********");
		given().spec(rbuilder.getBookingByNameRequest()).log().all()
				.when().get(prop.getProperty("booking"))
				.then().spec(rbuilder.getBookingByNameResponse()).log().all().extract().response();
	}

	@Test
	public void testdeleteBooking() {
		System.out.println("*********Delete recent booking Test case***********");
		Response deletedBooking = given().spec(rbuilder.deleteBookingIdRequest(getTokenID)).log().all()
				.when().delete(bookingEndpoint)
				.then().spec(rbuilder.deleteBookingIdResponse()).log().all().extract().response();
	}
}
