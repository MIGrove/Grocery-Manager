import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class TableGenWorker extends SwingWorker<String, Void> {
    private String query;
    private String outputString;
    
    public TableGenWorker (String query) {
        this.query = query;
        this.outputString = outputString;
    }
    
    @Override
    protected String doInBackground() throws InterruptedException {
        System.out.println("background process started");
        Thread.sleep(1000);
        return "0";
    }
    
    @Override
    protected void done() {
        try {
            outputString = get();
            new testClass().setResult(outputString);
        }
        catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
    }
}