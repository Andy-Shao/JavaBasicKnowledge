package com.github.andyshao.java7.demo.charset.resource;

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
public class ResourceBundleDemo {

	@Test
	public void useResourceBundle(){
		String baseName = "com.github.andyshao.java7.demo.charset.resource.Message";
		ResourceBundle bundleEn = ResourceBundle.getBundle(baseName, Locale.US);
		assertThat(bundleEn.getString("GREETING"), is("Hello!"));
		ResourceBundle bundleCn = ResourceBundle.getBundle(baseName, Locale.CHINA);
		assertThat(bundleCn.getString("THANK_YOU"), is("谢谢!"));
	}
}
