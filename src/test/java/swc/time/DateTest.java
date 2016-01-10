package swc.time;

import java.time.Instant;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 14, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class DateTest {

    @Test
    public void from() {
        Assert.assertTrue(Date.from(new Date().toInstant()) instanceof Date);
    }

    @Test
    public void toInstant() {
        Assert.assertTrue(new Date().toInstant() instanceof Instant);
    }
}
