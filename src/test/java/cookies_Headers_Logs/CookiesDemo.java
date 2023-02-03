package cookies_Headers_Logs;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class CookiesDemo {
	
//	@Test(priority = 1)
	void testCookies(){
		
		given()
		
		.when()
		      .get("https://www.google.com/")
		
		.then()
		      .cookie("AEC","ARSKqsKqA-KC1x7u5y6Qv06_HjZtqJ7y5khS7nrOTKzZOtBynoZ5wddKU2o")
		      .log().all();
		
	}
	
	@Test(priority = 2)
	void testCookiesInfo(){
		
		Response res = given()
		.when()
		      .get("https://www.google.com/");
		
		// get single cookie info
		
//		String cookie_value = res.getCookie("AEC");
//		System.out.println("value of cookie AEC :"+cookie_value);
		
		// get all cookies info
		Map<String, String> cookies_value = res.getCookies();
		
		System.out.println(cookies_value.keySet());
		
		for(String key: cookies_value.keySet()) {
			String value=res.getCookie(key);
			System.out.println(key+"  :"+value);
		}
	}


}
