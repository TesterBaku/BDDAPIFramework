package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlaceTag")
	public void beforeScenario() throws IOException {
		//write code that will give you place id
		//execute this code only when place id is null
		
		StepDefinition m = new StepDefinition();
		
		if(StepDefinition.place_id == null) {
		m.add_place_payload_with("Rufat", "Azerbaijani", "123 Rasulzadeh ave., Baku");
		m.user_calls_with_http_request("addPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Rufat", "getPlaceAPI");
		}
	}
	
}
