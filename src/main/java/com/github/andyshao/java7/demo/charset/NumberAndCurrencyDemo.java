package com.github.andyshao.java7.demo.charset;

import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

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
public class NumberAndCurrencyDemo {

	@Test
	public void formatAndParaseNumber() throws ParseException{
		NumberFormat format = NumberFormat.getNumberInstance();
		double num = 100.5;
		format.setMinimumFractionDigits(3);
		format.setMinimumIntegerDigits(5);
		assertThat(format.format(num), is("00,100.500"));
		String numStr = "523.34";
		format.setParseIntegerOnly(true);
		assertThat(format.parse(numStr), org.hamcrest.Matchers.<Number>is(523L));
	}
	
	@Test
	public void useDecimalFormat(){
		NumberFormat format = NumberFormat.getNumberInstance();
		DecimalFormat df = null;
		if(format instanceof DecimalFormat) df = (DecimalFormat) format;
		else df = new DecimalFormat();
		
		df.applyPattern("000.###");
		assertThat(df.format(3.14), is("003.14"));
	}
	
	@Test
	public void useChoiceFormat(){
		ChoiceFormat format = new ChoiceFormat(new double[]{0, 1, ChoiceFormat.nextDouble(1)}, new String[]{"no people", "person", "people"});
		int count = 2;
		String msg = count + " " + format.format(count);
		assertThat(msg, is("2 people"));
	}
}
