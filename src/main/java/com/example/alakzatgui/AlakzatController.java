package com.example.alakzatgui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;


public class AlakzatController {

    @FXML public ListView<String> listview_Listview;
    @FXML public ImageView imageview_Alakzat;
    @FXML public Pane pane_Alakzat;
    @FXML public RadioButton radioPiros;
    @FXML public RadioButton radioZold;
    @FXML public RadioButton radioKek;
    @FXML public RadioButton radioNegyzet;
    @FXML public RadioButton radioKor;
    @FXML public RadioButton radioHaromszog;

    public void onPirosSelected(ActionEvent actionEvent) {
        // System.out.println("onPirosSelected actionEvent" + actionEvent);

        pane_Alakzat.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
    }

    public void onZoldSelected(ActionEvent actionEvent) {
        pane_Alakzat.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
    }

    public void onKekSelected(ActionEvent actionEvent) {
        pane_Alakzat.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
    }

    public void onNegyzetSelected(ActionEvent actionEvent) throws FileNotFoundException {
        imageview_Alakzat.setImage(new Image("file:icons/negyzet.png"));
    }

    public void onKorSelected(ActionEvent actionEvent) throws FileNotFoundException {
        imageview_Alakzat.setImage(new Image("file:icons/kor.png"));
    }

    public void onHaromszogSelected(ActionEvent actionEvent) throws FileNotFoundException {
        imageview_Alakzat.setImage(new Image("file:icons/haromszog.png"));
    }

    public void onHozaadClick(ActionEvent actionEvent) {
        ObservableList<String> listviewLines = listview_Listview.getItems();

        String newLine = "";

        if (radioPiros.isSelected()) {
            newLine += "Piros, ";
        }

        if (radioZold.isSelected()) {
            newLine += "Zöld, ";
        }

        if (radioKek.isSelected()) {
            newLine += "Kek, ";
        }

        if (radioKor.isSelected()) newLine += "Kör";
        if (radioHaromszog.isSelected()) newLine += "Háromszög";
        if (radioNegyzet.isSelected()) newLine += "Négyzet";

        // TODO
        //listviewLines.add("Piros, Kör");
        if (!newLine.isEmpty()) listviewLines.add(newLine);

        listview_Listview.setItems(listviewLines);
        listview_Listview.getSelectionModel().selectLast();
    }

    public void onTorolClick(ActionEvent actionEvent) {
        ObservableList<String> listviewLines = listview_Listview.getItems();
        ObservableList<Integer> selectedIndices = listview_Listview.getSelectionModel().getSelectedIndices();

        ObservableList<String> newListviewLines = FXCollections.observableArrayList();
        for (int i = 0; i < listviewLines.size(); i++) {
            if (!selectedIndices.contains(i)) {
                newListviewLines.add(listviewLines.get(i));
            }
        }

        listview_Listview.setItems(newListviewLines);
        listview_Listview.getSelectionModel().selectLast();
    }
}