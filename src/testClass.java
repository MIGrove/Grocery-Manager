
public class testClass {
    public static void main(String args[]) {
        
        //System.out.println(new DatabaseManager().getString("SELECT * FROM StoreFullDetails", 1, 1));
        
        Map map = new Map(0, 0);
        
        for (Store s : map.getClosenessSortedStores()) {
            System.out.println(s.toString() + "\tdistance from origin: " + Math.round(map.distanceToStore(s)));
        }
    }
}