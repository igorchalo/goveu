package com.steeleye.concatcode;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

public class ConcatCodeService {
	
	/**
	 * Pattern compile is more fast than String.replace.
	 */
	private Pattern NAME_TITLE_PATTERN = Pattern.compile("^(atty|coach|dame|dr|fr|gov|honorable|madam(e)|maid|master|miss|monsieur|mr|mrs|ms|mx,ofc|ph.d|pres|prof|rev|sir)\\s",Pattern.CASE_INSENSITIVE);
	
	private Pattern REMOVE_PREFIX_PATTERN = Pattern.compile("^(am|auf dem|auf|aus der|d|da|de la|de le|de|de l’|del|di|do|dos|du|im|la|le|mac|mc|mhac,mhíc|mhic giolla|mic|ni|ní|níc|o|ó|ua|ui|uí|van de|van den|van der|van|vom|von|von dem|von den|von der)\\s",Pattern.CASE_INSENSITIVE);
	
	private Pattern ENGLISH_ALPHABET_PATTERN = Pattern.compile("[^a-z]",Pattern.CASE_INSENSITIVE);
	
	private Pattern NORMALIZER_PATTERN = Pattern.compile("\\p{InCombiningDiacriticalMarks}",Pattern.CASE_INSENSITIVE);
	
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
		
		Matcher matcherSurname = REMOVE_PREFIX_PATTERN.matcher(object.getSurname());
		
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
		String normalizedString = Normalizer.normalize(value, Normalizer.Form.NFD);
		normalizedString = NORMALIZER_PATTERN.matcher(value).replaceAll(normalizedString);
		
		String result = ENGLISH_ALPHABET_PATTERN.matcher(normalizedString).replaceAll("");
		return String.format("%1$-5s", result).replace(' ', '#').trim().substring(0, 5).toUpperCase();
	}
	
	public static void main(String[] args) {
		System.out.println("Van der Rohe".replaceAll("^(Van der|van hill)\\s", ""));
	}
}
