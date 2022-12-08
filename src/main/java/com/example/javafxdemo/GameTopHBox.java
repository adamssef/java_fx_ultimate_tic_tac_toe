package com.example.javafxdemo;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameTopHBox extends HBox {

    private String labelTextNotStarted = "The game has not started yet";
    private String labelTextStarted = "The game has started";

    private Label label = new Label(this.labelTextNotStarted);

    private TicTacToe ticTacToe;


    public GameTopHBox(TicTacToe ticTacToe) {
        super();
        this.ticTacToe = ticTacToe;
        this.setPadding(new Insets(15, 12, 15, 12));
        this.setSpacing(10);   // Gap between nodes
        this.setStyle("-fx-background-color: #000000;");
        this.label.setFont(Font.font("Verdana", 20));
        this.label.setTextFill(Color.WHITE);
        this.getChildren().addAll(this.label);
    }

    public void updateLabel() {
        this.label.setText("Player X: " + this.ticTacToe.xPlayerResult + " points" + System.lineSeparator() + "Player O: " + this.ticTacToe.oPlayerResult + " points");
        this.label.setFont(Font.font("Verdana", 20));
        this.label.setTextFill(Color.YELLOW);
    }

    public void updateLabelGameOver() {
        System.out.println("update labe lgame over triggered");
        boolean isXwinner = this.ticTacToe.xPlayerResult > this.ticTacToe.oPlayerResult;
        boolean isOwinner = this.ticTacToe.oPlayerResult > this.ticTacToe.xPlayerResult;

        if(isXwinner) {
            this.label.setText("GAME OVER. PLAYER X WON. CONGRATULATIONS!");
        } else if (isOwinner) {
            this.label.setText("GAME OVER. PLAYER O WON. CONGRATULATIONS!");
        } else {
            this.label.setText("GAME OVER. DRAW. GO HOME YOU'RE DRUNK.");
        }


        this.label.setFont(Font.font("Verdana", 20));
        this.label.setTextFill(Color.YELLOW);
    }

    public void setLabelTextToNotStarted () {
        this.label.setText(labelTextNotStarted);
    }

}
