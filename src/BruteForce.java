import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

public class BruteForce {

	public static GridCounter bruteSolve(GridCounter init) {
		LinkedList<GridCounter> queue = new LinkedList<GridCounter>();
		queue.add(init);
		HashSet<GridCounter> visited = new HashSet<GridCounter>();
		int visitednodes = 0;
		while (!queue.isEmpty()) {
			GridCounter current = queue.poll();
			if (!visited.contains(current)) {
				visitednodes += 1;
				visited.add(current);
				int check = NextMoves.searchCar(current.grid, 1)[0];
				if (check == 4) {
					current.visitednodes = visitednodes;
					return (current);
				} else {
					LinkedList<int[][]> moveList = NextMoves.nextPossibleMoves(current.grid);
					for (int[][] next : moveList) {
						GridCounter nextObject = new GridCounter(next, current.count + 1, current, 0);
						if (!visited.contains(nextObject)) {
							queue.addLast(nextObject);
						}
					}
				}
			}
		}
		return (null);
	}

	public static void main(String[] args) throws IOException, IncorrectFile {
		int[][] grid = CheckFile.init("ExRushHour/GameP37.txt");
		GridCounter init = new GridCounter(grid, 0, null, 0);
		long time = System.nanoTime();
		GridCounter solution = bruteSolve(init);
		time = (long) ((System.nanoTime() - time) * Math.pow(10, -6));
		int coups = solution.count;
		int visitednodes = solution.visitednodes;
		System.out.println("Le nombre de noeuds visites est de : " + visitednodes);
		System.out.println("Le nombre de coups de la solution est de : " + coups);
		System.out.println("Le temps d'execution est de : " + time + " ms");
	}

}
