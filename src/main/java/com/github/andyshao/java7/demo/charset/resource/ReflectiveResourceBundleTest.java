package com.github.andyshao.java7.demo.charset.resource;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

public class ReflectiveResourceBundleTest {

	@SuppressWarnings("unchecked")
	@Test
	public void useReflectiveResourceBundle(){
		String baseName = "com.github.andyshao.java7.demo.charset.resource.ReflectiveMessage";
		ReflectiveResourceBundleControl control = new ReflectiveResourceBundleControl();
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, Locale.CHINA, control);
		assertThat(bundle.getObject("greet").toString(), anyOf(is("你好, 先生"), is("你好, 女士")));
	}
}
