package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.jbehave.core.annotations.Named;
import org.junit.runner.RunWith;

import POJO.AddPlace;
import POJO.Location;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
public class StepDefinition extends Utils {

	RequestSpecification request;
	ResponseSpecification responseSpec;
	AddPlace addPlacePayload;
	Response response;
	TestDataBuilder testData = new TestDataBuilder();;
	static String place_id;
	
	@Given("^Add place payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void add_place_payload(String name, String language, String address) throws Throwable {
		
		System.out.println("This change is for develop branch");
		
		addPlacePayload = testData.payload(name,language,address);
		request = given().log().all().spec(requestSpecification()).body(addPlacePayload);
				
    }

    @When("user calls \"([^\"]*)\" with \\\"([^\\\"]*)\\\" http request")
    public void user_calls_something_with_post_http_request(String resource, String method) throws Throwable {
    	
    	  	
    	if(method.equalsIgnoreCase("POST")){
    	response = request.when()
		.post(APIResources.valueOf(resource).getAPIResources());
    	}
    	else if(method.equalsIgnoreCase("DELETE")){
    		response = request.when()
    				.post(APIResources.valueOf(resource).getAPIResources());
    	}
    	else if(method.equalsIgnoreCase("GET")){
    		response = request.when()
    				.get(APIResources.valueOf(resource).getAPIResources());
    	}
    	System.out.println(response.prettyPrint());
		
    }

    @Then("^the API call is success with status Code 200$")
    public void the_api_call_is_success_with_status_code_200() throws Throwable {
        
    	Assert.assertEquals(response.getStatusCode(), 200);
    }

    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void something_in_response_body_is_something(String strArg1, String strArg2) throws Throwable {
       
    	Assert.assertEquals(getJsonPath(response, strArg1), strArg2);
    	
    }
    @Then("^verify place_id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
    public void verify_place_id_created_maps_to_using(String actualName, String resource) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        place_id = getJsonPath(response, "place_id");
    	request = given().log().all().spec(requestSpecification()).queryParam("place_id", place_id);
    	user_calls_something_with_post_http_request(resource,"GET");
    	Assert.assertEquals(actualName, getJsonPath(response, "name"));
    }
    
    @Given("DeletePlace Payload")
    public void deletePlace() throws IOException{
    	request = given().log().all().spec(requestSpecification()).body(testData.deletePlacePayload(place_id));
    	
    }

}
