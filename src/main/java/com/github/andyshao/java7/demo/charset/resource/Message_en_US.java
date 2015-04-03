package com.github.andyshao.java7.demo.charset.resource;

import java.util.ListResourceBundle;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Aug 5, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class Message_en_US extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
            {
                "GREETING" , "Hello!"
            } , {
                "THANK_YOU" , "Thank you!"
            }
        };
    }

}
