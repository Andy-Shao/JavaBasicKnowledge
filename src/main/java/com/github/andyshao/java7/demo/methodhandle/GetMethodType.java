package com.github.andyshao.java7.demo.methodhandle;

import java.lang.invoke.MethodType;

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
public class GetMethodType {

    @SuppressWarnings("unused")
    @Test
    public void generateGenericMethodTypes() {
        //There are 3 object parameter in the method.
        MethodType mt1 = MethodType.genericMethodType(3);

        //there are 2 object parameter and one object[] in the end.
        MethodType mt2 = MethodType.genericMethodType(2 , true);
    }

    @SuppressWarnings("unused")
    @Test
    public void generateMethodTypes() {
        //String.length()
        MethodType mt1 = MethodType.methodType(int.class);

        //String.concat(String str)
        MethodType mt2 = MethodType.methodType(String.class , String.class);

        //String.getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
        MethodType mt3 = MethodType.methodType(void.class , int.class , int.class , char[].class , int.class);

        //String.startWith(String prefix)
        MethodType mt4 = MethodType.methodType(boolean.class , mt2);
    }

    @SuppressWarnings("unused")
    @Test
    public void generateMethodTypesFromDescriptor() {
        ClassLoader cl = this.getClass().getClassLoader();
        String descriptor = "(Ljava/lang/String;)Ljava/lang/String;";
        MethodType mt1 = MethodType.fromMethodDescriptorString(descriptor , cl);
    }
}
