package oracle.official.demo.datatime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class ParsingFormatting {

	@Test
	public void parsing(){
		{
			String in = "19590709";
			LocalDate date = LocalDate.parse(in, DateTimeFormatter.BASIC_ISO_DATE);
			Assert.assertThat(date, Matchers.is(LocalDate.of(1959, 7, 9)));
		}
		
		{
			String input = "Jan 3 2003";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
			LocalDate date = LocalDate.parse(input, formatter);
			Assert.assertThat(date, Matchers.is(LocalDate.of(2003, 1, 3)));
		}
	}
	
	@Test
	public void formatting(){
		ZoneId leavingZone = ZoneId.of("America/Los_Angeles");
		ZonedDateTime departure = ZonedDateTime.of(LocalDateTime.of(2013, 7, 20, 19, 30), leavingZone);
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm a");
		String out = departure.format(format);
		Assert.assertThat(out, Matchers.is("Jul 20 2013  07:30 PM"));
	}
}
