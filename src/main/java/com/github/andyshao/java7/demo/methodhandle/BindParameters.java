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
public class BindParameters {

    @Test
    public void bindTo() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh = lookup.findVirtual(String.class , "length" , MethodType.methodType(int.class));
        int len = (int) mh.invoke("Hello");
        Assert.assertThat(len , Matchers.is(5));
        mh = mh.bindTo("Hello World");
        len = (int) mh.invoke();
        Assert.assertThat(len , Matchers.is(11));
    }

    @Test
    public void multipleBindTo() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        {
            MethodHandle mh =
                lookup.findVirtual(String.class , "indexOf" ,
                    MethodType.methodType(int.class , String.class , int.class));
            mh = mh.bindTo("Hello").bindTo("l");
            Assert.assertThat((Integer) mh.invoke(2) , Matchers.is(2));
        }

        {
            MethodHandle mh =
                lookup.findVirtual(String.class , "substring" ,
                    MethodType.methodType(String.class , int.class , int.class));
            mh = mh.asType(mh.type().wrap());
            mh = mh.bindTo("Hello World").bindTo(3);
            Assert.assertThat((String) mh.invoke(5) , Matchers.is("lo"));
        }
    }

}
