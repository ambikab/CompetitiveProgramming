package edu.aub282.codechef.June2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Problem: http://www.codechef.com/JUNE14/problems/DIGJUMP
 * @author ambika_b
 *
 */
public class Digjump {

	static HashMap<Integer, ArrayList<Node>> map = new HashMap<Integer, ArrayList<Node>>();

	static Node head = null;

	static class Node {
		int value; // if final holds a -ve value.

		List<Node> adjList;

		int distTo;

		boolean visited;

		Node(int value) {
			this.value = value;
			this.visited = false;
			this.adjList = new ArrayList<Node>();
		}

	}

	public static int getShortest(Node source) {
		Queue<Node> bfs = new LinkedList<Node>();
		source.visited = true;
		source.distTo = 1;
		bfs.add(source);
		
		while (!bfs.isEmpty()) {
			Node curNode = bfs.poll();
			int curDist = curNode.distTo;
			if (curNode.value < 0) return curDist;
			for( Node adjNode : curNode.adjList) {
				if (!adjNode.visited) {
					adjNode.visited = true;
					adjNode.distTo = curDist + 1;
					if (adjNode.value < 0) return adjNode.distTo;
					bfs.add(adjNode);
				}
			}
		}
		return -1;
	}

	public static void relaxEdges(Node fromNode, Node toNode) {
		if ( toNode.distTo > fromNode.distTo + 1)
			toNode.distTo = fromNode.distTo + 1;
	}

	public static void addToMap(Node newNode) {
		int val = Math.abs(newNode.value);
		ArrayList<Node> simNodes = map.get(val);
		if (simNodes != null)
			for (Node adjNode : simNodes) {
				addToNode(adjNode, newNode);
				addToNode(newNode, adjNode);
			}
		else simNodes = new ArrayList<Node>();
		simNodes.add(newNode);
		map.put(val, simNodes);
	}

	public static void addToNode(Node from ,Node to) {
		List<Node> adjList = from.adjList;
		adjList.add(to);
	}

	public static void buildGraph(String ipSeq) {
		Node curHead = null;
		for (int i = 0; i < ipSeq.length(); i++) {
			int val = Integer.parseInt(ipSeq.charAt(i) + "");
			val = val == 0 ? 10 : val;
			if ( i == ipSeq.length() - 1) val = val * -1;
			Node temp = new Node(val);
			if (head == null)  curHead = head = temp; 
			else {
				addToNode(curHead, temp);
				addToNode(temp, curHead);
			}
			addToMap(temp);
			curHead = temp;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		String ipSeq = bReader.readLine();
		buildGraph(ipSeq);
		int result = head == null ? 0 : (getShortest(head) - 1);
		System.out.print(result);
	}
}