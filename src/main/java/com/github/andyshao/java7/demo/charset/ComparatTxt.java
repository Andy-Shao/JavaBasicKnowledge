package com.github.andyshao.java7.demo.charset;

import java.text.Collator;
import java.util.Locale;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Aug 6, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class ComparatTxt {

	@Test
	public void useCollator(){
		Collator collator = Collator.getInstance(Locale.US);
		collator.setStrength(Collator.PRIMARY);
		assertThat(collator.compare("abc", "ABC"), is(0));
		collator.setStrength(Collator.IDENTICAL);
		assertThat(collator.compare("abc", "ABC"), is(-1));
	}
}
