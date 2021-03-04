package model;

import java.util.Objects;

/**
 * @author Sophie Kadletz
 * @version 04.03.2021
 */

public class Entry implements Comparable<Entry> {
    private String name;
    private String address;
    private String phone;

    public Entry(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return name.equals(entry.name) &&
                address.equals(entry.address) &&
                phone.equals(entry.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, phone);
    }

    @Override
    public int compareTo(Entry o) {
        int result;
        if ((result = this.name.compareTo(o.getName())) == 0){
            if ((result = this.phone.compareTo(o.getPhone())) == 0) {
                return this.address.compareTo(o.getAddress());
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
