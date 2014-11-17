package oracle.official.demo.datatime;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TimeZoneAndOffsetDemo {

	public static void zoneld_zoneOffset(){
		Set<String> allZones = ZoneId.getAvailableZoneIds();
		LocalDateTime dt = LocalDateTime.now();
		
		List<String> zoneList = new ArrayList<>(allZones);
		Collections.sort(zoneList);
		
		for(String s : zoneList){
			ZoneId zone = ZoneId.of(s);
			ZonedDateTime zdt = dt.atZone(zone);
			ZoneOffset offset = zdt.getOffset();
			int secondsOfHour = offset.getTotalSeconds() % (60 * 60);
			String out = String.format("%35s %10s%n", zone, offset);
			
			if(secondsOfHour != 0) System.out.println(out);
		}
	}
	
	/**
	 * The following code, from the Flight example, defines the departure time 
	 * for a flight from San Francisco to Tokyo as a ZonedDateTime in the 
	 * America/Los Angeles time zone. The withZoneSameInstant and plusMinutes 
	 * methods are used to create an instance of ZonedDateTime that represents 
	 * the projected arrival time in Tokyo, after the 650 minute flight. The 
	 * ZoneRules.isDaylightSavings method determines whether it is daylight 
	 * saving time when the flight arrives in Tokyo.
	 */
	public static void zonedDateTime(){
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
		
		//Leaving from San Francisco on July 20, 2013, at 7:30 p.m.
		LocalDateTime leaving = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
		ZoneId leavingZone = ZoneId.of("America/Los_Angeles");
		ZonedDateTime departure = ZonedDateTime.of(leaving, leavingZone);
		
		try {
			String out1 = departure.format(format);
			System.out.printf("LEAVING: %s (%s)%n", out1, leavingZone);
		} catch (DateTimeException e) {
			System.out.printf("%s can't be formatted!%n", departure);
			throw e;
		}
		
		//Flight is 10 hours and 50 minutes, or 650 minutes
		ZoneId arrivingZone = ZoneId.of("Asia/Tokyo");
		ZonedDateTime arrival = departure.withZoneSameInstant(arrivingZone).plusMinutes(650);
		
		try {
			String out2 = arrival.format(format);
			System.out.printf("ARRIVING: %s (%s)%n", out2, arrivingZone);
		} catch (DateTimeException e) {
			System.out.printf("%s can't be formatted!%n", arrival);
			throw e;
		}
		
		if(arrivingZone.getRules().isDaylightSavings(arrival.toInstant()))
			System.out.printf("		(%s daylight saving time will be in effect.)%n", arrivingZone);
		else System.out.printf("	(%s standard time will be in effect.)%n", arrivingZone);
	}
	
	/**
	 * The OffsetDateTime class, in effect, combines the LocalDateTime class with the ZoneOffset
	 * class. It is used to represent a full date (year, month, day) and 
	 * time (hour, minute, second, nanosecond) with an offset from Greenwich/UTC 
	 * time (+/-hours:minutes, such as +06:00 or -08:00).The following example uses OffsetDateTime 
	 * with the TemporalAdjuster.lastDay method to find the last Thursday in July 2013.
	 */
	public static void offsetDateTime(){
		//Find the last Thursday in July 2013.
		LocalDateTime date = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
		ZoneOffset offset = ZoneOffset.of("-08:00");
		OffsetDateTime offDate = OffsetDateTime.of(date, offset);
		OffsetDateTime lastThursday = offDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY));
		System.out.printf("The last Thursday in July 2013 is the %sth.%n", lastThursday.getDayOfMonth());
	}
	
	public static void main(String[] args) {
		zoneld_zoneOffset();
		zonedDateTime();
		offsetDateTime();
	}
}
