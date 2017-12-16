package com.steeleye.concatcode;

import com.google.gson.Gson;

public class ConcatCodeService {
	
	private NameTitlesService nameTitlesService = new NameTitlesService();
	
	private RemovingPrefixesService removingPrefixesService = new RemovingPrefixesService();
	
	private ConcatNormalizerService normalizer = new ConcatNormalizerService();

	private Gson gson = new Gson();
	
	public String concat(final String json) {
		JsonObject object = gson.fromJson(json, JsonObject.class);
		
		String firstName = nameTitlesService.execute(object.getFirstName());
		firstName = removingPrefixesService.execute(firstName);
		firstName = normalizer.execute(firstName);
		
		String surname = removingPrefixesService.execute(object.getSurname());
		surname = normalizer.execute(surname);
		
		return firstName.concat(surname);
	}
	
	private String article6(String value){
		//return value.a
		return null;
	}

}
