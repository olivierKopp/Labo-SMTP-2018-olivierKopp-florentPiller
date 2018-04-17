package model.prank;

import model.mail.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * this class represent a prank that can be send by a client
 * @author olivier kopp, florent piller
 */

public class Prank {

    private Person sender;
    private List<Person> receivers;
    private List<Person> receiversCC;
    private List<Person> receiversBCC;
    private String message;

    public Prank(Person sender, List<Person> receivers, List<Person> receiversCC, List<Person> receiversBCC, String message){
        this.sender = sender;
        this.receivers = new ArrayList<Person>(receivers);
        this.receiversCC = new ArrayList<Person>(receiversCC);
        this.receiversBCC = new ArrayList<Person>(receiversBCC);
        this.message = message;
    }

    public Person getSender() {
        return sender;
    }

    public List<Person> getReceivers() {
        return receivers;
    }

    public List<Person> getReceiversCC() {
        return receiversCC;
    }

    public List<Person> getReceiversBCC() {
        return receiversBCC;
    }

    public String getMessage() {
        return message;
    }
}
