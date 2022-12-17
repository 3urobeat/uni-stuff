package triangle;

import javafx.*;

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
        StackPane root = new StackPane();
        root.getChildren().add(xIn);
        root.getChildren().add(yIn);
        root.getChildren().add(zIn);

        // create a label
        Label label = new Label("");
        root.getChildren().add(label);

        // Add event to detect input and update label
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
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
            }
        };

        // Show window with given dimensions
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

    }
}
