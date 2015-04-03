package com.github.andyshao.java7.demo.charset;

import java.text.Collator;
import java.util.Locale;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Aug 6, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class ComparatTxt {

    @Test
    public void useCollator() {
        Collator collator = Collator.getInstance(Locale.US);
        collator.setStrength(Collator.PRIMARY);
        Assert.assertThat(collator.compare("abc" , "ABC") , Matchers.is(0));
        collator.setStrength(Collator.IDENTICAL);
        Assert.assertThat(collator.compare("abc" , "ABC") , Matchers.is(-1));
    }
}
