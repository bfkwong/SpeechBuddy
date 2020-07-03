// MIT License 
// Author: Bryan Kwong on 7/3/2020

import java.lang.Math; 
import java.util.*;   

public class TextDistances {
	
	private String text1 = ""; 
	private String text2 = ""; 
	
	private float hammingPercSim = 0; 	
	private float editPercSim = 0; 
	
	public TextDistances(String text1, String text2) {
		this.text1 = text1.toLowerCase(); 
		this.text2 = text2.toLowerCase(); 
		
		this.calculateHammingDistance();
		this.calculateEditDistance();
	}
	
	private void calculateHammingDistance() {
		if (this.text1 == "" || this.text2 == "") {
			return; 
		} 
		
		int hammingDistance = 0; 
		int minStrLen = Math.min(text1.length(), text2.length());
		int maxStrLen = Math.max(text1.length(), text2.length()); 
		int i; 
		for (i = 0; i < minStrLen; i ++) {
			if (text1.charAt(i) != text2.charAt(i)) {
				hammingDistance += 1; 
			}
		}
		hammingDistance += Math.abs(text1.length() - text2.length()); 
		this.hammingPercSim = ((float)maxStrLen - hammingDistance) / (float)maxStrLen;
	}
	
	private static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }
	
    private static int min(int... numbers) {
        return Arrays.stream(numbers)
          .min().orElse(Integer.MAX_VALUE);
    }
	
	public void calculateEditDistance() {
		String x = this.text1; 
		String y = this.text2; 
	    int[][] dp = new int[x.length() + 1][y.length() + 1];
	 
	    for (int i = 0; i <= x.length(); i++) {
	        for (int j = 0; j <= y.length(); j++) {
	            if (i == 0) {
	                dp[i][j] = j;
	            }
	            else if (j == 0) {
	                dp[i][j] = i;
	            }
	            else {
	                dp[i][j] = min(dp[i - 1][j - 1] 
	                 + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)), 
	                  dp[i - 1][j] + 1, 
	                  dp[i][j - 1] + 1);
	            }
	        }
	    }
	 
	    float editDistance = (float)dp[x.length()][y.length()];
	    this.editPercSim = 1 - (editDistance / (float)Math.max(x.length(), y.length())); 
	}
	
	public float getHammingPercSim() {
		return this.hammingPercSim; 
	}
	
	public float getEditPercSim() {
		return this.editPercSim; 
	}
	
	public String getText1() {
		return this.text1;
	}
	
	public String getText2() {
		return this.text2; 
	}
}

