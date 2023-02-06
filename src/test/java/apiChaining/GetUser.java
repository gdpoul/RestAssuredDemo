package apiChaining;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {

	
	@Test
	void testGetUser(ITestContext context) {
//		int id=(Integer) context.getAttribute("user_id");  // scope within test level
		int id=(Integer) context.getSuite().getAttribute("user_id");  // suite level scope
		String bearerToken="5ec52f1166600ed551b8f9c86b72cde92518d6b69b091a0345f0503c47e3c2c3";

		given()
	     .headers("Authorization","Bearer "+bearerToken)
		 .pathParam("id", id)
		.when()
		 .get("https://gorest.co.in/public/v2/users/{id}")		
		.then()
		 .statusCode(200)
		 .log().all();
	}
}
