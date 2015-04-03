package com.github.andyshao.java7.demo.invokedynamic;

import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * Descript:Only could set method handle one time. Can't change it.<br>
 * Copyright: Copyright(c) Jul 30, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class ConstantCallSiteDemo {

    @Test
    public void useConstantCallSite() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(String.class , int.class , int.class);
        MethodHandle mh = lookup.findVirtual(String.class , "substring" , type);
        ConstantCallSite callSite = new ConstantCallSite(mh);
        MethodHandle invoker = callSite.dynamicInvoker();

        Assert.assertThat((String) invoker.invoke("Hello" , 2 , 3) , Matchers.is("l"));
    }
}
