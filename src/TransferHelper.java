import java.awt.Component;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;

public class TransferHelper extends TransferHandler {
    private static final long serialVersionUID = 1L;
    
    public TransferHelper() {
    }
    
    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }
    
    @Override
    protected Transferable createTransferable(JComponent source) {
        JTable table = (JTable) source;
        return new CellDataTransferable(new CellData(table));
    }
    
    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
    }
    
    @Override
    public boolean canImport(TransferSupport support) {
        boolean canImport = false;
        Component component = support.getComponent();
        
        if (component instanceof JTable) {
            JTable table = (JTable) component;
            DropLocation dropLocation = support.getDropLocation();
            Point dropPoint = dropLocation.getDropPoint();
            
            int dragColumn = table.columnAtPoint(dropPoint);
            
            try {
                Transferable transferable = support.getTransferable();
                CellData cellData = (CellData) transferable.getTransferData(CellDataTransferable.CELL_DATA_FLAVOR);
                
                if (cellData.getTable() != table) {
                    if (dragColumn == cellData.getColumn()) {
                        canImport = true;
                    }
                }
            }
            catch (UnsupportedFlavorException | IOException ex) {
                ex.printStackTrace();
            }
        }
        return canImport;
    }
    
    @Override
    public boolean importData(TransferSupport support) {
        boolean imported = false;
        
        Component component = support.getComponent();
        
        if (component instanceof JTable) {
            JTable target = (JTable) component;
            
            DropLocation dropLocation = support.getDropLocation();
            Point dropPoint = dropLocation.getDropPoint();
            int dropColumn = target.columnAtPoint(dropPoint);
            int dropRow = target.rowAtPoint(dropPoint);
            
            try {
                Transferable transferable = support.getTransferable();
                CellData cellData = (CellData) transferable.getTransferData(CellDataTransferable.CELL_DATA_FLAVOR);
                
                if (cellData.getTable() != target) {
                    if (dropColumn == cellData.getColumn()) {
                        //from "dropped" table
                        String exportValue = (String) target.getValueAt(dropRow, dropColumn);
                        //from "dragged" table
                        String importValue = cellData.getValue();
                        
                        //swap values
                        
                        //set target/dropped values
                        target.setValueAt(importValue, dropRow, dropColumn);
                        
                        //set source/dragged
                        JTable source = cellData.getTable();
                        int row = source.getSelectedRow();
                        int column = source.getSelectedColumn();
                        
                        source.setValueAt(exportValue, row, column);
                        
                        imported = true;
                    }
                }
            }
            catch (UnsupportedFlavorException | IOException ex) {
                ex.printStackTrace();
            }
        }
        
        return imported;
    }
}