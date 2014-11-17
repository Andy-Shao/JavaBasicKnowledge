package com.github.andyshao.java7.demo.methodfuction;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Jul 30, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class ArrayOperation {

	@SuppressWarnings("unused")
	private static final MethodType TYPE_CALLBACK = MethodType.methodType(Object.class, Object.class, int.class);
	
	public static void forEach(Object[] array, MethodHandle handle) throws Throwable{
		for (int i = 0; i < array.length; i++) {
			handle.invoke(array[i], i);
		}
	}
	
	public static Object[] map(Object[] array, MethodHandle handle) throws Throwable{
		Object[] result = new Object[array.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = handle.invoke(array[i], i);
		}
		
		return result;
	}
	
	public static Object reduce(Object[] array, Object initalValue, MethodHandle handle) throws Throwable{
		Object result = initalValue;
		for (int i = 0; i < array.length; i++) {
			result = handle.invoke(result, array[i]);
		}
		
		return result;
	}
}
