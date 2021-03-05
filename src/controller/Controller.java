package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Entry;
import model.Model_Adressbook;

/**
 * @author Sophie Kadletz
 * @version 05.03.2021
 */

public class Controller {

    private Model_Adressbook m;

    public Model_Adressbook getM() {
        return m;
    }

    @FXML
    private TextField name_txt;

    @FXML
    private TextField address_txt;

    @FXML
    private TextField phone_txt;

    @FXML
    private Button add_bt;

    @FXML
    private Button saveCSV_bt;

    @FXML
    private Button deleteEntry;

    @FXML
    private Button loadCSV_bt;

    @FXML
    private Button saveChanges_bt;

    @FXML
    private TextField name_search_txt;

    @FXML
    private TextField address_search_txt;

    @FXML
    private TextField phone_search_txt;

    @FXML
    private Button searchEntry;

    @FXML
    private Button pref_bt;

    @FXML
    private Button next_bt;

    @FXML
    private Label page_lbl;

    @FXML
    void AddEntry(ActionEvent event) {
        if (!name_txt.getText().isEmpty() && !address_txt.getText().isEmpty() && !phone_txt.getText().isEmpty()){
            boolean existed = m.addEntry(new Entry(name_txt.getText(),address_txt.getText(),phone_txt.getText()));
            if (existed)
                showAlert("Entry error","Eintrag existiert bereits");
            else
                showCurrentEntry();

        }
        else
            showAlert("Entry error","Alle 3 Felder ausfüllen");

    }

    @FXML
    void DeleteEntry(ActionEvent event) {
        m.deleteEntry();
        showCurrentEntry();
    }

    @FXML
    void LoadFromCSV(ActionEvent event) {
        if (m.loadCSV()){
            m.loadCSV();
            showCurrentEntry();
        }
        else
            showAlert("Fehler","Keine CSV-File");

    }

    @FXML
    public void SaveToCSV(ActionEvent event) {
        m.saveCSV();
        showCurrentEntry();
    }

    @FXML
    void NextEntry(ActionEvent event) {
        m.setCurrentIndex(m.getCurrentIndex()+1);
        showCurrentEntry();
    }

    @FXML
    void PrefEntry(ActionEvent event) {
        m.setCurrentIndex(m.getCurrentIndex()-1);
        showCurrentEntry();
    }

    @FXML
    void SaveChanges(ActionEvent event) {
        if(!name_txt.getText().isEmpty() && !address_txt.getText().isEmpty() && !phone_txt.getText().isEmpty()){
        m.saveChanges(new Entry(name_txt.getText(),address_txt.getText(),phone_txt.getText()));
        showCurrentEntry();}
        else
            showAlert("Entry error","Alle Felder ausfüllen");
    }

    @FXML
    void SearchEntry(ActionEvent event) {
        if(!name_search_txt.getText().isEmpty() || !address_search_txt.getText().isEmpty() || !phone_search_txt.getText().isEmpty()){
            Entry e = new Entry(name_search_txt.getText(),address_search_txt.getText(),phone_search_txt.getText());
            boolean existed = m.searchEntry(e);
            name_search_txt.clear();
            address_search_txt.clear();
            phone_search_txt.clear();
            if (!existed)
                showAlert("Entry error","Eintrag exisitiert nicht.");
            else
                showCurrentEntry();

        }
        else
            showAlert("Entry error","Mind. 1 Feld ausfüllen ");
        showCurrentEntry();
    }

    @FXML
    void clear(MouseEvent event) {
        name_txt.clear();
        address_txt.clear();
        phone_txt.clear();

    }

    private void showAlert(String title, String message) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title);
        a.setContentText(message);
        a.showAndWait();
    }

    private void showCurrentEntry (){
        int index =m.getCurrentIndex();
        int size = m.numbersOfEntries();
        Entry e;

        if (size > 0){
            e = m.getEntryViaIndex(m.getCurrentIndex());

            name_txt.setText(e.getName());
            address_txt.setText(e.getAddress());
            phone_txt.setText(e.getPhone());
            page_lbl.setText((index+1)+"/"+size);

            deleteEntry.setDisable(false);
            next_bt.setDisable(false);
            pref_bt.setDisable(false);
            saveCSV_bt.setDisable(false);
            saveChanges_bt.setDisable(false);
            searchEntry.setDisable(false);
        }

        else {
            name_txt.clear();
            address_txt.clear();
            phone_txt.clear();
            page_lbl.setText("0/0");

            deleteEntry.setDisable(true);
            next_bt.setDisable(true);
            pref_bt.setDisable(true);
            saveCSV_bt.setDisable(true);
            saveChanges_bt.setDisable(true);
        }

        if (index > 0)
            pref_bt.setDisable(false);

        else
            pref_bt.setDisable(true);

        if (index < m.numbersOfEntries()-1)
            next_bt.setDisable(false);

        else
            next_bt.setDisable(true);
    }

    @FXML
    public void initialize (){
        m = new Model_Adressbook();
        page_lbl.setText("0/0");
        saveChanges_bt.setDisable(true);
        searchEntry.setDisable(true);
        saveCSV_bt.setDisable(true);
        deleteEntry.setDisable(true);
        next_bt.setDisable(true);
        pref_bt.setDisable(true);
        m.loadCSV();
        showCurrentEntry();
    }
}
