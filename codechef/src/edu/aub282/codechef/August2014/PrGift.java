package edu.aub282.codechef.August2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Problem: http://www.codechef.com/AUG14/problems/PRGIFT
 * @author ambika_b
 *
 */
public class PrGift {

	
	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		int testCaseCnt = Integer.parseInt(bReader.readLine());
		StringBuffer result = new StringBuffer("");
		while (testCaseCnt-- > 0) {
			StringTokenizer tokens = new StringTokenizer(bReader.readLine());
			//n - size of the array and k : size of the segment.
			int n = Integer.parseInt(tokens.nextToken()), k = Integer.parseInt(tokens.nextToken());
			tokens = new StringTokenizer(bReader.readLine());
			int currCnt = 0;
			for ( int i = 0; i < n; i++) {
				int curr = Integer.parseInt(tokens.nextToken());
				if (curr % 2 == 0) currCnt++;
			}
			if (k == 0 && currCnt == n) result.append("NO" + "\n");
			else if (k == 0 && currCnt < n) result.append("YES" + "\n");
			else if (currCnt >= k) result.append("YES" + "\n");
			else result.append("NO" + "\n");
		}
		System.out.println(result.toString());
	}

}
