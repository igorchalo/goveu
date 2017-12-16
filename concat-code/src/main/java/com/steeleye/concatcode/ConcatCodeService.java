package com.steeleye.concatcode;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

public class ConcatCodeService {
	
	/**
	 * Pattern compile is more fast than String.replace.
	 */
	private Pattern NAME_TITLE_PATTERN = Pattern.compile("atty|coach|dame|dr|fr|gov|honorable|madam(e)|maid|master|miss|monsieur|mr|mrs|ms|mx,ofc|ph.d|pres|prof|rev|sir",Pattern.CASE_INSENSITIVE);
	
	private Pattern REMOVE_PREFIX_PATTERN = Pattern.compile("am|auf|auf dem|aus der|d|da|de|de l’|del|de la|de le|di|do|dos|du|im|la|le|mac|mc|mhac,mhíc|mhic giolla|mic|ni|ní|níc|o|ó|ua|ui|uí|van|van de|van den|van der|vom|von|von dem|von den|von der",Pattern.CASE_INSENSITIVE);
	
	/**
	 * Creating of new matcher is tread safe.
	 * @param json
	 * @return
	 */
	public String concat(final String json) {
		Gson gson = new Gson();
		
		JsonObject object = gson.fromJson(json, JsonObject.class);
		
		final String replacement = "";
		
		Matcher matcherFirstName = NAME_TITLE_PATTERN.matcher(object.getFirstName());
		Matcher matcherFirstNamePrefix = REMOVE_PREFIX_PATTERN.matcher(object.getFirstName());
		
		Matcher matcherSurname = NAME_TITLE_PATTERN.matcher(object.getSurname());
		
		String firstName = matcherFirstName.replaceAll(replacement);
		firstName = matcherFirstNamePrefix.replaceAll(replacement);
		firstName = article6(firstName);
		
		String surname = matcherSurname.replaceAll(replacement);
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
		String normalizedString = Normalizer.normalize(value, Normalizer.Form.NFKD);
		normalizedString.replaceAll("\\p{InCombiningDiacriticalMarks}", "");
		
		return String.format("%-5s", normalizedString).replace(' ', '#').trim().toUpperCase();
	}

}
