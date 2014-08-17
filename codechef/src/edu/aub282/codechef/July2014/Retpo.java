package edu.aub282.codechef.July2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Retpo {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		int queryCnt = Integer.parseInt(bReader.readLine());
		StringBuffer result = new StringBuffer();
		while(queryCnt-- > 0) {
			StringTokenizer tokens = new StringTokenizer(bReader.readLine());
			int x = Math.abs(Integer.parseInt(tokens.nextToken())), y = Math.abs(Integer.parseInt(tokens.nextToken()));
			int flag = (x + y) % 2;
			int res = Math.max(x, y - flag);
			result.append(((2 * res) + flag) + "\n");
		}
		System.out.println(result);
	}
}