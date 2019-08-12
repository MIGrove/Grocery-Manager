import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public final class TableModelMaker {  //this class contains only static methods
    
    public static void updateTable(String query, boolean verbose, JTable leaderboard) {        
        DefaultTableModel model = (DefaultTableModel) leaderboard.getModel();
        //gets the "height" of the access table, and makes the new table match it
        model.setRowCount(0);
        //gets the "width" of the access table, and makes the new table match it
        model.setColumnCount(0);
        
        if (verbose) System.out.print("Columns generated [");
        
        for (String columnName : new DatabaseManager().getColumnNames(query)) {
            model.addColumn(columnName);
            if (verbose) System.out.print("|");
        }
        
        if (verbose) System.out.print("]\nRows generated: [");
        
        String[][] cellContent = new DatabaseManager().convertTableTo2DArray(query);
        
        //adds rows to new table
        for (int i=0; i<cellContent.length; i++) {
            //remember to stop calling convertTableTo2DArray over and over again -- makes the app very slow
            model.addRow(cellContent[i]);
            if (verbose) System.out.print("|");
        }
        if (verbose ) System.out.println("]");
        
        model.fireTableDataChanged();
    }
}