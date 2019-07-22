
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableModelMaker {
    private DefaultTableModel model;
    
    public TableModelMaker(JTable leaderboard, String query, String database) {
        model = (DefaultTableModel) leaderboard.getModel();
        model.setRowCount(0);
        
        for (int i=0; i<convertTableTo2DArray(query, database).length; i++) {
            model.addRow((convertTableTo2DArray(query, database))[i]);
        }
        
        model.fireTableDataChanged();
    }
    
    private static String[][] convertTableTo2DArray(String query, String database) {
        DatabaseManager dbMan = new DatabaseManager(database);
        int numColumns = dbMan.getNumColumns(query);
        int numRows = dbMan.getNumRows(query);
        
        String[][] data = new String[numRows][numColumns];
        
        for (int i = 1; i < numRows; i++) {
            for (int j = 1; j < numColumns; j++) {
                data[i][j] = dbMan.getString(query, j, i);
            }
        }
        
        return data;
    }
    
    public DefaultTableModel getDefaultModel() {
        return model;
    }
}