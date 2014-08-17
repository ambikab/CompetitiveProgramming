package edu.aub282.codechef.July2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Problem: http://www.codechef.com/JULY14/problems/CSUB
 * @author ambika_b
 *
 */
public class CSub {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		int tCount = Integer.parseInt(bReader.readLine());
		StringBuffer result = new StringBuffer();
		while( tCount-- > 0) {
			bReader.readLine();
			String number = bReader.readLine();
			long sum = 0, k = 0;
			for ( int i = 0; i < number.length(); i++) {
				if (number.charAt(i) == '1') {
					k++;
					sum = sum + k;
				}
			}
			result.append(sum + "\n");
		}
		System.out.println(result);
	}
}
