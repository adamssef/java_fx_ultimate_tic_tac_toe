package com.example.javafxdemo;

import javafx.geometry.Insets;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class MultiBoard extends GridPane {

    TicTacToe ticTacToe;
    ArrayList<Board> boards = new ArrayList<>();
    boolean isPlayable = true;

    public MultiBoard(TicTacToe ticTacToe) {
        super();
        this.ticTacToe = ticTacToe;
        this.setPadding(new Insets(100, 0, 100, ticTacToe.getBounds().getWidth() / 11));
        this.setEffect(new GaussianBlur());
    }

    public void setPlayable(boolean playable) {
        isPlayable = playable;
    }

    public boolean checkIfIsPlayable() {
        for (Board board : this.boards) {
            if (board.checkIfTileIsPlayable()) {
                return true;
            }
        }
        return false;
    }
}
