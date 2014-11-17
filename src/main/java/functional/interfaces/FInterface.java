package functional.interfaces;
import java.util.function.*;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 14, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
@FunctionalInterface
public interface FInterface{
	
	public abstract void run();
	
	public default void action(Consumer<FInterface> cons){
		cons.accept(this);
	}
}
