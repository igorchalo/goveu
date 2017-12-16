package com.steeleye.concatcode;

import com.google.gson.Gson;

public class ConcatCodeService {
	
	private NameTitlesService nameTitlesService;
	
	private RemovingPrefixesService removingPrefixesService;

	private Gson gson = new Gson();
	
	public String concat(final String json) {
		JsonObject object = gson.fromJson(json, JsonObject.class);
		
		String firstName = nameTitlesService.execute(object.getFirstName());
		firstName = removingPrefixesService.execute(firstName);
		
		String surName = removingPrefixesService.execute(object.getSurname());
		
		return null;
	}

}
