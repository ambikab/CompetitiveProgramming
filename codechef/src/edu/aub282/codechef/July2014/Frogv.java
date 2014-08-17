package edu.aub282.codechef.July2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Problem: http://www.codechef.com/JULY14/problems/FROGV
 * @author ambika_b
 *
 */
public class Frogv {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(bReader.readLine());
		int frogCnt = Integer.parseInt(tokens.nextToken()), dist = Integer.parseInt(tokens.nextToken()), queryCnt = Integer.parseInt(tokens.nextToken());
		int[] pond = new int[frogCnt], comm = new int[frogCnt], group = new int[frogCnt];
		StringTokenizer pondTokens = new StringTokenizer(bReader.readLine());
		for ( int i = 0; i < pond.length; i++) { 
			pond[i] = Integer.parseInt(pondTokens.nextToken());
			comm[i] = pond[i];
		}
		Arrays.sort(comm);
		Map<Integer, Integer> lookUp = new HashMap<Integer, Integer>();
		for ( int i = 0; i < pond.length; i++) {
			lookUp.put(comm[i], i);
			group[i] = comm[i];
		}
		for (int i = 1; i < comm.length; i++) 
			if (comm[i] - comm[i-1] <= dist) group[i] = group[i - 1]; 
		
		StringBuffer result = new StringBuffer();
		while (queryCnt-- > 0) {
			StringTokenizer pairs = new StringTokenizer(bReader.readLine());
			int i1 = Integer.parseInt(pairs.nextToken()) - 1, i2 = Integer.parseInt(pairs.nextToken()) - 1;
			int frog1 = lookUp.get(pond[i1]), frog2 = lookUp.get(pond[i2]);
			result.append((group[frog1] == group[frog2] ? "Yes" : "No") + "\n");
		}
		System.out.println(result);
	}
}