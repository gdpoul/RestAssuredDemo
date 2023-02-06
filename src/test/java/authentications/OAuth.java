package authentications;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class OAuth {
	
	@Test
	void testOAuth1() {
		
		given()
		  .auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")
		 
	    .when()
	       .get("url")
	    .then()
	    .statusCode(200);
	}
	
	@Test
	void testOAuth2() {
		
		given()
		  .auth().oauth2("String access token");
		
		when()
		   .get("url")
		   
		.then()
		   .statusCode(200);
	}

}
