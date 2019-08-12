import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Map {
    private int[][] map;
    private static int xOrigin, yOrigin;
    
    public Map(int xOrigin, int yOrigin) {
        map = new int[30][30];
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
    }
    
    public static double calculateDistance(int x1, int y1, int x2, int y2) {
        double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        return distance;
    }
    
    public ArrayList<Store> getClosenessSortedStores() {
        //relies on the Comparable implemented in Store class (compareTo() method)
        ArrayList<Store> sortedArray = getStoreArray();
        Collections.sort(sortedArray);
        
        return sortedArray;
    }
    
    public double distanceToStore(Store store) {
        return calculateDistance(xOrigin, yOrigin, store.getX(), store.getY());
    }
    
    public ArrayList<Store> getStoreArray() {
        String query = "SELECT * FROM StoreFullDetails";
        DatabaseManager dbMan = new DatabaseManager();
        ArrayList<Store> storeArray = new ArrayList<>();
        
        String[][] table = dbMan.convertTableTo2DArray(query);
        
        for (String[] s : table) {
                        
            int storeID = Integer.parseInt(s[0]);            
            String storeName = s[1];            
            int locationID = Integer.parseInt(s[2]);
            int x = Integer.parseInt(s[3]);
            int y = Integer.parseInt(s[4]);
            
            Store store = new Store(storeID, storeName, locationID, x, y);
            storeArray.add(store);
        }
        
        return storeArray;
    }
    
    public static int getXOrigin() { return xOrigin; }
    
    public static int getYOrigin() { return yOrigin; }
}