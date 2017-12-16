package com.steeleye.concatcode;

import java.text.Normalizer;

public class ConcatNormalizerService {

	public String execute(String value) {
		String normalizedString = Normalizer.normalize(value, Normalizer.Form.NFKD);
		return normalizedString.replaceAll("\\p{InCombiningDiacriticalMarks}", "");
	}

}
