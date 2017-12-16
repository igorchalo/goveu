package com.steeleye.concatcode;

public class NameTitlesService {
	
	private String REGEX = "(?i)atty|coach|dame|dr|fr|gov|honorable|madam(e)|maid|master|miss|monsieur|mr|mrs|ms|mx,ofc|ph.d|pres|prof|rev|sir";
	
	public String execute(String name){
		return name.replaceAll(REGEX,"");
	}

}
