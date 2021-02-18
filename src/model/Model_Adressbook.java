package model;

import java.util.ArrayList;
import java.util.Collections;

public class Model_Adressbook {
    private ArrayList<Entry> entries;

    private int currentIndex;

    public boolean addEntry(Entry entry) {
        boolean existed;
        if (!entries.contains(entry)){
            entries.add(entry);
            existed = false;
            Collections.sort(entries);
            currentIndex = entries.indexOf(entry);
        }
        else{
            existed = true;
        }
        return existed;
    }

    public void deleteEntry(){
        entries.remove(currentIndex);
        if(currentIndex > 0){
            currentIndex--;
        }
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
}
