package edu.aub282.codechef.June2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Problem: http://www.codechef.com/JUNE14/problems/FORGETPW
 * @author ambika_b
 *
 */
public class Forgetpw {

	public static String encryptPass(Map<Character, Character> rules, char[] password) {
		if (rules.keySet().size() == 0) {
			String op = formatResult((password));
			return op.length() > 0 ? op : "0";
		}
		for (int i = 0; i < password.length; i++)
			if (rules.containsKey(password[i])) 
				password[i] = rules.get(password[i]);
		String op = formatResult((password));
		return op.length() > 0 ? op : "0";
	}

	public static String formatResult(char[] number) {
		int i, j;
		for (i = 0; i < number.length; i++ ) 
			if (number[i] != '0' ) break;
		int index = new StringBuffer(String.valueOf(number)).indexOf(".");
		index = index < 0 ? number.length : index; 
		for (j = number.length - 1; j >= index; j--) 
			if (number[j] != '0') break;
		if ((i >= number.length) && (j < 0)) return "0";
		if ((number[j] == '.')) j = j - 1;
		return String.valueOf(number).substring(i, j + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		int ipCount = Integer.parseInt(bReader.readLine());
		StringBuffer result = new StringBuffer();
		while( ipCount-- > 0) {
			int ruleCount = Integer.parseInt(bReader.readLine());
			Map<Character, Character> rules = new HashMap<Character, Character>();
			for (int i = 0; i < ruleCount; i++) {
				StringTokenizer rule = new StringTokenizer(bReader.readLine());
				rules.put(rule.nextToken().charAt(0), rule.nextToken().charAt(0));
			}
			result.append(encryptPass(rules, bReader.readLine().toCharArray()) + "\n");
		}
		System.out.println(result);
	}
}