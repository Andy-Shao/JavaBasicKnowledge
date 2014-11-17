package com.github.andyshao.java7.demo.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;

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
public class InvokeMethodHandle {
	public String testMethod(int arg1, int arg2){
		return Arrays.toString(new int[]{arg1, arg2});
	}
	
	@Test
	public void invoker() throws Throwable{
		MethodType typeInvoker = MethodType.methodType(String.class, Object.class, int.class, int.class);
		MethodHandle invoker = MethodHandles.invoker(typeInvoker);
		MethodType typeFind = MethodType.methodType(String.class, int.class, int.class);
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodHandle mh1 = lookup.findVirtual(String.class, "substring", typeFind);
		MethodHandle mh2 = lookup.findVirtual(InvokeMethodHandle.class, "testMethod", typeFind);
		
		String result = (String) invoker.invoke(mh1, "Hello", 2, 3);
		assertThat(result, is("l"));
		result = (String) invoker.invoke(mh2, this, 2, 3);
		assertThat(result, is("[2, 3]"));
	}
	
	@Test
	public void invokerTransform() throws Throwable{
		MethodType typeInvoker = MethodType.methodType(String.class, String.class, int.class, int.class);
		/*
		 * For exactInvoker(). 
		 * if the method handle changes, the others method handle also change when the new method
		 * handle call these method.*/  
		MethodHandle invoker = MethodHandles.exactInvoker(typeInvoker);
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodHandle mhUpperCase = lookup.findVirtual(String.class, "toUpperCase", MethodType.methodType(String.class));
		invoker = MethodHandles.filterReturnValue(invoker, mhUpperCase);
		MethodType typeFind = MethodType.methodType(String.class, int.class, int.class);
		MethodHandle mh1 = lookup.findVirtual(String.class, "substring", typeFind);
		
		assertThat((String)invoker.invoke(mh1, "Hello", 1, 4), is("ELL"));
	}
}
