package postRequstBody;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class UsingOrgJsonLib {

	// post request body using org.json library
	
	@Test(priority = 1)
	void testPostUsingJsonLibrary() {
		
		JSONObject data=new JSONObject();
		
		data.put("name", "David");
		data.put("location", "Aus");
		data.put("phone", "987456321");		
		String courseArr[]= {"C","C++"};
		data.put("courses", courseArr);
		
		
		given()
		       .contentType("application/json")
		       .body(data.toString())
		       
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
