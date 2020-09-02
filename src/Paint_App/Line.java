package Paint_App;

/**
 *
 * @author Abhishek Chauhan #000815438
 *
 */

import javafx.scene.canvas.GraphicsContext;

public class Line extends GeomatricObject {
    private double endX,endY;

    public Line(double x,double y,double endX,double endY){
        setX(x);
        setY(y);
        setEndX(endX);
        setEndY(endY);
    }

    public Line(){

    }

    public void setEndX(double x) {
        this.endX=x;
    }

    public double getEndX() {
        return this.endX;
    }

    public void setEndY(double endY) {
        this.endY = endY;
    }

    public double getEndY() {
        return this.endY;
    }

    public void draw(GraphicsContext gc){
        gc.setLineWidth(getLineWidth());
        gc.setStroke(getStrokeColor());
        gc.strokeLine(getX(),getY(),getEndX(),getEndY());
    }

    public String toString(){
        return "Line: startX"+getX()+" startY: "+getY()+" endX"+getEndX()+" endY"+getEndY();
    }
}
