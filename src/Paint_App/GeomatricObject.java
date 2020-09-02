package Paint_App;

/**
 *
 * @author Abhishek Chauhan
 *
 */

import javafx.scene.paint.Color;

public class GeomatricObject {
    private double x,y,lineWidth;
    private Color strokeColor,fillColor;

    public void setX(double x){
        this.x=x;
    }

    public double getX(){
        return this.x;
    }

    public void setY(double y){
        this.y=y;
    }

    public double getY(){
        return this.y;
    }

    public void setLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
    }

    public double getLineWidth() {
        return this.lineWidth;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Color getStrokeColor() {
        return this.strokeColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getFillColor() {
        return this.fillColor;
    }
}
