package com.example.javafxdemo;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameForm extends GridPane {

    private TicTacToe ticTacToe;
    private ChoiceBox cbOpponent = new ChoiceBox(FXCollections.observableArrayList("Play against computer", "Play with a friend"));
    private ChoiceBox cbWhoFirst = new ChoiceBox(FXCollections.observableArrayList("Player", "Computer"));
//    private RadioButton rbToggleGrupDifficulty = new RadioButton("Difficulty");
    private Label opponentLabel = new Label("Opponent");
    private Label whoFirstLabel = new Label("Who goes first");
    private Label difficultyLabel = new Label("Difficulty");
    private ToggleGroup group = new ToggleGroup();
    private RadioButton rbEasy = new RadioButton("Easy");
    private RadioButton rbNormal = new RadioButton("Difficult");
    private RadioButton rbHard = new RadioButton("Tic Tac Toe Hell");
    private final String[] opponentLabelTextArr = {"Play against computer", "Play with a friend"};

    public GameForm(TicTacToe ticTacToe) throws Exception {
        this.ticTacToe = ticTacToe;
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

        this.opponentLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        this.add(this.opponentLabel, 1, 1);
        this.cbOpponent.setStyle("-fx-text-fill: black; -fx-font-size: 24px; -fx-font: Arial");
        this.cbOpponent.setValue(opponentLabelTextArr[1]);
        this.add(this.cbOpponent, 1, 2);
        this.cbOpponent.setOnAction(event -> {
            try {
                if (cbOpponent.getValue() == "Play against computer") {
                    this.setFormSectionVisibility(FormSections.DIFFICULTY, true);
                    this.setFormSectionVisibility(FormSections.WHO_GOES_FIRST, true);
                } else {
                    this.setFormSectionVisibility(FormSections.DIFFICULTY, false);
                    this.setFormSectionVisibility(FormSections.WHO_GOES_FIRST, false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        this.whoFirstLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        this.add(this.whoFirstLabel, 1, 4);
        this.cbWhoFirst.setStyle("-fx-text-fill: black; -fx-font-size: 24px; -fx-font: Arial");
        this.cbWhoFirst.setMaxWidth(Double.POSITIVE_INFINITY);
        this.cbWhoFirst.setValue("Player");
        this.add(this.cbWhoFirst, 1, 5);

////        this.difficultyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
////        this.add(this.difficultyLabel, 1, 7);
//        this.rbDifficulty.setStyle("-fx-text-fill: black; -fx-font-size: 24px; -fx-font: Arial");
//        this.rbDifficulty.setMaxWidth(Double.POSITIVE_INFINITY);
//        rbDifficulty.setValue("Easy");
//        this.add(this.cbDifficulty, 1, 8);



        this.rbEasy.setToggleGroup(group);
        this.rbEasy.setStyle("-fx-text-fill: black; -fx-font-size: 24px; -fx-font: Arial");
        rbEasy.setFont(Font.font("Arial"));
        this.rbNormal.setToggleGroup(group);
        this.rbNormal.setStyle("-fx-text-fill: black; -fx-font-size: 24px; -fx-font: Arial");
        this.rbHard.setToggleGroup(group);
        this.rbHard.setStyle("-fx-text-fill: black; -fx-font-size: 24px; -fx-font: Arial");
        this.difficultyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        this.add(difficultyLabel, 1, 8);
        this.add(this.rbEasy, 1, 9);
        this.add(this.rbNormal, 1, 10);
        this.add(this.rbHard, 1, 11);

        Button btn = new Button("New Game");
        btn.setStyle("-fx-text-fill: black; -fx-font-size: 24px; -fx-background-color: #138BF6");
        btn.setOnAction((event) -> {
            if (!ticTacToe.isPlayable) {
                ticTacToe.isPlayable = true;
                ticTacToe.getMultiGameBoard().setEffect(null);
                ticTacToe.isBlurOn = false;
                ticTacToe.activateAllMultiGameBoardElements();
                ticTacToe.gameTopHBox.updateLabel();
            }
        });

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        this.add(hbBtn, 1, 13);

        this.setFormSectionVisibility(FormSections.DIFFICULTY, false);
        this.setFormSectionVisibility(FormSections.WHO_GOES_FIRST, false);
    }
    public void setFormSectionVisibility(FormSections formSection, Boolean isVisible) throws Exception {
        switch (formSection) {
            case GAME_MODE:
                cbOpponent.setVisible(isVisible);
                opponentLabel.setVisible(isVisible);
                cbOpponent.setManaged(isVisible);
                opponentLabel.setManaged(isVisible);
                break;
            case WHO_GOES_FIRST:
                cbWhoFirst.setVisible(isVisible);
                whoFirstLabel.setVisible(isVisible);
                cbWhoFirst.setManaged(isVisible);
                whoFirstLabel.setManaged(isVisible);
                break;
            case DIFFICULTY:
                this.rbEasy.setVisible(isVisible);
                this.rbEasy.setManaged(isVisible);
                this.rbNormal.setVisible(isVisible);
                this.rbNormal.setManaged(isVisible);
                this.rbHard.setVisible(isVisible);
                this.rbHard.setManaged(isVisible);
                this.difficultyLabel.setVisible(isVisible);
                this.difficultyLabel.setManaged(isVisible);

                break;
            default:
                throw new Exception("setFormSectionVisibility() - incorrect parameters");
        }
    }
}
