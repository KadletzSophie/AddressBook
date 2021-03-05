package model;

import java.util.Objects;

/**
 * @author Sophie Kadletz
 * @version 04.03.2021
 */

public class Entry implements Comparable<Entry> {
    private String vorname;
    private String nachname;
    private String address;
    private String phone;

    public Entry(String vorname, String nachname, String address, String phone) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.address = address;
        this.phone = phone;
    }


    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return vorname.equals(entry.vorname) &&
                nachname.equals(entry.nachname)&&
                address.equals(entry.address) &&
                phone.equals(entry.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vorname, nachname, address, phone);
    }

    @Override
    public int compareTo(Entry o) {
        int result;
        if ((result = this.vorname.compareTo(o.getVorname())) == 0){
            if ((result = this.nachname.compareTo(o.getNachname())) == 0) {
                if ((result = this.phone.compareTo(o.getPhone())) == 0)
                    return this.address.compareTo(o.getAddress());
            }

        }
        return result;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
