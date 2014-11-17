package oracle.official.demo.datatime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class PeriodAndDuration {

	@Test
	public void duration(){
		{
			//Nanos is nanosecond
			Instant t1 = Instant.now();
			Instant t2 = t1.plusNanos(15);
			long ns = Duration.between(t1, t2).toNanos();
			Assert.assertThat(ns, Matchers.is(15L));
			ns = Duration.between(t2, t1).toNanos();
			Assert.assertThat(ns, Matchers.is(-15L));
		}
		
		{
			Instant start = Instant.now();
			Duration gap = Duration.ofSeconds(10);
			Instant later = start.plus(gap);
			
			Assert.assertThat(Duration.between(start, later).getSeconds(), Matchers.is(10L));
		}
	}
	
	@Test
	public void chronoUnit(){
		Instant current = Instant.now();
		Instant previous = current.minusSeconds(20);
		
		long gap = ChronoUnit.MILLIS.between(previous, current);
		Assert.assertThat(gap, Matchers.is(20000L));
	}
	
	@Test
	public void period(){
		LocalDate today = LocalDate.of(2014, Month.AUGUST, 20);
		LocalDate birthday = LocalDate.of(1990, Month.FEBRUARY, 11);
		{
			
			Period p = Period.between(birthday, today);
			long p2 = ChronoUnit.DAYS.between(birthday, today);
			
			Assert.assertThat(p.getYears(), Matchers.is(24));
			Assert.assertThat(p.getMonths(), Matchers.is(6));
			Assert.assertThat(p.getDays(), Matchers.is(9));
			Assert.assertThat(p2, Matchers.is(8956L));
		}
		
		{
			LocalDate nextBDay = birthday.withYear(today.getYear());
			
			//If your birthday has occurred this year already, add 1 to the year.
			if(nextBDay.isBefore(today) || nextBDay.isEqual(today)) nextBDay = nextBDay.plusYears(1);
			
			Period p = Period.between(today, nextBDay);
			long p2 = ChronoUnit.DAYS.between(today, nextBDay);
			
			Assert.assertThat(p.getMonths(), Matchers.is(5));
			Assert.assertThat(p.getDays(), Matchers.is(22));
			Assert.assertThat(p2, Matchers.is(175L));
		}
	}
}
