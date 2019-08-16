import java.awt.Color;

public class ColorCircle extends javafx.scene.shape.Circle {
    private Color circleColor;
    private boolean isCrossed;
    
    public ColorCircle(double x, double y, double radius, Color color, boolean isCrossed) {
        super(x, y, radius);
        this.circleColor = color;
        this.isCrossed = isCrossed;
    }
    
    public Color getColor() { return circleColor; }
    
    public boolean isCrossed() { return isCrossed; }
}