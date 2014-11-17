package com.github.andyshao.java7.demo.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandleProxies;
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
public class MethodHandleProxy {

	public void doSomething(){
		System.out.println("WORK");
	}
	
	@Test
	public void useMethodHandleProxy() throws NoSuchMethodException, IllegalAccessException{
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodHandle mh = lookup.findVirtual(MethodHandleProxy.class, "doSomething", MethodType.methodType(void.class));
		mh = mh.bindTo(this);
		
		Runnable runnable = MethodHandleProxies.asInterfaceInstance(Runnable.class, mh);
		new Thread(runnable).start();;
	}
}
