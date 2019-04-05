import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseManager {
    
    static ArrayList<String> resStrings = new ArrayList<>();
    static ArrayList<Integer> resIntegers = new ArrayList<>();
    static ArrayList<Double> resDoubles = new ArrayList<>();
    static String statementSQL;
    
    public DatabaseManager (String statementSQL) {
        DatabaseManager.statementSQL = statementSQL;
        getResults();
    }
    
    /*
    have one of these (the below function) for all three types of arraylists.
    these should give the results back to the user one data item by one, when
    they choose to use it/them.
    */
    
    private String getResultString(int index) {
        return resStrings.get(index);
    }
    
    private int getResultInt(int index) {
        return resIntegers.get(index);
    }
    
    private double getResultDouble(int index) {
        return resDoubles.get(index);
    }
        
    
    private static void getResults() {

        // variables
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        // Step 1: Loading or registering Oracle JDBC driver class
        try {

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) {

            System.err.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }

        // Step 2: Opening database connection
        try {

            String msAccDB = "GroceryManager.accdb";
            String dbURL = "jdbc:ucanaccess://" + msAccDB; 

            // Step 2.A: Create and get connection using DriverManager class
            connection = DriverManager.getConnection(dbURL); 

            // Step 2.B: Creating JDBC Statement 
            statement = connection.createStatement();

            // Step 2.C: Executing SQL & retrieve data into ResultSet
            resultSet = statement.executeQuery(statementSQL);

            // 
            while(resultSet.next()) {
                int columns = getTotalColumns(resultSet);
                
                for (int i=1; i<=columns; i++) {
                    switch (getColumnType(resultSet, i)) {
                        case "NVARCHAR":
                            resStrings.add(resultSet.getString(i));
                            break;
                        
                        case "INTEGER":
                            resIntegers.add(resultSet.getInt(i));
                            break;
                            
                        case "DOUBLE":
                            resDoubles.add(resultSet.getDouble(i));
                            break;
                            
                        default:
                            System.err.println("Column type is not compatible");
                            break;
                    }
                }
                /*
                retString  += resultSet.getInt(1) + " "
                            + resultSet.getString(2) + " "
                            + resultSet.getString(3);
                */
                boolean allColumnsChecked = false;
                int column = 1;
                
            }
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {

            // Step 3: Closing database connection
            try {
                if(null != connection) {

                    // cleanup resources, once after processing
                    resultSet.close();
                    statement.close();

                    // and then finally close connection
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
    }
    
    public static int getTotalColumns(ResultSet resultSet) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columns = resultSetMetaData.getColumnCount();
        
        return columns;
    }
    
    public static String getColumnType(ResultSet resultSet, int column) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        System.out.println(resultSetMetaData.getColumnType(column));
        String type = "";
        
        switch(resultSetMetaData.getColumnType(column)) {
            case -9: type = "NVARCHAR"; break;
            case 4: type = "INTEGER"; break;
            case 8: type = "DOUBLE"; break;
        }
        
        return type;
    }
}