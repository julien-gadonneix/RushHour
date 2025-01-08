import java.awt.Color;

import javax.swing.table.AbstractTableModel;

public class ModeleStatique extends AbstractTableModel {
    private final Object[][] donnees;

    private final String[] entetes;

    public ModeleStatique(Object[][] donnees, String[] entetes) {
        super();
        this.donnees = donnees;
        this.entetes = entetes;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return Color.class;
    }

    public int getRowCount() {
        return donnees.length;
    }

    public int getColumnCount() {
        return entetes.length;
    }

    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return donnees[rowIndex][columnIndex];
    }
}