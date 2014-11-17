package com.github.andyshao.java7.demo.script;

import java.io.FileWriter;
import java.io.IOException;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Jul 30, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class JavaScriptEngine {

	public void greet() throws ScriptException{
		ScriptEngine engine = getJavaScriptEngine();
		if(engine == null)
			throw new RuntimeException("Can't find out the JavaScript.");
		engine.eval("println('Hello!');");
	}

	protected static ScriptEngine getJavaScriptEngine() {
	    ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
	    return engine;
    }
	
	public void useDefaultBinding() throws ScriptException{
		ScriptEngine engine = getJavaScriptEngine();
		engine.put("name", "Alex");
		engine.eval("var message = 'Hello, ' + name;");
		engine.eval("println(message);");
		Object obj = engine.get("message");
		System.out.println(obj);
	}
	
	public void useCustomBinding() throws ScriptException {
		ScriptEngine engine = getJavaScriptEngine();
		Bindings bindings = new SimpleBindings();
		bindings.put("hobby", "playing games");
		engine.eval("println('I like ' + hobby);", bindings);
	}
	
	public void scriptToFile() throws IOException, ScriptException{
		ScriptEngine engine = getJavaScriptEngine();
		ScriptContext context = engine.getContext();
		context.setWriter(new FileWriter("output.txt"));
		engine.eval("println('Hello World!');");
	}
	
	public void scriptContextAttribute(){
		ScriptEngine engine = getJavaScriptEngine();
		ScriptContext context = engine.getContext();
		context.setAttribute("name", "Alex", ScriptContext.GLOBAL_SCOPE);
		context.setAttribute("name", "Bob", ScriptContext.ENGINE_SCOPE);
		context.getAttribute("name");// the value is 'Bob'
	}
	
	public void scriptContextBindings() throws ScriptException{
		ScriptEngine engine = getJavaScriptEngine();
		ScriptContext context = engine.getContext();
		Bindings bindings1 = engine.createBindings();
		bindings1.put("name", "Alex");
		context.setBindings(bindings1, ScriptContext.GLOBAL_SCOPE);
		Bindings bindings2 = engine.createBindings();
		bindings2.put("name", "Bob");
		context.setBindings(bindings2, ScriptContext.ENGINE_SCOPE);
		engine.eval("println(name);");
	}
	
	public void useScriptContextValues() throws ScriptException{
		ScriptEngine engine = getJavaScriptEngine();
		ScriptContext context = engine.getContext();
		Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
		bindings.put("name", "Alex");
		engine.eval("println(name);"); // output Alex
	}
	
	public void attributeInBindings() throws ScriptException{
		ScriptEngine engine = getJavaScriptEngine();
		ScriptContext context = engine.getContext();
		context.setAttribute("name", "Alex", ScriptContext.GLOBAL_SCOPE);
		engine.eval("println(name);"); // output Alex
	}
}
