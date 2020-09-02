package Paint_App;

/**
 *
 * @author Abhishek Chauhan #000815438
 *
 */

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends GeomatricObject {

    private double width,height;

    public Rectangle(double x, double y,double width,double height){
        setX(x);
        setY(y);
        setHeight(height);
        setWidth(width);
    }

    public Rectangle(){

    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return this.height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getWidth() {
        return this.width;
    }

    public void draw(GraphicsContext gc){
        gc.setLineWidth(getLineWidth());
        gc.setStroke(getStrokeColor());
        gc.setFill(getFillColor());
        gc.fillRect(getX(),getY(),getWidth(),getHeight());
        gc.strokeRect(getX(),getY(),getWidth(),getHeight());

    }

    public String toString(){
        return "Rectangle: X"+getX()+" Y: "+getY()+" Width: "+getWidth()+" Height: "+getHeight();
    }
}
