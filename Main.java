import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import models.*;

public class Main {

    static ContactManager cm = new ContactManager();     

    public static void main(String[] args) {

        try {
            loadContacts("contacts.txt");
            System.out.println("CONTACTS LOADED\n\n");
            System.out.println(cm);
            manageContacts();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
           System.out.println("\nProcess Complete.");
        }  
    }
    
    public static void loadContacts(String filename) 
        throws FileNotFoundException{
        
            File file = new File(filename);

            if (!file.exists()){
                throw new FileNotFoundException("File does not exist");
            }

            Scanner sc = new Scanner(file);

                while (sc.hasNextLine()){

                    String contactString = sc.nextLine();
                    String[] cArray = contactString.split(" "); 
                    try {
                        cm.addContact(new Contact(cArray[0], cArray[2], cArray[1]));
                    } catch (ParseException e) {
                        System.out.println("Input string is not parseable");
                    }
                }
                sc.close();
                System.out.println(cm.toString());
    }   
    
    public static void manageContacts() {
        Scanner scan = new Scanner(System.in);

        loop: while (true) {
            System.out.println( "Press A to add contact, B to remove one or C to exit:");
            switch (scan.nextLine()) {
                case "A":

                    System.out.print("\tName: ");
                    String name = scan.nextLine();
                    System.out.print("\tPhone Number: ");
                    String phone = scan.nextLine();
                    System.out.print("\tBirth Date: ");
                    String bd = scan.nextLine();

                    if ((name.isBlank()) || (phone.isBlank()) || (phone.length()<5)){
                        System.out.println("The input uou provided is not valid");
                    }else{
                        try {
                            cm.addContact(new Contact(name, bd, phone));
                        } catch (ParseException e) {
                            System.out.println("Input string is not parseable");
                        } finally {
                            System.out.println( "\n\nUPDATED CONTACTS\n\n" + cm);
                        }
                    }
                    
                break;
                
                case "B":

                    System.out.println("\nWho would you like to remove?");
                    String nameToRemove = scan.nextLine();
                    cm.removeContact(nameToRemove);
                    System.out.println("\n\nUPDATED CONTACTS\n\n");
                    System.out.println(cm.toString());
                break;    

                default:

                    break loop;
                
            }
        }
        scan.close();
    }
}