package interfaces.improvements;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 14, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public interface InterfaceDefaultMethod{

	public default void demoMethod(){
		System.out.println("This is a default of method of a interface.");	
	}
}
