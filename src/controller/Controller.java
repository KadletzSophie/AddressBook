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
    private TextField vorname_txt;

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
    private TextField vorname_search_txt;

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
    private TextField nachname_search_txt;

    @FXML
    private TextField nachname_txt;

    @FXML
    void AddEntry(ActionEvent event) {
        if (!vorname_txt.getText().isEmpty() && !address_txt.getText().isEmpty() && !phone_txt.getText().isEmpty() && !nachname_txt.getText().isEmpty() && isText(vorname_txt.getText(),nachname_txt.getText()) && isNumeric(phone_txt.getText())){
            boolean existed = m.addEntry(new Entry(vorname_txt.getText(), nachname_txt.getText(), address_txt.getText(), phone_txt.getText()));
                if (existed)
                    showAlert("Entry error", "Eintrag existiert bereits");
                else
                    showCurrentEntry();
        }
        else if (!isText(vorname_txt.getText(),nachname_txt.getText()))
            showAlert("Entry error","Auf richtigen Eintrag achten! \nKeine Zahlen oder Sonderzeichen in Vor- und Nachname");
        else if (!isNumeric(phone_txt.getText()))
            showAlert("ENtry error","Auf richtigen Eintrag achten! \nGültige Telefonnummer z.B:  06442/3893023, +43 221 549144 \nNur Österreichische Nummern möglich ");
        else
            showAlert("Entry error","Alle Felder ausfüllen");

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
        if(!vorname_txt.getText().isEmpty() && !address_txt.getText().isEmpty() && !phone_txt.getText().isEmpty() && !nachname_txt.getText().isEmpty()){
        m.saveChanges(new Entry(vorname_txt.getText(),nachname_txt.getText(), address_txt.getText(),phone_txt.getText()));
        showCurrentEntry();}
        else
            showAlert("Entry error","Alle Felder ausfüllen");
    }

    @FXML
    void SearchEntry(ActionEvent event) {
        if(!vorname_search_txt.getText().isEmpty() || !address_search_txt.getText().isEmpty() || !phone_search_txt.getText().isEmpty() || !nachname_search_txt.getText().isEmpty()){
            Entry e = new Entry(vorname_search_txt.getText(),nachname_search_txt.getText(), address_search_txt.getText(),phone_search_txt.getText());
            boolean existed = m.searchEntry(e);
            vorname_search_txt.clear();
            nachname_search_txt.clear();
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
        vorname_txt.clear();
        nachname_txt.clear();
        address_txt.clear();
        phone_txt.clear();

    }

    private boolean isNumeric(String phone) {
        System.out.println(phone.matches("[0-9]*\\/*(\\+43)*[ ]*(\\([0-9]+\\))*([ ]*(-|–)*[ ]*[0-9]+)*"));
        return phone.matches("[0-9]*\\/*(\\+43)*[ ]*(\\([0-9]+\\))*([ ]*(-|–)*[ ]*[0-9]+)*");
        /*
        valid Formats:
        (06442) 3933023
        (02852) 5996-0
        (042) 1818 87 9919
        06442 / 3893023
        06442 / 38 93 02 3
        06442/3839023
        042/ 88 17 890 0
        +43 221 549144 – 79
        +43 221 - 542194 79
        +43 (221) - 542944 79
        0 52 22 - 9 50 93 10
        +43(0)121-79536 - 77
        +43(0)2221-39938-113
        +43 (0) 1739 906-44
        +43 (173) 1799 806-44
        0173173990644
        0214154914479
        02141 54 91 44 79
        01517953677
        +431517953677
        015777953677
        02162 - 54 91 44 79
        (02162) 54 91 44 79
         */
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

            vorname_txt.setText(e.getVorname());
            nachname_txt.setText(e.getNachname());
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
            vorname_txt.clear();
            nachname_txt.clear();
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

    private boolean isText(String v,String n) {
        boolean b = v.matches("\\p{L}*")&&n.matches("\\p{L}*");
        //System.out.println(b);
        return v.matches("\\p{L}*")&&n.matches("\\p{L}*");
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
