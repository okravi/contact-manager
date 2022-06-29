package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Contact {
  
    private String name;
    private int age;
    private String birthDate;
    private String phoneNumber;

    public Contact (String name, String birthDate, String phoneNumber) throws ParseException{
        if ((name == null) || (name.isBlank())) {
            throw new IllegalArgumentException 
            ("Name shouldn't be blank");
        }
        
        this.name = name;

        if ((phoneNumber == null) || (phoneNumber.length()<5)) {
            throw new IllegalArgumentException 
            ("Phone shouldn't be blank or shorter than 5");
        }
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.age = toAge(birthDate);
    }

    public Contact(Contact oldContact) {
        this.name = oldContact.name;
        this.phoneNumber = oldContact.phoneNumber;
        this.birthDate = oldContact.birthDate;
        this.age = oldContact.age;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        if ((name == null) || (name.isBlank())) {
            throw new IllegalArgumentException 
            ("Name shouldn't be blank");
        }
        this.name = name;
    }

    private void setAge(int age) {
        this.age = age;
    }
    
    public void setBirthDate(String birthDate) throws ParseException{
        this.birthDate = birthDate;
        setAge(toAge(birthDate));
    }
  
    public void setPhoneNumber(String phoneNumber) {
        if ((phoneNumber == null) || (phoneNumber.length()<5)) {
            throw new IllegalArgumentException 
            ("Phone shouldn't be blank or shorter than 5");
        }
        this.phoneNumber = phoneNumber;
    }

    private int toAge(String birthDate) throws ParseException{
        long bdi = 0L;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
        simpleDateFormat.setLenient(false);
       
        bdi = simpleDateFormat.parse(birthDate).getTime();
     
        Date date = new Date();    
        
        long ageMilli = date.getTime() - bdi;
        long ageYears = TimeUnit.MILLISECONDS.toDays(ageMilli)/365;
        
        return((int)ageYears);
    }

    public String toString() {
        return ("Name: " + this.name + "\n" +
        "Phone number: " + this.phoneNumber + "\n" +       
        "Birth Date: " + this.birthDate + "\n" +       
        "Age: " + this.age + " year old\n");
    }
}