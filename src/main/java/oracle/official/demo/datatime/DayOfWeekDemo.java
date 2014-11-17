package oracle.official.demo.datatime;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class DayOfWeekDemo {

	@Test
	public void test(){
//		System.out.printf("%s%n", DayOfWeek.MONDAY.plus(3));
		Assert.assertThat(DayOfWeek.MONDAY.plus(3), Matchers.is(DayOfWeek.THURSDAY));
	}
	
	@Test
	public void format(){
		DayOfWeek dow = DayOfWeek.MONDAY;
		Locale locale = Locale.US;
		Assert.assertThat(dow.getDisplayName(TextStyle.FULL, locale), Matchers.is("Monday"));
		Assert.assertThat(dow.getDisplayName(TextStyle.NARROW, locale), Matchers.is("M"));
		Assert.assertThat(dow.getDisplayName(TextStyle.SHORT, locale), Matchers.is("Mon"));
	}
}
