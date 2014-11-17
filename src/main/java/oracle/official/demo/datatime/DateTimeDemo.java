package oracle.official.demo.datatime;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class DateTimeDemo {

	@SuppressWarnings("unused")
	@Test
	public void localDate(){
		{
			LocalDate payday = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).minusDays(2);
		}
		{
			LocalDate dateOfBirth = LocalDate.of(1990, Month.FEBRUARY, 11);
			LocalDate firstBirthday = dateOfBirth.plusYears(1);
			Assert.assertThat(firstBirthday.toString(), Matchers.is("1991-02-11"));
		}
	}
}
