package com.github.andyshao.java7.demo.invokedynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Jul 31, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class MutableCallSiteDemo {

	@Test
	public void useMutableCallSite() throws Throwable{
		MethodType type = MethodType.methodType(int.class, int.class, int.class);
		MutableCallSite callSite = new MutableCallSite(type);
		MethodHandle invoker = callSite.dynamicInvoker();
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodHandle mhMax = lookup.findStatic(Math.class, "max", type);
		MethodHandle mhMin = lookup.findStatic(Math.class, "min", type);
		
		callSite.setTarget(mhMax);
		assertThat((int)invoker.invoke(3, 5), is(5));
		
		callSite.setTarget(mhMin);
		assertThat((int)invoker.invoke(3, 5), is(3));
	}
}
