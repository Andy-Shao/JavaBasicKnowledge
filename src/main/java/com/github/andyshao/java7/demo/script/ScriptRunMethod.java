package com.github.andyshao.java7.demo.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

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
public class ScriptRunMethod {

    public interface Greet {
        String getGreeting(String name);
    }

    @Test
    public void invokeFunction() throws NoSuchMethodException , ScriptException {
        ScriptEngine engine = JavaScriptEngine.getJavaScriptEngine();
        String scriptText = "function greet(name){print('Hello, ' + name);}";
        engine.eval(scriptText);
        Invocable invocable = (Invocable) engine;
        invocable.invokeFunction("greet" , "Alex");
    }

    @Test
    public void invokeMethod() throws NoSuchMethodException , ScriptException {
        ScriptEngine engine = JavaScriptEngine.getJavaScriptEngine();
        String scriptText = "var obj={getGreeting : function(name) {return 'Hello, ' + name;}};";
        engine.eval(scriptText);
        Invocable invocable = (Invocable) engine;
        Object scope = engine.get("obj");
        Object result = invocable.invokeMethod(scope , "getGreeting" , "Alex");
        System.out.println(result);
    }

    @Test
    public void useinterface() throws ScriptException {
        ScriptEngine engine = JavaScriptEngine.getJavaScriptEngine();
        String scriptText = "function getGreeting(name) {return 'Hello, ' + name;}";
        engine.eval(scriptText);
        Invocable invocable = (Invocable) engine;
        Greet greet = invocable.getInterface(Greet.class);
        System.out.println(greet.getGreeting("Alex"));
    }
}
