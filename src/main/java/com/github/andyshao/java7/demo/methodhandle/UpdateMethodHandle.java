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
public class UpdateMethodHandle {

    @Test
    public void dropArguments() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(String.class , int.class , int.class);
        MethodHandle mh0ld = lookup.findVirtual(String.class , "substring" , type);
        String value = (String) mh0ld.invoke("Hello" , 2 , 3);
        Assert.assertThat(value , Matchers.is("l"));
        //dropArgument could add some doesn't useful argument.
        MethodHandle mhNew = MethodHandles.dropArguments(mh0ld , 0 , float.class , String.class);
        value = (String) mhNew.invoke(0.5f , "Ignore" , "Hello" , 2 , 3);
        Assert.assertThat(value , Matchers.is("l"));
    }

    @Test
    public void insertArguments() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(String.class , String.class);
        MethodHandle mh0ld = lookup.findVirtual(String.class , "concat" , type);
        String value = (String) mh0ld.invoke("Hello" , "World");
        MethodHandle mhNew = MethodHandles.insertArguments(mh0ld , 1 , " --");
        value = (String) mhNew.invoke("Hello");
        Assert.assertThat(value , Matchers.is("Hello --"));
    }
}
