import java.awt.Point;

public class Store implements Comparable<Store> {
    //tblStoreLocations in GroceryManager.accdb
    
    private int locationID, storeID, x, y;
    private String storeName;
    
    public Store(int storeID, String storeName, int locationID, int x, int y) {
        this.storeID = storeID;
        this.storeName = storeName;
        this.locationID = locationID;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int compareTo(Store comparedStore) {
        int xOrigin = new Map().getXOrigin();
        int yOrigin = new Map().getYOrigin();
        
        double distanceToStore = Map.calculateDistance(this.getX(), this.getY(), xOrigin, yOrigin);
        double distanceToComparedStore = Map.calculateDistance(comparedStore.getX(), comparedStore.getY(), xOrigin, yOrigin);
        
        return (distanceToStore < distanceToComparedStore ? -1 :
                (distanceToStore == distanceToComparedStore ? 0 : 1));
    }
    
    @Override
    public String toString() {
        return ("storeID: " + storeID + "\tstoreName: " + storeName + "\t\tlocationID: " + locationID
                + "\tx: " + x + "\ty: " + y);
    }
    
    public int getX() { return x; }
    
    public int getY() { return y; }
    
    public Point getPoint() { return new Point(x, y); }
    
    public String getPointString() { return ("(" + x + " ; " + y + ")"); }
    
    public String getStoreName() { return storeName; }
}