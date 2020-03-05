package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import java.util.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Array used to remove values and to ensure no duplicate cards are pulled
        int[] intArray = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,
                31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54};

        //Array used to store the values pulled randomly
        int[] selectedCards = new int[3];
        //Defines the cardNumber
        int cardNumber = 0;

        for (int i = 0; i <= 2; i++) {
            boolean cond = false;

            //Loops until the card pulled is distinct and unique
            while(cond == false) {
                cond = false;

                //Pulls a random value from 0-53 which works for the array
                int randVal = new Random().nextInt(54);
                cardNumber = intArray[randVal];

                //Loops for each selected card to check that the number pulled has not been pulled already
                for (int j = 0; j < selectedCards.length; j++) {
                    //Checks if the card is already pulled
                    if (cardNumber == selectedCards[j]) {
                        cond = false;
                        break;
                    } else {
                        cond = true;
                    }
                }

            }
            //Assigns the card pulled to the cards displayed
            selectedCards[i] = cardNumber;
        }

        //Creates an Hbox
        HBox hBox = new HBox(1);

        //Pulls the card image from the cards folder and sets the width and height to fit the Hbox
        ImageView card1 = new ImageView(new Image(getClass().getResourceAsStream("cards/" + String.valueOf(selectedCards[0]) + ".png")));
        card1.setFitHeight(150);
        card1.setFitWidth(100);

        hBox.getChildren().add(card1);

        //Pulls the card image from the cards folder and sets the width and height to fit the Hbox
        ImageView card2 = new ImageView(new Image(getClass().getResourceAsStream("cards/" + String.valueOf(selectedCards[1]) + ".png")));
        card2.setFitHeight(150);
        card2.setFitWidth(100);

        hBox.getChildren().add(card2);

        //Pulls the card image from the cards folder and sets the width and height to fit the Hbox
        ImageView card3 = new ImageView(new Image(getClass().getResourceAsStream("cards/" + String.valueOf(selectedCards[2]) + ".png")));
        card3.setFitHeight(150);
        card3.setFitWidth(100);

        hBox.getChildren().add(card3);

        //Creates a new scene and assigns the Hbox to it
        Scene scene = new Scene(hBox,303,150);
        primaryStage.setTitle("Question 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}
