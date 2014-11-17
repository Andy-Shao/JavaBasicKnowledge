package oracle.official.demo.datatime;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class TemporalOperation {

	@Test
	public void temporal_package(){
		//LocalDate does not support ChronoField.CLOCK_HOUR_OF_DAY
		Assert.assertFalse(LocalDate.now().isSupported(ChronoField.CLOCK_HOUR_OF_DAY));
		//LocalTime support ChronoField.CLOCK_HOUR_OF_DAY
		Assert.assertTrue(LocalTime.now().isSupported(ChronoField.CLOCK_HOUR_OF_DAY));
		
		{
			LocalTime time = LocalTime.of(12, 25, 36, 999_999_999);
			Assert.assertThat(time.get(ChronoField.MILLI_OF_SECOND), Matchers.is(999));
			Assert.assertThat(LocalTime.now().get(ChronoField.MILLI_OF_SECOND), Matchers.greaterThanOrEqualTo(0));
			Assert.assertThat(LocalTime.now().get(ChronoField.MILLI_OF_SECOND), Matchers.lessThanOrEqualTo(1000));
			
			/*the quarter_of_year talk about the season of the year.
			 * From January to March is 1
			 * From April to June is 2
			 * From July to September is 3
			 * From October to December is 4 */
			LocalDate date = LocalDate.of(2014, 4, 1);
			Assert.assertThat(date.get(IsoFields.QUARTER_OF_YEAR), Matchers.is(2));
			Assert.assertThat(date.get(IsoFields.QUARTER_OF_YEAR), Matchers.greaterThanOrEqualTo(1));
			Assert.assertThat(date.get(IsoFields.QUARTER_OF_YEAR), Matchers.lessThanOrEqualTo(4));
		}
		Assert.assertTrue(Instant.now().isSupported(ChronoUnit.DAYS));
	}
	
	@Test
	public void temporalAdjuster(){
		{
			LocalDate date = LocalDate.of(2000, Month.OCTOBER, 15);
			Assert.assertThat(date.getDayOfWeek(), Matchers.is(DayOfWeek.SUNDAY));
			Assert.assertThat(date.with(TemporalAdjusters.firstDayOfMonth()), Matchers.is(LocalDate.of(2000, Month.OCTOBER, 1)));
			Assert.assertThat(date.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)), Matchers.is(LocalDate.of(2000, Month.OCTOBER, 2)));
			Assert.assertThat(date.with(TemporalAdjusters.lastDayOfMonth()), Matchers.is(LocalDate.of(2000, Month.OCTOBER, 31)));
			Assert.assertThat(date.with(TemporalAdjusters.firstDayOfNextMonth()), Matchers.is(LocalDate.of(2000, Month.NOVEMBER, 1)));
			Assert.assertThat(date.with(TemporalAdjusters.firstDayOfNextYear()), Matchers.is(LocalDate.of(2001, Month.JANUARY, 1)));
			Assert.assertThat(date.with(TemporalAdjusters.firstDayOfYear()), Matchers.is(LocalDate.of(2000, Month.JANUARY, 1)));
		}
		
		{
			LocalDate date = LocalDate.of(2014, Month.AUGUST, 23);
			Assert.assertThat(date.with(new PaydayAdjuster()), Matchers.is(LocalDate.of(2014, Month.AUGUST, 29)));
		}
	}
	
	@Test
	public void temporalQuery(){
		{
			TemporalQuery<TemporalUnit> query = TemporalQueries.precision();
			Assert.assertThat(LocalDate.now().query(query), Matchers.is(ChronoUnit.DAYS));
			Assert.assertThat(LocalDateTime.now().query(query), Matchers.is(ChronoUnit.NANOS));
			Assert.assertThat(Year.now().query(query), Matchers.is(ChronoUnit.YEARS));
			Assert.assertThat(YearMonth.now().query(query), Matchers.is(ChronoUnit.MONTHS));
			Assert.assertThat(Instant.now().query(query), Matchers.is(ChronoUnit.NANOS));
		}
		
		{
			LocalDate date = LocalDate.of(2014, Month.AUGUST, 23);
			// Invoking the query without using a lambda expression.
			Boolean isFamilyVacation = date.query(new FamilyVacations());
			// Invoking the query using a lambda expression.
			Boolean isFamilyBirthday = date.query(FamilyBirthdays::isFamilyBirthday);
			
			Assert.assertFalse(isFamilyBirthday);
			Assert.assertFalse(isFamilyVacation);
		}
	}
}
