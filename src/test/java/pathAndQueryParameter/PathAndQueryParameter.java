package pathAndQueryParameter;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class PathAndQueryParameter {
	
	// https://reqres.in/api/users?page=2&id=5
	
	
	@Test
	void testPathAndQueryParameters() {
		
		given()
		       .pathParam("mypath", "users")   //path parameters
		       .queryParam("page", "2")        //query parameters
		       .queryParam("id", "5")
		
		.when()
		       .get("https://reqres.in/api/{mypath}")
				
		.then()
		      .statusCode(200)
		      .log().body();
	}

}
