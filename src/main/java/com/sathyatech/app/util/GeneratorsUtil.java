package com.sathyatech.app.util;

import java.util.UUID;

public class GeneratorsUtil {
	
	public static final String generate(int length){
		return UUID.randomUUID().toString().replace("-", "").substring(0, length);
	}
	
	public static String codeGenerator(){
		return generate(10);
	}
}
