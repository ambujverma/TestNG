package apiTestCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class Test_getMethod {
	
	@Test
	public void Test01() {
		Response response = get("https://reqres.in/api/users?page=2");
		System.out.println(response.getStatusCode());
//		response.getBody();
		System.out.println(response.getBody().asString());
		System.out.println(response.getTime());
		System.out.println(response.getHeader("content-type"));
		int expectedStatus = 200;
		int actualStatus = response.getStatusCode();
		Assert.assertEquals(expectedStatus, actualStatus);
	}

	public void Test02() {
		
		//given() //when() //Then()
		baseURI = "https://reqres.in/api/users";
		given()
		.queryParam("page", 2)
		.when().get()
		.then().statusCode(200);
		
	}
}
