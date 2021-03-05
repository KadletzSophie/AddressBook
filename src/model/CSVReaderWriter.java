package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Sophie Kadletz
 * @version 05.03.2021
 */

public class CSVReaderWriter {
    public  void saveToFile(String filename, ArrayList<Entry> entries) {
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            fw.write("Vorname;Nachname;Adresse;Telefonnummer");
            bw.newLine();
            System.out.println(entries.size());
            for (Entry entry:entries) {
                System.out.println(entry.toString());
                bw.write(entry.getVorname()+";"+entry.getNachname() + ";"+entry.getAddress()+";"+entry.getPhone()+";");
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
        String[] strar;
        String line;
        try {
            BufferedReader reader =new BufferedReader(new FileReader(filename));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                strar = line.split(";");
                Entry e = new Entry(strar[0],strar[1],strar[2],strar[3]);
                entries.add(e);
            }

            Collections.sort(entries); //ArrayListe sortieren
            reader.close();
        } catch (IOException io) {
            return null;
        }
        return entries;
    }
}
