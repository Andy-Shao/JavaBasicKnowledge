package com.github.andyshao.lang;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class SomeUsefulProperties {
	
	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][]{
				{"os.name"},
				{"os.arch"},
				{"os.version"},
				{"file.separator"},
				{"path.separator"},
				{"line.separator"},
				{"user.name"},
				{"user.home"},
				{"user.dir"}
		});
	}
	
	private volatile String key;
	
	public SomeUsefulProperties(String key) {
		this.key = key;
	}

	@Test
	public void test(){
		System.getProperty(this.key);
	}
}
