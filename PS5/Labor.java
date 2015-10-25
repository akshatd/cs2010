
// Copy paste this Java Template and save it as "Labor.java"
import java.util.*;
import java.io.*;

// write your matric number here: A0103516U
// write your name here: Akshat Dubey
// write list of collaborators here: Suranjana Sengupta
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line)

class Labor {
	private int V; // number of vertices in the graph (number of junctions in
					// Singapore map)
	private int Q; // number of queries
	private Vector<Vector<IntegerPair>> AdjList; // the weighted graph (the
													// Singapore map), the
													// length of each edge
													// (road) is stored here
													// too, as the weight of
													// edge

	// if needed, declare a private data structure here that
	// is accessible to all methods in this class
	// --------------------------------------------

	private int[][] distanceList;
	private static final int SLEEPYEIGHT = 1000000000;

	// --------------------------------------------

	public Labor() {
		// Write necessary code during construction
		//
		// write your answer here

	}

	void PreProcess() {
		// Write necessary code to preprocess the graph, if needed
		//
		// write your answer here
		// -------------------------------------------------------------------------

		// -------------------------------------------------------------------------
	}

	int Query(int s, int t, int k) {
		int ans = -1;

		// You have to report the shortest path from Steven and Grace's current
		// position s
		// to reach their chosen hospital t, output -1 if t is not reachable
		// from s
		// with one catch: this path cannot use more than k vertices
		//
		// PS: this query means different thing for the Subtask D (R-option)
		//
		// write your answer here

		distanceList = new int[V][V+1];
		for (int x = 0; x < V; x++) {
			int[] tempArray = new int[V+1];
			Arrays.fill(tempArray, SLEEPYEIGHT);
			distanceList[x] = tempArray;
		}

		ans = Dijkstra(s, t, k);
		
		// -------------------------------------------------------------------------

		return ans;
	}

	// You can add extra function if needed
	// --------------------------------------------

	private int Dijkstra(int source,int destination, int maxHops) {
		int minDist = -1;
		distanceList[source][1] = 0;

		PriorityQueue<IntegerTriple> DJQueue = new PriorityQueue<IntegerTriple>();
		DJQueue.add(new IntegerTriple(distanceList[source][1], 1, source));
		
		while (!DJQueue.isEmpty()) {
			IntegerTriple front = DJQueue.remove();
			int dist = front.first();
			int hops = front.second();
			int dest = front.third();
			if (distanceList[dest][hops] == dist) {
				if (dest == destination) {
					return dist;
				}else if(hops < maxHops){
				Vector<IntegerPair> neighbors = AdjList.get(dest);
				for (int i = 0; i < neighbors.size(); i++) {
					IntegerPair v = neighbors.get(i);
					int nbrDist = v.second();
					int nbrHops = hops+1;
					int nbrDest	= v.first();
					if (dist + nbrDist < distanceList[nbrDest][nbrHops]) {
						distanceList[nbrDest][nbrHops] = dist + nbrDist;
						DJQueue.add(new IntegerTriple(distanceList[nbrDest][nbrHops], nbrHops, nbrDest));
						}
					}
				}
			}
		}
		return minDist;
	}

	// --------------------------------------------

	void run() throws Exception {
		// you can alter this method if you need to do so
		IntegerScanner sc = new IntegerScanner(System.in);
		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int TC = sc.nextInt(); // there will be several test cases
		while (TC-- > 0) {
			V = sc.nextInt();

			// clear the graph and read in a new graph as Adjacency List
			AdjList = new Vector<Vector<IntegerPair>>();
			for (int i = 0; i < V; i++) {
				AdjList.add(new Vector<IntegerPair>());

				int k = sc.nextInt();
				while (k-- > 0) {
					int j = sc.nextInt(), w = sc.nextInt();
					AdjList.get(i).add(new IntegerPair(j, w)); // edge (road)
																// weight (in
																// minutes) is
																// stored here
				}
			}

			PreProcess(); // optional

			Q = sc.nextInt();
			while (Q-- > 0)
				pr.println(Query(sc.nextInt(), sc.nextInt(), sc.nextInt()));

			if (TC > 0)
				pr.println();
		}

		pr.close();
	}

	public static void main(String[] args) throws Exception {
		// do not alter this method
		Labor ps5 = new Labor();
		ps5.run();
	}
}

class IntegerScanner { // coded by Ian Leow, using any other I/O method is not
						// recommended
	BufferedInputStream bis;

	IntegerScanner(InputStream is) {
		bis = new BufferedInputStream(is, 1000000);
	}

	public int nextInt() {
		int result = 0;
		try {
			int cur = bis.read();
			if (cur == -1)
				return -1;

			while ((cur < 48 || cur > 57) && cur != 45) {
				cur = bis.read();
			}

			boolean negate = false;
			if (cur == 45) {
				negate = true;
				cur = bis.read();
			}

			while (cur >= 48 && cur <= 57) {
				result = result * 10 + (cur - 48);
				cur = bis.read();
			}

			if (negate) {
				return -result;
			}
			return result;
		} catch (IOException ioe) {
			return -1;
		}
	}
}

class IntegerPair implements Comparable<IntegerPair> {
	Integer _first, _second;

	public IntegerPair(Integer f, Integer s) {
		_first = f;
		_second = s;
	}

	public int compareTo(IntegerPair o) {
		if (!this.second().equals(o.second()))
			return this.second() - o.second();
		else
			return this.first() - o.first();
	}

	Integer first() {
		return _first;
	}

	Integer second() {
		return _second;
	}
}

class IntegerTriple implements Comparable<IntegerTriple> {
	Integer _first, _second, _third;

	public IntegerTriple(Integer f, Integer s, Integer t) {
		_first = f;
		_second = s;
		_third = t;
	}

	public int compareTo(IntegerTriple o) {
		if (!this.first().equals(o.first()))
			return this.first() - o.first();
		else if (!this.second().equals(o.second()))
			return this.second() - o.second();
		else
			return this.third() - o.third();
	}

	Integer first() {
		return _first;
	}

	Integer second() {
		return _second;
	}

	Integer third() {
		return _third;
	}
}