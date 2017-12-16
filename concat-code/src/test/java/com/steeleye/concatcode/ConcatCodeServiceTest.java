package com.steeleye.concatcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class ConcatCodeServiceTest {
	
	private ConcatCodeService concatCodeService;
	
	private Gson gson = new Gson();
	
	@Before
	public void before(){
		concatCodeService = new ConcatCodeService();
	}
	
	//@Test
	public void nullTest(){
		JsonObject jsonObject = new JsonObject(null,null);

		String concated = concatCodeService.concat(gson.toJson(jsonObject));
		Assert.assertEquals("Error: ",concated);
	}

	//@Test
	public void emptyTest(){
		JsonObject jsonObject = new JsonObject("","");

		String concated = concatCodeService.concat(gson.toJson(jsonObject));
		
		Assert.assertEquals("Error: ",concated);
	}
	
	@Test
	public void notConvertedTest(){
		JsonObject jsonObject = new JsonObject("John","O'Brian");

		String concated = concatCodeService.concat(gson.toJson(jsonObject));
		
		Assert.assertEquals("IE19800113JOHN#OBRIA",concated);
	}
	
	//@Test
	public void notConvertedTestCaseInsensitive(){
		JsonObject jsonObject = new JsonObject("John","O'Brian");

		String concated = concatCodeService.concat(gson.toJson(jsonObject));
		
		Assert.assertEquals("IE19800113JOHN#OBRIA",concated);
	}

}
