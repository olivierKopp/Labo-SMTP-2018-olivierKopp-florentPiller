package model.mail;

public class Person {
    private String firstname;
    private String lastname;
    private String address;

    public Person(String address, String lastname, String firstname){
        this.address = address;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public Person(String address){
        this.address = address;
        String[] split = address.split("@");
        String[] names = split[0].split("\\.");
        firstname = names[0];
        lastname = names[1];
        firstname.replace(firstname.charAt(0), Character.toUpperCase(firstname.charAt(0)));
        lastname.replace(lastname.charAt(0), Character.toUpperCase(lastname.charAt(0)));
    }

    public String getAddress() {
        return address;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
