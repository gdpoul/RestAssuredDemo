package postRequstBody;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class UsingExternalJsonFile {
	
	// Post request body using External JSON File
	
	@Test(priority = 1)
	void testPostUsingExternalJsonFile() throws FileNotFoundException {
		
		File file=new File("./body.json");
		
		// to read the data from file
		FileReader fr=new FileReader(file);
		JSONTokener jt=new JSONTokener(fr);		
		JSONObject data =new JSONObject(jt);		
		
		given()
		       .contentType("application/json")
		       .body(data.toString())
		       
		.when()
		      .post("http://localhost:3000/students")
		
		.then()
		     .statusCode(201)
		     .body("name", equalTo("David"))
		     .body("location", equalTo("Austrilia"))
		     .body("phone", equalTo("978987"))
		     .body("courses[0]", equalTo("Java"))
		     .body("courses[1]", equalTo("Playwright"))
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
