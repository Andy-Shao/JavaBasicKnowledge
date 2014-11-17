package com.github.andyshao.java7.demo.charset.resource;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Aug 5, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class Locales {

	@Test
	public void useLocaleBuilder(){
		Locale locale = new Locale.Builder().setLanguage("zh").setRegion("CN")
		    .setExtension('m', "myext").build();
		
		assertThat(locale.toLanguageTag(), is("zh-CN-m-myext"));
	}
	
	@Test
	public void useLocaleCategory(){
		Locale.setDefault(Locale.US);
		ResourceBundle bundle = ResourceBundle.getBundle("com.github.andyshao.java7.demo.charset.resource.Message");
		String str = bundle.getString("GREETING");
		String msg = MessageFormat.format(str, "Zhang San", new Date());
		assertThat(msg, is("Hello!"));
	}
}
