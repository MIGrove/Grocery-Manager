import javax.swing.*;

public class LocatorWindow_new {
    JFrame mainFrame;
    
    public LocatorWindow_new() {
        mainFrame = new JFrame("Locator");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(250, 250);
        
        //menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu usersMenu = new JMenu("Users");
        JMenu settingsMenu = new JMenu("Settings");
        
        menuBar.add(usersMenu);
        menuBar.add(settingsMenu);
        
        //components to mainFrame
        mainFrame.getContentPane().add(menuBar);
    }
    
    public void run() {
        mainFrame.setVisible(true);
    }
    
    public static void main(String[] args) {
        LocatorWindow_new lw = new LocatorWindow_new();
        lw.run();
    }
}