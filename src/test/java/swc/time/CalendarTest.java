package swc.time;

import java.util.*;
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
public class CalendarTest{

    @Test
    public void toInstant(){
        Assert.assertTrue(Calendar.getInstance().toInstant() instanceof Instant);
    }
}
