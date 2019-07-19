import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

public class LocatorWindow_new {
    static JFrame mainFrame;
    
    final static boolean SHOULD_FILL = true, SHOULD_WEIGHT_X = true, RIGHT_TO_LEFT = false;
    
    public static void addComponents(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        //menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu usersMenu = new JMenu("Users");
        
        JMenuItem menuItem = new JMenuItem("Change user");
        
        usersMenu.add(menuItem);
        
        JMenu settingsMenu = new JMenu("Settings");
        
        menuBar.add(usersMenu);
        menuBar.add(settingsMenu);
        
        //back to config.
        if (SHOULD_FILL) {
            c.fill = GridBagConstraints.HORIZONTAL;
        }
        if (SHOULD_WEIGHT_X) {
            c.weightx = 0.5;
        }
        
        //components to mainFrame
        pane.add(menuBar);
    }
    
    private static void createAndShowGUI() {
        //create and set up window
        JFrame frame = new JFrame("title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //content pane setup
        addComponents(frame.getContentPane());
        
        //display window
        frame.pack();
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        /*
        scheduling a job for the event-dispatching thread
        and creating and showing GUI
        */
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}