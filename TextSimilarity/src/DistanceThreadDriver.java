// MIT License 
// Author: Bryan Kwong on 7/3/2020

public class DistanceThreadDriver implements Runnable{
	
	private String text1; 
	private String text2; 
	
	private Thread t; 
	private String tName; 
	
	private volatile WordDistances wd; 
	private volatile TextDistances td; 
	
	public DistanceThreadDriver(String tname, String text1, String text2) {
		this.text1 = text1; 
		this.text2 = text2; 
		this.tName = tname; 
	}
	
	public void run() {
		wd = new WordDistances(this.text1, this.text2); 
		td = new TextDistances(this.text1, this.text2);  
	}
	
	public void start() {
		if (t == null) {
			System.out.println("Starting Thread" + this.tName); 
			t = new Thread(this, this.tName); 
			t.start(); 
		}
	}
	
	public WordDistances getWD() {
		return wd; 
	}
	
	public TextDistances getTD() {
		return td;
	}
}
