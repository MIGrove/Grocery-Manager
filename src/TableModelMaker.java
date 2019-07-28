
import java.util.Arrays;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableModelMaker {
    private DefaultTableModel model;
    private final JTable leaderboard;
    static DatabaseManager dbMan;
    
    public TableModelMaker(JTable leaderboard) {
        this.leaderboard = leaderboard;
        dbMan = new DatabaseManager("GroceryManager.accdb");
    }
    
    public void updateTable(String query) {
        model = (DefaultTableModel) leaderboard.getModel();
        //gets the "height" of the access table, and makes the new table match it
        model.setRowCount(0);
        //gets the "width" of the access table, and makes the new table match it
        model.setColumnCount(0);
        
        for (String columnName : new DatabaseManager().getColumnNames(query)) {
            model.addColumn(columnName);
        }
        
        //adds rows to new table
        for (int i=0; i<convertTableTo2DArray(query).length; i++) {
            model.addRow((convertTableTo2DArray(query))[i]);
        }
        
        model.fireTableDataChanged();
    }
    
    public static String[][] convertTableTo2DArray(String query) {
        
        int numColumns = dbMan.getNumColumns(query);
        int numRows = dbMan.getNumRows(query);
        //number of rows and columns found is correct. no problem here.
        
        String[][] data = new String[numRows][numColumns];
        
        for (int i=0; i < numRows; i++) {
            for (int j=0; j < numColumns; j++) {
                data[i][j] = dbMan.getString(query, i+1, j+1);
            }
        }
        return data;
    }
    
    public DefaultTableModel getModel() {
        return model;
    }
}