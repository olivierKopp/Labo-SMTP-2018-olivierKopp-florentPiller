package model.prank;

import config.ConfigurationManager;
import model.mail.Group;
import model.mail.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * this class allow the generation of prank message and groups of victims
 */
public class PrankGenerator {
    private ConfigurationManager configurationManager;

    public PrankGenerator(ConfigurationManager config){
        configurationManager = config;
    }

    /**
     * this method create a List of prank, chosing for each prank :
     *  - the message
     *  - the groups
     *  - the sender inside the group
     * @return
     */
    public List<Prank> generatePranks(){
        List<Prank> pranks = new ArrayList<Prank>();
        List<String> messages = new ArrayList<String>(configurationManager.getMessages());
        int numberOfGroups = configurationManager.getNumbersOfGroupes();
        if(configurationManager.getVictims().size() / numberOfGroups < 3){
            System.err.println("you must have at least 3 victims per groups");
        }
        List<Group> groups = generateGroups(configurationManager.getVictims(), numberOfGroups);

        int indexMessage = 0;
        for(Group g : groups){
            List<Person> copyGroup = g.getMembers();
            Collections.shuffle(copyGroup);
            Person sender = copyGroup.remove(0);
            pranks.add(new Prank(sender, copyGroup, configurationManager.getCcVictims(), configurationManager.getBccVictims(), messages.get(indexMessage)));
            indexMessage = (indexMessage + 1) % messages.size();
        }
        return pranks;
    }

    /**
     * this method create groups composed by random victims
     * each groups need to have at least 3 victims in it
     * @param victims list of victims
     * @param numberOfGroups number of group to be created
     * @return
     */
    public List<Group> generateGroups(List<Person> victims, int numberOfGroups){
        if(numberOfGroups > victims.size()){
            System.err.println("You must have more victims than groups");
            return null;
        }
        List<Person> victimsCopy = new ArrayList<Person>(victims);
        Collections.shuffle(victimsCopy);
        List<Group> groups = new ArrayList<Group>();
        for(int i = 0; i < numberOfGroups; i++){
            groups.add(new Group());
        }
        int index = 0;
        while(victimsCopy.size() > 0){
            groups.get(index).addPerson(victimsCopy.get(0));
            victimsCopy.remove(0);
            index = (index + 1) % numberOfGroups;
        }
        return groups;
    }
}
