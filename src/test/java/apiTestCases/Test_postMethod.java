package apiTestCases;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Test_postMethod {
	@Test
	public void Test03() {
		JSONObject jsonData = new JSONObject();
		jsonData.put("Nane", "Ambuj");
		jsonData.put("job", "QA");
		RestAssured.baseURI = "https://reqres.in/api/users";
		RestAssured.given()
		.header("Content-type","application/json")
		.contentType(ContentType.JSON)
		.body(jsonData).when().post()
		.then().log().all()
		.statusCode(201);
		
	
	}

}
