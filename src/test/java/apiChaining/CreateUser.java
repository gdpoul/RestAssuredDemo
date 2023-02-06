 package apiChaining;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import com.github.javafaker.Faker;

public class CreateUser {

	@Test
	void testCreateUser(ITestContext context) {
		
		Faker faker=new Faker();		
		JSONObject data=new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		String bearerToken="5ec52f1166600ed551b8f9c86b72cde92518d6b69b091a0345f0503c47e3c2c3";
		
		int id = given()
		     .headers("Authorization","Bearer "+bearerToken)
		     .contentType("application/json")
		     .body(data.toString())		
		.when()
		     .post("https://gorest.co.in/public/v2/users")
		     .jsonPath().getInt("id");
		
		System.out.println("Generated id is :"+id);
//		context.setAttribute("user_id", id); // scope only within test level
		context.getSuite().setAttribute("user_id", id); //scope for suite level
	}
}
