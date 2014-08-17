package edu.aub282.codechef.July2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Problem: http://www.codechef.com/JULY14/problems/SGARDEN
 * @author ambika_b
 *
 */
public class SGarden {

	public static long lcm(List<Long> array) {
		long max = 0;
		for (Long number : array) max = number > max ? number : max;
		if (max <= 2) return max;
		HashMap<Long, Long> primeCount = new HashMap<Long, Long>();
		for (Long number : array)
			getPrimeFact(number, primeCount);
		return lcm(primeCount);
	}

	public static long lcm(HashMap<Long, Long> primeCount) {
		long lcm = 1;
		Set<Long> keys = primeCount.keySet();
		for ( Long key : keys) 
			lcm = (long) (lcm * Math.pow(key, primeCount.get(key)) % 1000000007);
		return lcm;
	}

	public static void updateKey(long key, long value, HashMap<Long, Long> map) {
		if (map.containsKey(key)) {
			long max = map.get(key);
			max = max > value ? max : value;
			map.put(key, max);
		}
		else map.put(key, value);
	}
	
	public static void getPrimeFact(Long i, HashMap<Long, Long> primeCount) {
		if (i < 2) return;
		long count = 0;
		while (i % 2 == 0) {
			count++;
			i = i / 2;
		}
		updateKey(2l, count, primeCount);
		long sqrt = i;	
		for ( int j = 3; j <= sqrt; j = j + 2) {
			count = 0;
			while ( i % j == 0) {
				count++;
				i = i / j;
			}
			updateKey(j, count, primeCount);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		int testCnt = Integer.parseInt(bReader.readLine());
		StringBuffer result = new StringBuffer();
		while(testCnt-- > 0) {
			long res = 0; 
			int size = Integer.parseInt(bReader.readLine());
			Node[] tree = new Node[size + 1];
			for (int i = 1; i <= size; i++)
				tree[i] = new Node(i, false, null);
			StringTokenizer tokens = new StringTokenizer(bReader.readLine());
			for (int i = 1; tokens.hasMoreTokens(); i++) {
				int cur = Integer.parseInt(tokens.nextToken());
				if (cur != i)
					tree[i].next = tree[cur];
			}
			List<Long> cycleLen = new ArrayList<Long>();
			for (int i = 1; i <= size; i++) {  
				if (!tree[i].flag) 
					cycleLen.add(traverse(tree[i]));
			}
			res = lcm(cycleLen);
			result.append((res == 0 ? 1 : (res % 1000000007)) + "\n");
		}
		System.out.println(result);
	}

	public static long traverse(Node cycle) {
		long sum = 0;
		if (cycle.next == null) return 1;
		while(!cycle.flag) {
			sum++;
			cycle.flag = true;
			cycle = cycle.next;
		}
		return sum;
	}

	static class Node {
		int id;
		boolean flag;
		Node next;

		public Node(int id, boolean flag, Node next) {
			this.id = id;
			this.flag = flag;
			this.next = next;
		}

	}
}