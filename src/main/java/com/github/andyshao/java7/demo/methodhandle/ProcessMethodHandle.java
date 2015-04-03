package com.github.andyshao.java7.demo.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Jul 30, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class ProcessMethodHandle {

    public static boolean guardTest() {
        return Math.random() > 0.5;
    }

    @Test
    public void filterReturnValue() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mhSubstring =
            lookup.findVirtual(String.class , "substring" , MethodType.methodType(String.class , int.class));
        MethodHandle mhUpperCase =
            lookup.findVirtual(String.class , "toUpperCase" , MethodType.methodType(String.class));
        MethodHandle mh = MethodHandles.filterReturnValue(mhSubstring , mhUpperCase);

        Assert.assertThat((String) mh.invoke("Hello World" , 5) , Matchers.is(" WORLD"));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void guardWithTest() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mhTest =
            lookup.findStatic(ProcessMethodHandle.class , "guardTest" , MethodType.methodType(boolean.class));
        MethodType type = MethodType.methodType(int.class , int.class , int.class);
        MethodHandle mhTarget = lookup.findStatic(Math.class , "max" , type);
        MethodHandle mhFallback = lookup.findStatic(Math.class , "min" , type);
        MethodHandle mh = MethodHandles.guardWithTest(mhTest , mhTarget , mhFallback);

        Assert.assertThat((int) mh.invoke(3 , 5) , Matchers.anyOf(Matchers.is(3) , Matchers.is(5)));
    }
}
