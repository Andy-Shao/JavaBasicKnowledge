package com.github.andyshao.java7.demo.basic;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Jul 30, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class Underscore {

	public void display(){
		System.out.println(1_500_000);
		double value1 = 5_6.3_4;
		int value2 = 89_3__1;
		
		System.out.println(value1);//output 56.34
		System.out.println(value2);//output 8931
	}
}
