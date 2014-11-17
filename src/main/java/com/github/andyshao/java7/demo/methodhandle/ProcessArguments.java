package com.github.andyshao.java7.demo.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Jul 30, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class ProcessArguments {

    @Test
	public void dropArguments() throws Throwable{
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodType type = MethodType.methodType(String.class, int.class, int.class);
		MethodHandle mh0ld = lookup.findVirtual(String.class, "substring", type);
		String value = (String) mh0ld.invoke("Hello", 2, 3);
		assertThat(value, is("l"));
		//dropArgument could add some doesn't useful argument.
		MethodHandle mhNew = MethodHandles.dropArguments(mh0ld, 0, float.class, String.class);
		value = (String) mhNew.invoke(0.5f, "Ignore", "Hello", 2, 3);
		assertThat(value, is("l"));
	}
    
    @Test
    public void insertArguments() throws Throwable{
    	MethodHandles.Lookup lookup = MethodHandles.lookup();
    	MethodType type = MethodType.methodType(String.class, String.class);
    	MethodHandle mh0ld = lookup.findVirtual(String.class, "concat", type);
    	String value = (String) mh0ld.invoke("Hello" , "World");
    	MethodHandle mhNew = MethodHandles.insertArguments(mh0ld, 1, " --");
    	value = (String) mhNew.invoke("Hello");
    	assertThat(value, is("Hello --"));
    }
    
    @Test
    public void filterArguments() throws Throwable{
    	MethodHandles.Lookup lookup = MethodHandles.lookup();
    	MethodType type = MethodType.methodType(int.class, int.class, int.class);
    	MethodHandle mhGetLength = lookup.findVirtual(String.class, "length", MethodType.methodType(int.class));
    	MethodHandle mhTarget = lookup.findStatic(Math.class, "max", type);
    	MethodHandle mhNew = MethodHandles.filterArguments(mhTarget, 0, mhGetLength, mhGetLength);
    	
    	assertThat((Integer)mhNew.invoke("Hello", "New World"), is(9));
    }
    
    public static int targetMethod(int arg1, int arg2, int arg3){
    	return arg1;
    }
    
    @Test
    public void foldArguments() throws Throwable{
    	MethodHandles.Lookup lookup = MethodHandles.lookup();
    	MethodType typeCombiner = MethodType.methodType(int.class, int.class, int.class);
    	MethodHandle mhCombiner = lookup.findStatic(Math.class, "max", typeCombiner);
    	MethodType typeTarget = MethodType.methodType(int.class, int.class, int.class, int.class);
    	MethodHandle mhTarget = lookup.findStatic(ProcessArguments.class, "targetMethod", typeTarget);
    	MethodHandle mhResult = MethodHandles.foldArguments(mhTarget, mhCombiner);
    	
    	assertThat((Integer)mhResult.invoke(3, 4), is(4));
    }
    
    @Test
    public void permuteArguments() throws Throwable{
    	MethodHandles.Lookup lookup = MethodHandles.lookup();
    	MethodType type = MethodType.methodType(int.class, int.class, int.class);
    	MethodHandle mhCompare = lookup.findStatic(Integer.class, "compare", type);
    	assertThat((int)mhCompare.invoke(3, 4), is(-1));
    	
    	MethodHandle mhNew = MethodHandles.permuteArguments(mhCompare, type, 1, 0);
    	assertThat((int)mhNew.invoke(3, 4), is(1));
    	
    	MethodHandle mhDuplicateArgs = MethodHandles.permuteArguments(mhCompare, type, 1, 1);
    	assertThat((int)mhDuplicateArgs.invoke(3, 4), is(0));
    }
}
