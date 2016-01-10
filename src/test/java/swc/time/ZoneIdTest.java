package swc.time;

import java.time.ZoneId;
import java.util.Map;

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
public class ZoneIdTest {

    @Test
    public void printSHORT_IDS() {
        for (Map.Entry<String , String> entry : ZoneId.SHORT_IDS.entrySet())
            System.out.println(entry.getKey() + "->" + entry.getValue());
    }
}
