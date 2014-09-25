package com.github.andyshao.jndi.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Lookup {

	public static void main(String[] args) throws IOException {
		String name = "jndi_resource";
		
		{
			Path path = Paths.get(name);
			name = path.toAbsolutePath().toString();
			Files.deleteIfExists(path);
			Files.createDirectories(path);
		}
		
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");

		try {
			// Create the initial context
			Context ctx = new InitialContext(env);

			// Look up an object
			Object obj = ctx.lookup(name);

			// Print it
			System.out.println(name + " is bound to: " + obj + "/t" + obj.getClass());

		} catch (NamingException e) {
			System.err.println("Problem looking up " + name + ": " + e);
		}
	}
}
