package uploadAndDownlaodFile;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class FileUploadValidate {

	
//	@Test
	void singleFileUpload() {
		
		File myfile=new File("D:\\API Testing\\testdata2.json");
		
		given()
		  .multiPart("file",myfile)
		  .contentType("multipart/form-data")
		.when()
		  .post("http://localhost:8080/uploadFile")
		.then()
		  .statusCode(200)
		  .body("fileName", equalTo("testdata2.json"))
		  .log().all();
	}
	
	@Test
	void multipleFilesUpload() {  
		
		File myfile1=new File("D:\\API Testing\\testdata1.csv");
		File myfile2=new File("D:\\API Testing\\testdata2.json");
		
		given()
		  .multiPart("files",myfile1)
		  .multiPart("files",myfile2)
		  .contentType("multipart/form-data")
		.when()
		  .post("http://localhost:8080/uploadMultipleFiles")
		.then()
		  .statusCode(200)
		  .body("[0].fileName", equalTo("testdata1.csv"))
		  .body("[1].fileName", equalTo("testdata2.json"))
		  .log().all();

	}
//	@Test
	void multipleFilesUpload2() {  // wont work for all kinds of API's
		
		File myfile1=new File("D:\\API Testing\\testdata1.csv");
		File myfile2=new File("D:\\API Testing\\testdata2.json");
		
		 File myfileArr[]= {myfile1, myfile2};
		given()
		  .multiPart("files",myfileArr)
		  .contentType("multipart/form-data")
		.when()
		  .post("http://localhost:8080/uploadMultipleFiles")
		.then()
		  .statusCode(200)
		  .body("[0].fileName", equalTo("testdata1.csv"))
		  .body("[1].fileName", equalTo("testdata2.json"))
		  .log().all();

	}
}
