package oracle.official.demo.datatime;

import java.time.Clock;

import org.junit.Test;

public class ClockDemo {

	@Test
	public void test(){
		Clock clock = Clock.systemUTC();
		System.out.println(clock);
	}
}
