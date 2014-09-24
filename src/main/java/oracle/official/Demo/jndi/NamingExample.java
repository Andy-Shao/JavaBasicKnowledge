package oracle.official.Demo.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * Descript: The Example of Naming<br>
 * Importing the JNDI Classes:
 * <ul>
 * <li>{@link javax.naming.Context}</li>
 * <li>{@link javax.naming.InitialContext}</li>
 * <li>{@link javax.naming.NamingException}</li>
 * </ul>
 * Copyright: Copyright(c) Sep 24, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class NamingExample {
	
	/**
	 * I.
	 * In the main() method of the program, create an initial context. Indicate that you're using the file system service provider by setting the environment 
	 * properties parameter (represented by a Hashtable class) to the InitialContext(in the API reference documentation) constructor, as follows.
	 * @throws NamingException General JNDI Exception.
	 */
	@Ignore
	@Test
	public void creatingInitialContext() throws NamingException{
		Hashtable<Object, Object> env = new Hashtable<Object, Object>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
		
		@SuppressWarnings("unused")
		Context ctx = new InitialContext(env);
	}
	
	/**
	 * II.
	 * Next, use Context.lookup()(in the API reference documentation) to look up an object. The following code looks up the object bound to the name supplied in
	 * the command line.
	 * @throws NamingException General JNDI Exception.
	 */
	@Ignore
	@Test
	public  void lookingUpObject() throws NamingException{
		Context ctx = null;
		String name = null;
		
		@SuppressWarnings({ "null", "unused" })
		Object obj = ctx.lookup(name);
	}
}
