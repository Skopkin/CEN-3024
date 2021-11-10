package module9;
import java.util.ArrayList;

public class Kopkin_Multi_Thread {
	private final static int numberCount = 200000000;
	private final static int threadCount = 1000;
	static ArrayList<Integer> list = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		
		System.out.println("Generating numbers\n"
				+ "------------------------------");
		// Create an array of 200 million random integers between 1 and 10
		for (int i = 0; i < numberCount; i++) {
			list.add((int) (Math.random() * 10));
		}
		
		System.out.println("Starting multithreaded summation\n"
				+ "------------------------------");
		// Start a timer before calling the MultiThreadSum method
		// 	and then call it by passing the list of integers
		long multiStart = System.currentTimeMillis();
		int multiSum = multiThreadSum(list);
		long multiRuntime = System.currentTimeMillis() - multiStart;
		
		System.out.println("Starting singlethreaded summation\n"
				+ "------------------------------");
		// Start a timer before calling the singleThreadSum method
		// 	and then call it by passing the list of integers
		long singleStart = System.currentTimeMillis();
		int singleSum = singleThreadSum(list);
		long singleRuntime = System.currentTimeMillis() - singleStart;
		
		// Print out the results of each sum method and the
		//	estimated run time of each
		System.out.println("The multi-thread sum method resulted in a sum of: " + multiSum 
				+ "\nAnd took approximately: " + multiRuntime + " milliseconds");
		System.out.println("The single-thread sum method resulted in a sum of: " + singleSum 
				+ "\nAnd took approximately: " + singleRuntime + " milliseconds");
	}
	
	
	// Single thread method that adds every number in the list and returns the sum
	private static int singleThreadSum(ArrayList<Integer> list) {
		int sum = 0;
		
		for(int i: list) {
			sum += i;
		}
		return sum;
	}
	
	// Multi thread method that adds every number in the list
	// 	by creating multiple threads that add a set of numbers 
	// 	per thread and combines the results
	private static int multiThreadSum(ArrayList<Integer> list) {
		// Create an ArrayList that will store the threads
		ArrayList<MultiThreadTask> threadResults = new ArrayList<MultiThreadTask>();
		
		// Variable for the total sum
		int sum = 0;
		
		// Divide the total size of the list by the number of
		// 	desired threads to generate
		int subListSize = list.size() / threadCount;
		
		// For each thread, fill a sublist with integers until it
		// 	has the right amount and then start a thread to add
		// 	them together
		for (int i = 0; i < threadCount; i++) {
			ArrayList<Integer> subList = new ArrayList<Integer>();
			int indexOffset = i * subListSize;
			for (int j = 0; j < subListSize; j++) {
				int currentIndex = j + indexOffset;
				if (currentIndex < list.size()) {
					subList.add(list.get(currentIndex));
				} else {
					break;
				}
			}
			threadResults.add(new MultiThreadTask(subList));
			threadResults.get(i).run();
		}
		
		// Wait for the last thread to finish before continuing
		try {
			threadResults.get(threadResults.size() - 1).join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Thread ripped");
			e.printStackTrace();
		}
		

		// Add the results from each thread
		for (MultiThreadTask m: threadResults) {
			sum += m.getSum();
		}
		
		// Return the complete sum
		return sum;
	}
	
	// Class that defines a thread that adds a list of integers
	private static class MultiThreadTask extends Thread {
		int sum = 0;
		ArrayList<Integer> list;
		
		MultiThreadTask(ArrayList<Integer> l) {
			list = l;
		}
		
		@Override
		public void run() {
			sum = singleThreadSum(list);
		}
		
		// Getter method for the sum
		public int getSum() {
			return sum;
		}
	}
}


