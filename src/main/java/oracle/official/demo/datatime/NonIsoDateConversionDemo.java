package oracle.official.demo.datatime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseChronology;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoChronology;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.chrono.ThaiBuddhistDate;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class NonIsoDateConversionDemo {

	@SuppressWarnings("unused")
	@Test
	public void convertingNonIsoBaseDate(){
		{
			LocalDateTime date = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
			JapaneseDate jdate     = JapaneseDate.from(date);
			HijrahDate hdate       = HijrahDate.from(date);
			MinguoDate mdate       = MinguoDate.from(date);
			ThaiBuddhistDate tdate = ThaiBuddhistDate.from(date);
		}
		
		{
			LocalDate date = LocalDate.of(1996, Month.OCTOBER, 29);
			Assert.assertThat(StringConverter.toString(date, JapaneseChronology.INSTANCE), Matchers.is("10/29/0008 H"));
			Assert.assertThat(StringConverter.toString(date, MinguoChronology.INSTANCE), Matchers.is("10/29/0085 1"));
			Assert.assertThat(StringConverter.toString(date, ThaiBuddhistChronology.INSTANCE), Matchers.is("10/29/2539 B.E."));
			Assert.assertThat(StringConverter.toString(date, HijrahChronology.INSTANCE), Matchers.is("6/16/1417 1"));
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void convertingIsoBaseDate(){
		{
			LocalDate date = LocalDate.from(JapaneseDate.now());
			date = LocalDate.from(HijrahDate.now());
			date = LocalDate.from(MinguoDate.now());
			date = LocalDate.from(ThaiBuddhistDate.now());
		}
		
		{
			LocalDate date = LocalDate.of(1996, Month.OCTOBER, 29);
			Assert.assertThat(StringConverter.fromString("10/29/0008 H", JapaneseChronology.INSTANCE), Matchers.is(date));
			Assert.assertThat(StringConverter.fromString("10/29/0085 1", MinguoChronology.INSTANCE), Matchers.is(date));
			Assert.assertThat(StringConverter.fromString("10/29/2539 B.E.", ThaiBuddhistChronology.INSTANCE), Matchers.is(date));
			Assert.assertThat(StringConverter.fromString("6/16/1417 1", HijrahChronology.INSTANCE), Matchers.is(date));
		}
	}
}
