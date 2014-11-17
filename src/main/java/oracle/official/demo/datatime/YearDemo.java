package oracle.official.demo.datatime;

import java.time.Year;

import junit.framework.Assert;

import org.junit.Test;

public class YearDemo {

	/**
	 * if the given year is a leap year.
	 * The call return true, confirming that 2012
	 * is a leap year.
	 */
	@Test
	public void test(){
		Assert.assertTrue(Year.of(2012).isLeap());
	}
}
