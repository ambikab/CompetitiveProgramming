package edu.aub282.codechef.June2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author ambika_b
 *
 */
public class Chefzot {

	public static void main(String[] args) throws IOException{
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		bReader.readLine();
		StringTokenizer arrayTokens = new StringTokenizer(bReader.readLine());
		int[] array = new int[arrayTokens.countTokens()];
		int max = 0, curMax = 0;
		for ( int i = 0; i < array.length; i++) {
			array[i] = Integer.parseInt(arrayTokens.nextToken());
			if (array[i] <= 0 ) {
				max = max > curMax ? max : curMax;
				curMax = 0;
			} else curMax ++;
		}
		max = max > curMax ? max : curMax;
		System.out.println(max + "\n");
	}
	
}