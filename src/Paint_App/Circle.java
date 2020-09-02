package Paint_App;

/**
 *
 * @author Abhishek Chauhan 
 *
 */

import javafx.scene.canvas.GraphicsContext;

public class Circle extends GeomatricObject {

    private double radius;

    public Circle(double x,double y,double radius){
        setX(x);
        setY(y);
        setRadius(radius);
    }

    public Circle(){

    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius(){
            return this.radius;
    }

    public void draw(GraphicsContext gc){
        gc.setLineWidth(getLineWidth());
        gc.setStroke(getStrokeColor());
        gc.setFill(getFillColor());
        gc.fillOval(getX(),getY(),getRadius(),getRadius());
        gc.strokeOval(getX(),getY(),getRadius(),getRadius());
    }


}
