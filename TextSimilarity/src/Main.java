// MIT License 
// Author: Bryan Kwong on 7/3/2020

import java.util.*; 

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub 
		String[] strArr = new String[] {"Hello There", "Helllloooo There", "Hello THREEREE"};
		List<String> str = Arrays.asList(strArr); 
		SimilarityChecker sc = new SimilarityChecker(str); 
		System.out.println(sc.getThreadDrivers()); 
		
	}

}
