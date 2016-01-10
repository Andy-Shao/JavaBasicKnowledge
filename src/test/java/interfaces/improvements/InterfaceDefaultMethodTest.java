package interfaces.improvements;

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
public class InterfaceDefaultMethodTest {

    @Test
    public void testDefaultDemoMethod() {
        new InterfaceDefaultMethod() {
        }.demoMethod();
    }
}
