import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javax.swing.JPanel;
/*
THIS CLASS IS SACRED -- IT TOOK CRAZY AMOUNTS OF TIME TO MAKE
so please don't touch it :)
*/
public class GridPane extends JPanel {
    private int columns = 11;    //this is both the number of columns AND the number of rows -- 11 is a nice looking default
    
    private ArrayList<Line> lineArray = new ArrayList<>();
    private ArrayList<Integer> xPoints = new ArrayList<>();
    private ArrayList<Integer> yPoints = new ArrayList<>();
    private ArrayList<Circle> circleArray = new ArrayList<>();
    
    private boolean drawBackground = true;
    
    public GridPane() {
    }
    
    public GridPane(int columns) {
        this.columns = columns;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400); //the size of the grid -- 400 x 400 is a nice looking default
    }
    
    @Override
    protected void paintComponent(Graphics gfx) {
        super.paintComponent(gfx);
        
        Graphics2D gfx2d = (Graphics2D) gfx.create();
                
        xPoints.clear();
        yPoints.clear();
        
        int lastXPoint = -1, lastYPoint = -1; //allows the last point (bottom right) to be recorded
        
        int size = Math.min(getWidth() - 4, getHeight() - 4) / columns;
        int width = getWidth() - (size * 2);
        int height = getHeight() - (size * 2);
        
        int y = (getHeight() - (size * columns)) / 2;
        
        for (int horz = 0; horz < columns; horz++) {
            int x = (getWidth() - (size * columns)) / 2;
               
            for (int vert = 0; vert < columns; vert++) {
                gfx2d.drawRect(x, y, size, size);
                
                xPoints.add(x);
                x += size;
                lastXPoint = x; //will always end up being the last point
            }
            xPoints.add(lastXPoint);
            
            if (horz > 0) {
                float xCoordOfString = (float) ((getWidth() - (size * columns)) / 2);
                gfx2d.drawString((horz + 1) + "", (float) (xCoordOfString + (0.075 * size)), (float) (y + (0.3 * size)));
            }
            
            yPoints.add(y);
            y += size;
            lastYPoint = y; //will always end up being the last point
        }
        yPoints.add(lastYPoint);
        
        //this creates one more row of x values in the x array -- it's just another loop
        int x = (getWidth() - (size * columns)) / 2;
        
        for (int vert = 0; vert < columns; vert++) {
                gfx2d.drawRect(x, y, size, size);
                
                float yCoordOfString = (float) (((getHeight() - (size * columns)) / 2) + (0.3 * size));
                gfx2d.drawString((vert + 1) + "", (float) (x + (0.1 * size)), yCoordOfString);
                
                xPoints.add(x);
                x += size;
                lastXPoint = x;
            }
        xPoints.add(lastXPoint);
        
        gfx2d.setStroke(new BasicStroke(6));
        gfx2d.setColor(Color.RED);
        
        //create lines if lines requested
        if (!lineArray.isEmpty()) {
            for (Line line : lineArray) {
                gfx2d.drawLine((int) line.getStartX(), (int) line.getStartY(), (int) line.getEndX(), (int) line.getEndY());
            }
        }
        
        //create circles if circles requested
        if (!circleArray.isEmpty()) {
            for (Circle circle : circleArray) {
                gfx2d.drawOval((int) circle.getCenterX(), (int) circle.getCenterY(), (int) circle.getRadius(), (int) circle.getRadius());
                gfx2d.fillOval((int) circle.getCenterX(), (int) circle.getCenterY(), (int) circle.getRadius(), (int) circle.getRadius());
            }
        }
        
        System.out.println(xPoints.toString());
        System.out.println(yPoints.toString());
        
        gfx2d.dispose();
    }
    
    public void setColumns(int columns) {
        this.columns = columns;
        repaint();
    }
    
    public void drawBackground(boolean drawBackground) {
        this.drawBackground = drawBackground;
    }
    
    public void addRouteOnGrid(ArrayList<Point> routePoints) {
        Point endPoint = routePoints.get(0);
        
        for (Point point : routePoints) {
            addLineOnGrid(point, endPoint);
            endPoint = point;
        }
    }
    
    public void addLineOnGrid(int x1, int y1, int x2, int y2)  {
        try {
            Point startPoint = convertCoordToIndexPoint(x1, y1);
            Point endPoint = convertCoordToIndexPoint(x2, y2);
            
            Line line = new Line(
                    xPoints.get(startPoint.x - 1),
                    yPoints.get(startPoint.y - 1),
                    xPoints.get(endPoint.x - 1),
                    yPoints.get(endPoint.y - 1)
            );
            
            lineArray.add(line);
            
            System.out.println(line.toString());

            repaint();
        }
        catch (IndexOutOfBoundsException iofbex) {
            iofbex.printStackTrace();
            System.out.println("ERROR: Not enough points on square!");
        }
    }
    
    public void addLineOnGrid(Point startPoint, Point endPoint)  {
        try {
            startPoint = convertCoordToIndexPoint(startPoint.x, startPoint.y);
            endPoint = convertCoordToIndexPoint(endPoint.x, endPoint.y);
            
            Line line = new Line(
                    xPoints.get(startPoint.x - 1),
                    yPoints.get(startPoint.y - 1),
                    xPoints.get(endPoint.x - 1),
                    yPoints.get(endPoint.y - 1)
            );
            
            lineArray.add(line);

            System.out.println(line.toString());

            repaint();
        }
        catch (IndexOutOfBoundsException iofbex) {
            iofbex.printStackTrace();
            System.out.println("ERROR: Not enough points on square!");
        }
    }
    
    public void addCircleOnGrid(int x, int y) {
        try {
            Point originPoint = convertCoordToIndexPoint(x, y);
            int size = Math.min(getWidth() - 4, getHeight() - 4) / columns;
            
            Circle circle = new Circle(
                    xPoints.get(originPoint.x - 1) - (0.125 * size),
                    yPoints.get(originPoint.y - 1) - (0.125 * size),
                    10
            );
                        
            circleArray.add(circle);
            
            System.out.println(circle.toString());
            
            repaint();
        }
        catch (IndexOutOfBoundsException iofbex) {
            iofbex.printStackTrace();
            System.out.println("ERROR: Not enough points on square!");
        }
    }
    
    public void addCircleOnGrid(Point originPoint) {
        try {
            originPoint = convertCoordToIndexPoint(originPoint.x, originPoint.y);
            int size = Math.min(getWidth() - 4, getHeight() - 4) / columns;
            
            Circle circle = new Circle(
                    xPoints.get(originPoint.x - 1) - (0.125 * size),
                    yPoints.get(originPoint.y - 1) - (0.125 * size),
                    10
            );
                        
            circleArray.add(circle);
            
            System.out.println(circle.toString());
            
            repaint();
        }
        catch (IndexOutOfBoundsException iofbex) {
            iofbex.printStackTrace();
            System.out.println("ERROR: Not enough points on square!");
        }
    }
    
    public void clearLines() {
        lineArray.clear();
        repaint();
    }
    
    public void clearCircles() {
        circleArray.clear();
        repaint();
    }
    
    private Point convertCoordToIndexPoint(int coordX, int coordY) {
         //example: given 3, 2 return 7, 2 (of which 7 is the x-"position" on the grid)
         
         int newX = (((columns + 1) * coordY) - ((columns + 1) - coordX));
         //there is no newY, because it does not change.
         
         return new Point(newX, coordY);
    }
    
    public ArrayList<Integer> getXPoints() { return xPoints; }
    
    public ArrayList<Integer> getYPoints() { return yPoints; }
}