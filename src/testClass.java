public class testClass {
    public static void main(String[] args) {
        
        String statement = "SELECT * FROM tblItems;";
        DatabaseManager dbMan = new DatabaseManager(statement);
        
        int itemID = dbMan.getResultInt(0);
        String itemName = dbMan.getResultString(0);
        
        System.out.println(itemID + "\t" + itemName);
    }
}