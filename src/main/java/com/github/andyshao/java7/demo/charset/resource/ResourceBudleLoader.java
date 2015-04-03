package com.github.andyshao.java7.demo.charset.resource;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Aug 6, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class ResourceBudleLoader {

    public static ResourceBundle load(String baseName , Locale locale) {
        BetterResourceControl control = new BetterResourceControl(null);
        return ResourceBundle.getBundle(baseName , locale , control);
    }
}
