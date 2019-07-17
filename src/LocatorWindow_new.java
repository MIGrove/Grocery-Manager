import javax.swing.*;

public class LocatorWindow_new {
    public static void main(String[] args) {
        
        //frame
        JFrame frame = new JFrame("Chat panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        
        //creating MenuBar and adding components
        JMenuBar menuBar = new JMenuBar();
        JMenu menu0 = new JMenu("file");
        JMenu menu1 = new JMenu("help");
        
        menuBar.add(menu0);
        menuBar.add(menu1);
        
        JMenuItem m0 = new JMenuItem("open");
        JMenuItem m1 = new JMenuItem("save as");
        
        //creating bottom section
        JPanel panel = new JPanel();
        JLabel label = new JLabel("enter text");
        JTextField tf  = new JTextField(10);
        
    }
}