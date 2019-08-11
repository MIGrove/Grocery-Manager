import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class testClass {
    static String result = "";
    
    public static void main(String args[]) throws InterruptedException, ExecutionException {
        
        TableGenWorker worker = new TableGenWorker("SELECT * FROM tblUsers");
        worker.execute();
        
        while (true) {
            
            if (worker.isDone()) {
                System.out.println(result);
            }
            else {
                System.out.println("worker not done yet");
            }
        }
    }
        
    
    public void setResult(String result) {
        this.result = result;
    }
}