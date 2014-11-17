package oracle.official.demo.datatime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class LocalDateDemo {

	@Test
	public void test(){
		{
			LocalDate date = LocalDate.of(2000, Month.NOVEMBER, 20);
			LocalDate nexWed = date.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
			Assert.assertThat(nexWed.toString(), Matchers.is("2000-11-22"));
		}
	}
}
