package oracle.official.demo.datatime;

import java.time.Month;
import java.time.YearMonth;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class YearMonthDemo {

	@Test
	public void test(){
		YearMonth date = YearMonth.now();
		
		date = YearMonth.of(2010, Month.FEBRUARY);
		Assert.assertThat(date.toString(), Matchers.is("2010-02"));
		Assert.assertThat(date.lengthOfMonth(), Matchers.is(28));
		
		date = YearMonth.of(2012, Month.FEBRUARY);
		Assert.assertThat(date.toString(), Matchers.is("2012-02"));
		Assert.assertThat(date.lengthOfMonth(), Matchers.is(29));
	}
}
