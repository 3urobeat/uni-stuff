package triangle;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    // Command line entry
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Set title
        primaryStage.setTitle("Ausgabe-fuer-Dreieck-erzeugen-Geraet");

        // Add 3 input fields
        TextField xIn = new TextField("x value");
        TextField yIn = new TextField("y value");
        TextField zIn = new TextField("z value");

        // Create StackPane
        GridPane root = new GridPane();

        // Space stuff reasonably
        root.setVgap(20);
        root.setHgap(10);
        root.setAlignment(Pos.CENTER);

        // Place stuff reasonably
        root.add(xIn, 0, 0);
        root.add(yIn, 0, 1);
        root.add(zIn, 0, 2);

        // Create an output label
        Label label = new Label("test");
        label.setAlignment(Pos.CENTER);
        root.add(label, 0, 3);

        // Add event to detect input and update label
        ChangeListener<String> listener = (observableValue, oldValue, newValue) -> {
            try {
                // Check if triangle is equilateral, isosceles or scalene
                String state = "";

                int x = Integer.parseInt(xIn.getText());
                int y = Integer.parseInt(yIn.getText());
                int z = Integer.parseInt(zIn.getText());

                if (x == y && y == z) { // equilateral, all sides are equally long
                    state = "gleichseitig & gleichschenklig";
                } else if (x == y || y == z || x == z) { // isosceles, 2 sides are equally long
                    state = "gleichschenklig";
                } else { // scalene, nothing matches each other
                    state = "ungleichseitig";
                }

                // Output result
                label.setText("Das angegebene Dreieck ist " + state + ".");

            } catch (NumberFormatException e) {
                label.setText("Eingabe ungültig!");
            }
        };

        xIn.textProperty().addListener(listener);
        yIn.textProperty().addListener(listener);
        zIn.textProperty().addListener(listener);

        // Show window with given dimensions
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

    }
}
