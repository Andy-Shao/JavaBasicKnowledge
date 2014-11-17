package oracle.official.demo.datatime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

import org.junit.Test;

/**
 * 
 * Descript:One of the core classes of the Date-Time API.
 * This class is used to represent date(month-day-year) together with time
 * (hour-minute-second-nanosecond) and is, in effect, a combination of
 * LocalDate with LocalTime.To include a time zone, you must use a ZonedDateTime
 * or an OffsetDateTime, as discussed in Time Zone and Offset Classes.<br>
 * Copyright: Copyright(c) Aug 18, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class LocalDateTimeDemo {

	@SuppressWarnings("unused")
	@Test
	public void test(){
		LocalDateTime localDateTime = LocalDateTime.now();
		localDateTime = LocalDateTime.of(1994, Month.APRIL, 15, 11, 30);
		localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
		localDateTime = LocalDateTime.now().plusMonths(6);
		localDateTime = LocalDateTime.now().minusMonths(6);
	}
}
