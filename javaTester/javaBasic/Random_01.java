package javaBasic;

import java.util.Random;

public class Random_01 {
	public static void main(String[] args) {
		
	}
	
	public String getEmailRandom() {
		Random rand = new Random();
		return "join" + rand.nextInt(99999) + "@kennedy.us";
	}
}
