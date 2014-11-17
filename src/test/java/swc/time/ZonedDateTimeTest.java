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
public class ZonedDateTimeTest{

	@SuppressWarnings("unused")
    @Test
	public void ofInstant(){
		ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.now(),ZoneId.systemDefault());
	}
}
