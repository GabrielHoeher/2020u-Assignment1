package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import org.w3c.dom.Text;

import java.util.Scanner;


import java.io.File;

public class Main extends Application {
    String text = "";
    int[] letters = new int[26];

    @Override
    public void start(Stage primaryStage) throws Exception{
        Canvas canvas = new Canvas(350,100);                                  //create canvas for BarChart
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Label label = new Label("  A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");   //BarChart label
        TextField field = new TextField();                                                       //file input space
        Button btn = new Button("View");                                                    //button to submit

        //button parse through
        btn.setOnAction(e -> { parseFile(gc,field); });

        //parse through if enter button
        field.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                parseFile(gc,field);
            }
        });

        VBox vbox = new VBox(2);
        HBox hbox = new HBox(2);

        //formats the UI
        hbox.getChildren().addAll(field,btn);
        vbox.getChildren().addAll(canvas,label,hbox);
        vbox.setAlignment(Pos.BASELINE_LEFT);                                           //sets vbox to the left

        //Sets scene and show
        Scene scene = new Scene(vbox,350,200);
        primaryStage.setTitle("Question-4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //finds percentage of each letter in the file
    private float[] getPercentage() {
        float[] temp = new float[letters.length];
        float total = 0f;
        for (int i=0; i<letters.length; i++) { total += letters[i]; }                   //finds char total
        for (int i=0; i<letters.length; i++) { temp[i] = (letters[i]/total)*100; }      //finds percentage of each letter
        return temp;
    }

    //Creates the chart using Canvas class
    private void createChart(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(10);
        text = text.toLowerCase();                                                      //Converts to lower case

        for (int i=0; i<letters.length; i++) { letters[i] = 0; }                        //Setting letters to 0
        for (int i=0; i<text.length(); i++) { letters[text.charAt(i)-97] += 1; }        //Filling letters array

        float[] percentage = getPercentage();
        for (int i=0; i<letters.length; i++) {
            int x = 10+i*11;                                                            //x value algorithm
            gc.strokeLine(x,100,x,100-percentage[i]);                         //prints line
        }
    }

    //Finds file text
    private void parseFile(GraphicsContext gc, TextField field) {
        File file = new File(field.getText());

        //adds next line to text until file ends
        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                text += input.next();
            }
            createChart(gc);
        }
        catch(Exception e1){ System.out.println("File DNE"); }                          //file doesn't exist
    }

    public static void main(String[] args) { launch(args); }
}