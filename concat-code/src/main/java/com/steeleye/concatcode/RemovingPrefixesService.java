package com.steeleye.concatcode;

public class RemovingPrefixesService {
	private String REGEX = "(?i)am|auf|auf dem|aus der|d|da|de|de l’|del|de la|de le|di|do|dos|du|im|la|le|mac|mc|mhac,mhíc|mhic giolla|mic|ni|ní|níc|o|ó|ua|ui|uí|van|van de|van den|van der|vom|von|von dem|von den|von der";
	
	public String execute(String name){
		return name.replaceAll(REGEX,"");
	}

}
