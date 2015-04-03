package com.github.andyshao.java7.demo.charset.resource;

import java.util.Locale;
import java.util.ResourceBundle;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class ReflectiveResourceBundleTest {

    @SuppressWarnings("unchecked")
    @Test
    public void useReflectiveResourceBundle() {
        String baseName = "com.github.andyshao.java7.demo.charset.resource.ReflectiveMessage";
        ReflectiveResourceBundleControl control = new ReflectiveResourceBundleControl();
        ResourceBundle bundle = ResourceBundle.getBundle(baseName , Locale.CHINA , control);
        Assert.assertThat(bundle.getObject("greet").toString() ,
            Matchers.anyOf(Matchers.is("你好, 先生") , Matchers.is("你好, 女士")));
    }
}
