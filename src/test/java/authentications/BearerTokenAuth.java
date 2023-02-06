package authentications;

import org.testng.annotations.Test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class BearerTokenAuth {
	
	@Test
	void testBearerTokenAuth() {
		
		String bearerToken="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";
		
		given()
		    .headers("Authorization","Bearer "+bearerToken)
		.when()
		    .get("https://api.github.com/user/repos")
		
		.then()
		   .statusCode(200)
		   .log().all();
	}

}
