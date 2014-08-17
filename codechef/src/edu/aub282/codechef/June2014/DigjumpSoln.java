package edu.aub282.codechef.June2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class DigjumpSoln {

	static int[] minHop;
	
	public static int getMinJumps(char[] input) {
		HashMap<Character, Integer> position = new HashMap<Character, Integer>();
		minHop = new int[input.length];
		
		for (int i = 1; i < input.length; i++) {
			minHop[i] = i - 1;
			int prevDist = i;
			
			if (!position.containsKey(input[i])) position.put(input[i], prevDist);
			else prevDist = position.get(input[i]);
			
			if (minHop[i - 1] > minHop[prevDist]) {
				minHop[i] = minHop[prevDist] + 1;
				//for (int j = i - 1,k = 1; minHop[j] > minHop[i] + k; j--, k++)
					//minHop[j] = minHop[i] + k;					
			} else minHop[i] = minHop[i - 1] + 1;
			
			//update the index with the one with least hops.
			int newIndex = minHop[prevDist] > minHop[i] ? i : prevDist;
			position.put(input[i], newIndex);
		}
		for ( int i = 0; i < minHop.length; i++) System.out.print(minHop[i]);
		System.out.println();
		return minHop[input.length - 1];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		String ipSeq = bReader.readLine();
		System.out.print(getMinJumps(ipSeq.toCharArray()));
	}
}