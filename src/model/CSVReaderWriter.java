package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Sophie Kadletz
 * @version 18.02.2021
 */

public class CSVReaderWriter {
    public  void saveToFile(String filename, ArrayList<Entry> entries) {
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            fw.write("Name;Adresse;Telefonnummer"); //In header schreiben
            bw.newLine();

            for (Entry entry:entries) {
                System.out.println(entry.toString());
                bw.write(entry.getName()+";"+entry.getAddress()+";"+entry.getPhone()+";");
                bw.newLine();
            }
            bw.close();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Entry> loadFromFile(String filename) {
        ArrayList<Entry> entries = new ArrayList<>();
        String[] strar = new String[3];
        String line;
        try {
            BufferedReader reader =new BufferedReader(new FileReader(filename));
            while ((line = reader.readLine()) != null) {
                strar = line.split(";");
                Entry e = new Entry(strar[0],strar[1],strar[2]);
                entries.add(e);
            }

            Collections.sort(entries); //ArrayListe sortieren
            reader.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return entries;
    }
}
