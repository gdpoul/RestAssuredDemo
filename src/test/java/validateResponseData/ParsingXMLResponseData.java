package validateResponseData;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponseData {

	//@Test(priority = 1)
	void testXMLResponse() {
		
		// Approch1: static data validate
		
/*		given()
		
		.when()
		   .get("http://restapi.adequateshop.com/api/Traveler?page=1")
		
		.then()
		     .statusCode(200)
		     .header("Content-Type", "application/xml; charset=utf-8")
		     .body("TravelerinformationResponse.page", equalTo("1"))
		     .body("TravelerinformationResponse.travelers.Travelerinformation[0].name",equalTo("Developer"));
*/		
		// Approch2: dynamic data validate
		
		Response res = given()		
		.when()
		   .get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(res.statusCode(), 200);  // validate status code
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");  //validate content type
		
		String pageNo=res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo, "1");        // validate page no

	}
	@Test(priority = 2)
	void testXMLResponse2() {
	
		Response res = given()		
		.when()
		   .get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xmlObj=new XmlPath(res.asString());
		
		// verify total no of traveller
		List<String> travellers = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation");
        Assert.assertEquals(travellers.size(),10);
        
        // verify traveller name present in response
        List<String> travellerNames = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");	
        
        boolean status=false;
        for(String name :travellerNames) {
        	
        	if(name.equals("Developer")) {
        		status =true;
        		break;
        	}
        }
        Assert.assertTrue(status);
	}

}
