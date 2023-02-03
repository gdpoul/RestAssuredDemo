package cookies_Headers_Logs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {
	
//	@Test(priority = 1)
	void testHeaders(){
		
		given()
		
		.when()
		      .get("https://www.google.com/")
		
		.then()
		      .header("Content-Type", "text/html; charset=ISO-8859-1")
		      .header("Content-Encoding", "gzip")
		      .header("Server", "gws");		
	}
	
	@Test(priority = 2)
	void testGetHeaders(){
		
		Response res = given()	
		.when()
		      .get("https://www.google.com/");	
		
		// get single header info
		
//		String headerValue = res.getHeader("Content-Type");
//		System.out.println("value of content value : "+headerValue);
		
		// get all headers info
		
		Headers allHeaders = res.getHeaders();
		
		for(Header hd:allHeaders) {
			System.out.println(hd.getName()+": "+hd.getValue());
		}
	}


}
