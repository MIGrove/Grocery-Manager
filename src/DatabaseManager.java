import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
 
public class DatabaseManager {
    /*
    the class will connect to the database when instantiated.
    the endConnection() method must be called after using the class, or
    else the app will begin to run very slowly.
    */
    
    private String msAccDB = "";
    
    Connection connection = null;
    Statement statement = null;
    
    public DatabaseManager() {
        register();
        msAccDB = "GroceryManager.accdb";
        
        connect();
    }
    
    public DatabaseManager(String msAccDB) {
        register();
        this.msAccDB = msAccDB;
        
        connect();
    }
    
    private ResultSet getResultSet(String query) {        
        try {
            return statement.executeQuery(query);
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();            
            return null;
        }
    }
    
    private ResultSetMetaData getMetaData(String query) {
        ResultSet resultSet = getResultSet(query);
        try {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            close(resultSet);
            
            return rsmd;
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
            close(resultSet);
            
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
        try {
            statement.executeUpdate(query);
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }
    
    public int getNumColumns(String query) {
        int resultInt = -1;
        
        try {
            resultInt = getMetaData(query).getColumnCount();
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
                
        return resultInt;
    }
    
    public ArrayList<String> getColumnNames(String query) {
        ArrayList<String> columnNames = new ArrayList<>();
        
        try {
            ResultSetMetaData rsmd = getMetaData(query);
            
            int columnCount = rsmd.getColumnCount(); //faster than calling getNumColumns() if there is already meta data.
            
            for (int i=1; i <= columnCount; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }
            
            return columnNames;
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
            return null;
        }                
    }
    
    public int getNumRows(String query) {
        int resultInt = 0;
        ResultSet resultSet = getResultSet(query);
        
        try {
            while (resultSet.next()) {  // must double check if this actually gets the correct number of rows
                resultInt++;            // because next() is not called inside of the loop
            }
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        
        close(resultSet);
        
        return resultInt;
    }
    
    public boolean hasRows(String query) {
        return getNumRows(query) != 0;
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
    private void connect() {
        String dbURL = "jdbc:ucanaccess://" + msAccDB + ";keepMirror=/dbMirror/";
        
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