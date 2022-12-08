package com.example.javafxdemo;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Board extends GridPane {
    public int boardId;
    static ArrayList<Board> boards = new ArrayList<>();
    private boolean isPlayable;
    TicTacToe ticTacToe;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public Board(int boardId, TicTacToe ticTacToe) {
        super();
        this.ticTacToe = ticTacToe;
        this.boardId = boardId;
        this.isPlayable = true;
        this.setVgap(0);
        this.setHgap(0);
        this.setPadding(new Insets(2, 2, 2, 2));
        this.setStyle("-fx-background-color: #FFFFFF; -fx-border-style: solid; -fx-border-width: 5; -fx-border-color: green");
        int counter = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile(counter, this);
                this.add(tile, j, i);
                this.tiles.add(tile);
                counter++;
            }
        }

        boards.add(this);
    }

    public void setIsPlayable(boolean boolVal) {
        this.isPlayable = boolVal;
    }

    public void activateBoardsExcept(int $exceptionId) {
        if ($exceptionId < 0 || $exceptionId >= boards.size()) {
            return;
        }

        for (int i = 0; i < boards.size(); i++) {
            if (i != $exceptionId) {
                if(boards.get(i).isPlayable) { //this is important condition that guarantees that only playable boards are being activated.
                    boards.get(i).setStyle("-fx-background-color: white");
                    boards.get(i).setDisable(false);
                }

            }
        }
    }

    public boolean checkIfBoardIsPlayable() {
        return this.isPlayable;
    }

    public void deactivateAllBoards() {
        for (int i = 0; i < boards.size(); i++) {
            boards.get(i).setStyle("-fx-background-color: grey");
            boards.get(i).setDisable(true);
        }
    }

    public void activateAllPlayableBoards() {
        for (int i = 0; i < boards.size(); i++) {
            if(boards.get(i).checkIfBoardIsPlayable()) {
                boards.get(i).setStyle("-fx-background-color: white");
                boards.get(i).setDisable(false);
            }
        }
    }

    public boolean checkIfTileIsPlayable() {
        for (Tile tile : this.tiles) {
            if (!tile.getIsChecked()) {
                return true;
            }
        }
        return false;
    }

    public int[] checkBoardAgainstAllCombos(String whoseTurnIsNow) {
        int[][] possibleTileCombos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        boolean isCombo = true;
        int[] emptyArr = {};

        for (int[] possibleTileCombo : possibleTileCombos) {
            if (Objects.equals(tiles.get(possibleTileCombo[0]).getText(), whoseTurnIsNow) && Objects.equals(tiles.get(possibleTileCombo[1]).getText(), whoseTurnIsNow) && Objects.equals(tiles.get(possibleTileCombo[2]).getText(), whoseTurnIsNow)) {
                if(this.ticTacToe.turnX) {
                    this.ticTacToe.xPlayerResult++;
                    this.ticTacToe.gameTopHBox.updateLabel();
                } else {
                    this.ticTacToe.oPlayerResult++;
                    this.ticTacToe.gameTopHBox.updateLabel();
                }

                return possibleTileCombo;
            }
        }

        return emptyArr;
    }

    public void deactivateBoardsExcept (int $exceptionId) {
        if (!boards.get($exceptionId).isPlayable){ // in some cases board indicated in the parameter might not be playable and in such case all the playable boards should be activated.
            for (int j = 0; j < boards.size() ; j++) {
                if(boards.get(j).isPlayable) {
                    boards.get(j).setStyle("-fx-background-color: white");
                    boards.get(j).setDisable(false);
                }
            }
        }
        else {
            for (int i = 0; i < boards.size(); i++) {
                if (i != $exceptionId) { // every board except the on indicated in parameter is being deactivated.
                    boards.get(i).setStyle("-fx-background-color: grey");
                    boards.get(i).setDisable(true);
                } else if (boards.get(i).isPlayable) { //when board indicated in parameter is playable it is activated
                    boards.get(i).setDisable(false);
                    boards.get(i).setStyle("-fx-background-color: white");
                }
            }
        }
    }

    public String getLocation() {
        switch(this.boardId) {
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