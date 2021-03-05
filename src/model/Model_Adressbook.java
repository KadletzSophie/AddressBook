package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Sophie Kadletz
 * @version 04.03.2021
 */

public class Model_Adressbook {

    private ArrayList<Entry> entries;
    private int currentIndex;


    public Model_Adressbook() {
        this.entries = new ArrayList<>();
        this.currentIndex = 0;
    }

    public boolean addEntry(Entry entry) {
        boolean existed;
        if (!entries.contains(entry)){
            entries.add(entry);
            existed = false;
            Collections.sort(entries);
            currentIndex = entries.indexOf(entry);
        }
        else
            existed = true;

        return existed;
    }

    public int numbersOfEntries(){
        return entries.size();
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public Entry getEntryViaIndex(int index){
        return entries.get(index);
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public void deleteEntry(){
        entries.remove(currentIndex);
        if(currentIndex > 0){
            currentIndex--;
        }
    }

    public void saveChanges(Entry e){
        //String line = "\n"+name+";"+address+";"+number+";";
        entries.set(currentIndex, e);

    }

    public void saveCSV(){
        CSVReaderWriter csv = new CSVReaderWriter();
        csv.saveToFile("Adress.csv",entries);
    }

    public boolean loadCSV(){
        CSVReaderWriter csv = new CSVReaderWriter();
        currentIndex = 0;
        entries = csv.loadFromFile("Adress.csv");
        if (entries == null) {
            entries = new ArrayList<>();
            return false;
        }
        else
            return true;

    }

    public boolean searchEntry(Entry e){
        for (Entry entry:entries) {
            if (e.getVorname().equals("")){
                if (e.getAddress().equals("")){
                    if(e.getPhone().equals(entry.getPhone())){
                        currentIndex = entries.indexOf(entry);
                        return true;
                    }
                }
                else if(e.getAddress().equals(entry.getAddress())) {
                    currentIndex = entries.indexOf(entry);
                    return true;
                }
            }
            else if(e.getVorname().equals(entry.getVorname())){
                currentIndex = entries.indexOf(entry);
                return true;
            }
        }
        return false;
    }
}
