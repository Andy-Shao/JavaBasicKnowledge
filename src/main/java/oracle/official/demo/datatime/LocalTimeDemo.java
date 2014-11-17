package oracle.official.demo.datatime;

import java.time.LocalTime;
import java.util.Locale;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * Descript: similar to the other class whose names are prefixed with {@link Locale}, 
 * but deals in time only. This class is useful for representing human-based time of 
 * day, such as movie times, or the opening and closing times of the local library.<br>
 * Copyright: Copyright(c) Aug 18, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class LocalTimeDemo {

	/**
	 * The {@link LocalTime} class does not store time zone or daylight
	 * saving time information.
	 */
	@Test
	public void test(){
		LocalTime thisSec = LocalTime.of(4, 23, 56);
		Assert.assertThat(thisSec.getHour(), Matchers.is(4));
		Assert.assertThat(thisSec.getMinute(), Matchers.is(23));
		Assert.assertThat(thisSec.getSecond(), Matchers.is(56));
	}
}
