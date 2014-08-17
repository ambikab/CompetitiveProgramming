package edu.aub282.codechef.August2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Problem: http://www.codechef.com/AUG14/problems/CLETAB
 * @author ambika_b
 *
 */
public class Cletab {

	static HashMap<Integer, Integer> restaurant;

	static int[][] orderList;

	static int tableCnt;

	static int orderCnt;

	public static void populateNext(StringTokenizer orders) {
		HashMap<Integer, Integer> prevOccurance = new HashMap<Integer, Integer>();
		orderList = new int[orderCnt][2];
		for ( int i = 0; i < orderList.length; i++) {
			int nextToken = Integer.parseInt(orders.nextToken());
			orderList[i][0] = nextToken;
			orderList[i][1] = Integer.MAX_VALUE;
			if (prevOccurance.containsKey(nextToken)) orderList[prevOccurance.get(nextToken)][1] = i;
			prevOccurance.put(nextToken, i);
		}
	}

	public static int countSwaps() {
		restaurant = new HashMap<Integer, Integer>();
		int swap = 0;
		for (int i = 0; i < orderCnt; i++) {
			if (restaurant.containsKey(orderList[i][0])) {
				restaurant.put(orderList[i][0], orderList[i][1]);
				continue;
			}
			if ((restaurant.size() == tableCnt)) {
				int lastOccurance = -1, lastCustomer = -1;
				for (Integer occupiedCustomer : restaurant.keySet()) 
					if (lastOccurance < restaurant.get(occupiedCustomer)) {
						lastCustomer = occupiedCustomer;
						lastOccurance = restaurant.get(occupiedCustomer);
					}
				restaurant.remove(lastCustomer);
			}
			swap++;
			restaurant.put(orderList[i][0], orderList[i][1]);
		}
		return swap;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		int testCnt = Integer.parseInt(bReader.readLine());
		StringBuffer result = new StringBuffer();
		while(testCnt-- > 0) {
			StringTokenizer tokens = new StringTokenizer(bReader.readLine());
			tableCnt = Integer.parseInt(tokens.nextToken()); orderCnt = Integer.parseInt(tokens.nextToken());
			populateNext(new StringTokenizer(bReader.readLine()));
			result.append(countSwaps()+ "\n");
		}
		System.out.println(result.toString());
	}

}
