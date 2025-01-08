import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class HeuristicSolving {

	public static HeuristicGridCounter heuristicSolve(HeuristicGridCounter init) {
		PriorityQueue<HeuristicGridCounter> queue = new PriorityQueue<HeuristicGridCounter>();
		queue.add(init);
		HashMap<HeuristicGridCounter, HeuristicGridCounter> visited = new HashMap<HeuristicGridCounter, HeuristicGridCounter>();
		int visitednodes = 0;
		while (!queue.isEmpty()) {
			HeuristicGridCounter current = queue.poll();
			HeuristicGridCounter formercurrent = visited.get(current);
			if (formercurrent == null || current.g < formercurrent.g) {
				visitednodes += 1;
				visited.put(current, current);
				int check = NextMoves.searchCar(current.grid, 1)[0];
				if (check == 4) {
					current.visitednodes = visitednodes;
					return (current);
				} else {
					LinkedList<int[][]> moveList = NextMoves.nextPossibleMoves(current.grid);
					for (int[][] next : moveList) {
						int g = current.count + 1 + heuristic(next);
						HeuristicGridCounter nextObject = new HeuristicGridCounter(next, current.count + 1, g, current,
								0);
						HeuristicGridCounter formerNextObject = visited.get(nextObject);
						if (formerNextObject == null || (nextObject.g < formerNextObject.g)) {
							queue.add(nextObject);
						}
					}
				}
			}
		}
		return (null);
	}

	// public static int heuristic(int[][] grid) {
	// return 0;
	// }

	// public static int heuristic(int[][] grid) {
	// int n = grid[0].length;
	// int cursor = n - 1;
	// int count = 0;
	// int currentid = grid[2][cursor];
	// while (currentid != 1) {
	// currentid = grid[2][cursor];
	// if (currentid != 1 && currentid != 0) {
	// count += 1;
	// }
	// cursor -= 1;
	// }
	// return count;
	// }

	public static int heuristic(int[][] grid) {
		int n = grid[0].length;
		int cursor = n - 1;
		LinkedList<Integer> blockingvehicles = new LinkedList<Integer>();
		int currentid = grid[2][cursor];
		while (currentid != 1) {
			currentid = grid[2][cursor];
			if (currentid != 1 && currentid != 0) {
				blockingvehicles.add(currentid);
			}
			cursor -= 1;
		}
		int count = blockingvehicles.size();
		LinkedList<Integer> blockingsecondvehicles = new LinkedList<Integer>();
		while (!blockingvehicles.isEmpty()) {
			int id = blockingvehicles.poll();
			PairListBoolean upperPair = upperBlockingList(grid, id);
			PairListBoolean lowerPair = lowerBlockingList(grid, id);
			int uppercount = 0;
			int lowercount = 0;
			if (upperPair.clear) {
				LinkedList<Integer> upperlist = upperPair.idlist;
				while (!upperlist.isEmpty()) {
					int c = upperlist.poll();
					if (!blockingsecondvehicles.contains(c)) {
						blockingsecondvehicles.add(c);
						uppercount += 1;
					}
				}
			} else {
				uppercount = 10;
			}
			if (lowerPair.clear) {
				LinkedList<Integer> lowerlist = lowerPair.idlist;
				while (!lowerlist.isEmpty()) {
					int c = lowerlist.poll();
					if (!blockingsecondvehicles.contains(c)) {
						blockingsecondvehicles.add(c);
						lowercount += 1;
					}
				}
			} else {
				lowercount = 10;
			}
			count += Math.min(uppercount, lowercount);
		}
		return (count);
	}

	public static PairListBoolean upperBlockingList(int[][] grid, int id) {
		LinkedList<Integer> idlist = new LinkedList<Integer>();
		int[] car = NextMoves.searchCar(grid, id);
		if (car[3] == 3) {
			return new PairListBoolean(idlist, false);
		} else {
			for (int i = 0; i < car[1]; i++) {
				if (grid[i][car[0]] != 0) {
					if (idlist.contains(grid[i][car[0]])) {
						idlist.clear();
						return new PairListBoolean(idlist, false);
					}
					idlist.add(grid[i][car[0]]);
				}
			}
			return new PairListBoolean(idlist, true);
		}
	}

	public static PairListBoolean lowerBlockingList(int[][] grid, int id) {
		LinkedList<Integer> idlist = new LinkedList<Integer>();
		int[] car = NextMoves.searchCar(grid, id);
		for (int i = car[1] + car[3]; i <= 2 + car[3]; i++) {
			if (grid[i][car[0]] != 0) {
				if (idlist.contains(grid[i][car[0]])) {
					return new PairListBoolean(idlist, false);
				}
				idlist.add(grid[i][car[0]]);
			}
		}
		return new PairListBoolean(idlist, true);
	}

	public static void main(String[] args) throws IOException, IncorrectFile {
		int[][] grid = CheckFile.init("ExRushHour/GameP05.txt");
		int g = heuristic(grid);
		long time;
		HeuristicGridCounter init = new HeuristicGridCounter(grid, 0, g, null, 0);
		time = System.nanoTime();
		HeuristicGridCounter solution = heuristicSolve(init);
		time = (long) ((System.nanoTime() - time) * Math.pow(10, -6));
		int coups = solution.count;
		int visitednodes = solution.visitednodes;
		System.out.println("Le nombre de noeuds visites est de : " + visitednodes);
		System.out.println("Le nombre de coups de la solution est de : " + coups);
		System.out.println("Le temps d'execution est de : " + time + " ms");
	}
}
