package com.github.andyshao.java7.demo.basic;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Jul 30, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class Switch {

	public String generate(String name, String gender){
		String title = "";
		switch (gender) {
		case "man":
			title = "Mr." + name;
			break;
		case "woman":
			title = "Mrs." + name;
			break;
		default:
			title = name;
			break;
		}
		
		return title;
	}
}
