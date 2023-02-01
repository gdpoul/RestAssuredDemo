package postRequstBody;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

/*
 * Here we use Encapsulation Concept i.e getter and setter to create a data
 * 
 */

public class USingPOJO {

	// post request body using POJO Class
	
	@Test(priority = 1)
	void testPostUsingPOJOClass() {
		
		Pojo_PostRequest data=new Pojo_PostRequest();
		data.setName("David");
		data.setLocation("Aus");
		data.setPhone("987456321");
		String courseArr[]= {"C","C++"};
		data.setCourses(courseArr);
		
		
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
