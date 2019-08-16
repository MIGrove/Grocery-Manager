import java.awt.Color;

public class ColorLine extends javafx.scene.shape.Line {
    private Color lineColor;
    
    public ColorLine(double x1, double y1, double x2, double y2, Color color) {
        super(x1, y1, x2, y2);
        this.lineColor = color;
    }
    
    public Color getColor() { return lineColor; }
}