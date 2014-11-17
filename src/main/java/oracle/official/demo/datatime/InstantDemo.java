package oracle.official.demo.datatime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class InstantDemo {

	private volatile Instant now;
	
	@Before
	public void before(){
		this.now = Instant.now();
	}

	@Test
	public void test(){
		now.plusSeconds(60 * 60);
	}
	
	@Test
	public void comparate(){
		Instant oneHoursLater = now.plusSeconds(60 * 60);
		
		Assert.assertTrue(oneHoursLater.isAfter(now));
		Assert.assertTrue(now.isBefore(oneHoursLater));
	}
	
	@Test
	public void until(){
		Instant before = Instant.ofEpochSecond(0L);
		long sencodesFromEpoch = before.until(now, ChronoUnit.SECONDS);
		long between = ChronoUnit.SECONDS.between(before, now);
		
		Assert.assertEquals(sencodesFromEpoch, between);
	}
	
	@Test
	public void toLocalDateTime(){
		LocalDateTime.ofInstant(this.now, ZoneId.systemDefault());
	}
}
