import javax.swing.*;

public class testClass {
    public static void main(String args[]) {
        
        DatabaseManager dbMan = new DatabaseManager();
        
        //testing single string output
        System.out.println(dbMan.getString("SELECT * FROM tblItems", 3, 1));
        System.out.println(dbMan.getString("SELECT * FROM tblItems", 3, 2));
        System.out.println(dbMan.getString("SELECT * FROM tblStores", 2, 1));
        
        //testing multiple string output
        System.out.println(dbMan.getRow("SELECT * FROM tblItems", 1));
        System.out.println(dbMan.getRow("SELECT * FROM tblItems", 2));
        System.out.println(dbMan.getRow("SELECT * FROM tblStores WHERE StoreName = 'Woolworths';", 1));
        
        //testing TableModelMaker class

        //TableModelMaker tableModelMaker = new TableModelMaker(table, "SELECT * FROM tblItems");
        
    }
}