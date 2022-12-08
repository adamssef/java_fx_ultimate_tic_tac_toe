package com.example.javafxdemo;

import com.almasb.fxgl.core.collection.grid.Grid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HistoryView extends GridPane {
    Label historyLabel = new Label("Game history");
    ListView<String> list = new ListView<String>();
    ObservableList<String> items = FXCollections.observableArrayList ();

    HistoryView(){
        super();
        this.historyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        this.list.setItems(this.items);
        this.list.setPrefHeight(500);
        this.list.setPrefWidth(310);
        this.add(historyLabel, 0, 1);
        this.add(list, 0, 2);
        this.setPadding(new Insets(100, 0, 0 ,0 ));
    }

}
