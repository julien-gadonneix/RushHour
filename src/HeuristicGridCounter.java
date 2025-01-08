import java.util.Arrays;

public class HeuristicGridCounter implements Comparable<HeuristicGridCounter> {
	int[][] grid;
	int count;
	int g;
	HeuristicGridCounter precedent;
	int visitednodes;

	HeuristicGridCounter(int[][] grid, int count, int g, HeuristicGridCounter precedent, int visitednodes) {
		this.grid = grid;
		this.count = count;
		this.g = g;
		this.precedent = precedent;
		this.visitednodes = visitednodes;
	}

	@Override
	public boolean equals(Object o) {
		HeuristicGridCounter that = (HeuristicGridCounter) o;
		int n = this.grid.length;
		int m = this.grid[0].length;
		int n1 = that.grid.length;
		int m1 = that.grid[0].length;
		if ((n != n1) || (m != m1)) {
			return false;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (this.grid[i][j] != that.grid[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		int n = this.grid.length;
		int h = 0;
		for (int i = 0; i < n; i++) {
			h += Arrays.hashCode(this.grid[i]);
		}
		return h;
	}

	@Override
	public int compareTo(HeuristicGridCounter other) {
		int g1 = this.g;
		int g2 = other.g;
		if (g1 > g2) {
			return 1;
		}
		if (g1 < g2) {
			return -1;
		} else {
			int c1 = this.count;
			int c2 = other.count;
			if (c1 > c2) {
				return 1;
			}
			if (c1 < c2) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}
