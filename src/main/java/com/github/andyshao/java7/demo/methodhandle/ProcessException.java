package com.github.andyshao.java7.demo.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import org.junit.Test;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Jul 30, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class ProcessException {

	public int handleException(Exception e, String str){
		System.out.println(e.getMessage());
		return 0;
	}
	
	@Test
	public void catchExceptions() throws Throwable{
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodType typeTarget = MethodType.methodType(int.class, String.class);
		MethodHandle mhParseInt = lookup.findStatic(Integer.class, "parseInt", typeTarget);
		MethodType typeHandler = MethodType.methodType(int.class, Exception.class, String.class);
		MethodHandle mhHandler = lookup.findVirtual(ProcessException.class, "handleException", typeHandler).bindTo(this);
		MethodHandle mh = MethodHandles.catchException(mhParseInt, NumberFormatException.class, mhHandler);
		mh.invoke("Hello");
	}
}
