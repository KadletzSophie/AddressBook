package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Entry;
import model.Model_Adressbook;

public class Controller {

    private Model_Adressbook m;

    @FXML
    private TextField name_txt;

    @FXML
    private TextField address_txt;

    @FXML
    private TextField phone_txt;

    @FXML
    private Button pref_bt;

    @FXML
    private Button loadCSV_bt;

    @FXML
    private Button deleteEntry;

    @FXML
    private Button saveChanges_bt;

    @FXML
    private Button next_bt;

    @FXML
    private Button saveCSV_bt;

    @FXML
    private Label page_lbl;

    @FXML
    private Button searchEntry;

    @FXML
    void AddEntry(ActionEvent event) {
        if (!name_txt.getText().isEmpty() && !address_txt.getText().isEmpty() && !phone_txt.getText().isEmpty()){
            boolean existed = m.addEntry(new Entry(name_txt.getText(),address_txt.getText(),phone_txt.getText()));
            if (existed)
                showAlert("Entry error","Entry already exists!");
            else{
                showCurrentEntry();
            }
        }
        else
            showAlert("Entry error","Notddd");

    }


    @FXML
    void DeleteEntry(ActionEvent event) {
        m.deleteEntry();
        showCurrentEntry();
    }

    @FXML
    void LoadFromCSV(ActionEvent event) {

    }

    @FXML
    void NextEntry(ActionEvent event) {

    }

    @FXML
    void PrefEntry(ActionEvent event) {

    }

    @FXML
    void SaveChanges(ActionEvent event) {
        m.saveChanges(new Entry(name_txt.getText(),address_txt.getText(),phone_txt.getText()));
        showCurrentEntry();
    }

    @FXML
    void SaveToCSV(ActionEvent event) {

    }

    @FXML
    void searchEntry(ActionEvent event) {

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


        if (index > 0){
            pref_bt.setDisable(false);
        }
        else{
            pref_bt.setDisable(true);
        }
        if (index < m.numbersOfEntries()-1){
            next_bt.setDisable(false);
        }
        else{
            next_bt.setDisable(true);
        }

    }

    @FXML
    public void initialize (){
        m = new Model_Adressbook();
        page_lbl.setText("0/0");
        deleteEntry.setDisable(true);
        next_bt.setDisable(true);
        pref_bt.setDisable(true);
    }

}
