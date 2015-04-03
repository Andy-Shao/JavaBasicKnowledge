package com.github.andyshao.java7.demo.script;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Jul 30, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class ScriptCompile {

    public CompiledScript compile(String scriptText) throws ScriptException {
        ScriptEngine engine = JavaScriptEngine.getJavaScriptEngine();
        if (engine instanceof Compilable) {
            CompiledScript script = ((Compilable) engine).compile(scriptText);
            return script;
        }

        return null;
    }

    public void run(String scriptText) throws ScriptException {
        CompiledScript script = this.compile(scriptText);
        if (script == null) return;

        for (int i = 0 ; i < 100 ; i++)
            script.eval();
    }
}
