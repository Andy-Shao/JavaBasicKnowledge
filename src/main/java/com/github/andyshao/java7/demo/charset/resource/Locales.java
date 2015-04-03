package com.github.andyshao.java7.demo.charset.resource;

import java.text.MessageFormat;
import java.util.Date;
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
public class Locales {

    @Test
    public void useLocaleBuilder() {
        Locale locale = new Locale.Builder().setLanguage("zh").setRegion("CN").setExtension('m' , "myext").build();

        Assert.assertThat(locale.toLanguageTag() , Matchers.is("zh-CN-m-myext"));
    }

    @Test
    public void useLocaleCategory() {
        Locale.setDefault(Locale.US);
        ResourceBundle bundle = ResourceBundle.getBundle("com.github.andyshao.java7.demo.charset.resource.Message");
        String str = bundle.getString("GREETING");
        String msg = MessageFormat.format(str , "Zhang San" , new Date());
        Assert.assertThat(msg , Matchers.is("Hello!"));
    }
}
