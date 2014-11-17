package oracle.official.demo.datatime;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class MonthDemo {

	@Test
	public void test(){
		Assert.assertThat(Month.FEBRUARY.maxLength(), Matchers.is(29));
	}
	
	@Test
	public void format(){
		Month month = Month.AUGUST;
		Locale locale = Locale.US;
		Assert.assertThat(month.getDisplayName(TextStyle.FULL, locale), Matchers.is("August"));
		Assert.assertThat(month.getDisplayName(TextStyle.NARROW, locale), Matchers.is("A"));
		Assert.assertThat(month.getDisplayName(TextStyle.SHORT, locale), Matchers.is("Aug"));
	}
}
