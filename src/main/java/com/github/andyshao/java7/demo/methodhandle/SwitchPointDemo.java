package com.github.andyshao.java7.demo.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.SwitchPoint;

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
public class SwitchPointDemo {

	@Test
	public void useSwitchPoint() throws Throwable{
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodType type = MethodType.methodType(int.class, int.class, int.class);
		MethodHandle mhMax = lookup.findStatic(Math.class, "max", type);
		MethodHandle mhMin = lookup.findStatic(Math.class, "min", type);
		
		SwitchPoint sp = new SwitchPoint();
		MethodHandle mhNew = sp.guardWithTest(mhMin, mhMax);
		assertThat((int)mhNew.invoke(3, 4), is(3));
		
		SwitchPoint.invalidateAll(new SwitchPoint[]{sp});//switch the point by this method.
		assertThat((int)mhNew.invoke(3, 4), is(4));
	}
}
