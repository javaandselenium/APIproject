package com.Ty.rmgyantra.genericlib;

import java.util.Random;

public class JavaUtility {
	
	public static int getRandomnumb() {
		Random ran=new Random();
		int num = ran.nextInt(2000);
		return num;
	}

}
