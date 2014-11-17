package com.github.andyshao.java7.demo.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Jul 30, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class ProcessMethodHandle {

	public static boolean guardTest(){
		return Math.random() > 0.5;
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void guardWithTest() throws Throwable{
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodHandle mhTest = lookup.findStatic(ProcessMethodHandle.class, "guardTest", MethodType.methodType(boolean.class));
		MethodType type = MethodType.methodType(int.class, int.class, int.class);
		MethodHandle mhTarget = lookup.findStatic(Math.class, "max", type);
		MethodHandle mhFallback = lookup.findStatic(Math.class, "min", type);
		MethodHandle mh = MethodHandles.guardWithTest(mhTest, mhTarget, mhFallback);
		
		assertThat((int)mh.invoke(3, 5), anyOf(is(3), is(5)));
	}
	
	@Test
	public void filterReturnValue() throws Throwable{
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodHandle mhSubstring = lookup.findVirtual(String.class, "substring", MethodType.methodType(String.class, int.class));
		MethodHandle mhUpperCase = lookup.findVirtual(String.class, "toUpperCase", MethodType.methodType(String.class));
		MethodHandle mh = MethodHandles.filterReturnValue(mhSubstring, mhUpperCase);
		
		assertThat((String)mh.invoke("Hello World", 5), is(" WORLD"));
	}
}
