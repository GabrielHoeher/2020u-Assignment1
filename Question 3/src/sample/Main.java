package sample;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    //Definitions for variables and arrays used in the program

    //Defines pointLine for the regenerateLine() function
    public Line pointLine1 = new Line();
    public Line pointLine2 = new Line();
    public Line pointLine3 = new Line();

    //Defines the radius for the circles that are dragged
    public double radius = 8;

    //Text fields for the angle output
    public Text[] angleText = {new Text(), new Text(), new Text()};


    //Creates the main circle that the interactive circles orbit
    Circle circleMain = new Circle(200, 150, 80);


    //Creates an array containing the interactive circles
    public Circle[] circle = {new Circle(80 * Math.cos(200) + 200, 80 * Math.sin(200) + 150, 5),
            new Circle(80 * Math.cos(140) + 200, 80 * Math.sin(140) + 150, 5),
            new Circle(80 * Math.cos(300) + 200, 80 * Math.sin(300) + 150, 5)};
    

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        //Creates the main circles colours (Outline Black filled with white)
        circleMain.setStroke(Color.BLACK);
        circleMain.setFill(Color.WHITE);
        for (int i = 0; i < circle.length; i++) {
            //Creates the interactive circles colours (Outline Black filled with red)
            circle[i].setStroke(Color.BLACK);
            circle[i].setFill(Color.RED);
        }

        //Creates a pane to display the circles
        Pane pane = new Pane();
        //Sets the lines before any actual interaction
        regenerateLines();
        //Adds all parameters to the pane
        pane.getChildren().addAll(circleMain, circle[0], circle[1], circle[2],
                pointLine1, pointLine2, pointLine3, angleText[0], angleText[1], angleText[2]);

        //Create a new scene and assign it to the primaryStage
        Scene scene = new Scene(pane, 400, 250);
        primaryStage.setTitle("Question 3");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Event Handlers for all 3 interactive circles
        circle[0].setOnMouseDragged(e -> {
            //Assigns everything as a Point2D vector to utilise the normalize() function
            //The normalize() function creates the unit vector which we then multiply by the radius of the main circle

            //Gets the cursor position
            Point2D cursor = new Point2D(e.getX(), e.getY());
            //Gets the center of the main circle
            Point2D mainCenter = new Point2D(circleMain.getCenterX(), circleMain.getCenterY());
            //gets the distance from mainCenter to the cursor
            Point2D mainCenterToCursor = cursor.subtract(mainCenter);
            //Gets the new position using the normal vector multiplied by the radius of circleMain
            Point2D mainCenterToNewPosition = mainCenterToCursor.normalize().multiply(circleMain.getRadius());
            //Gets the new value of the circle location
            Point2D newPosition = mainCenterToNewPosition.add(mainCenter);

            //Assigns the new position to the interactive circle
            circle[0].setCenterX(newPosition.getX());
            circle[0].setCenterY(newPosition.getY());
            regenerateLines();
        });

        circle[1].setOnMouseDragged(e -> {
            //Assigns everything as a Point2D vector to utilise the normalize() function
            //The normalize() function creates the unit vector which we then multiply by the radius of the main circle

            //Gets the cursor position
            Point2D cursor = new Point2D(e.getX(), e.getY());
            //Gets the center of the main circle
            Point2D mainCenter = new Point2D(circleMain.getCenterX(), circleMain.getCenterY());
            //gets the distance from mainCenter to the cursor
            Point2D mainCenterToCursor = cursor.subtract(mainCenter);
            //Gets the new position using the normal vector multiplied by the radius of circleMain
            Point2D mainCenterToNewPosition = mainCenterToCursor.normalize().multiply(circleMain.getRadius());
            //Gets the new value of the circle location
            Point2D newPosition = mainCenterToNewPosition.add(mainCenter);

            //Assigns the new position to the interactive circle
            circle[1].setCenterX(newPosition.getX());
            circle[1].setCenterY(newPosition.getY());

            regenerateLines();

        });


        circle[2].setOnMouseDragged(e -> {
            //Assigns everything as a Point2D vector to utilise the normalize() function
            //The normalize() function creates the unit vector which we then multiply by the radius of the main circle

            //Gets the cursor position
            Point2D cursor = new Point2D(e.getX(), e.getY());
            //Gets the center of the main circle
            Point2D mainCenter = new Point2D(circleMain.getCenterX(), circleMain.getCenterY());
            //gets the distance from mainCenter to the cursor
            Point2D mainCenterToCursor = cursor.subtract(mainCenter);
            //Gets the new position using the normal vector multiplied by the radius of circleMain
            Point2D mainCenterToNewPosition = mainCenterToCursor.normalize().multiply(circleMain.getRadius());
            //Gets the new value of the circle location
            Point2D newPosition = mainCenterToNewPosition.add(mainCenter);

            //Assigns the new position to the interactive circle
            circle[2].setCenterX(newPosition.getX());
            circle[2].setCenterY(newPosition.getY());

            regenerateLines();

        });
    }


    public void regenerateLines() {

        //Displays the lines between the interactive circles
        //Sets the line between circles 0,1 for line 1
        pointLine1.setStartX(circle[0].getCenterX());
        pointLine1.setStartY(circle[0].getCenterY());

        pointLine1.setEndX(circle[1].getCenterX());
        pointLine1.setEndY(circle[1].getCenterY());

        //Sets the line between circles 0,2 for line 2
        pointLine2.setStartX(circle[0].getCenterX());
        pointLine2.setStartY(circle[0].getCenterY());

        pointLine2.setEndX(circle[2].getCenterX());
        pointLine2.setEndY(circle[2].getCenterY());


        //Sets the line between circles 1,2 for line 3
        pointLine3.setStartX(circle[1].getCenterX());
        pointLine3.setStartY(circle[1].getCenterY());

        pointLine3.setEndX(circle[2].getCenterX());
        pointLine3.setEndY(circle[2].getCenterY());

        //Calculates the angles for the triangle
        //Assigns a Point2D to utilise the distance() function
        double a = new Point2D(circle[2].getCenterX(), circle[2].getCenterY()).distance(circle[1].getCenterX(), circle[1].getCenterY());

        double b = new Point2D(circle[2].getCenterX(), circle[2].getCenterY()).distance(circle[0].getCenterX(), circle[0].getCenterY());

        double c = new Point2D(circle[1].getCenterX(), circle[1].getCenterY()).distance(circle[0].getCenterX(), circle[0].getCenterY());

        //Creates an array to store the angle values
        double[] angles = new double[3];
        //Calculates the angle of each interactive circle
        angles[0] = Math.acos((a * a - b * b - c * c) / (-2 * b * c));

        angles[1] = Math.acos((b * b - a * a - c * c) / (-2 * a * c));

        angles[2] = Math.acos((c * c - b * b - a * a) / (-2 * a * b));

        for (int i = 0; i < 3; i++) {
            //Outputs the text displaying the angle for each interactive circle
            angleText[i].setX(circle[i].getCenterX());
            angleText[i].setY(circle[i].getCenterY() - radius);
            //Sets the text to a String (from double) and sets that text to the text value in the array
            angleText[i].setText(String.format("%.2f", Math.toDegrees(angles[i])));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}