//import
var System = Java.type("java.lang.System")
var Date = Java.type("java.util.Date")
var String = Java.type("java.lang.String")

//main
var date = new Date()
System.out.println(String.format("date.year + 1900 is %d", date.year + 1900))
System.out.println(String.format("date.year = 2014 - 1900 is %d", (date.year = 2014 - 1900)))