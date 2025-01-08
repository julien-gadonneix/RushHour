import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTableBasiqueAvecModeleStatique extends JFrame {
    public JTableBasiqueAvecModeleStatique(String path, String title) throws IOException, IncorrectFile {
        super();

        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int[][] grid = CheckFile.init(path);
        Object[][] donnees = CheckFile.colorConvert(grid);
        int n = grid.length;
        String[] entetes = new String[n];
        JTable tableau = new JTable(new ModeleStatique(donnees, entetes));
        tableau.setTableHeader(null);
        tableau.setDefaultRenderer(Color.class, new ColorCellRenderer());
        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

        pack();
    }

    public JTableBasiqueAvecModeleStatique(int[][] grid, String title) throws IOException, IncorrectFile {
        super();

        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Object[][] donnees = CheckFile.colorConvert(grid);
        int n = grid.length;
        String[] entetes = new String[n];
        JTable tableau = new JTable(new ModeleStatique(donnees, entetes));
        tableau.setTableHeader(null);
        tableau.setDefaultRenderer(Color.class, new ColorCellRenderer());
        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

        pack();
    }
}