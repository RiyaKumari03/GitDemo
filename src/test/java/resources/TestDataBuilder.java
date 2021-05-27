package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;

public class TestDataBuilder {

	public AddPlace payload(String name,String language,String address){
		AddPlace addPlacePayload = new AddPlace();
		addPlacePayload.setAccuracy(50);
		addPlacePayload.setAddress(address);
		addPlacePayload.setLanguage(language);
		addPlacePayload.setName(name);
		addPlacePayload.setPhone_number("(+91) 983 893 3937");
		addPlacePayload.setWebsite("http://google.com");
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		addPlacePayload.setLocation(location);
		List<String> lst = new ArrayList<String>();
		lst.add("shoe park");
		lst.add("shop");
		addPlacePayload.setTypes(lst);
		
		return addPlacePayload;
		
	}
	
	public String deletePlacePayload(String id){
		
		return "{\r\n\"place_id\":\""+id+"\"\r\n}";
	}
	
}
