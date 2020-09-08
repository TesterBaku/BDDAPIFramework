package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload (String name, String language, String address){
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setName(name);

		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		return p;
	}
	
	//addPlace with data from excel
	
	public HashMap<String, Object> addPlaceExcel() throws IOException {
		
		ExcelUtil d=new ExcelUtil();
		ArrayList data=d.getData("AddPlace","Place");
		
		
		HashMap<String, Object>  map = new HashMap<String, Object>();
		map.put("accuracy", data.get(1));
		map.put("name", data.get(2));
		map.put("phone_number", data.get(3));
		map.put("address", data.get(4));
		map.put("website", data.get(5));
		map.put("language", data.get(6));
		
		HashMap<String, Object>  mapLocation = new HashMap<String, Object>();
		mapLocation.put("lat", data.get(7));
		mapLocation.put("lng", data.get(8));
		
		map.put("location", mapLocation);
		
		
		List<Object> myList=new ArrayList<Object>();
		myList.add(data.get(9));
		myList.add(data.get(10));
		
		
		map.put("types", myList);
		
		return map;	
		
	}
	
	public String deletePlacePayload(String placeId) {
		
		return "{\r\n  \"place_id\":\""+ placeId +"\" \r\n}";
		
	}

}
