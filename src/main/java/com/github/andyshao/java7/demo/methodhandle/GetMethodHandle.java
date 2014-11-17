package com.github.andyshao.java7.demo.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Jul 30, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class GetMethodHandle {

	@Test
	public void lookupMethod() throws NoSuchMethodException, IllegalAccessException{
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		
		//constructor
		lookup.findConstructor(String.class, MethodType.methodType(void.class, byte[].class));
		
		//String.substring
		lookup.findVirtual(String.class, "substring", MethodType.methodType(String.class, int.class, int.class));
		
		//String.format
		lookup.findStatic(String.class, "format", MethodType.methodType(String.class, String.class, Object[].class));
	}
	
	@Test
	public void lookupSpecial() throws NoSuchMethodException, IllegalAccessException{
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		lookup.findSpecial(GetMethodHandle.class, "privateMethod", MethodType.methodType(void.class), GetMethodHandle.class);
	}
	
	@SuppressWarnings("unused")
    private void privateMethod(){}
	
	@Test
	public void lookupFieldAccessor() throws NoSuchFieldException, IllegalAccessException{
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		lookup.findGetter(Sample.class, "name", String.class);
		lookup.findSetter(Sample.class, "name", String.class);
		lookup.findStaticGetter(Sample.class, "value", String.class);
		lookup.findStaticSetter(Sample.class, "value", String.class);
	}
	
	public static class Sample{
		//The member must be not private.
		public String name;
		public static String value;
		
		public String getName() {
        	return name;
        }
		public void setName(String name) {
        	this.name = name;
        }
		public static String getValue() {
        	return value;
        }
		public static void setValue(String value) {
        	Sample.value = value;
        }
	}
	
    @Test
	public void unreflect() throws NoSuchMethodException, SecurityException, IllegalAccessException, NoSuchFieldException{
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		Constructor<String> constructor = String.class.getConstructor(byte[].class);
		lookup.unreflectConstructor(constructor);
		Method method = String.class.getMethod("substring", int.class, int.class);
		lookup.unreflect(method);
		
		Method privateMethod = GetMethodHandle.class.getDeclaredMethod("privateMethod");
		lookup.unreflectSpecial(privateMethod, GetMethodHandle.class);
		
		Field field = Sample.class.getField("name");
		lookup.unreflectGetter(field);
		lookup.unreflectSetter(field);
	}
    
    @Test
    public void arrayHandles() throws Throwable{
    	int[] array = new int[]{1, 2, 3, 4, 5};
    	MethodHandle setter = MethodHandles.arrayElementSetter(int[].class);
    	setter.invoke(array, 3, 6);
    	MethodHandle getter = MethodHandles.arrayElementGetter(int[].class);
    	Assert.assertThat((Integer)getter.invoke(array, 3), Matchers.is(6));
    }
    
    @Test
    public void identity() throws Throwable{
    	//The method handle return what you input.
    	MethodHandle mh = MethodHandles.identity(String.class);
    	Assert.assertThat((String)mh.invoke("Hello"), Matchers.is("Hello"));
    }
    
    @Test
    public void constant() throws Throwable{
    	//Always return what are you set.
    	MethodHandle mh = MethodHandles.constant(String.class, "Hello");
    	Assert.assertThat((String) mh.invoke(), Matchers.is("Hello"));
    }
}
