package edu.aub282.codechef.August2014;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.InputMismatchException;

public class Equake {

	static SegNode[] segTree = null;

	static String[] heights;

	static int[] lazy = null; 

	static class SegNode {
		String value;
		int max;

		SegNode (String value, int max) {
			this.max = max;
			this.value = value;
		}
	}

	public static void buildTree() {
		int pow = (int) (Math.log(heights.length) / (0.6931471805599453)) + 1;
		int size = (int) (1 << pow) << 1;
		segTree = new SegNode[size + 1];
		lazy = new int[size + 1];
		buildTree(1, 0, heights.length - 1);
	}

	public static void buildTree(int index, int low, int high) {
		if (low == high) segTree[index] = new SegNode(heights[low], Integer.parseInt(heights[low]));
		else {
			int mid = low + ((high - low) >> 1), left = (index  << 1);
			int right = left + 1;
			buildTree(left, low, mid);
			buildTree(right, mid + 1, high);
			int max = segTree[left].max > segTree[right].max ? segTree[left].max : segTree[right].max;
			segTree[index] = new SegNode(null, max);
		}
	}

	public static void updateQuery(int index, int low, int high, int value) {
		if (high == low) {
			segTree[index].value = shiftString(segTree[index].value, value);
			segTree[index].max = Integer.parseInt(segTree[index].value);
		} else {
			int mid = low + ((high - low) >> 1), left = (index  << 1), right = left + 1, shiftValue = value;
			if (lazy[index] == 1) shiftValue += lazy[index];
			if (shiftValue != 0) {
				updateQuery(left, low, mid, shiftValue);
				updateQuery(right, mid + 1, high, shiftValue);
				int max = segTree[left].max > segTree[right].max ? segTree[left].max : segTree[right].max;
				segTree[index].max = max;
			}
			lazy[index] = 0; //mark for lazy update. 
		}
	}

	public static int queryRange(int index, int i, int j, int low, int high) {
		if (j < low || i > high)  return 0;
		if ((i >= low && j<= high)) return segTree[index].max;
		int mid = i + ((j - i) >> 1), left = index << 1, right = left + 1;
		return Math.max(queryRange(left, i , mid, low, high),  queryRange(right, mid + 1, j, low, high));
	}

	public static String shiftString(String value, int shift) {
		char newStr[] = new char[value.length()];
		if ( shift >= value.length())
			shift = shift % value.length();
		if (shift == 0) return value;
		int index = 0;
		for (int i = shift; i < value.length(); i++, index++)
			newStr[index] = value.charAt(i);
		for (int i = 0; i < shift && index < value.length(); index++, i++)
			newStr[index] = value.charAt(i);
		return String.copyValueOf(newStr);
	}

	public static void shiftHeights(int index, int i, int j, int low, int high, int shift) {
		if (j < low || i > high) return;
		if(i == j) {
			segTree[index].value = shiftString(segTree[index].value, shift);
			segTree[index].max = Integer.parseInt(segTree[index].value.toString());
			return;
		}  if (i == low && j == high) {
			lazy[index] = lazy[index] + shift;
			return;
		}
		int mid = i + ((j - i) >> 1), right = index << 1, left = right + 1;
		shiftHeights(right, i, mid, low, high, shift);
		shiftHeights(left, mid + 1, j, low, high, shift);
		segTree[index].max = Math.max(segTree[left].max, segTree[right].max);
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader bReader = new InputStreamReader(System.in);
		OutputStreamWriter out = new OutputStreamWriter(System.out);
		StringBuffer result = new StringBuffer();
		int buildings = bReader.readInt(); //skip the building count, can be obtained from the token count.
		heights = new String[buildings];
		for (int i = 0; i < buildings; i++) heights[i] = bReader.readInt() + "";
		buildTree();
		int queryCnt = bReader.readInt();
		while(queryCnt-- > 0) {
			int choice = bReader.readInt();
			int left = bReader.readInt(), right = bReader.readInt();
			if (choice == 0)  shiftHeights(1, 0, heights.length - 1, left, right, bReader.readInt());
			else {
				updateQuery(1, 0, heights.length - 1, 0);
				result.append(queryRange(1, 0, heights.length - 1, left, right) + "\n");
			}
			for (int i = 1; i < segTree.length; i++) if (segTree[i] != null) System.out.print(segTree[i].max + " " + segTree[i].value + "\t");
			System.out.println("------------");
		}
		out.print(result);
		out.close();
	}
}

class InputStreamReader {
	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;

	public InputStreamReader (InputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public int readInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public String readString() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuffer res = new StringBuffer();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}

	static boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public String next() {
		return readString();
	}
}

class OutputStreamWriter {
	private final PrintWriter writer;

	public OutputStreamWriter(OutputStream outputStream) {
		writer = new PrintWriter(outputStream);
	}

	public OutputStreamWriter(Writer writer) {
		this.writer = new PrintWriter(writer);
	}

	public void print(Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			if (i != 0)
				writer.print(' ');
			writer.print(objects[i]);
		}
	}

	public void printLine(Object... objects) {
		print(objects);
		writer.println();
	}

	public void close() {
		writer.close();
	}
}