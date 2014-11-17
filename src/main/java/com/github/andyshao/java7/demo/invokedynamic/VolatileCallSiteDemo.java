package com.github.andyshao.java7.demo.invokedynamic;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VolatileCallSite;

import org.junit.Test;

/**
 * 
 * Descript:Could use it in Mult-Thread<br>
 * Copyright: Copyright(c) Jul 31, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class VolatileCallSiteDemo {

	@Test
	public void useMutableCallSite() throws Throwable{
		MethodType type = MethodType.methodType(int.class, int.class, int.class);
		VolatileCallSite callSite = new VolatileCallSite(type);
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
