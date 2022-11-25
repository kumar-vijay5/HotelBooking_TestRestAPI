package ReusableMethods;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import baseconfig.BaseSetup;

public class Payloads extends BaseSetup{
	
	public static String payLoadForAuthorizeToken() {
		String getPayLoadForAuthorizeToken = 
				"{\r\n" 
					+ "		\"username\" : \""+prop.getProperty("username")+"\",\r\n"
					+ "		\"password\" : \""+prop.getProperty("password")+"\"\r\n"
					+ "}";
		return getPayLoadForAuthorizeToken;
	}
	
	public static String newBookingPayload() throws IOException {		
		String payLoadForNewBooking = 
			"{"
				+ "    \"firstname\" : \""+prop.getProperty("firstname")+"\",\r\n"
				+ "    \"lastname\" : \""+prop.getProperty("lastname")+"\",\r\n"
				+ "    \"totalprice\" : "+prop.getProperty("totalprice")+",\r\n"
				+ "    \"depositpaid\" : "+prop.getProperty("depositpaid")+",\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \""+prop.getProperty("checkin")+"\",\r\n"
				+ "        \"checkout\" : \""+prop.getProperty("checkout")+"\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \""+prop.getProperty("additionalneeds")+"\"\r\n"
				+ "}";
		return payLoadForNewBooking;
	}
	
	public static String updateBookingPartialDetailsPayLoad() {
		String payLoadForPartialBooking = 
				"{\r\n"
	  			+ "    \"firstname\" : \"James\",\r\n"
	  			+ "    \"lastname\" : \"Brown\",\r\n"
	  			+ "    \"totalprice\" : 111 \r\n"
	  			+ "}";		
		return payLoadForPartialBooking;
	}
	
	public static Map<String,String> getBookingByNamePayLoad() {
		Map<String, String> map = new HashMap();
		map.put("firstname", "Sally");
		map.put("lastname", "Brown");
		return map;
	}
	
}
