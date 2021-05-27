package stepDefinition;

import cucumber.api.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void prerequisite() throws Throwable{
		
		StepDefinition m = new StepDefinition();
		
		if(m.place_id==null){
			m.add_place_payload("RIYA", "ENGLISH", "USA");
			m.user_calls_something_with_post_http_request("AddPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("RIYA","GetPlaceAPI");
		}
		
		
	}

}
