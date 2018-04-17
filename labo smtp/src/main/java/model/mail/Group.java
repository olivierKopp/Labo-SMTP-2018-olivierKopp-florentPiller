package model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * this class represents a group of Person
 * @author olivier kopp, florent piller
 */

public class Group {
    private List<Person> members;

    public Group(){
        members = new ArrayList<Person>();
    }

    public void addPerson(Person p){
        members.add(p);
    }

    public List<Person> getMembers() {
        return members;
    }
}
