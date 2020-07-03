// MIT License 
// Author: Bryan Kwong on 7/3/2020

import java.util.*; 

public class WordDistances {

	private String text1 = ""; 
	private String text2 = "";
	private String[] text1Split; 
	private String[] text2Split; 
	
	private float jaccardMetric; 
	private float editMetric; 
	private float cosineMetric; 
	
	public WordDistances(String text1, String text2) {
		this.text1 = text1; 
		this.text2 = text2; 
		this.text1Split = text1.split("[.,:;?()\"\'-/ ]"); 
		this.text2Split = text2.split("[.,:;?()\"\'-/ ]");
		
		this.calcJaccardDistance();
		this.calcEditDistance(); 
	}
	
	private void calcJaccardDistance() {
		Set<String> t1Set = new HashSet<>(); 
		Set<String> t2Set = new HashSet<>(); 
		
		for (String s: text1Split) {
			t1Set.add(s); 
		}
		for (String s: text2Split) {
			t2Set.add(s); 
		}
		
		Set<String> union = new HashSet<>(t1Set); 
		Set<String> inter = new HashSet<>(t1Set); 
		union.addAll(t2Set); 
		inter.retainAll(t2Set);
		
		this.jaccardMetric = (float)inter.size() / (float)union.size(); 
	}
	
	private static int costOfSubstitution(String a, String b) {
        return a.equals(b) ? 0 : 1;
    }
	
    private static int min(int... numbers) {
        return Arrays.stream(numbers)
          .min().orElse(Integer.MAX_VALUE);
    }
	
	private void calcEditDistance() {
		List<String> x = Arrays.asList(this.text1Split); 
		List<String> y = Arrays.asList(this.text2Split); 
		
	    int[][] dp = new int[x.size() + 1][y.size() + 1];
	 
	    for (int i = 0; i <= x.size(); i++) {
	        for (int j = 0; j <= y.size(); j++) {
	            if (i == 0) {
	                dp[i][j] = j;
	            }
	            else if (j == 0) {
	                dp[i][j] = i;
	            }
	            else {
	                dp[i][j] = min(dp[i - 1][j - 1] 
	                 + costOfSubstitution(x.get(i - 1), y.get(j - 1)), 
	                  dp[i - 1][j] + 1, 
	                  dp[i][j - 1] + 1);
	            }
	        }
	    }
	 
	    float editDistance = (float)dp[x.size()][y.size()];
	    this.editMetric = 1 - (editDistance / (float)Math.max(x.size(), y.size())); 
	}
	
	public float getJaccardMetric() {
		return this.jaccardMetric; 
	}
	
	public float getEditMetric() {
		return this.editMetric; 
	}
	
	public String getText1() {
		return this.text1;
	}
	
	public String getText2() {
		return this.text2; 
	}
}
