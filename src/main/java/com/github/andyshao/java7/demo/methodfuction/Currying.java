package com.github.andyshao.java7.demo.methodfuction;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Jul 30, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class Currying {

    public static int add(int a , int b) {
        return a + b;
    }

    public static int add5(int a) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(int.class , int.class , int.class);
        MethodHandle mhAdd = lookup.findStatic(Currying.class , "add" , type);
        MethodHandle mh = Currying.curry(mhAdd , 5);
        return (int) mh.invoke(a);
    }

    public static MethodHandle curry(MethodHandle handle , int value) {
        return MethodHandles.insertArguments(handle , 0 , value);
    }
}
