import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.imgscalr.Scalr;
/*
THIS CLASS IS SACRED -- IT TOOK CRAZY AMOUNTS OF TIME TO MAKE
so please don't touch it :)
*/
public class GridPane extends JPanel {
    private final int DEFAULT_STROKE_SIZE = 6;
    
    private int columns = 11;    //this is both the number of columns AND the number of rows -- 11 is a nice looking default
    
    private ArrayList<Integer> xPoints = new ArrayList<>();
    private ArrayList<Integer> yPoints = new ArrayList<>();
    
    private ArrayList<ColorLine> lineArray = new ArrayList<>();
    private ArrayList<ColorCircle> circleArray = new ArrayList<>();
    
    private boolean drawBackground = true;
    
    public GridPane() {
    }
    
    public GridPane(int columns) {
        this.columns = columns;
    }
    
    public GridPane(boolean drawBackground) {
        this.drawBackground = drawBackground;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400); //the size of the grid -- 400 x 400 is a nice looking default
    }
    
    @Override
    protected void paintComponent(Graphics gfx) {
        super.paintComponent(gfx);
        
        Graphics2D gfx2d = (Graphics2D) gfx.create();
        
        if (drawBackground) {
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("map.jpg"));
            }
            catch (IOException ioex) {
                ioex.printStackTrace();
            }
            
            int scaledHeight = this.getHeight() + 25;

            img = Scalr.resize(img, scaledHeight);

            float alpha = 0.8f;
            gfx2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

            gfx2d.drawImage(img, 1, 1, null);

            alpha = 1f;
            gfx2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        }
        
        gfx2d.setFont(new Font("monospaced", Font.BOLD, 16));
        
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
                gfx2d.setColor(Color.LIGHT_GRAY);
                gfx2d.drawRect(x, y, size, size);
                
                xPoints.add(x);
                x += size;
                lastXPoint = x; //will always end up being the last point
            }
            xPoints.add(lastXPoint);
            
            if (horz > 0) {
                float xCoordOfString = (float) ((getWidth() - (size * columns)) / 2);
                gfx2d.setColor(Color.WHITE);
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
        
        gfx2d.setStroke(new BasicStroke(DEFAULT_STROKE_SIZE));
        
        //create lines if lines requested
        if (!lineArray.isEmpty()) {
            for (ColorLine line : lineArray) {
                
                //adds a border, or shadow, to the lines
                gfx2d.setColor(Color.BLACK);
                gfx2d.setStroke(new BasicStroke(2));
                gfx2d.drawLine((int) (line.getStartX() + (DEFAULT_STROKE_SIZE * 0.5)), (int) line.getStartY(), (int) (line.getEndX() + (DEFAULT_STROKE_SIZE * 0.5)), (int) line.getEndY());
                
                gfx2d.setColor(line.getColor());
                gfx2d.setStroke(new BasicStroke(DEFAULT_STROKE_SIZE));
                
                gfx2d.drawLine((int) line.getStartX(), (int) line.getStartY(), (int) line.getEndX(), (int) line.getEndY());
            }
        }
        
        //create circles if circles requested
        if (!circleArray.isEmpty()) {
            for (ColorCircle circle : circleArray) {
                
                //adds a border, or shadow, to the circles
                gfx2d.setColor(Color.BLACK);
                
                gfx2d.drawOval((int) circle.getCenterX(), (int) circle.getCenterY(), (int) (circle.getRadius() + 2), (int) (circle.getRadius() + 2));
                gfx2d.fillOval((int) circle.getCenterX(), (int) circle.getCenterY(), (int) (circle.getRadius() + 2), (int) (circle.getRadius() + 2));
                
                //need to figure out how to add diagonal line from left corner to right corner
                
                gfx2d.setColor(circle.getColor());
                
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
    
    public void addRouteOnGrid(ArrayList<Point> routePoints, Color color) {
        Point endPoint = routePoints.get(0);
        
        for (Point point : routePoints) {
            addLineOnGrid(point, endPoint, color);
            endPoint = point;
        }
    }
    
    public void addLineOnGrid(int x1, int y1, int x2, int y2, Color color)  {
        try {
            Point startPoint = convertCoordToIndexPoint(x1, y1);
            Point endPoint = convertCoordToIndexPoint(x2, y2);
            
            ColorLine line = new ColorLine(
                    xPoints.get(startPoint.x - 1),
                    yPoints.get(startPoint.y - 1),
                    xPoints.get(endPoint.x - 1),
                    yPoints.get(endPoint.y - 1),
                    color
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
    
    public void addLineOnGrid(Point startPoint, Point endPoint, Color color)  {
        try {
            startPoint = convertCoordToIndexPoint(startPoint.x, startPoint.y);
            endPoint = convertCoordToIndexPoint(endPoint.x, endPoint.y);
            
            ColorLine line = new ColorLine(
                    xPoints.get(startPoint.x - 1),
                    yPoints.get(startPoint.y - 1),
                    xPoints.get(endPoint.x - 1),
                    yPoints.get(endPoint.y - 1),
                    color
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
    
    public void addCircleOnGrid(int x, int y, Color color, boolean isCrossed) {
        try {
            Point originPoint = convertCoordToIndexPoint(x, y);
            int size = Math.min(getWidth() - 4, getHeight() - 4) / columns;
            
            ColorCircle circle = new ColorCircle(
                    xPoints.get(originPoint.x - 1) - (0.125 * size),
                    yPoints.get(originPoint.y - 1) - (0.125 * size),
                    10,
                    color,
                    isCrossed
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
    
    public void addCircleOnGrid(Point originPoint, Color color, boolean isCrossed) {
        try {
            originPoint = convertCoordToIndexPoint(originPoint.x, originPoint.y);
            int size = Math.min(getWidth() - 4, getHeight() - 4) / columns;
            
            ColorCircle circle = new ColorCircle(
                    xPoints.get(originPoint.x - 1) - (0.125 * size),
                    yPoints.get(originPoint.y - 1) - (0.125 * size),
                    10,
                    color,
                    isCrossed
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