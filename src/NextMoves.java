import java.util.LinkedList;

public class NextMoves {
	public static LinkedList<int[][]> nextPossibleMoves(int[][] current) {
		LinkedList<int[][]> list = new LinkedList<int[][]>();
		int id = 1;
		int[] res = searchCar(current, id);
		int x, y, o, l;
		while (res != null) {
			x = res[0];
			y = res[1];
			o = res[2];
			l = res[3];
			if (o == 2) {
				int s = 1;
				while ((y + l - 1 + s) < current[0].length && current[y + l - 1 + s][x] == 0) {
					int[][] M = newMove(current, id, x, y, o, l, s);
					list.add(M);
					s += 1;
				}
				s = -1;
				while ((y + s) >= 0 && current[y + s][x] == 0) {
					int[][] M = newMove(current, id, x, y, o, l, s);
					list.add(M);
					s -= 1;
				}
			} else {
				int s = 1;
				while ((x + l - 1 + s) < current.length && current[y][x + l - 1 + s] == 0) {
					int[][] M = newMove(current, id, x, y, o, l, s);
					list.add(M);
					s += 1;
				}
				s = -1;
				while ((x + s) >= 0 && current[y][x + s] == 0) {
					int[][] M = newMove(current, id, x, y, o, l, s);
					list.add(M);
					s -= 1;
				}
			}
			id += 1;
			res = searchCar(current, id);
		}
		return list;
	}

	public static int[] searchCar(int[][] current, int id) {
		int n = current.length;
		int m = current[0].length;
		int[] res = new int[4];
		boolean check = false;
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < m; x++) {
				if (current[y][x] == id) {
					check = true;
					res[0] = x;
					res[1] = y;
					int o;
					int length;
					if (y == n - 1) {
						o = 1;
						if (x + 2 < m) {
							if (current[y][x + 2] == id) {
								length = 3;
							} else {
								length = 2;
							}
						} else {
							length = 2;
						}
					} else if (x == m - 1) {
						o = 2;
						if (y + 2 < n) {
							if (current[y + 2][x] == id) {
								length = 3;
							} else {
								length = 2;
							}
						} else {
							length = 2;
						}
					} else {
						if (current[y][x + 1] == id) {
							o = 1;
							if (x + 2 < m) {
								if (current[y][x + 2] == id) {
									length = 3;
								} else {
									length = 2;
								}
							} else {
								length = 2;
							}
						} else {
							o = 2;
							if (y + 2 < n) {
								if (current[y + 2][x] == id) {
									length = 3;
								} else {
									length = 2;
								}
							} else {
								length = 2;
							}
						}
					}
					res[2] = o;
					res[3] = length;
					return res;
				}
			}
		}
		if (check) {
			return res;
		}
		return null;
	}

	public static int[][] newMove(int[][] current, int id, int x, int y, int o, int l, int s) {
		int[][] Move = new int[current.length][current[0].length];
		for (int k = 0; k < current.length; k++) {
			for (int p = 0; p < current[0].length; p++) {
				Move[k][p] = current[k][p];
			}
		}
		if (o == 1) {
			for (int i = 0; i < l; i++) {
				Move[y][x + i] = 0;
			}
			for (int i = 0; i < l; i++) {
				Move[y][x + s + i] = id;
			}
		} else {
			for (int i = 0; i < l; i++) {
				Move[y + i][x] = 0;
			}
			for (int i = 0; i < l; i++) {
				Move[y + s + i][x] = id;
			}
		}
		return Move;
	}

}
