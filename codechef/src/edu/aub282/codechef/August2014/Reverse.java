package edu.aub282.codechef.August2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Problem: http://www.codechef.com/AUG14/problems/REVERSE
 * @author ambika_b
 *
 */
public class Reverse {

	static class Node {
		public int value;
		public boolean isReverse;

		Node(int value, boolean isReverse) {
			this.value = value;
			this.isReverse = isReverse;
		}

		@Override
		public boolean equals(Object obj) {
			Node node = (Node) obj;
			return node.value == this.value;
		}
	}

	static class Distance implements Comparable<Distance> {
		int id;
		int reverseCnt;

		public Distance(int id, int reverseCnt) {
			this.id = id;
			this.reverseCnt = reverseCnt;
		}

		@Override
		public int compareTo(Distance node) {
			return new Integer(reverseCnt).compareTo(node.reverseCnt);
		}

	}

	public static int getMinReversePath(Set<Node>[] graph) {
		PriorityQueue<Distance> minPq = new PriorityQueue<Distance>();
		boolean[] isVisited = new boolean[graph.length];
		boolean[] contains = new boolean[graph.length];
		int[] distance = new int[graph.length];
		minPq.add(new Distance(1, 0));

		while(! minPq.isEmpty()) {
			Distance curr = minPq.poll();
			isVisited[curr.id] = true;
			if (curr.id == (graph.length - 1)) return curr.reverseCnt;
			if (graph[curr.id] == null) continue;
			for (Node adjNode : graph[curr.id]) {
				int revCount = curr.reverseCnt + (adjNode.isReverse ? 1 : 0);
				if (!isVisited[adjNode.value]) {
					if (contains[adjNode.value]) revCount = Math.min(distance[adjNode.value], revCount);
					else contains[adjNode.value] = true;
					distance[adjNode.value] = revCount;
					minPq.offer(new Distance(adjNode.value, revCount));
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException  {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer ipDesc = new StringTokenizer(bReader.readLine());
		int node = Integer.parseInt(ipDesc.nextToken()), edgeCnt = Integer.parseInt(ipDesc.nextToken());
		HashSet<Node>[] graph = new HashSet[node + 1];
		for ( int i = 0; i < edgeCnt; i++) {
			StringTokenizer edges = new StringTokenizer(bReader.readLine());
			int from = Integer.parseInt(edges.nextToken()), to = Integer.parseInt(edges.nextToken());
			if (graph[from] == null) graph[from] = new HashSet<Node>();
			if (graph[to] == null) graph[to] = new HashSet<Node>();
			graph[from].add(new Node(to, false));
			graph[to].add(new Node(from, true)); // might be a problem if both the edges are present as a part of the graph itself
		}
		
		System.out.println(getMinReversePath(graph));
	}

}