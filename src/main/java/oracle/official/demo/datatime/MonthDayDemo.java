package oracle.official.demo.datatime;

import java.time.Month;
import java.time.MonthDay;

import junit.framework.Assert;

import org.junit.Test;

public class MonthDayDemo {

	/**
	 * The following example uses the {@link MonthDay#isValidYear(int)}
	 * method to determine if February 29 is valid for the year 2010.
	 * The call returen false, confirming that 2010 is not a leap year.
	 */
	@Test
	public void isValidYear(){
		MonthDay date = MonthDay.of(Month.FEBRUARY, 29);
		Assert.assertFalse(date.isValidYear(2010));
	}
}
