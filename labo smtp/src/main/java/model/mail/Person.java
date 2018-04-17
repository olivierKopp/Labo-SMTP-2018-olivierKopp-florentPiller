package model.mail;

/**
 * this class represent a person
 * @author olivier kopp, florent piller
 */
public class Person {
    private String address;

    public Person(String address){
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
