package models;

import java.util.ArrayList;

import java.util.ArrayList;

public class ContactManager {
    ArrayList<Contact> contacts;

    public ContactManager() {
        this.contacts = new ArrayList<Contact>();
    }

    public Contact getContact(int index) {
        return new Contact(contacts.get(index));
    }

    public void setContact(int index, Contact contact) {       
        this.contacts.set(index, new Contact(contact));
    }

    public void addContact(Contact contact) {
        this.contacts.add(new Contact(contact));
    }

    public void removeContact(String contactName) throws IllegalStateException{

        if (contacts.isEmpty()){
            throw new IllegalStateException("Cannot remove contact from an empty list");
        }
        for (int i=0; i<contacts.size(); i++) {
            if (contacts.get(i).getName().equals(contactName)){
                contacts.remove(i);
            }
        }
    }

    public String toString() {
        String temp = "";
        for (Contact contact : contacts) {
            temp += contact.toString();
            temp += "\n\n";
        }

        return temp;
    }
}