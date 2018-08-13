package com.yahtzee;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import sample.Main;
import sample.Yahtzee;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class mainForm
{

    @FXML public ImageView imgOne;
    @FXML public ImageView imgTwo;
    @FXML public ImageView imgThree;
    @FXML public ImageView imgFour;
    @FXML public ImageView imgFive;
    @FXML public Button btnRollDice;
    @FXML public Button btnOnes;
    @FXML public Button btnTwos;
    @FXML public Button btnThrees;
    @FXML public Button btnFours;
    @FXML public Button btnFives;
    @FXML public Button btnSixes;
    @FXML public Button btn3OAK;
    @FXML public Button btn4OAK;
    @FXML public Button btnFullHouse;
    @FXML public Button btnSStraight;
    @FXML public Button btnLStraight;
    @FXML public Button btnYahtzee;
    @FXML public Button btnChance;
    @FXML public Button btnFirstDie;

    ArrayList<Button> pointsButtons = new ArrayList<Button>();

    private JButton button1;
    private JPanel panel1;
    private JTextArea textGoesHereItTextArea;

    Random rand = new Random();

    int intTotalPoints = 0;
    int intOnePoints = 0;
    int intTwoPoints = 0;
    int intThreePoints = 0;
    int intFourPoints = 0;
    int intFivePoints = 0;
    int intSixPoints = 0;
    int int3OAKPoints = 0;
    int int4OAKPoints = 0;
    int intFullHousePoints = 0;
    int intSmallStraightPoints = 0;
    int intLargeStraightPoints = 0;
    int intYahtzeePoints = 0;
    int intChancePoints = 0;
    int intBonusPoints = 0;

    int handsLeft = 13;
    int rollsLeft = 3;
    int onesInHand = 0;
    int twosInHand = 0;
    int threesInHand = 0;
    int foursInHand = 0;
    int fivesInHand = 0;
    int sixesInHand = 0;



    public static void main(String[] args) { }

    public void rollDice(ActionEvent actionEvent) {

        if (handsLeft == 13) {
            Collections.addAll(pointsButtons, btnOnes, btnTwos, btnThrees, btnFours, btnFives, btnSixes, btn3OAK,
                    btn4OAK, btnFullHouse, btnSStraight, btnLStraight, btnYahtzee, btnChance);

            for (Button button: pointsButtons) {
                button.setDisable(false);
            }
        }

        rollsLeft--;

        if (rollsLeft == 0)
            btnRollDice.setDisable(true);

        ArrayList<ImageView> pics = new ArrayList<ImageView>();
        Collections.addAll(pics, imgOne, imgTwo, imgThree, imgFour, imgFive);

        for (ImageView image: pics) {
            int rNumber = rand.nextInt(6) + 1;

            if (rNumber == 1) {
                image.setImage(new Image("@../../Images/Dice_1.png"));
                onesInHand++;
            } else if (rNumber == 2) {
                image.setImage(new Image("@../../Images/Dice_2.png"));
                twosInHand++;
            } else if (rNumber == 3) {
                image.setImage(new Image("@../../Images/Dice_3.png"));
                threesInHand++;
            } else if (rNumber == 4) {
                image.setImage(new Image("@../../Images/Dice_4.png"));
                foursInHand++;
            } else if (rNumber == 5) {
                image.setImage(new Image("@../../Images/Dice_5.png"));
                fivesInHand++;
            } else {
                image.setImage(new Image("@../../Images/Dice_6.png"));
                sixesInHand++;
            }
        }

        if (!btnOnes.isDisabled()) {
            btnOnes.setText(String.valueOf(getOnesValue()));
        }
    }

    public void confirmSelection(ActionEvent actionEvent) {
        handsLeft--;
        rollsLeft = 3;
        btnRollDice.setDisable(false);


    }

    public int getOnesValue() {
        return onesInHand;
    }

    public void clickedOnes(ActionEvent actionEvent) {
        btnOnes.setDisable(true);
        btnOnes.setStyle("-fx-text-fill: black");
        handsLeft--;
    }

}
