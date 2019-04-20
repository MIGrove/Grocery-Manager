import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
 
public class DatabaseManager {
    
    static Connection connection = null;
    static Statement statement = null;
    
    public DatabaseManager() {
        this.register();
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
                //resultString = resultSet.getString(column);
                
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int numColumns = rsmd.getColumnCount();
                
                for (int i = 1; i <= numColumns; i++) {
                    resultArray.add(resultSet.getString(i));
                }
            }
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        
        return resultArray;
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
        String msAccDB = "GroceryManager.accdb";
        String dbURL = "jdbc:ucanaccess://" + msAccDB;
        
        try {
            connection = DriverManager.getConnection(dbURL);
            statement = connection.createStatement();
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }
    
    public void connect(String msAccDB) {
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