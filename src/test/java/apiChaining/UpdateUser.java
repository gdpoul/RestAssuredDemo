package apiChaining;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {

	@Test
	void testUpdateUser(ITestContext context) {
		Faker faker=new Faker();		
		JSONObject data=new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		String bearerToken="5ec52f1166600ed551b8f9c86b72cde92518d6b69b091a0345f0503c47e3c2c3";
//		int id=(Integer) context.getAttribute("user_id");  // scope within test level
		int id=(Integer) context.getSuite().getAttribute("user_id");  // suite level scope
		 given()
		     .headers("Authorization","Bearer "+bearerToken)
		     .contentType("application/json")
		     .pathParam("id", id)
		     .body(data.toString())		
		 .when()
		     .put("https://gorest.co.in/public/v2/users/{id}")
		 .then()
		     .statusCode(200)
		     .log().all();
		
	}
}
