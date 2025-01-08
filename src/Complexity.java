import java.io.IOException;

public class Complexity {

	public static void main(String[] args) throws IOException, IncorrectFile {
		String s = "ExRushHour/GameP";
		String end = ".txt";
		String filename;
		for (int i = 1; i <= 40; i++) {
			if (i <= 9) {
				filename = s + "0" + i + end;
			} else {
				filename = s + i + end;
			}
			int[][] grid = CheckFile.init(filename);
			int g = HeuristicSolving.heuristic(grid);
			HeuristicGridCounter init = new HeuristicGridCounter(grid, 0, g, null, 0);
			long time = System.nanoTime();
			HeuristicGridCounter solution = HeuristicSolving.heuristicSolve(init);
			time = (long) ((System.nanoTime() - time) * Math.pow(10, -6));
			int coups = solution.count;
			int visitednodes = solution.visitednodes;
			System.out.println(filename + ":" + coups + ":" + visitednodes + ":" + time);
		}
		s = "ExRushHour/RushHour";
		for (int i = 1; i <= 6; i++) {
			filename = s + i + end;
			int[][] grid = CheckFile.init(filename);
			int g = HeuristicSolving.heuristic(grid);
			HeuristicGridCounter init = new HeuristicGridCounter(grid, 0, g, null, 0);
			long time = System.nanoTime();
			HeuristicGridCounter solution = HeuristicSolving.heuristicSolve(init);
			time = (long) ((System.nanoTime() - time) * Math.pow(10, -6));
			int coups = solution.count;
			int visitednodes = solution.visitednodes;
			System.out.println(filename + ":" + coups + ":" + visitednodes + ":" + time);
		}
	}
}
