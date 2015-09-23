package com.fayuan.algorithm;

import java.io.*;
import java.util.*;

public class BFS {
	static int N;
	static int[][] map, to = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	static class Node {
		int x, y, step;

		public Node(int x, int y, int step) {
			this.x = x;
			this.y = y;
			this.step = step;
		}
	}

	public static void main(String[] args) throws IOException {
		Reader r = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(r);

		while (st.nextToken() != StreamTokenizer.TT_EOF) {
			N = (int) st.nval;
			map = new int[N][N];
			System.out.println(N);
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					st.nextToken();
					map[i][j] = (int) st.nval;
				}
			System.out.println(bfs(0, 0));
		}
	}

	private static int bfs(int x, int y) {
		if (map[0][0] == 1 || map[N - 1][N - 1] == 1)
			return -1;
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(new Node(x, y, 0));
		map[x][y] = 1;
		while (!queue.isEmpty()) {
			Node p = queue.poll();
			if (p.x == N - 1 && p.y == N - 1)
				return p.step;
			for (int k = 0; k < to.length; k++) {
				int i = p.x + to[k][0];
				int j = p.y + to[k][1];
				if (ok(i, j) && map[i][j] == 0) {
					map[i][j] = 1;
					queue.offer(new Node(i, j, p.step + 1));
				}
			}
		}
		return -1;
	}

	private static boolean ok(int x, int y) {
		return (x >= 0 && x < N) && (y >= 0 && y < N);
	}
}