import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Map {
    private int xOrigin, yOrigin;
    
    public Map(int xOrigin, int yOrigin) {
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
    }
    
    public Map() {  //remember to have these details come from the user
        xOrigin = UserLocationWindow.getOriginPoint().x;
        yOrigin = UserLocationWindow.getOriginPoint().y;
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
    
    public ArrayList<Point> getRouteToPoint(Point storePoint) {
        int horizontalMovement = (storePoint.x - xOrigin);
        int verticalMovement = (storePoint.y - yOrigin);
        
        int currentX = xOrigin, currentY = yOrigin;
        
        System.out.println("current x: " + currentX);
        System.out.println("horizontal movement: " + horizontalMovement);
        System.out.println("vertical movement: " + verticalMovement);
        
        ArrayList<Point> routePoints = new ArrayList<>();
        
        //the only reason for the repition below, as well as the random double, is to make the route a bit more exciting :)
        double randDouble = Math.random();
        
        //if the route must move right (forwards)
        if (horizontalMovement >= 0) {
            for (int i = 0; i < horizontalMovement; i++) {
                routePoints.add(new Point(currentX, currentY));
                currentX++;

                System.out.println("current x: " + currentX);
            }

            //if the route must move downwards
            if (verticalMovement >= 0) {
                for (int i = 0; i < verticalMovement + 1; i++) {
                    routePoints.add(new Point(currentX, currentY));
                    currentY++;
                }
            }
            //if the route must move upwards
            else {
                for (int i = 0; i > verticalMovement - 1; i--) {
                    routePoints.add(new Point(currentX, currentY));
                    currentY--;
                }
            }
        }
        //if the route must move left (backwards)
        else {
            for (int i = 0; i > horizontalMovement; i--) {
                routePoints.add(new Point(currentX, currentY));
                currentX--;

                System.out.println("current x: " + currentX);
            }

            //if the route must move downwards
            if (verticalMovement >= 0) {
                for (int i = 0; i < verticalMovement + 1; i++) {
                    routePoints.add(new Point(currentX, currentY));
                    currentY++;
                }
            }
            //if the route must move upwards
            else {
                for (int i = 0; i > verticalMovement - 1; i--) {
                    routePoints.add(new Point(currentX, currentY));
                    currentY--;
                }
            }
        }
        
        return routePoints;
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
    
    public Store getStoreAt(Point point) {
        for (Store store : getStoreArray()) {
            if (store.getPoint().equals(point)) {
                return store;
            }
        }
        
        return null;
    }
    
    public int getXOrigin() { return xOrigin; }
    
    public int getYOrigin() { return yOrigin; }
    
    public Point getOriginPoint() { return new Point(xOrigin, yOrigin); }
}