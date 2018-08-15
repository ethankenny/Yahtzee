package com.yahtzee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class mainForm
{
    @FXML public ImageView imgOne, imgTwo, imgThree, imgFour, imgFive;
    @FXML public Button btnRollDice, btnOnes, btnTwos, btnThrees, btnFours, btnFives, btnSixes;
    @FXML public Button btn3OAK, btn4OAK, btnFullHouse, btnSStraight, btnLStraight, btnYahtzee, btnChance;
    @FXML public Button btnFirstDie, btnSecondDie, btnThirdDie, btnFourthDie, btnFifthDie;
    @FXML public ImageView defaultDieOne, defaultDieTwo, defaultDieThree, defaultDieFour, defaultDieFive, defaultDieSix;
    @FXML public TextField txtBonusPoints, txtTotalPoints;

    ArrayList<Button> pointsButtons = new ArrayList<Button>();
    ArrayList<Button> dieButtons = new ArrayList<Button>();
    ArrayList<Button> diePointsButtons = new ArrayList<Button>();

    private JButton button1;
    private JPanel panel1;
    private JTextArea textGoesHereItTextArea;

    Random rand = new Random();

    int intTotalPoints, intBonusPoints, intOnePoints, intTwoPoints, intThreePoints, intFourPoints, intFivePoints, intSixPoints = 0;
    int int3OAKPoints, int4OAKPoints, intFullHousePoints, intSmallStraightPoints, intLargeStraightPoints, intYahtzeePoints, intChancePoints = 0;
    int handsLeft = 13;
    int rollsLeft = 3;
    int onesInHand, twosInHand, threesInHand, foursInHand, fivesInHand, sixesInHand = 0;

    boolean blnDieOneActive, blnDieTwoActive, blnDieThreeActive, blnDieFourActive, blnDieFiveActive, blnBonus = false;

    public static void main(String[] args) { }

    public void rollDice(ActionEvent actionEvent) {

        if (handsLeft == 13) {
            Collections.addAll(pointsButtons, btnOnes, btnTwos, btnThrees, btnFours, btnFives, btnSixes, btn3OAK,
                    btn4OAK, btnFullHouse, btnSStraight, btnLStraight, btnYahtzee, btnChance);
            Collections.addAll(diePointsButtons, btnOnes, btnTwos, btnThrees, btnFours, btnFives, btnSixes);
        }

        if (rollsLeft == 3) {
            for (Button button : pointsButtons) {
                if (button.getStyle() != "-fx-text-fill: black")
                    button.setDisable(false);
            }
        }

        rollsLeft--;

        if (rollsLeft == 0)
            btnRollDice.setDisable(true);

        Collections.addAll(dieButtons, btnFirstDie, btnSecondDie, btnThirdDie, btnFourthDie, btnFifthDie);

        for (Button button: dieButtons) {
            button.setDisable(false);
        }

        ArrayList<ImageView> pics = new ArrayList<ImageView>();
        Collections.addAll(pics, imgOne, imgTwo, imgThree, imgFour, imgFive);

        Image priorImageOne = imgOne.getImage();
        Image priorImageTwo = imgTwo.getImage();
        Image priorImageThree = imgThree.getImage();
        Image priorImageFour = imgFour.getImage();
        Image priorImageFive = imgFive.getImage();

        onesInHand = twosInHand = threesInHand = foursInHand = fivesInHand = sixesInHand = 0;

        for (ImageView image: pics) {
            int rNumber = rand.nextInt(6) + 1;

            if (rNumber == 1) {
                image.setImage(new Image("@../../Images/Dice_1.png"));
            } else if (rNumber == 2) {
                image.setImage(new Image("@../../Images/Dice_2.png"));
            } else if (rNumber == 3) {
                image.setImage(new Image("@../../Images/Dice_3.png"));
            } else if (rNumber == 4) {
                image.setImage(new Image("@../../Images/Dice_4.png"));
            } else if (rNumber == 5) {
                image.setImage(new Image("@../../Images/Dice_5.png"));
            } else {
                image.setImage(new Image("@../../Images/Dice_6.png"));
            }
        }

        if (blnDieOneActive) {
            imgOne.setImage(priorImageOne);
        } if (blnDieTwoActive) {
            imgTwo.setImage(priorImageTwo);
        } if (blnDieThreeActive) {
            imgThree.setImage(priorImageThree);
        } if (blnDieFourActive) {
            imgFour.setImage(priorImageFour);
        } if (blnDieFiveActive) {
            imgFive.setImage(priorImageFive);
        }

        blnDieOneActive = blnDieTwoActive = blnDieThreeActive = blnDieFourActive = blnDieFiveActive = false;

        for (ImageView image: pics) {
            String imageURL = image.getImage().impl_getUrl();

            if (imageURL.equals(defaultDieOne.getImage().impl_getUrl())) {
                onesInHand++;
            } else if (imageURL.equals(defaultDieTwo.getImage().impl_getUrl())) {
                twosInHand++;
            } else if (imageURL.equals(defaultDieThree.getImage().impl_getUrl())) {
                threesInHand++;
            } else if (imageURL.equals(defaultDieFour.getImage().impl_getUrl())) {
                foursInHand++;
            } else if (imageURL.equals(defaultDieFive.getImage().impl_getUrl())) {
                fivesInHand++;
            } else if (imageURL.equals(defaultDieSix.getImage().impl_getUrl())) {
                sixesInHand++;
            }
        }

        for (Button button: dieButtons) {
            button.setStyle("-fx-background-color: transparent;" + "-fx-border-radius: 10 10 10 10");
        }

        if (!btnOnes.isDisabled()) {
            btnOnes.setText(String.valueOf(onesInHand));
        }

        if (!btnTwos.isDisabled()) {
            btnTwos.setText(String.valueOf(twosInHand * 2));
        }

        if (!btnThrees.isDisabled()) {
            btnThrees.setText(String.valueOf(threesInHand * 3));
        }

        if (!btnFours.isDisabled()) {
            btnFours.setText(String.valueOf(foursInHand * 4));
        }

        if (!btnFives.isDisabled()) {
            btnFives.setText(String.valueOf(fivesInHand * 5));
        }

        if (!btnSixes.isDisabled()) {
            btnSixes.setText(String.valueOf(sixesInHand * 6));
        }

        if (!btn3OAK.isDisabled()) {
            if (onesInHand >= 3 || twosInHand >= 3 || threesInHand >= 3|| foursInHand >= 3 || fivesInHand >= 3 || sixesInHand >= 3) {
                int3OAKPoints = (onesInHand) + (twosInHand * 2) + (threesInHand * 3) + (foursInHand * 4) + (fivesInHand * 5) + (sixesInHand * 6);
            }
            btn3OAK.setText(String.valueOf(int3OAKPoints));
            int3OAKPoints = 0;
        }

        if (!btn4OAK.isDisabled()) {
            if (onesInHand >= 4 || twosInHand >= 4 || threesInHand >= 4|| foursInHand >= 4 || fivesInHand >= 4 || sixesInHand >= 4) {
                int4OAKPoints = (onesInHand) + (twosInHand * 2) + (threesInHand * 3) + (foursInHand * 4) + (fivesInHand * 5) + (sixesInHand * 6);
            }
            btn4OAK.setText(String.valueOf(int4OAKPoints));
            int4OAKPoints = 0;
        }

        if (!btnFullHouse.isDisabled()) {
            if (onesInHand == 3 || twosInHand == 3 || threesInHand == 3 || foursInHand == 3 || fivesInHand == 3 || sixesInHand == 3) {
                if (onesInHand == 2 || twosInHand == 2 || threesInHand == 2 || foursInHand == 2 || fivesInHand == 2 || sixesInHand == 2) {
                    intFullHousePoints = 25;
                }
            }
            btnFullHouse.setText(String.valueOf(intFullHousePoints));
            intFullHousePoints = 0;
        }

        if (!btnSStraight.isDisabled()) {
            if ((onesInHand > 0 && twosInHand > 0 && threesInHand > 0 && foursInHand > 0) || (twosInHand > 0 && threesInHand > 0 && foursInHand > 0 && fivesInHand > 0) ||
                    threesInHand > 0 && foursInHand > 0 && fivesInHand > 0 && sixesInHand > 0) {
                intSmallStraightPoints = 30;
            }
            btnSStraight.setText(String.valueOf(intSmallStraightPoints));
            intSmallStraightPoints = 0;
        }

        if (!btnLStraight.isDisabled()) {
            if (twosInHand > 0 && threesInHand > 0 && foursInHand > 0 && fivesInHand > 0)
                if (onesInHand > 0 || sixesInHand > 0)
                    intLargeStraightPoints = 40;

            btnLStraight.setText(String.valueOf(intLargeStraightPoints));
            intLargeStraightPoints = 0;
        }

        if (!btnYahtzee.isDisabled()) {
            if (onesInHand == 5 || twosInHand == 5 || threesInHand == 5|| foursInHand == 5 || fivesInHand == 5 || sixesInHand == 5) {
                intYahtzeePoints = 50;
            }
            btnYahtzee.setText(String.valueOf(intYahtzeePoints));
            intYahtzeePoints = 0;
        }

        if (!btnChance.isDisabled()) {
            intChancePoints = (onesInHand) + (twosInHand * 2) + (threesInHand * 3) + (foursInHand * 4) + (fivesInHand * 5) + (sixesInHand * 6);
            btnChance.setText(String.valueOf(intChancePoints));
            intChancePoints = 0;
        }
    }

    public void firstDieClicked(ActionEvent actionEvent) {
        if (!blnDieOneActive) {
            blnDieOneActive = true;
            btnFirstDie.setStyle("-fx-border-color: black;" + "-fx-background-color: transparent;" + "-fx-border-radius: 10 10 10 10");
        } else {
            blnDieOneActive = false;
            btnFirstDie.setStyle("-fx-background-color: transparent;" + "-fx-border-radius: 10 10 10 10");
        }
    }

    public void secondDieClicked(ActionEvent actionEvent) {
        if (!blnDieTwoActive) {
            blnDieTwoActive = true;
            btnSecondDie.setStyle("-fx-border-color: black;" + "-fx-background-color: transparent;" + "-fx-border-radius: 10 10 10 10");
        } else {
            blnDieTwoActive = false;
            btnSecondDie.setStyle("-fx-background-color: transparent;" + "-fx-border-radius: 10 10 10 10");
        }
    }

    public void thirdDieClicked(ActionEvent actionEvent) {
        if (!blnDieThreeActive) {
            blnDieThreeActive = true;
            btnThirdDie.setStyle("-fx-border-color: black;" + "-fx-background-color: transparent;" + "-fx-border-radius: 10 10 10 10");
        } else {
            blnDieThreeActive = false;
            btnThirdDie.setStyle("-fx-background-color: transparent;" + "-fx-border-radius: 10 10 10 10");
        }
    }

    public void fourthDieClicked(ActionEvent actionEvent) {
        if (!blnDieFourActive) {
            blnDieFourActive = true;
            btnFourthDie.setStyle("-fx-border-color: black;" + "-fx-background-color: transparent;" + "-fx-border-radius: 10 10 10 10");
        } else {
            blnDieFourActive = false;
            btnFourthDie.setStyle("-fx-background-color: transparent;" + "-fx-border-radius: 10 10 10 10");
        }
    }

    public void fifthDieClicked(ActionEvent actionEvent) {
        if (!blnDieFiveActive) {
            blnDieFiveActive = true;
            btnFifthDie.setStyle("-fx-border-color: black;" + "-fx-background-color: transparent;" + "-fx-border-radius: 10 10 10 10");
        } else {
            blnDieFiveActive = false;
            btnFifthDie.setStyle("-fx-background-color: transparent;" + "-fx-border-radius: 10 10 10 10");
        }
    }

    public void clickedOnes(ActionEvent actionEvent) {
        btnOnes.setDisable(true);
        btnOnes.setStyle("-fx-text-fill: black");
        intOnePoints = onesInHand;

        allButtonClicks();
    }

    public void clickedTwos(ActionEvent actionEvent) {
        btnTwos.setDisable(true);
        btnTwos.setStyle("-fx-text-fill: black");
        intTwoPoints = twosInHand * 2;

        allButtonClicks();
    }

    public void clickedThrees(ActionEvent actionEvent) {
        btnThrees.setDisable(true);
        btnThrees.setStyle("-fx-text-fill: black");
        intThreePoints = threesInHand * 3;

        allButtonClicks();
    }

    public void clickedFours(ActionEvent actionEvent) {
        btnFours.setDisable(true);
        btnFours.setStyle("-fx-text-fill: black");
        intFourPoints = foursInHand * 4;

        allButtonClicks();
    }

    public void clickedFives(ActionEvent actionEvent) {
        btnFives.setDisable(true);
        btnFives.setStyle("-fx-text-fill: black");
        intFivePoints = fivesInHand * 5;

        allButtonClicks();
    }

    public void clickedSixes(ActionEvent actionEvent) {
        btnSixes.setDisable(true);
        btnSixes.setStyle("-fx-text-fill: black");
        intSixPoints = sixesInHand * 6;

        allButtonClicks();
    }

    public void clicked3OAK(ActionEvent actionEvent) {
        btn3OAK.setDisable(true);
        btn3OAK.setStyle("-fx-text-fill: black");
        if (onesInHand >= 3 || twosInHand >= 3 || threesInHand >= 3|| foursInHand >= 3 || fivesInHand >= 3 || sixesInHand >= 3) {
            int3OAKPoints = (onesInHand) + (twosInHand * 2) + (threesInHand * 3) + (foursInHand * 4) + (fivesInHand * 5) + (sixesInHand * 6);
        }

        allButtonClicks();
    }

    public void clicked4OAK(ActionEvent actionEvent) {
        btn4OAK.setDisable(true);
        btn4OAK.setStyle("-fx-text-fill: black");
        if (onesInHand >= 4 || twosInHand >= 4 || threesInHand >= 4|| foursInHand >= 4 || fivesInHand >= 4 || sixesInHand >= 4) {
            int4OAKPoints = (onesInHand) + (twosInHand * 2) + (threesInHand * 3) + (foursInHand * 4) + (fivesInHand * 5) + (sixesInHand * 6);
        }

        allButtonClicks();
    }

    public void clickedFullHouse(ActionEvent actionEvent) {
        btnFullHouse.setDisable(true);
        btnFullHouse.setStyle("-fx-text-fill: black");

        if (onesInHand == 3 || twosInHand == 3 || threesInHand == 3 || foursInHand == 3 || fivesInHand == 3 || sixesInHand == 3) {
            if (onesInHand == 2 || twosInHand == 2 || threesInHand == 2 || foursInHand == 2 || fivesInHand == 2 || sixesInHand == 2) {
                intFullHousePoints = 25;
            }
        }

        allButtonClicks();
    }

    public void clickedSmallStraight(ActionEvent actionEvent) {
        btnSStraight.setDisable(true);
        btnSStraight.setStyle("-fx-text-fill: black");

        if ((onesInHand > 0 && twosInHand > 0 && threesInHand > 0 && foursInHand > 0) || (twosInHand > 0 && threesInHand > 0 && foursInHand > 0 && fivesInHand > 0) ||
             threesInHand > 0 && foursInHand > 0 && fivesInHand > 0 && sixesInHand > 0) {
            intSmallStraightPoints = 30;
        }

        allButtonClicks();
    }

    public void clickedLargeStraight(ActionEvent actionEvent) {
        btnLStraight.setDisable(true);
        btnLStraight.setStyle("-fx-text-fill: black");

        if (twosInHand > 0 && threesInHand > 0 && foursInHand > 0 && fivesInHand > 0)
            if (onesInHand > 0 || sixesInHand > 0)
                intLargeStraightPoints = 40;

        allButtonClicks();
    }

    public void clickedYahtzee(ActionEvent actionEvent) {
        btnYahtzee.setDisable(true);
        btnYahtzee.setStyle("-fx-text-fill: black");
        if (onesInHand == 5 || twosInHand == 5 || threesInHand == 5|| foursInHand == 5 || fivesInHand == 5 || sixesInHand == 5) {
            intYahtzeePoints = 50;
        }

        allButtonClicks();
    }

    public void clickedChance(ActionEvent actionEvent) {
        btnChance.setDisable(true);
        btnChance.setStyle("-fx-text-fill: black");
        intChancePoints = (onesInHand) + (twosInHand * 2) + (threesInHand * 3) + (foursInHand * 4) + (fivesInHand * 5) + (sixesInHand * 6);

        allButtonClicks();
    }

    public void allButtonClicks() {
        for (Button button: pointsButtons) {
            button.setDisable(true);
            if (button.getStyle() != "-fx-text-fill: black") {
                button.setText(String.valueOf(0));
            }
        }

        for (Button button: dieButtons)
            button.setDisable(true);

        rollsLeft = 3;
        handsLeft--;
        btnRollDice.setDisable(false);

        blnDieOneActive = blnDieTwoActive = blnDieThreeActive = blnDieFourActive = blnDieFiveActive = false;

        bonusPointsUpdater();
        totalPointsUpdater();
    }

    public void bonusPointsUpdater() {
        intBonusPoints = 0;
        ArrayList<Integer> diePointValues = new ArrayList<Integer>();
        Collections.addAll(diePointValues, intOnePoints, intTwoPoints, intThreePoints, intFourPoints, intFivePoints, intSixPoints);

        for (Integer integer: diePointValues)
            intBonusPoints += integer;

        txtBonusPoints.setText(String.valueOf(intBonusPoints) + "/63");

        if (intBonusPoints >= 63) {
            txtBonusPoints.setText("63/63");
            txtBonusPoints.setStyle("-fx-text-fill: green;" + "-fx-background-color: #B2DFEE");
            blnBonus = true;
        }
    }

    public void totalPointsUpdater() {
        intTotalPoints = 0;

        ArrayList<Integer> pointValues = new ArrayList<Integer>();
        Collections.addAll(pointValues, intOnePoints, intTwoPoints, intThreePoints, intFourPoints, intFivePoints, intSixPoints, int3OAKPoints, int4OAKPoints, intFullHousePoints,
                                        intSmallStraightPoints, intLargeStraightPoints, intYahtzeePoints, intChancePoints);

        for (Integer integer: pointValues)
            intTotalPoints += integer;

        if (blnBonus)
            intTotalPoints += 35;

        txtTotalPoints.setText("Total Points: " + String.valueOf(intTotalPoints));
    }

}