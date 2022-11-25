package ReusableMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Verification {
	public static JsonPath jspath;
	
	public static JsonPath parseAsJson(Response verifyReponse) {
		return new JsonPath(verifyReponse.asString());
	}
	
	
}
