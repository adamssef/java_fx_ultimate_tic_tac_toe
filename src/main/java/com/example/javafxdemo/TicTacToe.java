package com.example.javafxdemo;

import com.almasb.fxgl.ui.InGamePanel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.function.ToDoubleBiFunction;

public class TicTacToe extends Application {
    private Rectangle2D bounds = Screen.getPrimary().getBounds();
    public Boolean turnX = true;
    public Boolean isPlayable = false;
    public Boolean isBlurOn = true;
    public Boolean isMultiplayerModeOn = true;
    public GameDifficultyLevels gameLevel = GameDifficultyLevels.NON_APPLICABLE;
    public GameTopHBox gameTopHBox;
    public MultiBoard multiBoard;
    public int oPlayerResult = 0;
    public int xPlayerResult = 0;
    public HistoryView historyContainer;
    public int turn = 1;
    public int keyboardPressCounter;
    public int firstKeyboardPressVal;
    public int secondKeyboardPressVal;


    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = prepareScene();
        prepareStage(stage, scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public TicTacToe() {
        this.firstKeyboardPressVal = 0;
        this.secondKeyboardPressVal = -1;
        this.historyContainer = new HistoryView();
        this.multiBoard = new MultiBoard(this);
        this.keyboardPressCounter = 0;
    }

    private BorderPane prepareMainGamePane() throws Exception {
        this.gameTopHBox = new GameTopHBox(this);
        BorderPane mainGamePane = new BorderPane();
        mainGamePane.setTop(this.gameTopHBox);
        mainGamePane.setLeft(this.historyContainer);

        int counter = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Board gameBoard = new Board(counter, this);
                gameBoard.setDisable(true);//boards get disabled before the new game button is pressed
                this.multiBoard.add(gameBoard, j, i);
                this.multiBoard.boards.add(gameBoard);

                counter++;
            }
        }

        mainGamePane.setCenter(this.multiBoard);
        GridPane form = new GameForm(this);
        mainGamePane.setRight(form);
        return mainGamePane;
    }

    public GridPane getMultiGameBoard() {
        return this.multiBoard;
    }

    public Rectangle2D getBounds() {
        return this.bounds;
    }

    public void setMultiplayerMode(Boolean bool) {
        this.isMultiplayerModeOn = bool;
    }

    public void setDifficultyLevel(GameDifficultyLevels level) {
        this.gameLevel = level;
    }

    public void activateAllMultiGameBoardElements() {
        for (int i = 0; i < this.multiBoard.getChildren().size(); i++) {
            this.multiBoard.getChildren().get(i).setDisable(false);
        }
    }

    private Scene prepareScene() throws Exception {
        BorderPane borderPane = prepareMainGamePane();
        Scene scene = new Scene(borderPane);
        scene.setOnKeyPressed(event -> {
            int pressedKeyValue = Integer.parseInt(event.getText());

            if (pressedKeyValue >= 1 && pressedKeyValue <= 9) {
                System.out.println("pressed key");
                if(this.keyboardPressCounter >2 ) {
                    this.keyboardPressCounter = 0;
                }
                this.keyboardPressCounter++;


                if(this.keyboardPressCounter == 1) {
                    this.firstKeyboardPressVal = pressedKeyValue - 1;
                } else if (this.keyboardPressCounter == 2) {
                    this.secondKeyboardPressVal = pressedKeyValue - 1;
                    Board board = this.multiBoard.boards.get(firstKeyboardPressVal);
                    Tile tile = board.tiles.get(secondKeyboardPressVal);

                    if(board.isDisable() == true) {
                        this.keyboardPressCounter = 0;
                    }
                    else if (board.isDisable() == false && tile.getIsChecked()){
                        this.keyboardPressCounter = 1;
                    } else {
                        tile.setTileAsChecked();
                        this.keyboardPressCounter = 0;

                       tile.handleCombos();
//                        if (board.ticTacToe.isPlayable && board.ticTacToe.turnX) {
//                            tile.drawX();
//                            board.ticTacToe.historyContainer.list.getItems().add("Player X   |   Turn no. " + board.ticTacToe.turn + "   |   Location: " + board.getLocation() + " " + tile.getLocation());
//                            board.ticTacToe.turn++;
//
//                            if(board.checkBoardAgainstAllCombos("X").length == 3){
//                                board.setIsPlayable(false);
//                                board.setDisable(true);
//                                board.setStyle("-fx-background-color: grey");
//                            };
//
//                            tile.handleBoards(false);
//                            if ((board.ticTacToe.oPlayerResult + board.ticTacToe.xPlayerResult) == 9) {
//                                board.ticTacToe.gameTopHBox.updateLabelGameOver();
//                            }
//                        }
//                        else if (board.ticTacToe.isPlayable) {
//                            tile.drawO();
//                            board.ticTacToe.historyContainer.list.getItems().add("Player O   |   Turn no. " + board.ticTacToe.turn + "   |   Location: " + board.getLocation() + " " + tile.getLocation());
//                            board.ticTacToe.turn++;
//
//                            if(board.checkBoardAgainstAllCombos("O").length == 3){
//                                board.setIsPlayable(false);
//                                board.setDisable(true);
//                                board.setStyle("-fx-background-color: grey");
//                            };
//                            tile.handleBoards(true);
//                            if ((board.ticTacToe.oPlayerResult + board.ticTacToe.xPlayerResult) == 9 || board.ticTacToe.oPlayerResult == 4 && board.ticTacToe.xPlayerResult == 4) {
//                                board.ticTacToe.gameTopHBox.updateLabelGameOver();
//                            }
//                        }
                    }
                }
            }
        });
        return scene;
    }

    private void prepareStage(Stage stage, Scene scene) {
        stage.setTitle("Tic Tac Toe HELL");
        stage.setScene(scene);
        stage.setWidth(bounds.getWidth() / 1.8);
        stage.setHeight(bounds.getHeight() / 1.2);
    }
}
