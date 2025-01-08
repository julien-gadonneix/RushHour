import java.awt.Color;
import java.io.*;

public class CheckFile {

	public static int[][] init(String path) throws IOException, IncorrectFile {
		File file = new File(path);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
		String l0 = br.readLine();
		int l = Integer.parseInt(l0);
		int[][] grid = new int[l][l];
		int n = Integer.parseInt(br.readLine());
		for (int k = 0; k < n; k++) {
			String line = br.readLine();
			int id = k + 1;
			if (k > 8) {
				int length = Character.getNumericValue(line.charAt(5));
				char orientation = line.charAt(3);
				int o = (orientation == 'h' ? 0 : 1);
				int j = Character.getNumericValue(line.charAt(7)) - 1;
				int i = Character.getNumericValue(line.charAt(9)) - 1;
				for (int m = 0; m < length; m++) {
					if (grid[i + m * o][j + m * (1 - o)] == 0) {
						grid[i + m * o][j + m * (1 - o)] = id;
					} else {
						throw new IncorrectFile("The file isn't correct");
					}
				}
			} else {
				int length = Character.getNumericValue(line.charAt(4));
				char orientation = line.charAt(2);
				int o = (orientation == 'h' ? 0 : 1);
				int j = Character.getNumericValue(line.charAt(6)) - 1;
				int i = Character.getNumericValue(line.charAt(8)) - 1;
				for (int m = 0; m < length; m++) {
					if (grid[i + m * o][j + m * (1 - o)] == 0) {
						grid[i + m * o][j + m * (1 - o)] = id;
					} else {
						throw new IncorrectFile("The file isn't correct");
					}
				}
			}
		}
		br.close();
		return (grid);
	}

	public static Object[][] colorConvert(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		Object[][] donnees = new Object[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int id = grid[i][j];
				if (id == 0) {
					donnees[i][j] = Color.white;
				} else if (id == 1) {
					donnees[i][j] = Color.red;
				} else if (id == 2) {
					donnees[i][j] = Color.yellow;
				} else if (id == 3) {
					donnees[i][j] = Color.green;
				} else if (id == 4) {
					donnees[i][j] = Color.blue;
				} else if (id == 5) {
					donnees[i][j] = Color.magenta;
				} else if (id == 6) {
					donnees[i][j] = Color.cyan;
				} else if (id == 7) {
					donnees[i][j] = Color.black;
				} else if (id == 8) {
					donnees[i][j] = Color.gray;
				} else {
					int a = (10 * id) % 255;
					int b = (20 * id) % 255;
					int c = (30 * id) % 255;
					Color color = new Color(a, b, c);
					donnees[i][j] = color;
				}
			}
		}
		return donnees;
	}

	public static void main(String[] args) throws IOException, IncorrectFile {
		int[][] grid = init("Init.txt");
		int n = grid.length;
		int m = grid[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
	}

}
