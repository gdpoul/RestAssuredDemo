package uploadAndDownlaodFile;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class FileDownload {

	@Test(priority = 1)
	void singleFileUpload() {
		
		File myfile=new File("D:\\API Testing\\testdata2.json");
		
		given()
		  .multiPart("file",myfile)
		  .contentType("multipart/form-data")
		.when()
		  .post("http://localhost:8080/uploadFile")
		.then()
		  .statusCode(200)
		  .body("fileName", equalTo("testdata2.json"));
	}

	@Test(priority = 2)
	void testFileDownlaod(){
		
		given()		
		.when()
		   .get("http://localhost:8080/downloadFile/testdata2.json")
		.then()
		   .statusCode(200)
		   .log().body();		
	}
}
