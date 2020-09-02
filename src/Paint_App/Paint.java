package Paint_App;

/**
 * This is a Paint like application through which user can draw line, circle, and rectangle on canvas.
 * User can select shape from combobox items, set stroke and fill color using colorpicker, and set line width from textfield.
 * User can also undo and clear.
 * @author Abhishek Chauhan
 *
 */

import javafx.application.Application;
import javafx.stage.Stage;


public class Paint extends Application {

    /**
     * @param stage
     */
    public void start(Stage stage) {
        Draw draw = new Draw(stage);
        draw.draw().show();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
