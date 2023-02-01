package httpMethods;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 given()
    content type, set cookies, add auth, add parameter, set header info etc..
    
 when()
    get, post, put, delete urls
    
 then()
    validate status code, extract response, extract header cookies and response 
    
    */

public class HttpRequest {
	
	int id;
	
	@Test(priority = 1)
	void getUsers() {
		
		given()
		
		.when()
		  .get("https://reqres.in/api/users?page=2")
		
		.then()
		  .statusCode(200)
		  .body("page", equalTo(2))
		  .log().all();
	}
	
	@Test(priority = 2)
	void crateUser() {
		HashMap<String, String> data=new HashMap<String, String>();
		data.put("name", "Ganesh");
		data.put("job", "Test Engineer");
		
		id=given()
		       .contentType("application/json")
	           .body(data)
		
		.when()
		       .post("https://reqres.in/api/users")
		       .jsonPath().getInt("id");
		 
//		.then()
//		       .statusCode(201)
//		       .log().all();
		
	}
	
	@Test(priority = 3, dependsOnMethods = {"crateUser"})
	void updateUser() {
	
		HashMap<String, String> data=new HashMap<String, String>();
		data.put("name", "Ganesh");
		data.put("job", "Test Automation Engineer");
		
		given()
		       .contentType("application/json")
	           .body(data)
		
		.when()
		       .put("https://reqres.in/api/users/"+id)
		       
		   	.then()
		       .statusCode(200)
		       .log().all();

	}
	
	@Test(priority = 4)
	void deleteUser() {
	
		given()
		
		.when()
		      .delete("https://reqres.in/api/users/"+id)
		
		.then()
		      .statusCode(204)
		      .log().all();
	}

}
