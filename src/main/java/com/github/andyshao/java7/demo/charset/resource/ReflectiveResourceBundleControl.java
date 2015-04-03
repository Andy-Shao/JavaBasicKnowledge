package com.github.andyshao.java7.demo.charset.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Aug 5, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class ReflectiveResourceBundleControl extends ResourceBundle.Control {

    @Override
    public List<String> getFormats(String baseName) {
        if (baseName == null) throw new NullPointerException();
        return Arrays.asList("reflection");
    }

    @Override
    public ResourceBundle newBundle(
        String baseName , Locale locale , String format , ClassLoader loader , boolean reload) {
        if (baseName == null || loader == null || format == null || locale == null) throw new NullPointerException();
        ResourceBundle bundle = null;
        if (format.equals("reflection")) {
            String bundleName = this.toBundleName(baseName , locale);
            try {
                Class<?> clazz = loader.loadClass(bundleName);
                return new ReflectiveResourceBundle(clazz);
            } catch (ClassNotFoundException e) {
                return bundle;
            }
        }

        return bundle;
    }
}
