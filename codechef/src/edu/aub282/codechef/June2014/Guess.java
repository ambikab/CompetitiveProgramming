package edu.aub282.codechef.June2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Problem: http://www.codechef.com/JUNE14/problems/GUESS
 * @author ambika_b
 *
 */
public class Guess {

	public static String getOddProbability(long m, long n) {
		long even1 = getEven(m), even2 = getEven(n);
		long odd1 =  m - getEven(m), odd2 = n - even2;
		long totalOdd = (even1 * odd2) + (odd1 * even2);
		long total = m * n; 
		long gcd = gcd(total, totalOdd);
		return (totalOdd / gcd) + "/" + (total / gcd);
	}
	
	
	public static long gcd(long p, long q) {
	    if (q == 0) {
	      return p;
	    }
	    return gcd(q, p % q);
	  }
	
	public static long getEven(long end) {
		return (int) end / 2;
	}
	
	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		int ipCount = Integer.parseInt(bReader.readLine());
		StringBuffer result = new StringBuffer();
		while(ipCount-- > 0) {
			StringTokenizer tokens = new StringTokenizer(bReader.readLine());
			long m = Integer.parseInt(tokens.nextToken()), n = Integer.parseInt(tokens.nextToken());
			result.append(getOddProbability(m, n) + "\n");
		}
		System.out.println(result);
	}
}
