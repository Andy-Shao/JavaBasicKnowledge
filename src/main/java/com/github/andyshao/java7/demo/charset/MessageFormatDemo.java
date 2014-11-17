package com.github.andyshao.java7.demo.charset;

import java.text.MessageFormat;

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
public class MessageFormatDemo {

	@Test
	public void formatWithNumber(){
		String pattern = "has bought {0,number,integer} goods, {1,number,currency} per unit, total:{2,number,\u00A4#,###.##}";
		MessageFormat format = new MessageFormat(pattern);
		int count = 3;
		double price = 1599.3;
		double total = price * count;
		String msg = format.format(new Object[]{count, price, total});
		assertThat(msg, is("has bought 3 goods, $1,599.30 per unit, total:$4,797.9"));
	}
}
