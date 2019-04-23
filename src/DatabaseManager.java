import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
 
public class DatabaseManager {
    
    private static String msAccDB = "";
    
    static Connection connection = null;
    static Statement statement = null;
    
    public DatabaseManager() {
        this.register();
        this.msAccDB = "GroceryManager.accdb";
    }
    
    public DatabaseManager(String msAccDB) {
        this.register();
        this.msAccDB = msAccDB;
    }
    
    public String getString(String query, int column, int row) {
        String resultString = "errorString";
        this.connect();
        
        try {
            ResultSet resultSet = statement.executeQuery(query);
            
            if (resultSet.next()) {
                for(int i = 1; i <= row - 1; i++) {
                    resultSet.next();
                }
                resultString = resultSet.getString(column);
            }
            
            this.close(resultSet, statement, connection);
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        
        return resultString;
    }
    
    public ArrayList<String> getRow(String query, int row) {
        ArrayList<String> resultArray = new ArrayList<>();
        this.connect();
        
        try {
            ResultSet resultSet = statement.executeQuery(query);
            
            if (resultSet.next()) {
                for(int i = 1; i <= row - 1; i++) {
                    resultSet.next();
                }
                
                for (int i = 1; i <= getNumColumns(resultSet); i++) {
                    resultArray.add(resultSet.getString(i));
                }
            }
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        
        return resultArray;
    }
    
    public int getNumColumns(ResultSet resultSet) {
        int resultInt = -1;
        
        try {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            resultInt = rsmd.getColumnCount();
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        
        return resultInt;
    }
    
    public int getNumColumns(String query) {
        int resultInt = -1;
        this.connect();
        
        try {
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            resultInt = rsmd.getColumnCount();
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        
        return resultInt;
    }
    
    public int getNumRows(ResultSet resultSet) {
        int resultInt = 0;
        
        try {
            while (resultSet.next()) {
                resultInt++;
            }
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        
        if (resultInt == 0) {
            System.out.println("No rows found!");
        }
        
        return resultInt;
    }
    
    public int getNumRows(String query) {
        int resultInt = 0;
        
        try {
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                resultInt++;
            }
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        
        if (resultInt == 0) {
            System.out.println("No rows found!");
        }
        
        return resultInt;
    }
    
    public void register() {
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