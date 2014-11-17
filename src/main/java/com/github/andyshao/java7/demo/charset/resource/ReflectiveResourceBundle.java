package com.github.andyshao.java7.demo.charset.resource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Aug 5, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class ReflectiveResourceBundle extends ResourceBundle{
	private Class<?> clazz;
	
	public ReflectiveResourceBundle(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	protected Object handleGetObject(String key) {
		if(key == null) throw new NullPointerException();
		
		try {
			Method method = clazz.getMethod(key);
			if(method == null) return null;
			return method.invoke(null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			return null;
		}
	}

	@Override
	public Enumeration<String> getKeys() {
		Vector<String> result = new Vector<>();
		Method[] methods = clazz.getMethods();
		for(Method method : methods){
			int mod = method.getModifiers();
			if(Modifier.isStatic(mod) && Modifier.isPublic(mod) && method.getParameterTypes().length==0)
				result.add(method.getName());
		}
		
		return result.elements();
	}

}
