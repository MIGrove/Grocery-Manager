public class TableModelMaker {
    
    public TableModelMaker() {
        
    }
    
    public String[][] convertTableTo2DArray(String query, String database) {
        DatabaseManager dbMan = new DatabaseManager(database);
        int numColumns = dbMan.getNumColumns(query);
        int numRows = dbMan.getNumRows(query);
        
        String[][] data = new String[numColumns][numRows];
        
        for (int i = 1; i <= numRows; i++) {
            for (int j = 1; j <= numColumns; j++) {
                data[i][j] = dbMan.getString(query, j, i);
            }
        }
        
        return data;
    }
}