package com.example.javafxdemo;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
    private Text text = new Text();
    private int tileId;
    private boolean isChecked = false;
    Board board;

    public Tile(int tileId, Board board) {
        Rectangle rect = new Rectangle(80, 80);
        this.board = board;
        this.tileId = tileId;
        rect.setFill(null);
        rect.setStroke(Color.BLACK);
        text.setFont(Font.font(72));
        setAlignment(Pos.CENTER);
        getChildren().addAll(rect, text);


        setOnMouseClicked(event -> {
            if (this.isChecked)
                return;

            this.setTileAsChecked();

            this.handleCombos();
        });
    }

    void handleCombos() {
        if (this.board.ticTacToe.isPlayable && board.ticTacToe.turnX) {
            drawX();
            this.board.ticTacToe.historyContainer.list.getItems().add("Player X   |   Turn no. " + this.board.ticTacToe.turn + "   |   Location: " + this.board.getLocation() + " " + this.getLocation());
            this.board.ticTacToe.turn++;

            if(board.checkBoardAgainstAllCombos("X").length == 3){
                board.setIsPlayable(false);
                board.setDisable(true);
                board.setStyle("-fx-background-color: grey");
            };

            this.handleBoards(false);
            if ((this.board.ticTacToe.oPlayerResult + this.board.ticTacToe.xPlayerResult) == 9) {
                this.board.ticTacToe.gameTopHBox.updateLabelGameOver();
            }
        }
        else if (board.ticTacToe.isPlayable) {
            drawO();
            this.board.ticTacToe.historyContainer.list.getItems().add("Player O   |   Turn no. " + this.board.ticTacToe.turn + "   |   Location: " + this.board.getLocation() + " " + this.getLocation());
            this.board.ticTacToe.turn++;

            if(board.checkBoardAgainstAllCombos("O").length == 3){
                board.setIsPlayable(false);
                board.setDisable(true);
                board.setStyle("-fx-background-color: grey");
            };
            this.handleBoards(true);
            if ((this.board.ticTacToe.oPlayerResult + this.board.ticTacToe.xPlayerResult) == 9 || this.board.ticTacToe.oPlayerResult == 4 && this.board.ticTacToe.xPlayerResult == 4) {
                this.board.ticTacToe.gameTopHBox.updateLabelGameOver();
            }
        }
    }

    void handleBoards(Boolean turnX) {
        board.ticTacToe.turnX = turnX;
        board.deactivateBoardsExcept(this.tileId);
        if (board.checkIfBoardIsPlayable()) { // we check if board is playable

            /**
             * Board is playable.
             *
             * If TILE that is clicked leads to BOARD that is not playable activateBoardsExcept() method is triggered.
             */
            if(!board.ticTacToe.multiBoard.boards.get(this.tileId).checkIfBoardIsPlayable()){
                board.activateBoardsExcept(this.tileId);
            }

        } else {
            if(board.ticTacToe.multiBoard.checkIfIsPlayable()) {// board is not playable: we check if multiboard is playable
                board.ticTacToe.multiBoard.isPlayable = true;
                board.setDisable(true);
                board.setStyle("-fx-background-color: grey");
            } else {
                board.ticTacToe.isPlayable = false;
                board.deactivateAllBoards();
                board.ticTacToe.gameTopHBox.updateLabelGameOver();
            }
        }
    }

    public void drawX() {
        text.setText("X");
    }

    void drawO() {
        text.setText("O");
    }

    public String getText() {
        return text.getText();
    }

    public int getTileId() {
        return this.tileId;
    }

    public void setTileAsChecked() {
        this.isChecked = true;
    }

    public boolean getIsChecked() {
        return this.isChecked;
    }

    public String getLocation() {
        switch(this.tileId) {
            case 0:
                return "NW";
            case 1:
                return "NC";
            case 2:
                return "NE";
            case 3:
                return "CW";
            case 4:
                return "CC";
            case 5:
                return "CE";
            case 6:
                return "SW";
            case 7:
                return "SC";
            case 8:
                return "SE";
            default:
                return "";
        }
    }
}