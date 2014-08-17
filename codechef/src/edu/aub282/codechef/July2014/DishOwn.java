package edu.aub282.codechef.July2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Problem: www.codechef.com/JULY14/problems/dishown
 * @author ambika_b
 *
 */
public class DishOwn {

	static int[] owner, highest;

	public static String compete(int dish1, int dish2) {
		int owner1 = findOwner(dish1), owner2 = findOwner(dish2);
		if (owner1 == owner2)return "Invalid query!" + "\n";
		else if (highest[owner1] > highest[owner2])
			union(owner1, owner2);
		else if (highest[owner1] < highest[owner2])
			union(owner2, owner1);
		return "";
	}

	public static void union(int winner, int loser) {
		owner[loser] = owner[winner];
	}

	//path compression is performed here.
	public static int findOwner(int dishId) {
		if(dishId == owner[dishId]) return dishId;
		int root = findOwner(owner[dishId]);
		owner[dishId] = root;
		return root;
		/*if (dishId != owner[dishId])  {
			owner[dishId] = owner[findOwner(owner[dishId])];
			return owner[dishId];
		}
		else return dishId;*/
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		int testCnt = Integer.parseInt(bReader.readLine());
		StringBuffer result = new StringBuffer();
		while(testCnt-- > 0) {
			int count = Integer.parseInt(bReader.readLine());
			owner = new int[count + 1]; highest = new int[count + 1];
			StringTokenizer tokens = new StringTokenizer(bReader.readLine());
			//initialize the chef score.
			for (int i = 1; i <= count; i++) { 
				owner[i] = i;
				highest[i] = Integer.parseInt(tokens.nextToken());
			}
			int queryCnt = Integer.parseInt(bReader.readLine());
			while(queryCnt-- > 0) {
				StringTokenizer query = new StringTokenizer(bReader.readLine());
				int choice = Integer.parseInt(query.nextToken());
				if (choice == 0) {
					int dish1 = Integer.parseInt(query.nextToken()), dish2 = Integer.parseInt(query.nextToken());
					result.append(compete(dish1, dish2));
				} else {
					int dishId = Integer.parseInt(query.nextToken());
					result.append(findOwner(dishId) + "\n");
				}
			}
		}
		System.out.println(result);
	}
}