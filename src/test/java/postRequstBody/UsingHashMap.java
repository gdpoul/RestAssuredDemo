package postRequstBody;

import java.util.HashMap;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


/*
   Different Ways to Create a Post Request Body
   1) Using Hashmap
   2) Using org.JSON
   3) Using POJO Class(Plain Old Java Object)
   4) Using External JSON File Data
 */
public class UsingHashMap {

	// post request body using Hashmap
	
	@Test(priority = 1)
	void testPostUsingHashMap() {
		
		HashMap<String, Object> data =new HashMap<String, Object>();
		data.put("name", "David");
		data.put("location", "Aus");
		data.put("phone", "987456321");		
		String courseArr[]= {"C","C++"};
		data.put("courses", courseArr);
		
		
		given()
		       .contentType("application/json")
		       .body(data)
		       
		.when()
		      .post("http://localhost:3000/students")
		
		.then()
		     .statusCode(201)
		     .body("name", equalTo("David"))
		     .body("location", equalTo("Aus"))
		     .body("phone", equalTo("987456321"))
		     .body("courses[0]", equalTo("C"))
		     .body("courses[1]", equalTo("C++"))
		     .header("Content-Type", "application/json; charset=utf-8")
		     .log().all();
				
	}
	
	// deleting student record
	@Test(priority = 2)
	void testDelete() {
		
		given()
		
		.when()
		       .delete("http://localhost:3000/students/4")
		
		.then()
		      .statusCode(200);
	}
	
}
