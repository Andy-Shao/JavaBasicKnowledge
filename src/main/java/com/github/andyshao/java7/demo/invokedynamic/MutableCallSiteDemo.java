package com.github.andyshao.java7.demo.invokedynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Jul 31, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class MutableCallSiteDemo {

    @Test
    public void useMutableCallSite() throws Throwable {
        MethodType type = MethodType.methodType(int.class , int.class , int.class);
        MutableCallSite callSite = new MutableCallSite(type);
        MethodHandle invoker = callSite.dynamicInvoker();
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mhMax = lookup.findStatic(Math.class , "max" , type);
        MethodHandle mhMin = lookup.findStatic(Math.class , "min" , type);

        callSite.setTarget(mhMax);
        Assert.assertThat((int) invoker.invoke(3 , 5) , Matchers.is(5));

        callSite.setTarget(mhMin);
        Assert.assertThat((int) invoker.invoke(3 , 5) , Matchers.is(3));
    }
}
