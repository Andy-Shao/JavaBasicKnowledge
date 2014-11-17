package com.github.andyshao.java7.demo.invokedynamic;

import java.io.IOException;
import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.nio.file.Files;
import java.nio.file.Paths;

import jdk.internal.org.objectweb.asm.Opcodes;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

/**
 * Should has the ASM 4.0 or up!!
 * Descript:<br>
 * Copyright: Copyright(c) Jul 31, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class Demo {

	public static class ToUpperCase{
		public static CallSite bootstrap(Lookup lookup, String name, MethodType type, String value) throws NoSuchMethodException, IllegalAccessException{
			MethodHandle mh = lookup.findVirtual(String.class, "toUpperCase", MethodType.methodType(String.class)).bindTo(value);
			return new ConstantCallSite(mh);
		}
	}
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, IOException {
		MethodHandle bsm = MethodHandles.lookup().findStatic(Demo.ToUpperCase.class, "bootstrap", 
				MethodType.methodType(CallSite.class, Lookup.class, String.class, MethodType.class, String.class));
		
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		//version, access, class' name, package's name, super class' name, interfaces' name
		cw.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER, "ToUpperCaseMain", null, "java/lang/Object", null);
		{
			//access, method's name, method type, 
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
			mv.visitCode();
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream");
//			mv.visitInvokeDynamicInsn("toUpperCase", "()Ljava/lang/String;", BSM, "Hello");
			mv.visitMethodInsn(Opcodes.INVOKEDYNAMIC, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}
		cw.visitEnd();
		
		Files.write(Paths.get("ToUpperCaseMain.class"), cw.toByteArray());
	}
}
