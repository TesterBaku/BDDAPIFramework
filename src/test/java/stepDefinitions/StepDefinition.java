package stepDefinitions;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) {
		try {
			res = given().spec(requestSpecification())
						.body(data.addPlacePayload(name, language, address));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	

	@When("user calls {string} with Post http request")
	public void user_calls_with_post_http_request(String resource) {
		
		APIResources resourceAPi = APIResources.valueOf(resource);
		
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response = res.when().post(resourceAPi.getResource())
				.then().spec(resSpec).extract().response();
	}

	@Then("the API call is successful with status code {int}")
	public void the_api_call_is_successful_with_status_code(Integer int1) {	
		//need to come back to this. code should not be hardcoded
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} is response body is {string}")
	public void is_response_body_is(String keyValue, String expectedValue) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		Assert.assertEquals(js.get(keyValue).toString(), expectedValue);
	}

}
