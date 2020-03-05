package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import java.lang.Math;
import java.text.DecimalFormat;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //creates and formats pane
        GridPane pane = new GridPane();
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);

        //creates fields for input and adds to pane
        TextField amount = new TextField();
        TextField years = new TextField();
        TextField rate = new TextField();
        pane.add(amount,1,0);
        pane.add(years,1,1);
        pane.add(rate,1,2);

        //creates the TextFields labels and adds to pane
        pane.add(new Label("Investment Amount:"),0,0);
        pane.add(new Label("Years:"),0,1);
        pane.add(new Label("Annual Interest Rate:"),0,2);
        pane.add(new Label("Future Value:"),0,3);
        pane.add(new Label(),1,3);

        //add button
        Button btn = new Button("Calculate");
        pane.add(btn,1,4);

        //Calculates on button press
        btn.setOnAction(button -> {
            //sets values
            float total = Float.parseFloat(amount.getText());
            float monthlyRate = Float.parseFloat(rate.getText())/100/12;
            float exponent = Float.parseFloat(years.getText())*12;
            double futureValue = total * (Math.pow(1+monthlyRate,exponent));

            DecimalFormat df = new DecimalFormat("#.00");                                  //format to 2 decimals
            pane.add(new Label(String.valueOf(df.format(futureValue))),1,3);  //add equation to pane
        });

        //formats scene and shows
        Scene scene = new Scene(pane,300,200);
        primaryStage.setTitle("Question 2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
}
