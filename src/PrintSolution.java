import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class PrintSolution {

	public static void main(String[] args) throws IOException, IncorrectFile, InterruptedException {
		int[][] grid = CheckFile.init("ExRushHour/RushHour6.txt");
		GridCounter init = new GridCounter(grid, 0, null, 0);
		GridCounter solution = BruteForce.bruteSolve(init);
		int visitednodes = solution.visitednodes;
		System.out.println("Voici la solution, le nombre de noeuds visites est de : " + visitednodes);
		LinkedList<GridCounter> list = new LinkedList<GridCounter>();
		while (solution.precedent != null) {
			list.addFirst(solution);
			solution = solution.precedent;
		}
		String title;
		int stepcounter = 0;
		JTableBasiqueAvecModeleStatique frame;
		while (!list.isEmpty()) {
			GridCounter current = list.pop();
			TimeUnit.MILLISECONDS.sleep(500);
			stepcounter += 1;
			title = "Etape n°" + stepcounter;
			frame = new JTableBasiqueAvecModeleStatique(current.grid, title);
			frame.setVisible(true);
		}

	}
}
