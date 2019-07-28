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
    
    public String getString(String query, int row, int column) {
        return getRow(query, row).get(column - 1);
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
            
            for (int i=0; i < 3; i++) {
                resultArray.add("errorString");
            }
        }
        
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
            return getNumColumns(resultSet);
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        
        return resultInt;
    }
    
    public ArrayList<String> getColumnNames(ResultSet resultSet) {
        ArrayList<String> columnNames = new ArrayList<>();
        
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
        
        return columnNames;
    }
    
    public static ArrayList<String> getColumnNames(String query) {
        /*
            without this line, the method will fail (this is because
            static methods here need to run the connect() method in a
            new instance of DatabaseManager
        */
        new DatabaseManager().connect();
        
        ArrayList<String> columnNames = new ArrayList<>();
        
        try {
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            
            for (int i=1; i <= columnCount; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        
        return columnNames;
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
        
        return resultInt;
    }
    
    public int getNumRows(String query) {
        int resultInt = 0;
        
        try {
            ResultSet resultSet = statement.executeQuery(query);
            return getNumRows(resultSet);
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        
        return resultInt;
    }
    
    public static boolean hasRows(ResultSet resultSet) {
        return new DatabaseManager().getNumRows(resultSet) != 0;
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