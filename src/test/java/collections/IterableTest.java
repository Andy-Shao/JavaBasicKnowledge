package collections;
import org.junit.*;
import java.util.*;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 14, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class IterableTest{
	@Test
	public void forEach(){
		Collection<String> collection = Arrays.asList("one", "two", "three");
		collection.forEach(t -> { System.out.println(t); });
	}

	@Test
	public void spliterator(){
		Collection<String> collection = Arrays.asList("one", "two", "three");
		collection.spliterator();
	}
}
