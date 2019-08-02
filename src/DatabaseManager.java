import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
 
public class DatabaseManager {
    
    private static String msAccDB = "";
    
    Connection connection = null;
    Statement statement = null;
    
    public DatabaseManager() {
        this.register();
        this.msAccDB = "GroceryManager.accdb";
    }
    
    public DatabaseManager(String msAccDB) {
        this.register();
        this.msAccDB = msAccDB;
    }
    
    private ResultSet getResultSet(String query) {
        this.connect();
        
        try {
            ResultSet resultSet = statement.executeQuery(query);
            close(statement);
            close(connection);
            
            return resultSet;
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
            close(statement);
            close(connection);
            
            return null;
        }
    }
    
    public String getString(String query, int row, int column) {
        return getRow(query, row).get(column - 1);
    }
    
    public ArrayList<String> getRow(String query, int row) {
        ArrayList<String> resultArray = new ArrayList<>();
        ResultSet resultSet = getResultSet(query);
        
        try {            
            if (resultSet.next()) {
                for(int i = 1; i <= row - 1; i++) {
                    resultSet.next();
                }
                
                for (int i = 1; i <= getNumColumns(query); i++) {
                    resultArray.add(resultSet.getString(i));
                }
            }
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
            
            for (int i=0; i < 3; i++) {
                resultArray.add("errorString");
            }
        }
        
        close(resultSet);
        
        return resultArray;
    }
    
    public void executeUpdate(String query) {
        this.connect();
        
        try {
            statement.executeUpdate(query);
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        
        close(statement);
        close(connection);
    }
    
    public int getNumColumns(String query) {
        int resultInt = -1;
        ResultSet resultSet = getResultSet(query);
        
        try {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            resultInt = rsmd.getColumnCount();
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        
        close(resultSet);
        
        return resultInt;
    }
    
    public ArrayList<String> getColumnNames(String query) {
        ArrayList<String> columnNames = new ArrayList<>();
        ResultSet resultSet = getResultSet(query);
        
        try {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            
            for (int i=1; i <= columnCount; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        
        close(resultSet);
        
        return columnNames;
    }
    
    public int getNumRows(String query) {
        int resultInt = 0;
        ResultSet resultSet = getResultSet(query);
        
        try {
            while (resultSet.next()) {
                resultInt++;
            }
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        
        close(resultSet);
        
        return resultInt;
    }
    
    public static boolean hasRows(String query) {
        return new DatabaseManager().getNumRows(query) != 0;
    }
    
    private void register() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch (ClassNotFoundException cnfex) {
            System.err.println("Problem loading/registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }
    }
    
    //default connect method uses GroceryManager database
    public void connect() {
        String dbURL = "jdbc:ucanaccess://" + msAccDB;
        
        try {
            connection = DriverManager.getConnection(dbURL);
            statement = connection.createStatement();
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }
    
    public void close(ResultSet resultSet) {
        try {
            resultSet.close();
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }
    
    public void close(Statement statement) {
        try {
            statement.close();
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }
    
    public void close(Connection connection) {
        try {
            connection.close();
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }
    
    public void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }
}