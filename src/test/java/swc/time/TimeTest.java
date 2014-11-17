package swc.time;

import java.time.*;

import org.junit.*;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 14, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class TimeTest{

	/**
	 * <ul>
	 * <li> LocalDateTime, LocalDate, LocalTime</li>
	 * <li> Year, Month, YearMonth, MonthDay, DayOfWeek</li>
	 * </ul>
	 */
	@SuppressWarnings("unused")
    @Test
	public void extremelyUseful(){
		{
			//LocalDateTime
			LocalDateTime ldt = LocalDateTime.now();
			// of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond);
			ldt = LocalDateTime.of(2014, 2, 2, 11, 15, 59, 98);

			int year = ldt.getYear();

			Month month = ldt.getMonth();
			int monthValue = ldt.getMonthValue();

			int dayOfMonth = ldt.getDayOfMonth();
			DayOfWeek dayOfWeek = ldt.getDayOfWeek();
			int dayOfYear = ldt.getDayOfYear();

			int hour = ldt.getHour();

			int minute = ldt.getMinute();

			int second = ldt.getSecond();
		}

		{
			//LocalDate
			LocalDate ld = LocalDate.now();
			//of(int year, int month, int dayOfMonth);
			ld = LocalDate.of(2014, 2, 2);
			//atTime(int hour, int minute, int second);
			LocalDateTime ldt = ld.atTime(11, 18, 45);

			int year = ld.getYear();

			Month month = ld.getMonth();
			int monthValue = ld.getMonthValue();

			int dayOfMonth = ld.getDayOfMonth();
			DayOfWeek dayOfWeek = ld.getDayOfWeek();
			int dayOfYear = ld.getDayOfYear();
		}

		{
			//LocalTime
			LocalTime lt = LocalTime.now();
			//of(int hour, int minute, int second, int nanoOfSecond);
			lt = LocalTime.of(11, 29, 47, 87);
			LocalDateTime ldt = lt.atDate(LocalDate.now());

			int hour = lt.getHour();

			int minute = lt.getMinute();

			int second = lt.getSecond();
		}

		{
			//YearMonth
			YearMonth yearMonth = YearMonth.now();
			yearMonth = YearMonth.of(2014, 2);

			int year = yearMonth.getYear();
			Month month = yearMonth.getMonth();
			int monthValue = yearMonth.getMonthValue();
		}

		{
			//MonthDay
			MonthDay monthDay = MonthDay.now();
			monthDay = MonthDay.of(2, 13);

			int dayOfMonth = monthDay.getDayOfMonth();
			Month month = monthDay.getMonth();
			int monthValue = monthDay.getMonthValue();
		}

		{
			//DayOfWeek
			DayOfWeek dayOfWeek = DayOfWeek.of(3);

			int value = dayOfWeek.getValue();
		}
	}

	/**
	 * Other useful types:<br>
	 * <ul>
	 * <li> Instant - similar to java.util.Date</li>
	 * <li> ZonedDateTime, Zoneld - for when time zones are important</li>
	 * <li> OffsetDateTime, OffsetTime, ZoneOffset - for dealing with offsets from UTC</li>
	 * <li> Duration, Period - although if you're trying to find the amount of time 
	 * between two dates, you might be looking for ChronoUnit instead(see below).</li>
	 * </ul>
	 */
	@SuppressWarnings("unused")
    @Test
	public void lessUseful(){
		{
			//Instant
			Instant instant = Instant.now();
		}
	}

	/**
	 * Other useful types:<br>
	 * <ul>
	 * <li> DateTimeFormatter - for converting datetime objects to strings.</li>
	 * <li> ChronoUnit - for figuring out the amount of time bewteen two points, e.g.
	 * ChronoUnit.DAYS.between(t1, t2);</li>
	 * <li> TemporalAdjustor - e.g.
	 * date.with(TemporalAdjuster.firstDayOfMonth())</li>
	 * </ul>
	 */
	@Test
	public void otherUseful(){}
}
