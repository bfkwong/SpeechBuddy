// MIT License 
// Author: Bryan Kwong on 7/3/2020

import java.util.*; 

public class SimilarityChecker {
	
	private List<String> texts; 
	private List<DistanceThreadDriver> tDrivers = new ArrayList<>(); 
	private List<Thread> threads = new ArrayList<>();
	
	public SimilarityChecker(List<String> texts) throws InterruptedException {
		this.texts = texts; 
		
		this.startThreads(); 
		this.joinThreads(); 
	}
	
	private void startThreads() {
		for (int i = 0; i < this.texts.size() - 1; i++) {
			for (int j = i + 1; j < this.texts.size(); j++) {
				String tname = String.valueOf(i) + "_" + String.valueOf(j); 
				String txt1 = texts.get(i); 
				String txt2 = texts.get(j);
				DistanceThreadDriver tdrive = new DistanceThreadDriver(tname, txt1, txt2);
				Thread t = new Thread(tdrive); 
				t.start(); 
				tDrivers.add(tdrive);
				threads.add(t); 
			}
		}
	}
	
	private void joinThreads() throws InterruptedException {
		for (Thread t: threads) {
			t.join();
		}
	}
	
	public List<String> getTexts() {
		return this.texts; 
	}
	
	public List<DistanceThreadDriver> getThreadDrivers() {
		return this.tDrivers;
	}
}
