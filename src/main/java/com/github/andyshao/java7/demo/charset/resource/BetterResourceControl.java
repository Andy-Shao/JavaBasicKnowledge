package com.github.andyshao.java7.demo.charset.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * 
 * Descript:if the format is "java.properties", it is attribute file. If it is
 * attribute file, Should get {@link InputStream} class and create a
 * {@link Reader}. finally build the {@link PropertyResourceBundle}.<br>
 * Copyright: Copyright(c) Aug 6, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class BetterResourceControl extends ResourceBundle.Control {

    private String encoding = "UTF-8";

    public BetterResourceControl(String encoding) {
        if (encoding != null) this.encoding = encoding;
    }

    @Override
    public ResourceBundle newBundle(
        String baseName , Locale locale , String format , ClassLoader loader , boolean reload)
        throws IllegalAccessException , InstantiationException , IOException {
        if ("java.properties".equals(format)) {
            String bundleName = this.toBundleName(baseName , locale);
            String resrourceName = this.toResourceName(bundleName , "properties");
            InputStream stream = null;
            if (reload) {
                URL url = loader.getResource(resrourceName);
                if (url != null) {
                    URLConnection connection = url.openConnection();
                    if (connection != null) {
                        connection.setUseCaches(false);
                        stream = connection.getInputStream();
                    }
                }
            } else stream = loader.getResourceAsStream(resrourceName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream , this.encoding));
            return new PropertyResourceBundle(reader);
        }
        return super.newBundle(baseName , locale , format , loader , reload);
    }
}
