package com.example.alakzatgui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;


public class AlakzatController implements Initializable {

    public static boolean isRunningTest = false;
    public static String TesztSzin, TesztAlakzat;
    public static ArrayList<String> tesztLista = new ArrayList<>();
    public static int tesztIndex;

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

        if(!isRunningTest){
            ObservableList<String> listviewLines = listview_Listview.getItems();
            String eredmeny = "";
            String szin = "";
            String alakzat = "";

            szin = radioPiros.isSelected() ? "Piros" : radioKek.isSelected() ? "Kék" : radioZold.isSelected() ? "Zöld" : "";
            alakzat = radioKor.isSelected() ? "Kör" : radioNegyzet.isSelected() ? "Négyzet" : radioHaromszog.isSelected() ? "Háromszög" : "";

            if (!szin.isEmpty() && !alakzat.isEmpty()) {
                eredmeny = szin + ", " + alakzat;
                listviewLines.add(eredmeny);
                listview_Listview.setItems(listviewLines);
                listview_Listview.getSelectionModel().selectLast();
            }
        }

        else{
            String eredmeny = TesztSzin + ", " + TesztAlakzat;
            tesztLista.add(eredmeny);
        }
    }

    public void onTorolClick(ActionEvent actionEvent) {

        if (!isRunningTest) {
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

        else {
            tesztLista.remove(tesztIndex);
        }

    }

    public void modositListabol(MouseEvent mouseEvent) throws FileNotFoundException {
        int index = listview_Listview.getSelectionModel().getSelectedIndex();
        String valasztas = listview_Listview.getItems().get(index);
        String szin = valasztas.split(", ")[0].strip();
        String alakzat = valasztas.split(", ")[1].strip();
        ActionEvent actionEvent = new ActionEvent();

        if (szin.equals("Piros")) {
            radioPiros.setSelected(true);
            onPirosSelected(actionEvent);
        }
        if (szin.equals("Zöld")) {
            radioZold.setSelected(true);
            onZoldSelected(actionEvent);
        }
        if (szin.equals("Kék")){
            radioKek.setSelected(true);
            onKekSelected(actionEvent);
        }

        if (alakzat.equals("Négyzet")){
            radioNegyzet.setSelected(true);
            onNegyzetSelected(actionEvent);
        }
        if (alakzat.equals("Kör")){
            radioKor.setSelected(true);
            onKorSelected(actionEvent);
        }
        if (alakzat.equals("Háromszög")){
            radioHaromszog.setSelected(true);
            onHaromszogSelected(actionEvent);
        }
    }

    public void onSaveButton(ActionEvent actionEvent) throws IOException {

        if (!isRunningTest) {
            File fki = new File("alakzat.dat");
            FileWriter fwki = new FileWriter(fki);
            for (int i = 0; i < listview_Listview.getItems().size(); i++) {
                fwki.write(listview_Listview.getItems().get(i) + "\n");
            }
            fwki.close();
        }

        else {
            File fki = new File("alakzat.dat");
            FileWriter fwki = new FileWriter(fki);
            for (int i = 0; i < tesztLista.size(); i++) {
                fwki.write(tesztLista.get(i) + "\n");
            }
            fwki.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("alakzat.dat");

        if (!isRunningTest) {
            try {
                Scanner beolvasas = new Scanner(file);
                ObservableList<String> lista = listview_Listview.getItems();
                while (beolvasas.hasNextLine()) {
                    String line = beolvasas.nextLine();
                    lista.add(line);
                }
                listview_Listview.setItems(lista);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        else {
            try {
                Scanner beolvasas = new Scanner(file);
                while (beolvasas.hasNextLine()) {
                    String line = beolvasas.nextLine();
                    tesztLista.add(line);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }
}