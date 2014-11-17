//import
var System = Java.type("java.lang.System")
var String = Java.type("java.lang.String")

//main
/*
 * var IntArrayType = Java.type("int[]")
 * var arr = new IntArrayType(10)
 * arr[1] = 123
 * arr[2] = 321*/

var arr = [123 , 321]
System.out.println(arr.class)
var intArrType = Java.type("int[]")
var javaArr = Java.to(arr, intArrType)
System.out.println(javaArr.class)
System.out.println("for (var i in javaArr) System.out.print(i):")
for (var i in javaArr) System.out.print(i)
System.out.println()
System.out.println("for each (var i in javaArr) System.out.print(i):")
for each (var i in javaArr) System.out.print(i)