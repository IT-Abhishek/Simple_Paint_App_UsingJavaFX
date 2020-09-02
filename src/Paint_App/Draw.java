package Paint_App;

/**
 * This is a Paint like application through which user can draw line, circle, and rectangle on canvas.
 * User can select shape from combobox items, set stroke and fill color using colorpicker, and set line width from textfield.
 * User can also undo and clear.
 * @author Abhishek Chauhan 
 *
 */


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Draw {

    private Stage stage;
    private Group root;
    private Scene scene;
    private ColorPicker cp,fillCp;
    private Canvas canvas;
    private GraphicsContext gc;
    private ComboBox combobox;
    private Paint_App.Circle circle;
    private Paint_App.Rectangle rect;
    private Paint_App.Line line;
    private ToolBar tb;
    private ArrayList<GeomatricObject> objects;
    private TextField lineWidth;

    /**
     * @param stage
     */
    public Draw(Stage stage) {
        this.stage = stage;
    }

    /**
     * @param e
     */
    public void undo(ActionEvent e) {

        try {
            objects.remove(objects.size() - 1);
        }
        catch (Exception ex){
            new Alert(Alert.AlertType.WARNING,"Already Empty").showAndWait();
        }
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 1000, 600);
        for (GeomatricObject obj : objects) {
            if (obj.getClass().equals(circle.getClass())) {
                ((Circle) obj).draw(gc);
            }

            if (obj.getClass().equals(rect.getClass())) {
                ((Rectangle) obj).draw(gc);
            }

            if (obj.getClass().equals(line.getClass())) {
                ((Line) obj).draw(gc);
            }

        }

    }

    /**
     * @param e
     */
    public void clear(ActionEvent e) {
        objects.clear();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 1000, 600);
    }


    public Stage draw() {

        this.objects = new ArrayList<>();

        this.root = new Group();
        scene = new Scene(root, 1000, 600);

        this.circle = new Paint_App.Circle();
        this.rect = new Paint_App.Rectangle();
        this.line = new Paint_App.Line();

        this.tb = new ToolBar();
        this.tb.setMinWidth(1000);
        this.tb.setMinHeight(50);
        this.tb.setCursor(Cursor.DEFAULT);

        this.canvas = new Canvas(this.scene.getWidth(), this.scene.getHeight());
        this.canvas.relocate(0, tb.getMinHeight());
        canvas.setCursor(Cursor.CROSSHAIR);
        gc = canvas.getGraphicsContext2D();

        this.combobox = new ComboBox();
        combobox.getItems().addAll("Line", "Rectangle", "Circle");
        combobox.setValue("Line");

        Label stroke = new Label("Stroke");
        this.cp = new ColorPicker(Color.BLACK);


        Button undo = new Button("UNDO");
        undo.setOnAction(this::undo);

        Button clear = new Button("CLEAR");
        clear.setOnAction(this::clear);

        Label lineWidthLabel =new Label("LineWidth");
        Label fillColorLabel=new Label("Fill Color");

        fillCp=new ColorPicker(Color.WHITE);

        lineWidth = new TextField("1");

        tb.getItems().addAll(stroke, cp,fillColorLabel,fillCp, combobox, undo, clear,lineWidthLabel, lineWidth);


        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                if (combobox.getValue().equals("Line")) {
                    line.setStrokeColor(cp.getValue());
                    line.setX(mouseEvent.getX());
                    line.setY(mouseEvent.getY());
                    try {
                        line.setLineWidth(Double.parseDouble(lineWidth.getText()));
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.WARNING, "Invalid Line Width").showAndWait();
                    }
                }

                if (combobox.getValue().equals("Circle")) {
                    circle.setStrokeColor(cp.getValue());
                    circle.setFillColor(fillCp.getValue());
                    circle.setX(mouseEvent.getX());
                    circle.setY(mouseEvent.getY());
                    try {
                        circle.setLineWidth(Double.parseDouble(lineWidth.getText()));
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.WARNING, "Invalid Line Width").showAndWait();
                    }
                }

                if (combobox.getValue().equals("Rectangle")) {
                    rect.setStrokeColor(cp.getValue());
                    rect.setFillColor(fillCp.getValue());
                    rect.setX(mouseEvent.getX());
                    rect.setY(mouseEvent.getY());
                    try {
                        rect.setLineWidth(Double.parseDouble(lineWidth.getText()));
                    }
                    catch(Exception e){
                        new Alert(Alert.AlertType.WARNING, "Invalid Line Width").showAndWait();
                    }
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if (combobox.getValue().equals("Line")) {
                    line.setEndX(mouseEvent.getX());
                    line.setEndY(mouseEvent.getY());
                    line.draw(gc);
                    Line tempLine=new Line(line.getX(), line.getY(), line.getEndX(), line.getEndY());
                    tempLine.setStrokeColor(line.getStrokeColor());
                    objects.add(tempLine);
                }

                if (combobox.getValue().equals("Circle")) {
                    circle.setRadius((Math.abs(mouseEvent.getX() - circle.getX()) + Math.abs(mouseEvent.getY() - circle.getY())) / 2);
                    if (circle.getX() > mouseEvent.getX())
                        circle.setX(mouseEvent.getX());
                    if (circle.getY() > mouseEvent.getY())
                        circle.setY(mouseEvent.getY());
                    circle.draw(gc);
                    Circle tempCircle=new Circle(circle.getX(), circle.getY(), circle.getRadius());
                    tempCircle.setStrokeColor(circle.getStrokeColor());
                    tempCircle.setFillColor(circle.getFillColor());
                    tempCircle.setLineWidth(circle.getLineWidth());
                    objects.add(tempCircle);
                }

                if (combobox.getValue().equals("Rectangle")) {
                    rect.setWidth(Math.abs(mouseEvent.getX() - rect.getX()));
                    rect.setHeight(Math.abs(mouseEvent.getY() - rect.getY()));
                    if (rect.getX() > mouseEvent.getX())
                        rect.setX(mouseEvent.getX());
                    if (rect.getY() > mouseEvent.getY())
                        rect.setY(mouseEvent.getY());
                    rect.draw(gc);
                    Rectangle tempRect=new Rectangle(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                    tempRect.setStrokeColor(rect.getStrokeColor());
                    tempRect.setFillColor(rect.getFillColor());
                    tempRect.setLineWidth(rect.getLineWidth());
                    objects.add(tempRect);
                }
            }

        });

        this.stage.setTitle("Simple Paint using JavaFX");
        this.stage.setScene(scene);


        root.getChildren().addAll(tb, canvas);


        return this.stage;
    }

}
