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
		firstName = article6(firstName);
		
		String surname = removingPrefixesService.execute(object.getSurname());
		surname = normalizer.execute(surname);
		surname = article6(surname);
		
		String countryCode = "IE19800113";
		
		return countryCode.concat(firstName.concat(surname));
	}
	
	/**
	 *  <ul>
	 *  <li>For the purposes of paragraph 4, prefixes to names shall be excluded and first names and surnames shorter than five characters shall be appended by ‘#’</li>
	 *  <li>Ensure that references to names and surnames in accordance with paragraph 4 contain five characters.</li> 
	 *  <li>All characters shall be in upper case. No apostrophes, accents, hyphens, punctuation marks or spaces shall be used.</li>
	 *  </ul>
	 * @param value
	 * @return
	 */
	private String article6(String value){
		return String.format("%-5s", value).replace(' ', '#').toUpperCase();
	}

}
