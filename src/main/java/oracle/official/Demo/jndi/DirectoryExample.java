package oracle.official.Demo.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * Descript:The example of Directory<br>
 * Importing the JNDI Directory Classes:
 * <ul>
 * <li>{@link javax.naming.Context}</li>
 * <li>{@link javax.naming.directory.InitialDirContext}</li>
 * <li>{@link javax.naming.directory.DirContext}</li>
 * <li>{@link javax.naming.directory.Attributes}</li>
 * <li>{@link javax.naming.NamingException}</li>
 * </ul>
 * Copyright: Copyright(c) Sep 24, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class DirectoryExample {

	/**
	 * I.
	 * In the main() method of the program, create an initial directory context. This is similar to creating an initial context in the previous naming example, 
	 * except that you use the constructor for InitialDirContext(in the API reference documentation).<br>
	 *  you indicate that you're using the LDAP service provider by setting the Hashtable parameter to the InitialDirContext constructor(in the API reference 
	 *  documentation) appropriately.  For now, the only thing to understand is that the program by default identifies an LDAP server on the local machine.
	 * @throws NamingException General JNDI Exception.
	 */
	@Ignore
	@Test
	public void creatingInitialDirectoryContext() throws NamingException{
		Hashtable<Object, Object> env = new Hashtable<Object, Object>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:389/o=JNDITutorial");
		
		@SuppressWarnings("unused")
		DirContext ctx = new InitialDirContext(env);
	}
	
	/**
	 * II.
	 * Next, use getAttributes()(in the API reference documentation) to get an object's attributes. The following code retrieves all of the attributes 
	 * associated with the object bound to the name "cn=Ted Geisel, ou=People":
	 * @throws NamingException General JNDI Exception.
	 */
	@Ignore
	@Test
	public void gettingDirectoryObjectAttributes() throws NamingException{
		DirContext ctx = null;
		
		@SuppressWarnings({ "null", "unused" })
		Attributes attributes = ctx.getAttributes("cn = Ted Geisel, ou=People");
	}
	
	/**
	 * III.
	 * From a set of attributes, Attributes(in the API reference documentation), you can ask for a particular attribute by using Attributes.get()(in the API 
	 * reference documentation) and then from that attribute get its value. 
	 * @throws NamingException General JNDI Exception.
	 */
	@SuppressWarnings("null")
	@Ignore
	@Test
	public void extractingDesiredAttribute() throws NamingException{
		Attributes attributes = null;
		
		attributes.get("sn").get();
	}
}
