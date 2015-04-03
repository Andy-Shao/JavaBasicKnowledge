package com.github.andyshao.java7.demo.charset.resource;

import java.util.Locale;
import java.util.ResourceBundle;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Aug 5, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class ResourceBundleDemo {

    @Test
    public void useResourceBundle() {
        String baseName = "com.github.andyshao.java7.demo.charset.resource.Message";
        ResourceBundle bundleEn = ResourceBundle.getBundle(baseName , Locale.US);
        Assert.assertThat(bundleEn.getString("GREETING") , Matchers.is("Hello!"));
        ResourceBundle bundleCn = ResourceBundle.getBundle(baseName , Locale.CHINA);
        Assert.assertThat(bundleCn.getString("THANK_YOU") , Matchers.is("谢谢!"));
    }
}
